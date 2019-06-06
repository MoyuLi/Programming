import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
public class Percolation {
    private static boolean[][] grid;
    private static int numberOfOpenSites;
    private int N;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF uf;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        grid = new boolean[n + 1][n + 1];
        numberOfOpenSites = n * n;
        N = n;
        top = 0;
        bottom = n * n + 1;
        uf = new WeightedQuickUnionUF(n * n + 2);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > N || col <= 0 || col > N) {
            throw new IllegalArgumentException("not in range");
        }
        grid[row - 1][col - 1] = true;
        numberOfOpenSites++;

        if (row == 1) {
            uf.union(top, getIndex(row, col));
        }
        if (row == N) {
            uf.union(bottom, getIndex(row, col));
        }
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(getIndex(row, col), getIndex(row - 1, col));
        }
        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(getIndex(row, col), getIndex(row, col - 1));
        }
        if (row < N && isOpen(row + 1, col)) {
            uf.union(getIndex(row, col), getIndex(row + 1, col));
        }
        if (col < N && isOpen(row, col + 1)) {
            uf.union(getIndex(row, col), getIndex(row, col + 1));
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > N || col <= 0 || col > N) {
            throw new IllegalArgumentException("not in range");
        }
        return !grid[row - 1][col - 1];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > N || col <= 0 || col > N) {
            throw new IllegalArgumentException("not in range");
        }
        return grid[row - 1][col - 1];
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    private int getIndex(int row, int col) {
        return (row - 1) * N + col;

    }

    public static void main(String[] args) {

    }
}
