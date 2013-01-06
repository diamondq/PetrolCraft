package petrolcraft.power;

import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public interface IEnergyConsumer {
	/**
	 * Will this consumer accept a power connection from this direction
	 * @param pEmitter 
	 * 
	 * @param pDirection
	 *            the direction
	 * @return true or false
	 */
	public boolean acceptEnergyFrom(TileEntity pEmitter, ForgeDirection pDirection);

	/**
	 * Returns the amount of energy that this consumer will accept
	 * 
	 * @return
	 */
	public int getAcceptedEnergyAmount();

	public int addEnergy(ForgeDirection pForgeDirection, int pEnergy);

	public int getMaxSafeInput();

	public int getMaxStorage();

	public Packet getDescriptionPacket();

}
