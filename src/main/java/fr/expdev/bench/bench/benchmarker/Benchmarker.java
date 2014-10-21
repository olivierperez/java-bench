package fr.expdev.bench.bench.benchmarker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.tuple.Pair;
import fr.expdev.bench.bench.Benchmarkable;

/**
 * @author Olivier PEREZ
 */
public class Benchmarker {

    public static final Comparator<Class> CLASS_COMPARATOR = (o1, o2) -> o1.getSimpleName().compareTo(o2.getSimpleName());

    private final List<Benchmarkable> benchmarkables = new ArrayList<>();

    private final Map<Long, Map<Class, Long>> results = new TreeMap<>();

    private final ExecutorService executorService;

    private long end = 0;
    private long start = 0;

    public Benchmarker(ThreadMode mode) {
        executorService = buildExecutorForMode(mode);
    }

    private ExecutorService buildExecutorForMode(ThreadMode mode) {
        if (mode == ThreadMode.BY_BENCHMARKABLE) {
            return Executors.newFixedThreadPool(2);
        } else {
            return Executors.newSingleThreadExecutor();
        }
    }

    public void addBenchmarkable(Benchmarkable benchmarkable) {
        benchmarkables.add(benchmarkable);
    }

    public void bench(long loops) throws ExecutionException, InterruptedException {
        if (start == 0) {
            start = System.nanoTime();
        }

        Map<Class, Long> result = new TreeMap<>(CLASS_COMPARATOR);
        List<Future<Pair<Benchmarkable, Long>>> futures = new ArrayList<>(benchmarkables.size());

        // Start benching
        for (Benchmarkable benchmarkable : benchmarkables) {
            Looper looper = new Looper(benchmarkable, loops);
            futures.add(executorService.submit(looper));
        }

        // Retrieve bench results
        for (Future<Pair<Benchmarkable, Long>> future : futures) {
            Pair<Benchmarkable, Long> pair = future.get();
            if (pair != null) {
                result.put(pair.getKey().getClass(), pair.getValue());
            }
        }

        results.put(loops, result);
    }

    public Map<Long, Map<Class, Long>> stop() {
        end = System.nanoTime();
        executorService.shutdownNow();
        return results;
    }

    public long totalTime() {
        return end - start;
    }
}
