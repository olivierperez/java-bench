package fr.expdev.bench.bench.serialization;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Olivier PEREZ
 */
@Data
public class POJO implements Serializable {
    private int id;
    private String firstname;
    private String lastname;
    private long tooLong;
}
