package algorithmic.toolbox;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fibonacci {
  private static long calc_fib(int n) {
    List<Long> fib = new ArrayList<Long>();
    fib.add((long) 0);
    fib.add((long) 1);
    for(int i=2;i<=n;i++) {
    	fib.add(fib.get(i-1) + fib.get(i-2));
    }
    return fib.get(n);
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    System.out.println(calc_fib(n));
  }
}
