package connections;

abstract public class Connection implements IConnection {
    protected String host;
    protected String port;
    protected String username;
    protected String password;

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
