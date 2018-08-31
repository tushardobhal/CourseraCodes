package algorithmic.toolbox;
import java.util.*;

public class FibonacciLastDigit {
    private static int getFibonacciLastDigitNaive(int n) {
        List<Integer> fib = new ArrayList<Integer>();
        fib.add(0);
        fib.add(1);
        for(int i=2;i<=n;i++) {
        	fib.add((fib.get(i-1) + fib.get(i-2))%10);
        }
        return fib.get(n);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigitNaive(n);
        System.out.println(c);
    }
}

