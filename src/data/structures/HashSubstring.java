//package data.structures;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    // Choose a large prime # can avoid collision
    // The total length of all occurrences of P doesn’t exceed 10^8
    private static int prime;
    // The multiplier for rotating hash function
    private static int multiplier;
    private static final Random random = new Random();

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    /**
     * Key Idea:
     * Choose a prime and multiplier for polynomial hash function.
     * Traverse all substrings of size |P|.
     * If hash(P) != hash(S), not found.
     * If hash values are equal, compare each letter.
     * <p>
     * Optimization: Instead of computing runtime hash value
     *               of each substring, precompute hashes
     *               starting at i: H[T(i)].</br>
     * Note: Java's substring() does NOT create a new String.
     *
     * @param input
     * @return
     */
    public static List<Integer> getOccurrences(Data input) {
        List<Integer> occurrences = new ArrayList<Integer>();
        int n = input.text.length(), p = input.pattern.length();
        // Find the 1st prime greater than |T|
        prime = nextPrime(n * p);
        multiplier = 31;
        // int hashPattern = hash(input.pattern);
        int[] hashes = precomputeHashes(input.text, p, prime, multiplier);
        // Leave at least |P| letters in the end
        for (int i = 0; i <= n - p; i++) {
            // Base i + taking |P| characters
            String substr = input.text.substring(i, i + p);
            // if (hash(substr) != hashPattern) continue;
            if (hash(substr) != hashes[i]) continue;
            // Re-initialization in outer loop
            boolean matching = true;
            for (int j = 0; j < p; j++) {
                if (substr.charAt(j) != input.pattern.charAt(j)) {
                    matching = false;
                    break;
                }
            }
            if (matching) occurrences.add(i);
        }
        return occurrences;
    }
    /**
     * Precompute hashes for all substrings of size |P|.
     * Build array of hashes for all |T|-|P|+1 substrings.
     * Fill in the hash of last cell at |T|-|P|.
     * Generate x^|P| beforehand.
     * Compute hashes from |T| - |P| - 1 down to 0.
     * <p>
     * Polynomial hashes of two consecutive substrings of
     * are very similar. Recurrence of Hashes:
     * <pre>H[i] = (x*H[i + 1] + T[i] - T[i + |P|]*x^|P|) mod p</pre>
     * H[i+1]*x; add T[i]; no T[i + |P|] * x^|P|; mod p for all
     * <p>
     * Take modular with negative numbers:</br>
     * add p to the result and take modulo p again:
     * <pre>
     * int x = ((a - b) % p + p) % p;
     * instead of
     * int x = (a - b) % p;
     *
     * int x = ((a * b - c) % p + p) % p;
     * instead of
     * int x = (a * b - c) % p;
     * </pre>
     *
     * @param text
     * @param pLength
     * @param prime
     * @param multiplier
     * @return
     */
    private static int[] precomputeHashes(
            String text,
            int pLength,
            int prime,
            int multiplier) {
        // Build array of hashes for all substrings;
        // i=0...T-P -> T-P+1 hashes in total
        int tLength = text.length();
        int[] hashes = new int[tLength - pLength + 1];
        // Compute the last substring's hash value
        hashes[tLength - pLength] = hash(text.substring(tLength - pLength));
        // Generate x^|P| beforehand
        int y = 1;
        for (int i = 1; i <= pLength; i++) {
            // BZ: why must % p?
            // Integer overflow: take (mod p) as soon as possible
            y = (y * multiplier) % prime;
        }
        // Polynomial hashes from |T|-|P|-1 down to 0
        for (int i = tLength - pLength - 1; i >= 0; i--) {
            int subtraction = text.charAt(i) - y * text.charAt(i + pLength);
            // H[i+1]*x; add T[i]; no T[i+|P|] * x^|P|
            hashes[i] = ((hashes[i + 1] * multiplier) % prime +
                    // BZ: (text.charAt(i) - y * text.charAt(i + pLength))?
                    // subtraction + modular:
                    // int x = ((a - b) % p + p) % p;
                    (subtraction % prime + prime) % prime) % prime;
        }
        return hashes;
    }

    private static int nextPrime(int n) {
        int prime = n + 1;
        while (! isPrime(prime)) prime++;
        return prime;
    }
    private static boolean isPrime(int n) {
        if (n <= 1 || n % 2 == 0) return false;
        if (n == 2 || n == 3) return true;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private static int hash(String str) {
        long hashval = 0;  // Prevent integer overflow
        for (int i = str.length() - 1; i >= 0; i--) {
            hashval = (str.charAt(i) + hashval * multiplier) % prime;
        }
        return (int) hashval;
    }
    private static String generateRandomString(int size) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append((char) (random.nextInt(75) + '0'));
        }
        return sb.toString();
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
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