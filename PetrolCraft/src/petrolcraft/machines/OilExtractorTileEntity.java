package petrolcraft.machines;

import ic2.api.Direction;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class OilExtractorTileEntity extends TileEntity implements IEnergySink {

	private int mStoredEnergy = 0;
	private int mMaxEnergy = 3000;

	public OilExtractorTileEntity(World pWorld, int pMetadata) {
		System.out.println("new entity");
	}

	public OilExtractorTileEntity() {

	}

	@Override
	public boolean acceptsEnergyFrom(TileEntity pEntity, Direction pDir) {
		System.out.println(toString() + " acceptsEnergyFrom");
		return true;
	}

	@Override
	public boolean isAddedToEnergyNet() {
		System.out.println(toString() + " isAddedToEnergyNet");
		return true;
	}

	@Override
	public int injectEnergy(Direction pEntity, int pAmount) {
		int consumable = Math.min(pAmount, mMaxEnergy - mStoredEnergy);
		mStoredEnergy += consumable;
		if ((consumable > 0) && (mStoredEnergy == mMaxEnergy))
			System.out.println("Extractor energy full");
		return pAmount - consumable;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void invalidate() {
		System.out.println(toString() + " invalidate");
		EnergyNet.getForWorld(getWorldObj()).removeTileEntity(this);
		super.invalidate();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void validate() {
		System.out.println(toString() + " validate");
		super.validate();
		EnergyNet.getForWorld(getWorldObj()).addTileEntity(this);
	}

	@Override
	public int demandsEnergy() {
		System.out.println(this.toString() + " demandsEnergy " + String.valueOf(mStoredEnergy));
		return mMaxEnergy - mStoredEnergy;
	}

	@Override
	public int getMaxSafeInput() {
		System.out.println(toString() + " getMaxSafeInput");
		return Integer.MAX_VALUE;
	}

}
