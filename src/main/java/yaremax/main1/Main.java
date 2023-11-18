package yaremax.main1;

public class Main {

    private double totalResult;
    private int finishedThreads;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run(){
        double a = 0;
        double b = Math.PI;
        int n = 1_000_000_000;

        long startTime = System.currentTimeMillis();
        IntegralCalculator calculator = new IntegralCalculator(a, b, n, Math::sin);
        double res = calculator.calculate();
        long finishTime = System.currentTimeMillis();

        System.out.println("res = " + res);
        System.out.println("time = " + (finishTime-startTime) + " ms");
    }

    public void run2() {
        double a = 0;
        double b = Math.PI;
        int n = 1_000_000_000;
        int nThreads = 10;
        totalResult = 0;
        finishedThreads = 0;
        double delta = (b - a) / nThreads;

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < nThreads; i++) {
            double ai = a + i * delta;
            double bi = ai + delta;
            new Thread(new RunnableCalculator(ai, bi, n/nThreads, Math::sin, this)).start();
        }

        try {
            synchronized (this) {
                while (finishedThreads < nThreads) {
                    wait();
                }
            }
            long finishTime = System.currentTimeMillis();
            System.out.println("res = " + totalResult);
            System.out.println("time = " + (finishTime-startTime) + " ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendResult(double result) {
        totalResult += result;
        finishedThreads++;
        notify();
    }
}