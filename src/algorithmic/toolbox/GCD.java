package algorithmic.toolbox;
import java.util.*;

public class GCD {
  private static int gcd_naive(int a, int b) {
	  if(b == 0) {
		  return a;
	  }
	  int a1 = a % b;
	  return gcd_naive(b, a1);
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();
    if(a<b) {
    	int temp = a;
    	a = b;
    	b = temp;
    }
    System.out.println(gcd_naive(a, b));
  }
}
