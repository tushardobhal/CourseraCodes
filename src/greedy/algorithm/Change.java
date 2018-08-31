package greedy.algorithm;

import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
        //write your code here
    	int n = 0;
    	int[] a = {10, 5, 1};
    	for(int i=0;i<3;i++) {
    		n += m/a[i];
    		m = m%a[i];
    	}
        return n;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

