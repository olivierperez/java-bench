package fr.expdev.bench.bench.serialization;

import java.io.ByteArrayOutputStream;

import fr.expdev.bench.bench.Benchmarkable;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.JavaSerializer;

/**
 * @author Olivier PEREZ
 */
public class KryoBench extends Benchmarkable {

    private Kryo kryo;
    private POJO pojo;

    @Override
    public boolean beforeAll() {
        kryo = new Kryo();
        kryo.register(POJO.class, new JavaSerializer());
        pojo = new POJO();
        return true;
    }

    @Override
    public void execute(long id) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, pojo);
        output.close();

        //System.out.println("kryo: " + baos.toString());
    }
}
