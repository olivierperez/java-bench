package fr.expdev.bench.bench;

/**
 * @author Olivier PEREZ
 */
public abstract class Benchmarkable {

    /**
     * This method is called on time before looping.
     * @return {@code true} is the Benchmarkable is ready to be benched.
     */
    public boolean beforeAll(){return true;}

    /**
     * Do one execution of the test, it's call one time per loop.
     * @param id The id
     */
    public abstract void execute(long id);

    /**
     * This method is called on time after looping.
     */
    public void afterAll(){}
}
