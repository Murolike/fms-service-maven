package connections;

public interface IConnection {
    String getHost();

    String getPort();

    String getUsername();

    String getPassword();

    String getDbName();
}
