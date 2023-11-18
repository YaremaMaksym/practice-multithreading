package yaremax.main2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main2 {
    public static void main(String[] args) {
        double a = 0;
        double b = Math.PI;
        int n = 1_000_000_000;
        int nThreads = 100;
        double delta = (b-a)/nThreads;

        long startTime = System.currentTimeMillis();

        ExecutorService es = Executors.newWorkStealingPool(50);
        List<Future<Double>> futures = new ArrayList<>();
        for (int i = 0; i < nThreads; i++) {
            double ai = a + i * delta;
            double bi = ai + delta;
            futures.add(es.submit(new CallableCalculator(ai, bi, n / nThreads, Math::sin)));
        }
        es.shutdown();

        double total = 0;

        try {
            for (Future<Double> future : futures) {
                total += future.get();
            }

            long finishTime = System.currentTimeMillis();

            System.out.println("res = " + total);
            System.out.println("time = " + (finishTime-startTime) + " ms");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
