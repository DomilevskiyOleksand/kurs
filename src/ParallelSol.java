
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ParallelSol {

    private int tPointsAmount;
    private int hPointsAmount;
    private double x0;
    private double h;
    private double t0;
    private double tau;
    private differential diff;

    public ParallelSol(differential diff) {
        this.diff = diff;
        this.tPointsAmount = diff.getTpointsAmount();
        this.hPointsAmount = diff.getHpointsAmount();
        this.x0 = diff.getX0();
        this.h = diff.getH();
        this.t0 = diff.getT0();
        this.tau = diff.getTau();
    }

    public double[][] solve() {
        double t = t0 + tau;
        double x = x0;
        double[][] w = new double[tPointsAmount][hPointsAmount];
        for (int j = 0; j < hPointsAmount; j++, x += h) {
            w[0][j] = diff.calculateBottom(x);
        }

        for (int i = 1; i < tPointsAmount; ++i, t += tau) {
            w[i][0] = diff.calculateLeft(t);

            AtomicInteger valueI = new AtomicInteger(i);

            IntStream.range(1, diff.getHpointsAmount() - 1).parallel().forEach(j -> {
                int k = valueI.get();
//нашое
                w[k][j] = diff.calculateApproximateSolution(w[k - 1][j - 1], w[k - 1][j], w[k - 1][j + 1]);

            });

            w[i][hPointsAmount - 1] = diff.calculateRight(t);
        }
        return w;
    }

}
