package fr.expdev.bench.bench.demo;

import fr.expdev.bench.bench.Benchmarkable;

/**
 * @author Olivier PEREZ
 */
public class Slow extends Benchmarkable {
    @Override
    public void execute(long id) {
        int a = 0;
        for (int i = 0; i < 1000; i++) {
            a += i;
        }
    }
}
