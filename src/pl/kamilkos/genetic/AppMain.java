package pl.kamilkos.genetic;

public class AppMain {
	public static void main(String[] args) {
		TravelingSalesmanIndividual.initCosts();
		Population tp = new TravelingSalesmanPopulation(Config.populationSize);
		tp.initializePopulation();
		//tp.printPopulation();
		for(int i=0; i<Config.populations; i++) {
			System.out.println("Generation "+i+":");
			tp.printBest();
			System.out.println();
			tp = tp.reproduct();
		}
		System.out.println("Best solution found:");
		tp.printBest();
	}
}
