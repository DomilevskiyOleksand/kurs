public class differential {

        private final double x0 = 0;
        private final double x1 = 1;
        private final double h = 1.0/30;//шаг по х
        private final double t0 = 0;
        private final double t1 = 1;
        private final double tau = 1.0/900;//шаг по времени
        private final double a = 0.2;
        private final double b = 0.005;
        private final double c1 = 1.0;//конст с точного
        private final double alt = 0.005;//конст с точно



        public double getX0() {
            return x0;
        }

        public double getH() {
            return h;
        }

        public double getT0() {
            return t0;
        }

        public double getTau() {
            return tau;
        }

        public int getTpointsAmount() {
            return (int) Math.ceil((t1 - t0) / tau) + 1;
        }

        public int getHpointsAmount() {
            return (int) Math.ceil((x1 - x0) / h) + 1;
        }

        public double calculateTrueSolution(double x, double t) {
            return  (1/Math.sqrt(c1*Math.exp(-2*alt/a*(x+alt*t))-b/(3*alt)));
        }

        public double calculateBottom(double x) {
            double t = 0;
            return  (1/Math.sqrt(c1*Math.exp(-2*alt/a*(x+alt*t))-b/(3*alt)));
        }

        public double calculateLeft(double t) {
            double x = 0;
            return  (1/Math.sqrt(c1*Math.exp(-2*alt/a*(x+alt*t))-b/(3*alt)));
        }

        public double calculateRight(double t) {
            double x = 1;
            return  (1/Math.sqrt(c1*Math.exp(-2*alt/a*(x+alt*t))-b/(3*alt)));
        }

        public double calculateApproximateSolution(double wiLeft, double wiCurrent, double wiRight) {
            return (wiCurrent + (a*(wiRight -2* wiCurrent + wiLeft)/Math.pow(h,2) + Math.pow(wiCurrent,2)*(wiRight - wiLeft)*b/2/h)*tau);//для приблизительного
        }


    }
