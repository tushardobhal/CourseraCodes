package dynamic.programming;

import java.util.*;

public class PrimitiveCalculator {
    private static List<Long> optimal_sequence(long n) {
        List<Long> sequence = new ArrayList<Long>();
        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        List<Long> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Long x : sequence) {
            System.out.print(x + " ");
        }
    }
}

