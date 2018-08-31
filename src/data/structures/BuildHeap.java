//package data.structures;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;
    int n = 0;
    private static final int FRONT = 1;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
    	
        n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }
   
      public void generateSwaps()
      {
    	  swaps = new ArrayList<Swap>();
    	  for (int i=n/2-1; i>=0; i--) 
    		  siftdown(i);
      }
      
      public boolean isLeaf(int pos)
      { return (pos >= n/2) && (pos < n); }

      /** Return position for left child of pos */
      public int leftchild(int pos) {
        assert pos < n/2 : "Position has no left child";
        return 2*pos + 1;
      }

      /** Return position for right child of pos */
      public int rightchild(int pos) {
        assert pos < (n-1)/2 : "Position has no right child";
        return 2*pos + 2;
      }

      /** Return position for parent */
      public int parent(int pos) {
        assert pos > 0 : "Position has no parent";
        return (pos-1)/2;
      }
      
      private void siftdown(int pos) {
    	  assert (pos >= 0) && (pos < n) : "Illegal heap position";
    	  while (!isLeaf(pos)) {
    	    int j = leftchild(pos);
    	    if ((j<(n-1)) && (data[j] > data[j+1] )) 
    	      j++; 
    	    if (data[pos] <= data[j])
    	      return;
    	    swaps.add(new Swap(pos, j));
    	    int tmp = data[pos];
    	    data[pos] = data[j];
    	    data[j] = tmp;
    	    pos = j;  // Move down
    	  }
    	}
    

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
