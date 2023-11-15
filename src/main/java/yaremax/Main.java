package yaremax;

public class Main {
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
}