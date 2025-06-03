package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据源工具类，提供获取数据库连接池和连接的功能
 * 使用 C3P0 数据库连接池管理数据库连接
 */
public class DataSourceUtils {

    // 使用 C3P0 数据库连接池进行管理
    private static DataSource ds = new ComboPooledDataSource();

    /**
     * 获取数据源对象，用于数据库连接池管理
     *
     * @return DataSource 数据源对象
     */
    public static DataSource getDataSource() {
        return ds;
    }

    /**
     * 获取数据库连接
     *
     * @return Connection 数据库连接
     * @throws SQLException 如果获取连接时发生错误
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
