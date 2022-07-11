package cn.ikangjia.demo.sql.datasource;

import cn.ikangjia.demo.domain.entity.DataSourceDO;
import cn.ikangjia.demo.sql.exception.DMSException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/11 12:14
 */
public class AbstractJdbcConnection extends AbstractConnection {
    @Override
    public boolean testConnection(DataSourceDO dataSourceDO) {
        String username = dataSourceDO.getUsername();
        String password = dataSourceDO.getPassword();
        String host = dataSourceDO.getHost();
        String port = dataSourceDO.getPort();
        int type = dataSourceDO.getType();

        if (type != 0) {
            throw new DMSException("数据库类型错误");
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new DMSException("缺少驱动包", e);
        }
        String url = "jdbc:mysql://" + host + ":" + port + "?serverTimezone=GMT%2B8";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();) {
            return statement.execute("SELECT 1");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        DataSourceDO dataSourceDO = new DataSourceDO();
        dataSourceDO.setUsername("root")
                .setPassword("123456")
                .setHost("localhost")
                .setPort("3306");

        System.out.println(new AbstractJdbcConnection().testConnection(dataSourceDO));
    }
}
