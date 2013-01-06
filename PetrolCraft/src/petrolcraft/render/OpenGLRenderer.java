package petrolcraft.render;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

public class OpenGLRenderer extends ModelRenderer {

	private List<VertexData> mDataList;
	private boolean mCompiled = false;

	public OpenGLRenderer(String pObjName, List<VertexData> pDataList) {
		super(new ModelBase() {
		});
		mDataList = pDataList;
	}

	@Override
	public void render(float pPar1) {
		if (mCompiled == false) {
			compileList();
			mCompiled = true;
		}
		super.render(pPar1);
	}

	@Override
	public void renderWithRotation(float pPar1) {
		if (mCompiled == false) {
			compileList();
			mCompiled = true;
		}
		super.renderWithRotation(pPar1);
	}

	public void renderParts() {
		// GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glBegin(GL11.GL_TRIANGLES);
		for (VertexData d : mDataList) {
			GL11.glNormal3f(d.mNX, d.mNY, d.mNZ);
			GL11.glVertex3d(d.mX, d.mY, d.mZ);
		}
		GL11.glEnd();
		GL11.glFlush();
	}

	public void compileList() {
		int displayListID = GLAllocation.generateDisplayLists(1);
		GL11.glNewList(displayListID, GL11.GL_COMPILE);
		Tessellator var2 = Tessellator.instance;

		var2.startDrawing(GL11.GL_TRIANGLES);
		var2.setTranslation(0, 0, 0);
		for (VertexData d : mDataList) {
			var2.setColorRGBA(255, 0, 0, 128);
			if (d.mUseNormal == true)
				var2.setNormal(d.mNX, d.mNY, d.mNZ);
			if (d.mUseTexture == true)
				var2.setTextureUV(d.mU, d.mV);
			var2.addVertex(d.mX, d.mY, d.mZ);
		}
		var2.draw();
		GL11.glEndList();

		try {
			Field compiledField = ModelRenderer.class.getDeclaredField("compiled");
			compiledField.setAccessible(true);
			compiledField.set(this, true);

			Field displayListField = ModelRenderer.class.getDeclaredField("displayList");
			displayListField.setAccessible(true);
			displayListField.set(this, displayListID);
		} catch (SecurityException ex) {
			throw new RuntimeException(ex);
		} catch (NoSuchFieldException ex) {
			throw new RuntimeException(ex);
		} catch (IllegalArgumentException ex) {
			throw new RuntimeException(ex);
		} catch (IllegalAccessException ex) {
			throw new RuntimeException(ex);
		}

	}
}
