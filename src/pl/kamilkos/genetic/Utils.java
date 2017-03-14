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
	
	public static int parseBinary(String bin) {
		int result = 0;
		for(int i = 0; i<bin.length(); i++) {
			int j = bin.length()-i;
			if(bin.charAt(i)=='1')
				result += Math.pow(2, j-1);
		}
		return result;
	}
	
	public static double log2(double number) {
		return Math.log(number)/Math.log(2);
	}
	
	public static String intToBin(int number) {
		int len = (int) Math.floor(log2(number));
		String result = Integer.toBinaryString(number);
		while(result.length() < len) {
			result = '0' + result;
		}
		return result;
	}
	
	public static String intToBin(int number, int len) {
		String result = Integer.toBinaryString(number);
		while(result.length() < len) {
			result = '0' + result;
		}
		return result;
	}
	

}
