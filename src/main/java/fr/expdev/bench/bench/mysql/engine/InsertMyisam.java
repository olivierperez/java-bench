package fr.expdev.bench.bench.mysql.engine;

/**
 * Table created with :
 * <p>CREATE TABLE User_MYISAM
 * (
 * id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
 * login VARCHAR(100) NOT NULL,
 * firstName VARCHAR(255) NOT NULL,
 * lastName VARCHAR(255) NOT NULL
 * );
 * CREATE UNIQUE INDEX UK_login ON User_MYISAM ( login );
 * </p>
 * @author Olivier PEREZ
 */
public class InsertMyisam extends InsertAbstract {
    @Override
    protected String getTable() {
        return "User_MYISAM";
    }
}
