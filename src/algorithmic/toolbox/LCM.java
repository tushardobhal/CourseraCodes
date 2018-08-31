package algorithmic.toolbox;
import java.util.*;

public class LCM {
  private static long lcm_naive(long a, long b) {
	  return (a*b/gcd_naive(a,b));
  }
  
  private static long gcd_naive(long a, long b) {
	  if(b == 0) {
		  return a;
	  }
	  long a1 = a % b;
	  return gcd_naive(b, a1);
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    long a = scanner.nextLong();
    long b = scanner.nextLong();
    if(a<b) {
    	long temp = a;
    	a = b;
    	b = temp;
    }
    System.out.println(lcm_naive(a, b));
  }
}
