package graph.dfs;

import it.uniupo.algoTools.MaxHeap;

public class FracKnapsack {
	double capacity;
	double[] volume;
	double[] value;
	double[] dose;
	
	public FracKnapsack(double capacity, double[] volume, double[] value) {
		this.capacity = capacity;
		this.volume = volume;
		this.value = value;
		dose = new double[volume.length];
	}
	
	void algoFracKnapsack() {
		MaxHeap<Integer, Double> ordered = new MaxHeap<Integer, Double>(); 
		double[] quant = new double[volume.length];
		for(int i = 0; i < volume.length; i++) {
			dose[i] = 0;
			quant[i] = 0;
			ordered.add(i, value[i]/volume[i]);
		}
		
		double spazioRimasto = capacity;
		int valTot = 0;
		while(spazioRimasto > 0 && !ordered.isEmpty()) {
			Integer tmp = ordered.extractMax();
			if(spazioRimasto > volume[tmp]) {
				dose[tmp] = 1;
				quant[tmp] = volume[tmp];
				
				valTot += value[tmp];
				spazioRimasto -= volume[tmp];
			}
			else {
				dose[tmp] = (double) spazioRimasto / (double) volume[tmp];
				quant[tmp] = spazioRimasto;
				
				valTot += value[tmp] * dose[tmp];
				spazioRimasto = 0;
			}
		}
				
	}
	
	public double maxVal() {
		algoFracKnapsack();
		
		double maxVal = 0;
		for(int i = 0; i < volume.length; i++) {
			maxVal += value[i] * dose[i];
			System.out.println(value[i] + " " + dose[i]);
		}
		
		return maxVal;
	}
	
	public double dose(int i) {
		algoFracKnapsack();
		
		return dose[i];
	}
}
