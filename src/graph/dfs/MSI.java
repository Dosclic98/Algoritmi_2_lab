package graph.dfs;

import java.util.ArrayList;

public class MSI {
	int[] wei;
	int[] A;
	
	public MSI(int[] w) {
		wei = w;
		A = new int[wei.length];
	}
	
	// Ritorna il valore del MSI
	public int getMaxVal() {
		
		A[0] = wei[0];
		for(int i = 1; i < wei.length; i++) {
			if(i == 1) {
				A[i] = max(A[i-1],wei[i]);
			}
			else {
				A[i] = max(A[i-1], A[i-2] + wei[i]);
			}
		}
		
		return A[wei.length-1];
	}
	
	public ArrayList<Integer> getOptSol() {
		getMaxVal();
		
		ArrayList<Integer> solution = new ArrayList<Integer>();
		int i = wei.length-1;
		
		while(i > 0) {
			if(i == 1) {
				if(A[i-1] > wei[i]) {
					i--;
				} else {
					solution.add(i);
					i-=2;					
				}
			} else {
				if(A[i-1] > A[i-2]+wei[i]) {
					i--;
				} else {
					solution.add(i);
					i-=2;
				}
			}
		}
		
		if(i == 0) {
			solution.add(0);
		}
		
		return solution;
	}
	
	int max(int a, int b) {
		if(a > b) return a;
		else return b;
	}
}
