package graph.dfs;

public class Atletica {
	int numDisc;
	
	public Atletica(int numeroDiscipline) {
		numDisc = numeroDiscipline;
	}
	
	public int scelta(int[] rendAtleta1, int[] rendAtleta2) {
		if(rendAtleta1.length != numDisc || rendAtleta2.length != numDisc) throw new IllegalArgumentException();
		
		int[] arrSol1 = new int[rendAtleta1.length];
		int[] arrSol2 = new int[rendAtleta2.length];
		arrSol1[0] = rendAtleta1[0];
		arrSol2[0] = rendAtleta2[0];
		
		int i = 1;
		while(i < rendAtleta1.length) {
			if(i == 1) {
				arrSol1[i] = Math.max(arrSol1[i-1], rendAtleta1[i]);
			} else {
				arrSol1[i] = Math.max(arrSol1[i-1], arrSol1[i-2] + rendAtleta1[i]);
			}
			i++;
		}
		
		int j = 1;
		while(j < rendAtleta2.length) {
			if(j == 1) {
				arrSol2[j] = Math.max(arrSol2[j-1], rendAtleta2[j]);
			} else {
				arrSol2[j] = Math.max(arrSol2[j-1], arrSol2[j-2] + rendAtleta2[j]);
			}
			j++;
		}
		if(arrSol1[rendAtleta1.length - 1] == arrSol2[rendAtleta2.length - 1]) return 0;
		else if(arrSol1[rendAtleta1.length - 1] == arrSol2[rendAtleta2.length - 1]) return 1;
		else return 2;
		
		// da mettere lancio illegal argument exc
	}
}
