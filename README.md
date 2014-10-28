java-bench
==========

Tools to bench and compare different codes producing the same result.

# How-to
## Implements the Benchmarkable interface
You have to implements Benchamrkable for each code you want to bench.

    public class Slow extends Benchmarkable {
        @Override
        public void execute(long id) {
            int a = 0;
            for (int i = 0; i < 1000; i++) {
                a += i;
            }
        }
    }


## Declares what Benchmarkable to bench
In the Main class, you have to call benchmarker.addBenchmarkable for each Benchmarkable you want to bench.

__Example:__ _We want to compare execution velocity between Fast and Slow Benchmarkables._

    benchmarker.addBenchmarkable(new Fast());
    benchmarker.addBenchmarkable(new Slow());

## Defines the number of loops
In the Main class, you have to call benchmarker.bench for each test you want to execute.
For each execution the benchmarker will calculate the time elapsed.

__Example:__ _We want to execute benchmarkables 1 time, then 50 times._

    benchmarker.bench(1);
    benchmarker.bench(50);

## Start the benchmark
Just run the main method in the Main class.

Look at the output console.

    ---------------------------------------
    1 loops
        Fast
            ->         1746754 ns
        Slow
            ->         1558736 ns
        Fast (per loop)
            ->         1746754 ns
        Slow (per loop)
            ->         1558736 ns
    ---------------------------------------
    50 loops
        Fast
            ->          316510 ns
        Slow
            ->          967180 ns
        Fast (per loop)
            ->            6330 ns
        Slow (per loop)
            ->           19343 ns
    --------------------------------------------
    Total time: 11283908 ns

# Want something more ?
If you want the Benchmarker do something more, feel free to tell me what you need/want.

You may also [fork](https://github.com/olivierperez/java-bench/fork) this repository, do the job in your own fork, and ask a pull request.
