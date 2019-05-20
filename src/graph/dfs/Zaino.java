package graph.dfs;

public class Zaino {
	
	private int[] value;
	private int[] volume;
	private int cap;
	
	public Zaino(int[] value, int[] volume, int capacity) {
		this.value= value;
		this.volume = volume;
		cap = capacity;
	}
	
	public int getMaxVal() {
		int[][] arrSol = new int[value.length + 1][cap + 1];
		
		for(int c = 0; c <= cap; c++) {
			arrSol[0][c] = 0;
		}
		
		for(int n = 1; n <= value.length; n++) {
			for(int c = 0; c <= cap; c++) {
				if(volume[n-1] <= c) {
					arrSol[n][c] = Math.max(arrSol[n-1][c], arrSol[n-1][c - volume[n-1] ] + value[n-1]);
				}
				else {
					arrSol[n][c] = arrSol[n-1][c];
				}
			}
		}
		
		return arrSol[value.length][cap];
	}
}
