package pl.kamilkos.genetic;

import java.util.HashMap;

//extended problem for 8-node graph
public class TravelingSalesmanIndividual extends Individual {

	//IT IS VERY IMPORTATNT TO ALWAYS HAVE THESE TWO SET PROPERLY
	public static int nodeLength = 3;
	public static int graphNodes = 8;
	
	public static int genomeLength = nodeLength*(graphNodes+1);
	public static int errorCost = 200;
	
	public static final int UNDIRECTED_LINK = 1;
	public static final int DIRECTED_LINK = 2;
	public static final int LOCK_INVERTED_EGDE = 3;
	
	public static HashMap<String, Integer> costs;
	
	public TravelingSalesmanIndividual(String genome) {
		super(genome);
	}
	
	@Override
	public float calculateFitnessValue() {
		return parseGene();
	}
	
	public static void initCosts() {
		//graph Edges values-costs
		costs = new HashMap<>();
		lockEdges();
		
		//your edges costs goes here
		addEdgeCost(0, 1, 3);
		addEdgeCost(0, 2, 8);
		addEdgeCost(0, 3, 9);
		
		addEdgeCost(1, 2, 4);
		addEdgeCost(1, 3, 5);
		addEdgeCost(1, 5, 5);
		
		addEdgeCost(2, 3, 5);
		
		addEdgeCost(3, 4, 5);
		addEdgeCost(3, 6, 5);
		addEdgeCost(3, 7, 6);
		
		addEdgeCost(4, 5, 4);
		addEdgeCost(4, 7, 11);
		
		addEdgeCost(5, 6, 3);
		addEdgeCost(5, 7, 7);
		
		addEdgeCost(6, 7, 2);
		
		addEdgeCost(7, 0, 1);
		
	}
	
	public static void addEdgeCost(int from, int to, int cost) {
		addEgdeCost(from, to, cost, UNDIRECTED_LINK);
	}
	
	public static void addEgdeCost(int from, int to, int cost, int flag) {
		switch(flag) {
		case UNDIRECTED_LINK:
			costs.put(Utils.intToBin(from, nodeLength)+Utils.intToBin(to, nodeLength), cost);
			costs.put(Utils.intToBin(to, nodeLength)+Utils.intToBin(from, nodeLength), cost);
			break;
		case DIRECTED_LINK:
			costs.put(Utils.intToBin(from, nodeLength)+Utils.intToBin(to, nodeLength), cost);
			break;
		case LOCK_INVERTED_EGDE:
			costs.put(Utils.intToBin(from, nodeLength)+Utils.intToBin(to, nodeLength), cost);
			costs.put(Utils.intToBin(to, nodeLength)+Utils.intToBin(from, nodeLength), errorCost);
			break;
			default: break;
		}
		
	}

	public static void lockEdges() {
		//lock all edges, by setting them big travel cost
		for(int i=0; i<graphNodes; i++) {
			for(int j=i; j<graphNodes; j++) {
				addEdgeCost(i, j, errorCost);
			}
		}
	}
	
	public String parsePath() {
		String result ="";
		String node1;
		for(int i = 0; i+nodeLength<=genome.length()-nodeLength; i+=nodeLength) {
			node1 = genome.substring(i, i+nodeLength);
			result += Utils.parseBinary(node1) + "->";
		}
		result += Utils.parseBinary(genome.substring(genome.length()-nodeLength));
		return result;
	}

	@Override
	public int parseGene() {
		int result = 0;
		String node1;
		String node2 = genome.substring(0, nodeLength);
		int[] allNodes = new int [graphNodes];
		for(int i=0; i<allNodes.length; i++)
			allNodes[i] = 1;
		
		if(!genome.substring(0, nodeLength).equals(Utils.intToBin(0, nodeLength)))
			result -= errorCost*10;
		if(!genome.substring(genome.length()-nodeLength, genome.length()).equals(Utils.intToBin(0, nodeLength)))
			result -= errorCost*10;

		for(int i = nodeLength; i+nodeLength<=genome.length(); i+=nodeLength) {
			node1 = node2;
			node2 = genome.substring(i, i+nodeLength);
			allNodes[Utils.parseBinary(node2)]--;
			//System.out.println(node1+node2+" "+costs.get(node1+node2));
			result -=costs.get(node1+node2);
			//System.out.println(node1+" "+node2+" "+result);
		}
		for(int i=0; i<allNodes.length; i++) {
			if(allNodes[i] != 0) {
				result -= errorCost*10;
			}
		}
		return result;
	}

}
