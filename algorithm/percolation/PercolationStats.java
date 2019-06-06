
/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int[] numberOfOpen;
    private int N;
    private int numberOfTrials;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        numberOfOpen = new int[trials];
        N = n;
        numberOfTrials = trials;

        for (int i = 0; i < trials; i++) {
            Percolation pc = new Percolation(n);
            while (!pc.percolates()) {
                pc.open(StdRandom.uniform(n + 1), StdRandom.uniform(n + 1));
            }
            numberOfOpen[i] = pc.numberOfOpenSites();
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(numberOfOpen) / (N * N);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(numberOfOpen) / N;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(numberOfTrials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(numberOfTrials);
    }

    // test client (described below)
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats pStats = new PercolationStats(N, T);

        String confidence = pStats.confidenceLo() + ", "
                + pStats.confidenceHi();
        StdOut.println("mean                    = " + pStats.mean());
        StdOut.println("stddev                  = " + pStats.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}
