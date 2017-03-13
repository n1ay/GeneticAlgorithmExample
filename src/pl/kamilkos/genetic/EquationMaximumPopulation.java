package pl.kamilkos.genetic;

public class EquationMaximumPopulation extends Population {

	public EquationMaximumPopulation(int populationSize) {
		super(populationSize);
	}
	
	@Override
	public void initializePopulation() {
		for(int i=0; i<individuals.length; i++) {
			String genome = "";
			for(int j=0; j<EquationMaximumIndividual.genomeLength; j++) {
				float r = (float) Math.random();
				if(r > 0.5f)
					genome += '1';	
				else
					genome += '0';
			}
			individuals[i] = new EquationMaximumIndividual(genome);				
		}
	}

	@Override
	public Population reproduct() {
		Population newPopulation = new EquationMaximumPopulation(individuals.length);
		int populationSize = 0;
		Utils.sort(individuals);
		while(populationSize < individuals.length) {
			Individual parent1 = null;
			Individual parent2 = null;
			for(int i=individuals.length-1; i>=0; i--) {
				float r = (float) Math.random();
				if(r <= Config.reproductionProbabilityThreshold) {
					parent1 = individuals[i];
					break;
				}				
			}
			for(int i=individuals.length-1; i>=0; i--) {
				float r = (float) Math.random();
				if(r <= Config.reproductionProbabilityThreshold) {
					parent2 = individuals[i];
					break;
				}				
			}
			if(parent1 == parent2 || parent1 == null || parent2 == null)
				continue;
			
			Individual child1 = new EquationMaximumIndividual(parent1.getGene());
			Individual child2 = new EquationMaximumIndividual(parent2.getGene());
			
			child1.tryPerformCrossOver(parent1, parent2);
			child1.tryMutate();
			
			child2.tryPerformCrossOver(parent1, parent2);
			child2.tryMutate();
			
			newPopulation.getIndividuals()[populationSize++] = child1;
			if(populationSize < individuals.length)
				newPopulation.getIndividuals()[populationSize++] = child2;
		}
		return newPopulation;
		
	}

}
