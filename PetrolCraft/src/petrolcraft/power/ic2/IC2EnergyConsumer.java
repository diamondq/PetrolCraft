package petrolcraft.power.ic2;

import ic2.api.Direction;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;

import java.util.logging.Logger;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import petrolcraft.common.TileEntityDelegate;
import petrolcraft.power.IEnergyConsumer;
import petrolcraft.power.PowerConversion;

public class IC2EnergyConsumer extends TileEntityDelegate implements IEnergySink {

	private static final Logger sLog = Logger.getLogger("PetrolCraft");
	private static final String sClassName = IC2EnergyConsumer.class.getName();

	private IEnergyConsumer mConsumer;
	private boolean mIsAddedToNet = false;

	public IC2EnergyConsumer(TileEntity pEntity, IEnergyConsumer pConsumer) {
		super(pEntity);
		mConsumer = pConsumer;
	}

	/**
	 * @see ic2.api.energy.tile.IEnergyAcceptor#acceptsEnergyFrom(net.minecraft.tileentity.TileEntity,
	 *      ic2.api.Direction)
	 */
	@Override
	public boolean acceptsEnergyFrom(TileEntity pEmitter, Direction pDirection) {
		sLog.entering(sClassName, "acceptsEnergyFrom");
		return mConsumer.acceptEnergyFrom(pEmitter, pDirection.toForgeDirection());
	}

	/**
	 * @see ic2.api.energy.tile.IEnergyTile#isAddedToEnergyNet()
	 */
	@Override
	public boolean isAddedToEnergyNet() {
		sLog.entering(sClassName, "isAddedToEnergyNet");
		return mIsAddedToNet;
	}

	/**
	 * @see ic2.api.energy.tile.IEnergySink#demandsEnergy()
	 */
	@Override
	public int demandsEnergy() {
		sLog.entering(sClassName, "demandsEnergy");
		return PowerConversion.convertToEU(mConsumer.getAcceptedEnergyAmount());
	}

	/**
	 * @see ic2.api.energy.tile.IEnergySink#injectEnergy(ic2.api.Direction, int)
	 */
	@Override
	public int injectEnergy(Direction pDirectionFrom, int pAmount) {
		sLog.entering(sClassName, "injectEnergy");
		return PowerConversion.convertToEU(mConsumer.addEnergy(pDirectionFrom.toForgeDirection(), PowerConversion.convertFromEU(pAmount)));
	}

	/**
	 * @see ic2.api.energy.tile.IEnergySink#getMaxSafeInput()
	 */
	@Override
	public int getMaxSafeInput() {
		sLog.entering(sClassName, "getMaxSafeInput");
		int eu = PowerConversion.convertToEU(mConsumer.getMaxSafeInput());
		if (eu <= 32)
			return 32;
		if (eu <= 128)
			return 128;
		if (eu <= 512)
			return 512;
		if (eu <= 2048)
			return 2048;
		if (eu <= 8192)
			return 8192;
		return eu;
	}

	public void disable() {
		sLog.entering(sClassName, "disable");
		if (mIsAddedToNet == true) {
			mIsAddedToNet = false;
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
		}
	}

	public void enable() {
		sLog.entering(sClassName, "enable");
		if (mIsAddedToNet == false) {
			mIsAddedToNet = true;
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
		}
	}
}
