package petrolcraft.power.bc;

import buildcraft.api.core.SafeTimeTracker;
import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerFramework;
import buildcraft.api.power.PowerProvider;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import petrolcraft.power.IEnergyConsumer;
import petrolcraft.power.PowerConversion;

public class PetrolBCPowerProvider extends PowerProvider {

	private IPowerProvider mDelegate;
	private BCPoweredTileEntity mPoweredTileEntity;

	public PetrolBCPowerProvider(BCPoweredTileEntity pPoweredTileEntity) {
		super();
		mPoweredTileEntity = pPoweredTileEntity;
		if (PowerFramework.currentFramework != null)
			mDelegate = PowerFramework.currentFramework.createPowerProvider();
	}

	public PetrolBCPowerProvider(BCPoweredTileEntity pPoweredTileEntity, IPowerProvider pProvider) {
		super();
		mPoweredTileEntity = pPoweredTileEntity;
		mDelegate = pProvider;
	}

	public int getLatency() {
		return mDelegate.getLatency();
	}

	public int getMinEnergyReceived() {
		return mDelegate.getMinEnergyReceived();
	}

	public int getMaxEnergyReceived() {
		return mDelegate.getMaxEnergyReceived();
	}

	public int getMaxEnergyStored() {
		return mDelegate.getMaxEnergyStored();
	}

	public int getActivationEnergy() {
		return mDelegate.getActivationEnergy();
	}

	public float getEnergyStored() {
		return mDelegate.getEnergyStored();
	}

	public void configure(int pLatency, int pMinEnergyReceived, int pMaxEnergyReceived, int pMinActivationEnergy, int pMaxStoredEnergy) {
		mDelegate.configure(pLatency, pMinEnergyReceived, pMaxEnergyReceived, pMinActivationEnergy, pMaxStoredEnergy);
	}

	public void configurePowerPerdition(int pPowerLoss, int pPowerLossRegularity) {
		mDelegate.configurePowerPerdition(pPowerLoss, pPowerLossRegularity);
	}

	public boolean update(IPowerReceptor pReceptor) {
		return mDelegate.update(pReceptor);
	}

	/**
	 * @see buildcraft.api.power.PowerProvider#preConditions(buildcraft.api.power.IPowerReceptor)
	 */
	public boolean preConditions(IPowerReceptor pReceptor) {
		return mDelegate.preConditions(pReceptor);
	}

	public float useEnergy(float pMin, float pMax, boolean pDoUse) {
		return mDelegate.useEnergy(pMin, pMax, pDoUse);
	}

	public void readFromNBT(NBTTagCompound pNbttagcompound) {
		mDelegate.readFromNBT(pNbttagcompound);
	}

	public void writeToNBT(NBTTagCompound pNbttagcompound) {
		mDelegate.writeToNBT(pNbttagcompound);
	}

	public void receiveEnergy(float pQuantity, ForgeDirection pFrom) {
		IEnergyConsumer consumer = mPoweredTileEntity.getConsumer();
		if (consumer.acceptEnergyFrom(null, pFrom) == true) {
			mDelegate.receiveEnergy(pQuantity, pFrom);
			int petrolPower = PowerConversion.convertFromMJ(pQuantity);
			int remainingEnergy = consumer.addEnergy(pFrom, petrolPower);
			int usedPower = petrolPower - remainingEnergy;
			mDelegate.useEnergy(0, PowerConversion.convertToMJ(usedPower), true);
		}
	}

	public boolean isPowerSource(ForgeDirection pFrom) {
		return mDelegate.isPowerSource(pFrom);
	}

	public SafeTimeTracker getTimeTracker() {
		return mDelegate.getTimeTracker();
	}

}
