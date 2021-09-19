package mydatas.tx.config;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public Connection getThreadConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection == null) {
            connection = dataSource.getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }


    public void removeConnection(){
        threadLocal.remove();
    }









}
