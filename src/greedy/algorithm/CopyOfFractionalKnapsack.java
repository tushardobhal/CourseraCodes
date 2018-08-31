package greedy.algorithm;

import java.util.Scanner;

public class CopyOfFractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights, double[] arr) {
        double value = 0.0;
        //write your code here
        for(int i=0;i<values.length;i++) {
        	if(capacity == 0) {
        		return value;
        	}
        	int a = Math.min(weights[i], capacity);
        	value = value + (double)(a* arr[i]);
        	capacity = capacity - a;
        }
        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        double[] arr = new double[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();  
            arr[i] = (double)values[i]/weights[i];
        }
        for(int i=0;i<n;i++) {
        	for(int j=i+1;j<n;j++) {
        		if(arr[i] < arr[j]) {
        			int temp = values[i];
        			values[i] = values[j];
        			values[j] = temp;
        			int temp1 = weights[i];
        			weights[i] = weights[j];
        			weights[j] = temp1;
        			double temp2 = arr[i];
        			arr[i] = arr[j];
        			arr[j] = temp2;
        		}
        	}
        }
        System.out.println(getOptimalValue(capacity, values, weights, arr));
    }
} 
