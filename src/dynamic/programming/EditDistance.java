//package dynamic.programming;

import java.util.*;

class EditDistance {
  public static int editDistance(String s, String t) {
	if(s.equals(t)) {
		return 0;
	}
    int[][] d = new int[s.length()+1][t.length()+1];
	for(int i=0;i<=s.length();i++) {
		d[i][0] = i;
	}
	for(int j=0;j<=t.length();j++) {
		d[0][j] = j;
	}
    for(int j=1; j<=t.length();j++) {
    	for(int i=1;i<=s.length();i++) {
    		if(s.charAt(i-1) == t.charAt(j-1)) {
    			d[i][j] = Math.min(d[i][j-1] + 1, Math.min(d[i-1][j] + 1, d[i-1][j-1]));
    		}
    		else {
    			d[i][j] = Math.min(d[i][j-1] + 1, Math.min(d[i-1][j] + 1, d[i-1][j-1] + 1));
    		}
    	}
    }
    return d[s.length()][t.length()];
  }
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(editDistance(s, t));
  }

}
