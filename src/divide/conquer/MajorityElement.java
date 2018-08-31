//package divide.conquer;

import java.util.*;
import java.io.*;

public class MajorityElement {
    private static long getMajorityElement(long[] a, int left, int right) {
    	HashMap<Long, Integer> hm = new HashMap<Long, Integer>();

        for (int i = 0; i < a.length; i++) {

            Long key = a[i];

            if (hm.containsKey(key)) {
                Integer addMe = (Integer)hm.get(key);
                hm.put(key, addMe + 1);
            } else {
                hm.put(key, 1);
            }
        }

        double majority = Math.ceil(a.length / 2);

        for (Object val : hm.values()) {

            if ((int)val > majority) {
//                System.out.println("Majority Found " + (int)val + " Is >=" + majority);
                return 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

