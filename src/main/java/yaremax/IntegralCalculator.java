package yaremax;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.IntStream;

public class IntegralCalculator {
    private double a;
    private double b;
    private int n;

    private DoubleUnaryOperator func;

    public IntegralCalculator(double a, double b, int n, DoubleUnaryOperator func) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.func = func;
    }

    public double calculate() {
        double h = (b - a)/n;
//        double sum = 0;
//        for (int i = 0; i < n; i++) {
//            double x = a + i * h;
//            sum += func.applyAsDouble(x) * h;
//        }
//        return sum;
        return IntStream.range(0, n)
                .mapToDouble(i -> a + i * h)
                .map(func)
                .map(y -> y * h)
                .sum();
    }
}
