package fr.expdev.bench;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
import fr.expdev.bench.bench.benchmarker.Benchmarker;
import fr.expdev.bench.bench.benchmarker.ThreadMode;
import fr.expdev.bench.bench.demo.Fast;
import fr.expdev.bench.bench.demo.Slow;
import fr.expdev.bench.bench.mysql.engine.InsertInnodb;
import fr.expdev.bench.bench.mysql.engine.InsertMyisam;

/**
 * @author Olivier PEREZ
 */
public class Main {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Benchmarker benchmarker = new Benchmarker(ThreadMode.BY_BENCHMARKABLE);

        benchmarker.addBenchmarkable(new Fast());
        benchmarker.addBenchmarkable(new Slow());

        benchmarker.bench(1);
        benchmarker.bench(50);

        display(benchmarker.stop());

        System.out.println("--------------------------------------------");
        System.out.println("Total time: " + benchmarker.totalTime() + " ns");
    }

    private static void display(Map<Long, Map<Class, Long>> results) throws IOException {
        for (Map.Entry<Long, Map<Class, Long>> entries : results.entrySet()) {
            System.out.println("---------------------------------------");
            System.out.println(entries.getKey() + " loops");
            for (Map.Entry<Class, Long> entry : entries.getValue().entrySet()) {
                Class clazz = entry.getKey();
                String nsTotal = String.valueOf(entry.getValue());

                System.out.println("    " + clazz.getSimpleName());
                System.out.println("        -> " + StringUtils.leftPad(nsTotal, 15, " ") + " ns");
            }

            for (Map.Entry<Class, Long> entry : entries.getValue().entrySet()) {
                Class clazz = entry.getKey();
                String nsPerLoop = String.valueOf(entry.getValue() / entries.getKey());

                System.out.println("    " + clazz.getSimpleName() + " (per loop)");
                System.out.println("        -> " + StringUtils.leftPad(nsPerLoop, 15, " ") + " ns");
            }
        }
    }

}
