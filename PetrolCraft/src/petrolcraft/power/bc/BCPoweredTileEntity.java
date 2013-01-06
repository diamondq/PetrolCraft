package petrolcraft.power.bc;

import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerFramework;

import java.util.logging.Logger;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import petrolcraft.machines.PoweredTileEntity;
import petrolcraft.power.PowerConversion;

public class BCPoweredTileEntity extends PoweredTileEntity implements IPowerReceptor {

	private static final Logger sLog = Logger.getLogger("PetrolCraft");
	private static final String sClassName = BCPoweredTileEntity.class.getName();

	private IPowerProvider mProvider;

	public BCPoweredTileEntity(World pWorld, int pMetadata) {
		super(pWorld, pMetadata);
		mProvider = new PetrolBCPowerProvider(this);
		mProvider.configure(0, 0, PowerConversion.convertToMJ(mConsumer.getAcceptedEnergyAmount()), 0,
				PowerConversion.convertToMJ(mConsumer.getMaxStorage()));
	}

	public BCPoweredTileEntity() {
		mProvider = new PetrolBCPowerProvider(this);
	}

	/**
	 * @see petrolcraft.machines.PoweredTileEntity#readFromNBT(net.minecraft.nbt.NBTTagCompound)
	 */
	@Override
	public void readFromNBT(NBTTagCompound pTags) {
		super.readFromNBT(pTags);
		PowerFramework.currentFramework.loadPowerProvider(this, pTags);
	}

	/**
	 * @see petrolcraft.machines.PoweredTileEntity#writeToNBT(net.minecraft.nbt.NBTTagCompound)
	 */
	@Override
	public void writeToNBT(NBTTagCompound pTags) {
		super.writeToNBT(pTags);
		PowerFramework.currentFramework.savePowerProvider(this, pTags);
	}

	/**
	 * @see buildcraft.api.power.IPowerReceptor#setPowerProvider(buildcraft.api.power.IPowerProvider)
	 */
	@Override
	public void setPowerProvider(IPowerProvider pProvider) {
		if (pProvider instanceof PetrolBCPowerProvider)
			mProvider = pProvider;
		else
			mProvider = new PetrolBCPowerProvider(this, pProvider);
	}

	/**
	 * @see buildcraft.api.power.IPowerReceptor#getPowerProvider()
	 */
	@Override
	public IPowerProvider getPowerProvider() {
		return mProvider;
	}

	/**
	 * @see buildcraft.api.power.IPowerReceptor#doWork()
	 */
	@Override
	public void doWork() {
		/* We don't do any work. We'll handle work in our update routine */
	}

	/**
	 * @see buildcraft.api.power.IPowerReceptor#powerRequest()
	 */
	@Override
	public int powerRequest() {
		// sLog.entering(sClassName, "powerRequest");
		if (mConsumer == null)
			sLog.warning("Consumer is null");
		int result = (mConsumer == null ? 0 : PowerConversion.convertToMJ(mConsumer.getAcceptedEnergyAmount()));
		// sLog.exiting(sClassName, "powerRequest", result);
		return result;
	}

}
