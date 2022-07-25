import java.util.Scanner;

public class Main {
    /*
    The starter code is supposed to find the sum of two inclusive integer ranges
    in parallel but it’s not getting the correct result. Fix the code so that it
    gives the correct sum.

Example
// Input
2 6
1 4

// Output
30
     */
    private static final long mainThreadId = Thread.currentThread().getId();

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        int start1 = scanner.nextInt();
        int end1 = scanner.nextInt();

        int start2 = scanner.nextInt();
        int end2 = scanner.nextInt();

        RangeAdder adder1 = new RangeAdder(start1, end1);
        RangeAdder adder2 = new RangeAdder(start2, end2);
        System.out.println("hello from the thread!");

        // First burger
        adder1.start(); // Start burger
        adder1.join(); // Wait until burger is done
        long partialSum1 = adder1.getSum(); // Get burger
        System.out.println("paritalSum1 = " + partialSum1);

        // Second burger
        adder2.start(); // Start second burger
        adder2.join(); // Wait until second burger is done
        long partialSum2 = adder2.getSum(); // Get second burger
        System.out.println("paritalSum2 = " + partialSum2);

        long sum = partialSum1 + partialSum2;
        System.out.println("Hello from the after sum!");

        System.out.println(sum);

        scanner.close();
    }

    // DO NOT modify the RangeAdder class
    static class RangeAdder extends Thread {

        int start;
        int end;

        private volatile long sum = 0;

        public RangeAdder(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            final long currentId = Thread.currentThread().getId();

            if (currentId == mainThreadId) {
                throw new RuntimeException("You must start a new thread!");
            }

            long total = 0;
            for (int i = start; i <= end; i++) {
                total += i;
            }

            this.sum = total;
        }

        public long getSum() {
            return sum;
        }
    }
}