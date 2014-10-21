package fr.expdev.bench.bench.mysql.engine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbutils.DbUtils;
import fr.expdev.bench.bench.Benchmarkable;

/**
 * @author Olivier PEREZ
 */
public abstract class InsertAbstract extends Benchmarkable {

    public static final int LASTNAME_INDEX = 3;
    public static final int FIRSTNAME_INDEX = 2;
    public static final int LOGIN_INDEX = 1;

    public static final String HOST = "localhost";
    public static final int PORT = 3306;
    public static final String SCHEMA = "test";
    public static final String USER = "user";
    public static final String PASSWORD = "password";

    private Connection connection;
    private PreparedStatement preparedInsertion;
    private int count;

    protected abstract String getTable();

    @Override
    public boolean beforeAll() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + SCHEMA + "?user=" + USER + "&password=" + PASSWORD);
            // Drop data
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE " + getTable());
            DbUtils.closeQuietly(statement);

            // Prepare engine statement
            preparedInsertion = connection.prepareStatement("INSERT INTO " + getTable() + " (login, firstName, lastName) VALUES (?,?,?)");
            count = 0;
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void execute(long id) {
        count += 1;
        try {
            preparedInsertion.setString(LOGIN_INDEX, "gcc " + count);
            preparedInsertion.setString(FIRSTNAME_INDEX, "firstname");
            preparedInsertion.setString(LASTNAME_INDEX, "lastname");
            preparedInsertion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterAll() {
        DbUtils.closeQuietly(preparedInsertion);
        DbUtils.closeQuietly(connection);
    }
}
