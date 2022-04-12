package connections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MysqlTest {

    @Test
    void getHost() {
        String host = "localhost";
        String port = "port";
        String username = "username";
        String password = "password";
        Mysql connection = new Mysql(host, port, username, password);
        assertEquals(host, connection.getHost());
    }

    @Test
    void getPort() {
        String host = "localhost";
        String port = "port";
        String username = "username";
        String password = "password";
        Mysql connection = new Mysql(host, port, username, password);
        assertEquals(port, connection.getPort());
    }

    @Test
    void getPassword() {
        String host = "localhost";
        String port = "port";
        String username = "username";
        String password = "password";
        Mysql connection = new Mysql(host, port, username, password);
        assertEquals(password, connection.getPassword());
    }

    @Test
    void getDbName() {
        String host = "localhost";
        String port = "port";
        String username = "username";
        String password = "password";
        Mysql connection = new Mysql(host, port, username, password);
        assertEquals(username, connection.getDbName());
    }
}