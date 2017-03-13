package pl.kamilkos.genetic;

public abstract class Population {
	protected Individual[] individuals;
	
	public Population(int populationSize) {
		this.individuals = new Individual [populationSize];
	}
	
	public abstract void initializePopulation();
	
	public abstract Population reproduct();

	public Individual[] getIndividuals() {
		return individuals;
	}

	public void setIndividuals(Individual[] individuals) {
		this.individuals = individuals;
	}
	
	public void printPopulation() {
		for(Individual i:individuals) {
			System.out.println(i.parseGene()+" "+i.calculateFitnessValue()+" "+i.genome);
		}
	}
	
	public void printBest() {
		Utils.sort(individuals);
		System.out.println(individuals[individuals.length-1].parseGene()+" "+individuals[individuals.length-1].calculateFitnessValue()+" "+individuals[individuals.length-1].getGene());
	}
}
