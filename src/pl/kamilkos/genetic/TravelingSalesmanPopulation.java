package pl.kamilkos.genetic;

public class TravelingSalesmanPopulation extends Population {

	public TravelingSalesmanPopulation(int populationSize) {
		super(populationSize);
	}

	@Override
	public void initializePopulation() {
		for(int i=0; i<individuals.length; i++) {
			String genome = "";
			for(int j=0; j<TravelingSalesmanIndividual.genomeLength; j++) {
				float r = (float) Math.random();
				if(r > 0.5f)
					genome += '1';	
				else
					genome += '0';
			}
			individuals[i] = new TravelingSalesmanIndividual(genome);				
		}
	}

	@Override
	public Population reproduct() {
		Population newPopulation = new TravelingSalesmanPopulation(individuals.length);
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
			
			Individual child1 = new TravelingSalesmanIndividual(parent1.getGene());
			Individual child2 = new TravelingSalesmanIndividual(parent2.getGene());
			
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
	
	public void printPopulation() {
		for(Individual i:individuals) {
			System.out.println(((TravelingSalesmanIndividual)i).parsePath()+" "+i.calculateFitnessValue()+" "+i.genome);
		}
	}
	
	public void printBest() {
		Utils.sort(individuals);
		System.out.println(((TravelingSalesmanIndividual)individuals[individuals.length-1]).parsePath()+" "+individuals[individuals.length-1].calculateFitnessValue()+" "+individuals[individuals.length-1].getGene());
	}

}
