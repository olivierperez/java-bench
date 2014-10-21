package fr.expdev.bench.bench.serialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import fr.expdev.bench.bench.Benchmarkable;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Olivier PEREZ
 */
public class JacksonBench extends Benchmarkable {

    private ObjectMapper mapper;
    private POJO pojo;

    @Override
    public boolean beforeAll() {
        mapper = new ObjectMapper();
        pojo = new POJO();
        return true;
    }

    @Override
    public void execute(long id) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            mapper.writeValue(baos, pojo);
            //System.out.println("jackson: " + baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
