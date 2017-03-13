package pl.kamilkos.genetic;

import static org.junit.Assert.*;

import org.junit.Test;

public class GenomeParseTest {

	@Test
	public void parseGeneTest1() {
		Individual individual = new EquationMaximumIndividual("1010");
		assertEquals(individual.parseGene(), 10);
	}
	
	@Test
	public void parseGeneTest2() {
		Individual individual = new EquationMaximumIndividual("0000");
		assertEquals(individual.parseGene(), 0);
	}
	
	@Test
	public void parseGeneTest3() {
		Individual individual = new EquationMaximumIndividual("101101010110");
		assertEquals(individual.parseGene(), 2902);
	}

}
