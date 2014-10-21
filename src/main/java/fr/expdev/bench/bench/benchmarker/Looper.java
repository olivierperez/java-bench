package fr.expdev.bench.bench.benchmarker;

import java.util.concurrent.Callable;

import org.apache.commons.lang3.tuple.Pair;
import fr.expdev.bench.bench.Benchmarkable;

/**
 * @author Olivier PEREZ
 */
public class Looper implements Callable<Pair<Benchmarkable, Long>> {

    private final Benchmarkable benchmarkable;
    private final long loops;

    public Looper(Benchmarkable benchmarkable, long loops) {
        this.benchmarkable = benchmarkable;
        this.loops = loops;
    }

    @Override
    public Pair<Benchmarkable, Long> call() throws Exception {
        boolean ready = benchmarkable.beforeAll();

        if (ready) {
            long start = System.nanoTime();
            loop(benchmarkable, loops);
            long stop = System.nanoTime();

            benchmarkable.afterAll();

            return Pair.of(benchmarkable, stop - start);
        } else {
            return null;
        }
    }

    private void loop(Benchmarkable benchmarkable, Long loops) {
        System.out.println("Starting " + loops + " loops for " + benchmarkable.getClass().getSimpleName());
        for (long i = 0; i < loops; i++) {
            benchmarkable.execute(i);
        }
    }
}
