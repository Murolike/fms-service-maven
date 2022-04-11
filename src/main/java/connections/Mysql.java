package connections;

public class Mysql extends Connection {

    public Mysql(String host, String port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    @Override
    public String getDbName() {
        return getUsername();
    }
}
