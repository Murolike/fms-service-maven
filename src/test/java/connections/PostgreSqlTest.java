package connections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostgreSqlTest {

    @Test
    void getHost() {
        String host = "localhost";
        String port = "port";
        String username = "username";
        String password = "password";
        String dbName = "dbName";
        PostgreSql connection = new PostgreSql(host, port, username, password, dbName);
        assertEquals(host, connection.getHost());
    }

    @Test
    void getPort() {
        String host = "localhost";
        String port = "port";
        String username = "username";
        String password = "password";
        String dbName = "dbName";
        PostgreSql connection = new PostgreSql(host, port, username, password, dbName);
        assertEquals(host, connection.getHost());
    }

    @Test
    void getPassword() {
        String host = "localhost";
        String port = "port";
        String username = "username";
        String password = "password";
        String dbName = "dbName";
        PostgreSql connection = new PostgreSql(host, port, username, password, dbName);
        assertEquals(host, connection.getHost());
    }

    @Test
    void getDbName() {
        String host = "localhost";
        String port = "port";
        String username = "username";
        String password = "password";
        String dbName = "dbName";
        PostgreSql connection = new PostgreSql(host, port, username, password, dbName);
        assertEquals(dbName, connection.getDbName());
    }
}