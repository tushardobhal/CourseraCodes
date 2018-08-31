//package dynamic.programming;

import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        int[][] val = new int[W+1][w.length+1];
        for(int i=1;i<=w.length;i++) {
        	for(int j=1;j<=W;j++) {
        		val[j][i] = val[j][ i-1];
        		if(w[i-1] <= j) {
        			int value = val[j-w[i-1]][i-1] + w[i-1];              
        			if(value > val[j][i]) {
        				val[j][i] = value;
        			}
            	}
        	}
        }
        return val[W][w.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

