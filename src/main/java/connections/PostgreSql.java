package connections;

public class PostgreSql extends Connection {
    protected String dbName;

    public PostgreSql(String host, String port, String username, String password, String dbName) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.dbName = dbName;
    }

    @Override
    public String getDbName() {
        return dbName;
    }
}
