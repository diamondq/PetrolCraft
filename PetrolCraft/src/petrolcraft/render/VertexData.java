package petrolcraft.render;

public class VertexData {
	public float mNX;
	public float mNY;
	public float mNZ;
	public double mX;
	public double mY;
	public double mZ;
	public double mU;
	public double mV;
	public boolean mUseNormal;
	public boolean mUseTexture;

	public VertexData(double pX, double pY, double pZ, boolean pUseTexture, double pU, double pV, boolean pUseNormal, float pNX, float pNY,
			float pNZ) {
		super();
		mUseNormal = pUseNormal;
		mNX = pNX;
		mNY = pNY;
		mNZ = pNZ;
		mX = pX;
		mY = pY;
		mZ = pZ;
		mUseTexture = pUseTexture;
		mU = pU;
		mV = pV;
	}

}
