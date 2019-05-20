package graph.dfs;

public class Rebooking {
	
	int avSeats;
	
	public Rebooking(int availableSeats) {
		avSeats = availableSeats;
	}
	
	public int maxSaving(int[] groupSizes, int[] groupCosts) {
		if(avSeats < 0 || groupSizes.length != groupCosts.length) return -1;
		int[][] arrSol = new int[groupSizes.length + 1][avSeats + 1];
		
		for(int c = 0; c <= avSeats; c++) {
			arrSol[0][c] = 0;
		}
		
		for(int n = 1; n <= groupSizes.length; n++) {
			for(int c = 0; c <= avSeats; c++) {
				if(groupSizes[n-1] <= c) {
					arrSol[n][c] = Math.max(arrSol[n-1][c], arrSol[n-1][c - groupSizes[n-1]] + groupCosts[n-1]);
				} else {
					arrSol[n][c] = arrSol[n-1][c];
				}
			}
			
		}
		return arrSol[groupSizes.length][avSeats];
	}
}
