package graph.dfs;

import it.uniupo.algoTools.MaxHeap;

public class DB {
	int[] dim = null;
	double[] time = null;
	int[] quant = null;
	double[] dose = null;
	
	public DB(int[] dim, double[] time) {
		this.dim = dim;
		this.time = time;
		quant = new int[dim.length];
		dose = new double[dim.length];
	}
	
	public double algoFracKnapsack(int capacity) {
		MaxHeap<Integer, Double> ordered = new MaxHeap<Integer, Double>();
		for(int i = 0; i < dim.length; i++) {
			quant[i] = 0;
			dose[i] = 0;
			ordered.add(i, time[i] / (double) dim[i]);
		}
		int spazioRimasto = capacity;
		double valTot = 0;
		while(spazioRimasto > 0 && !ordered.isEmpty()) {
			Integer tmp = ordered.extractMax();
			if(spazioRimasto > dim[tmp]) {
				dose[tmp] = 1;
				quant[tmp] = dim[tmp];
				valTot += time[tmp];
				
				spazioRimasto -= dim[tmp];
			}
			else {
				dose[tmp] = (double) spazioRimasto / (double) dim[tmp];
				quant[tmp] = spazioRimasto;
				
				valTot += (double) time[tmp] * dose[tmp];
				spazioRimasto = 0;
			}
		}
		
		return valTot;
	}
	
	public int[] memorize(int memSpace) {
		if(memSpace < 0) throw new IllegalArgumentException();
		algoFracKnapsack(memSpace);
		
		return quant;
	}
	
	public double timeSaved(int memSpace) {
		if(memSpace < 0) throw new IllegalArgumentException();
		return algoFracKnapsack(memSpace);
	}
}
