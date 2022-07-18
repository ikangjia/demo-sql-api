package cn.ikangjia.demo.core;

import cn.ikangjia.demo.core.entity.DataSourceEntity;
import cn.ikangjia.demo.core.exception.DMSException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/7/18 15:35
 */
@Slf4j
public class DbUtil {
    public static Connection getConnection(DataSourceEntity dataSourceEntity) {
        String username = dataSourceEntity.getUsername();
        String password = dataSourceEntity.getPassword();
        String host = dataSourceEntity.getHost();
        String port = dataSourceEntity.getPort();

        return getConnection(host, port, username, password);
    }

    public static Connection getConnection(String host, String port, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new DMSException("缺少驱动包", e);
        }
        String url = "jdbc:mysql://" + host + ":" + port + "?serverTimezone=GMT%2B8";

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            log.error("创建数据库连接失败！");
            throw new DMSException(e.getMessage());
        }
    }

    public static boolean testConnection(DataSourceEntity dataSourceEntity) {
        if (dataSourceEntity.getType() != 0) {
            throw new DMSException("数据库类型错误");
        }
        try (Connection connection = getConnection(dataSourceEntity);
             Statement statement = connection.createStatement()) {
            return statement.execute("SELECT 1");
        } catch (SQLException e) {
            throw new DMSException(e.getMessage(), e);
        }
    }

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("数据库关闭失败！");
            throw new DMSException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        DataSourceEntity dataSourceEntity = new DataSourceEntity();
        dataSourceEntity.setUsername("root")
                .setPassword("123456")
                .setHost("localhost")
                .setPort("3306");

        System.out.println(testConnection(dataSourceEntity));
    }
}
