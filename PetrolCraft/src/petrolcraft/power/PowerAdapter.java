package petrolcraft.power;

import net.minecraft.tileentity.TileEntity;
import petrolcraft.power.ic2.IC2EnergyConsumer;

public class PowerAdapter {

	private IC2EnergyConsumer mIC2Consumer;

	public PowerAdapter(TileEntity pEntity, IEnergyConsumer pConsumer) {
		mIC2Consumer = new IC2EnergyConsumer(pEntity, pConsumer);
	}

	public void disable() {
		if (mIC2Consumer != null)
			mIC2Consumer.disable();
	}

	public void enable() {
		if (mIC2Consumer != null)
			mIC2Consumer.enable();
	}

	public void updateInternals() {
		if (mIC2Consumer != null)
			mIC2Consumer.updateInternals();
	}
}
