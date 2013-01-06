package petrolcraft.power;

public class PowerConversion {

	private static final double sENERGY_TO_MJ = 1.0D;
	public static double sENERGY_TO_EU = 1.0D;

	/**
	 * Converts petrol energy to EU energy
	 * 
	 * @param pPetrolEnergy
	 * @return
	 */
	public static int convertToEU(int pPetrolEnergy) {
		return (int) (pPetrolEnergy * sENERGY_TO_EU);
	}

	public static int convertFromEU(int pEU) {
		return (int) (pEU / sENERGY_TO_EU);
	}

	public static int convertToMJ(int pPetrolEnergy) {
		return (int) (pPetrolEnergy * sENERGY_TO_MJ);
	}

	public static int convertFromMJ(float pQuantity) {
		return (int) (pQuantity / sENERGY_TO_MJ);
	}

}
