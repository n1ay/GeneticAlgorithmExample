package pl.kamilkos.genetic;

public abstract class Individual {
	protected String genome;
	
	public Individual(String genome) {
		this.genome = genome;
	}

	public void tryMutate() {
		if(Math.random()>Config.mutationProbability)
			return;
		float r = (float) Math.random();
		for(int i=0; i<genome.length(); i++) {
			if(r<(1.0f + i)/genome.length()) {
				genome = changeGene(genome.toCharArray(), i);
				return;
			}
		}	
	}

	public abstract float calculateFitnessValue();
	
	public abstract int parseGene();
	
	public void tryPerformCrossOver(Individual parent1, Individual parent2) {
		float r = (float) Math.random();
		if(r > Config.crossOverProbability)
			return;
		
		int index = Math.round((float)Math.random()*(genome.length() - 2) + 1);
		String genome1 = parent1.getGene().substring(0, index);
		String genome2 = parent2.getGene().substring(index);
		genome = genome1 + genome2;
	}

	public String getGene() {
		return genome;
	}

	public void setGene(String genome) {
		this.genome = genome;
	}
	
	public String changeGene(char[] genome, int index) {
		char[] result = genome;
		if(genome[index]=='0')
			result[index]='1';
		else
			result[index]='0';
		return String.valueOf(result);
			
	}
}
