package pl.kamilkos.genetic;

public final class Utils {
	public static void sort(Individual[] array) {
		for(int i=0; i<array.length-1; i++) {
			for(int j=0; j<array.length-1; j++) {
				if(array[j].calculateFitnessValue()>array[j+1].calculateFitnessValue()) {
					Individual tmp = array[j+1];
					array[j+1] = array[j];
					array[j] = tmp;
				}
			}
		}
	}
}
