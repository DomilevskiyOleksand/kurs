public class Errors {
    public static double mediumAbsoleError(double[][] approximateResult, double[][] exactMatrix, differential diff) {
        double[][] matrix = new double[diff.getTpointsAmount()][diff.getHpointsAmount()];
        double error = 0;
        double errorResult = 0;

        for (int i = 0; i < diff.getTpointsAmount(); i++) {
            for (int j = 0; j < diff.getHpointsAmount(); j++) {
                matrix[i][j] = Math.abs(approximateResult[i][j] - exactMatrix[i][j]);
            }
        }


        for (int i = 0; i < diff.getTpointsAmount(); i++) {
            for (int j = 0; j < diff.getHpointsAmount(); j++) {
                error += matrix[i][j];
            }
        }
        errorResult = error / (diff.getTpointsAmount() * diff.getHpointsAmount());

        return errorResult;
    }
    public static double maxAbsoleError(double[][] approximateResult, double[][] exactMatrix, differential diff) {
        double[][] matrix = new double[diff.getTpointsAmount()][diff.getHpointsAmount()];
        double maxError = 0;

        for (int i = 0; i < diff.getTpointsAmount(); i++) {
            for (int j = 0; j < diff.getHpointsAmount(); j++) {
                matrix[i][j] = Math.abs(approximateResult[i][j] - exactMatrix[i][j]);
            }
        }

        maxError = matrix[0][0];
        for (int i = 0; i < diff.getTpointsAmount(); i++) {
            for (int j = 0; j < diff.getHpointsAmount(); j++) {
                if (maxError < matrix[i][j]) {
                    maxError = matrix[i][j];
                }
            }
        }


        return maxError;
    }
    public static double mediumRelativeError(double[][] approximateResult, double[][] exactMatrix, differential diff) {
        double[][] matrix = new double[diff.getTpointsAmount()][diff.getHpointsAmount()];
        double error = 0;
        double errorResult = 0;

        for (int i = 0; i < diff.getTpointsAmount(); i++) {
            for (int j = 0; j < diff.getHpointsAmount(); j++) {
                if (exactMatrix[i][j] == 0) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = (Math.abs(approximateResult[i][j] - exactMatrix[i][j]) / exactMatrix[i][j]) * 100;
                }
                error += matrix[i][j];
            }
        }


        errorResult = error / (diff.getTpointsAmount() * diff.getHpointsAmount());

        return errorResult;
    }
    public static double maxRelativeError(double[][] approximateResult, double[][] exactMatrix, differential diff) {
        double[][] matrix = new double[diff.getTpointsAmount()][diff.getHpointsAmount()];
        double maxError = 0;

        for (int i = 0; i < diff.getTpointsAmount(); i++) {
            for (int j = 0; j < diff.getHpointsAmount(); j++) {
                if (exactMatrix[i][j] == 0) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = (Math.abs(approximateResult[i][j] - exactMatrix[i][j]) / exactMatrix[i][j]) * 100;
                }
            }
        }

        maxError = matrix[0][0];
        for (int i = 0; i < diff.getTpointsAmount(); i++) {
            for (int j = 0; j < diff.getHpointsAmount(); j++) {
                if (maxError < matrix[i][j]) {
                    maxError = matrix[i][j];
                }
            }
        }


        return maxError;
    }

}
