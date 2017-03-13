package pl.kamilkos.genetic;

import java.util.HashMap;

public class TravelingSalesmanIndividual extends Individual {

	public static int genomeLength = 2*5;
	public static HashMap<String, Integer> costs;
	
	public static void initCosts() {
		//graph Edges values-costs
				costs = new HashMap<>();
				costs.put("0001", 3);
				costs.put("0100", 3);
				costs.put("0010", 5);
				costs.put("1000", 5);
				costs.put("0011", 4);
				costs.put("1100", 4);
				
				costs.put("0110", 4);
				costs.put("1001", 4);
				costs.put("0111", 5);
				costs.put("1101", 5);
				
				costs.put("1011", 3);
				costs.put("1110", 3);
				
				costs.put("0000", 100);
				costs.put("0101", 100);
				costs.put("1010", 100);
				costs.put("1111", 100);
	}
	
	public TravelingSalesmanIndividual(String genome) {
		super(genome);
	}
	
	@Override
	public float calculateFitnessValue() {
		/*
		 */
		return parseGene();
		
	}
	
	public String parsePath() {
		String result ="";
		String node1;
		for(int i = 0; i+2<=genome.length(); i+=2) {
			node1 = genome.substring(i, i+2);
			result += node1 + "->";
		}
		return result;
	}
	
	public int parseBinary(String bin) {
		int result = 0;
		for(int i = 0; i<bin.length(); i++) {
			int j = bin.length()-i;
			if(bin.charAt(i)=='1')
				result += Math.pow(2, j-1);
		}
		return result;
	}

	@Override
	public int parseGene() {
		int result = 0;
		String node1;
		String node2 = genome.substring(0, 2);
		int[] allNodes = {1, 1, 1};
		if(!genome.substring(0, 2).equals("00"))
			result -= 100;
		if(!genome.substring(genome.length()-2, genome.length()).equals("00"))
			result -= 100;

		for(int i = 2; i+2<=genome.length(); i+=2) {
			node1 = node2;
			node2 = genome.substring(i, i+2);
			if(node2.equals("01"))
				allNodes[0]--;
			else if(node2.equals("10"))
				allNodes[1]--;
			else if(node2.equals("11"))
				allNodes[2]--;
			//System.out.println(node1+node2+" "+costs.get(node1+node2));
			result -=costs.get(node1+node2);
			//System.out.println(node1+" "+node2+" "+result);
		}
		for(int i=0; i<3; i++) {
			if(allNodes[i] != 0)
				result -= 100;
		}
		return result;
	}

}
