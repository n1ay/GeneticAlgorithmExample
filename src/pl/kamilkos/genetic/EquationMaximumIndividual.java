package pl.kamilkos.genetic;

public class EquationMaximumIndividual extends Individual {
	
	public static int genomeLength = 30;
	
	public EquationMaximumIndividual(String genome) {
		super(genome);
	}

	@Override
	public float calculateFitnessValue() {
		/*
		 */
		int solution = 1389;
		float eps = 0.1f;
		
		//inverse of relative error
		return solution/Math.abs(parseGene()+eps-solution);
		
	}
	
	@Override
	public int parseGene() {
		int result = 0;
		for(int i = 0; i<getGene().length(); i++) {
			int j = getGene().length()-i;
			if(getGene().charAt(i)=='1')
				result += Math.pow(2, j-1);
		}
		return result;
	}
}
