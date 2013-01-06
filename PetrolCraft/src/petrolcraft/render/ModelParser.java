package petrolcraft.render;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelParser {

	public static Map<String, OpenGLRenderer> parse(InputStream pStream1, InputStream pStream2) {
		try {
			String line;

			/* First walk through the file to find the counts */

			int vertexCount = 0;
			int textureCount = 0;
			int normalCount = 0;

			BufferedReader br = new BufferedReader(new InputStreamReader(pStream1));
			try {
				while ((line = br.readLine()) != null) {
					if (line.startsWith("v "))
						vertexCount++;
					if (line.startsWith("vt "))
						textureCount++;
					if (line.startsWith("vn "))
						normalCount++;
				}
			} finally {
				br.close();
			}

			/* Now parse the file into the buffers */

			Map<String, OpenGLRenderer> modelList = new HashMap<String, OpenGLRenderer>();
			String objName = null;

			double[][] vertexData = new double[vertexCount][3];
			double[][] textureData = new double[textureCount][3];
			float[][] normalData = new float[normalCount][3];
			List<VertexData> dataList = new ArrayList<VertexData>();
			int vertexOffset = -1;
			int textureOffset = -1;
			int normalOffset = -1;
			br = new BufferedReader(new InputStreamReader(pStream2));
			try {
				while ((line = br.readLine()) != null) {
					if (line.startsWith("v ")) {
						String[] split = line.split(" ");
						double x = Double.valueOf(split[1]);
						double y = Double.valueOf(split[2]);
						double z = Double.valueOf(split[3]);
						double w = 1.0D;
						if (split.length == 5)
							w = Double.valueOf(split[5]);
						vertexOffset++;
						vertexData[vertexOffset][0] = x / w;
						vertexData[vertexOffset][1] = y / w;
						vertexData[vertexOffset][2] = z / w;
					} else if (line.startsWith("vt ")) {
						String[] split = line.split(" ");
						double u = Double.valueOf(split[1]);
						double v = 0.0D;
						double w = 0.0D;
						if (split.length > 2)
							v = Double.valueOf(split[2]);
						if (split.length > 3)
							w = Double.valueOf(split[3]);
						textureOffset++;
						textureData[textureOffset][0] = u;
						textureData[textureOffset][1] = v;
						textureData[textureOffset][2] = w;
					} else if (line.startsWith("vn ")) {
						String[] split = line.split(" ");
						float x = Float.valueOf(split[1]);
						float y = Float.valueOf(split[2]);
						float z = Float.valueOf(split[3]);
						normalOffset++;
						normalData[normalOffset][0] = x;
						normalData[normalOffset][1] = y;
						normalData[normalOffset][2] = z;
					} else if (line.startsWith("f ")) {
						String[] split = line.split(" ");
						for (int o = 1; o < split.length; o++) {
							String[] pieces = split[o].split("/");
							int vOff = (pieces[0].length() > 0 ? Integer.parseInt(pieces[0]) : -1);
							int tOff = (pieces[1].length() > 0 ? Integer.parseInt(pieces[1]) : -1);
							int nOff = (pieces[2].length() > 0 ? Integer.parseInt(pieces[2]) : -1);
							dataList.add(new VertexData(vertexData[vOff - 1][0], vertexData[vOff - 1][1], vertexData[vOff - 1][2],
									(tOff == -1 ? false : true), (tOff == -1 ? 0 : textureData[tOff - 1][0]), (tOff == -1 ? 0
											: textureData[tOff - 1][1]), (nOff == -1 ? false : true), (nOff == -1 ? 0
											: normalData[nOff - 1][0]), (nOff == -1 ? 0 : normalData[nOff - 1][1]), (nOff == -1 ? 0
											: normalData[nOff - 1][2])));
						}
					} else if (line.startsWith("o ")) {
						if (dataList.size() > 0)
							modelList.put(objName, new OpenGLRenderer(objName, dataList));

						objName = line.substring(2).trim();
						dataList = new ArrayList<VertexData>();
					}

				}
			} finally {
				br.close();
			}
			if (dataList.size() > 0)
				modelList.put(objName, new OpenGLRenderer(objName, dataList));
			return modelList;
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
}
