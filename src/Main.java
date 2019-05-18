public class Main {
    public static void main(String[] args) {

        System.out.println("Явний метод");
        differential diff = new differential();

        long beginTimeSerial = System.nanoTime();
        double[][] approximateResult = new SerialSol(diff).solve();
        long endTimeSerial = System.nanoTime();
        long serialTime = endTimeSerial - beginTimeSerial;
        printMatrix(approximateResult, diff);
        System.out.println("Время явного решения: " + serialTime);
        System.out.println();


        System.out.println("Паралельное вычисления");
        long beginTimeParallel = System.nanoTime();
        double[][] parallelSolution = new ParallelSol(diff).solve();
        long endTimeParallel = System.nanoTime();
        long parallelTime = endTimeParallel - beginTimeParallel;
        printMatrix(parallelSolution, diff);
        System.out.println("Время паралельного вычисления: " + parallelTime + " ns");

        System.out.println("Точное решения:");
        printErrors(approximateResult, calculateExactResult(diff), diff);
    }

    private static double[][] calculateExactResult(differential diff) {
        double[][] exactMatrix = new double[diff.getTpointsAmount()][diff.getHpointsAmount()];
        double t = diff.getT0();
        for (int i = 0; i < diff.getTpointsAmount(); i++) {
            double x = diff.getX0();
            for (int j = 0; j < diff.getHpointsAmount(); j++) {
                exactMatrix[i][j] = diff.calculateTrueSolution(x, t);
                x += diff.getH();
            }
            t += diff.getTau();
        }

        System.out.println();

        for (int i = 0; i < diff.getTpointsAmount(); ++i) {
            for (int j = 0; j < diff.getHpointsAmount(); ++j) {
                System.out.print(String.format("%.5f\t", exactMatrix[i][j]));
            }
            System.out.println();
        }

        return exactMatrix;
    }

    private static void printMatrix(double[][] matrix, differential diff) {
        for (int i = 0; i < diff.getTpointsAmount(); ++i) {
            for (int j = 0; j < diff.getHpointsAmount(); ++j) {
                System.out.print(String.format("%.5f\t", matrix[i][j]));
            }
            System.out.println();

        }
    }

    private static void printErrors(double[][] approximateResult, double[][] exactMatrix, differential diff) {
        System.out.println("Средняя абсолютная ошибка: " + Errors.mediumAbsoleError(approximateResult, exactMatrix, diff));
        System.out.println("Максимальная Абсолютная Ошибка: " + Errors.maxAbsoleError(approximateResult, exactMatrix, diff));
        System.out.println("Средняя относительная ошибка: " + Errors.mediumRelativeError(approximateResult, exactMatrix, diff));
        System.out.println("Максимальная  Относительная ошибка: " + Errors.maxRelativeError(approximateResult, exactMatrix, diff));
    }











}
