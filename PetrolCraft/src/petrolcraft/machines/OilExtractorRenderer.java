package petrolcraft.machines;

import java.io.InputStream;
import java.util.Map;
import java.util.logging.Logger;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import petrolcraft.render.ModelParser;
import petrolcraft.render.OpenGLRenderer;

public class OilExtractorRenderer extends TileEntitySpecialRenderer {
	private static final Logger sLog = Logger.getLogger("PetrolCraft");
	private static final String sClassName = OilExtractorRenderer.class.getName();

	private OpenGLRenderer mObj1;
	private OpenGLRenderer mObj2;
	private OpenGLRenderer mObj3;
	private OpenGLRenderer mObj4;

	private float mAngle = 0;
	private long mLastChange = System.currentTimeMillis();

	public OilExtractorRenderer() {
		super();
		InputStream pStream1 = OilExtractorRenderer.class.getResourceAsStream("/gfx/model/OilExtractor.obj");
		InputStream pStream2 = OilExtractorRenderer.class.getResourceAsStream("/gfx/model/OilExtractor.obj");
		Map<String, OpenGLRenderer> map = ModelParser.parse(pStream1, pStream2);
		mObj1 = map.get("pCube1");
		mObj2 = map.get("pCube2");
		mObj3 = map.get("pCube3");
		mObj4 = map.get("pCylinder1");
	}

	@Override
	public void renderTileEntityAt(TileEntity pEntity, double pX, double pY, double pZ, float pF) {
		sLog.entering(sClassName, "renderTileEntityAt", new Object[] { pX, pY, pZ, pF });

		GL11.glPushMatrix();
		GL11.glDisable(2896 /* GL_LIGHTING */);

		GL11.glTranslatef((float) pX, (float) pY + 3, (float) pZ);
		mObj1.render(1.0F);

		if (System.currentTimeMillis() - mLastChange > 50) {
			mLastChange = System.currentTimeMillis();
			mAngle += 0.15;
		}
		mObj2.rotationPointY = 0.0F;
		mObj2.rotateAngleX = mAngle;

		mObj2.render(1.0F);
		mObj3.render(1.0F);
		mObj4.render(1.0F);
		// GL11.glColor3f(0, 1, 0);
		// mObj1.renderParts();
		// GL11.glColor3f(1, 0, 0);
		// mObj2.renderParts();
		// GL11.glColor3f(0, 0, 1);
		// mObj3.renderParts();
		// GL11.glColor3f(1, 1, 1);
		// mObj4.renderParts();
		GL11.glEnable(2896 /* GL_LIGHTING */);
		GL11.glPopMatrix();

	}
}
