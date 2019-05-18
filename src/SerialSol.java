
public class SerialSol{

    private int tPointsAmount;
    private int hPointsAmount;
    private double x0;
    private double h;
    private double t0;
    private double tau;
    private differential diff;

    public SerialSol(differential diff) {
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
            for (int j = 1; j < hPointsAmount - 1; j++) {
                w[i][j] = diff.calculateApproximateSolution(w[i-1][j-1],w[i-1][j],w[i-1][j+1]);
            }
            w[i][hPointsAmount - 1] = diff.calculateRight(t);
        }
        return w;
    }
}
