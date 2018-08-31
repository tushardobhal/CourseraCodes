package algorithmic.toolbox;
import java.math.BigInteger;
import java.util.*;

public class FibonacciHuge {
    private static BigInteger getFibonacciHugeNaive(long n, long m) {
    	BigInteger[][] arr = new BigInteger[2][2];
    	 arr[0][0] = new BigInteger("1");
    	 arr[0][1] = new BigInteger("1");
    	 arr[1][0] = new BigInteger("1");
    	 arr[1][1] = new BigInteger("0");
    	 BigInteger[][] ans = arr;
    	 long k = n/2;
    	 for(int i=0;i<k-1;i++) {
    		 ans = mulMatrix(ans, arr);  
    	 }
    	 if(k > 0) {
    		 if(n % 2 == 0) {
        		 ans = mulMatrix(ans, ans);
        	 }
        	 else {
        		 ans = mulMatrix(ans, ans);
        		 ans = mulMatrix(ans, arr);
        	 }
    	 }
    	 return ans[0][1].mod(new BigInteger(String.valueOf(m)));
    }

    private static BigInteger[][] mulMatrix(BigInteger[][] ans, BigInteger[][] arr) {
    	BigInteger[][] c = new BigInteger[2][2];
    	c[0][0] = (ans[0][0].multiply(arr[0][0])).add(ans[0][1].multiply(arr[1][0]));
    	c[0][1] = (ans[0][0].multiply(arr[0][1])).add(ans[0][1].multiply(arr[1][1]));
    	c[1][0] = (ans[1][0].multiply(arr[0][0])).add(ans[1][1].multiply(arr[1][0]));
    	c[1][1] = (ans[1][0].multiply(arr[0][1])).add(ans[1][1].multiply(arr[1][1])); 
    	
    	return c;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHugeNaive(n, m));
    }
}

