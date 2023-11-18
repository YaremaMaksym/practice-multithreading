package yaremax.main1;

import yaremax.IntegralCalculator;

import java.util.function.DoubleUnaryOperator;

public class RunnableCalculator implements Runnable {

    private final IntegralCalculator integralCalculator;
    private final Main main;

    public RunnableCalculator(double a, double b, int n, DoubleUnaryOperator func, Main main) {
        integralCalculator = new IntegralCalculator(a, b, n, func);
        this.main = main;
    }

    @Override
    public void run() {
        main.sendResult(integralCalculator.calculate());
    }
}
