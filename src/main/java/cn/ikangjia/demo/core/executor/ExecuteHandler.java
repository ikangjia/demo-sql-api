package cn.ikangjia.demo.core.executor;

import cn.ikangjia.demo.core.DbUtil;
import cn.ikangjia.demo.core.entity.DataSourceEntity;
import cn.ikangjia.demo.core.exception.DMSException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;

/**
 * 执行 sql
 *
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/7/18 13:39
 */
public class ExecuteHandler {

    private final Connection connection;

    public ExecuteHandler(DataSourceEntity dataSourceEntity) {
        this.connection = DbUtil.getConnection(dataSourceEntity);
    }

    private Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    private PreparedStatement getPreparedStatement(String sql, List<String> params) throws SQLException {
        PreparedStatement p = connection.prepareStatement(sql);
        if (Objects.isNull(params)) {
            return p;
        }
        for (int i = 0; i < params.size(); i++) {
            p.setObject(i + 1, params.get(i));
        }
        return p;
    }

    public void execute(String sql) {
        try (Statement statement = this.getStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new DMSException(e.getMessage());
        }
    }

    public List<Map<String, Object>> executeQuery(String sql) {
        try (Statement statement = this.getStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            return this.doMapResult(rs);
        } catch (SQLException e) {
            throw new DMSException(e.getMessage());
        }
    }

    public <T> List<T> executeQuery(String sql, Class<T> t) {
        try (Statement statement = this.getStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            return this.doObjectResult(rs, t);
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new DMSException(e.getMessage());
        }
    }

    private List<Map<String, Object>> doMapResult(ResultSet rs) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        // 逻辑
        while (rs.next()) {
            Map<String, Object> row = new LinkedHashMap<>(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                row.put(metaData.getColumnLabel(i), rs.getObject(i));
            }
            result.add(row);
        }

        // 如果没有结果，自动构建一行返回值
        if (result.isEmpty()) {
            Map<String, Object> row = new LinkedHashMap<>(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                row.put(metaData.getColumnLabel(i), null);
            }
            result.add(row);
        }
        return result;
    }

    private <T> List<T> doObjectResult(ResultSet rs, Class<T> t) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<T> result = new ArrayList<>();

        // 先获取所有的列名称
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<String> columnNameList = new ArrayList<>(columnCount);
        for (int i = 1; i <=columnCount ; i++) {
            columnNameList.add(metaData.getColumnLabel(i));
        }

        // 遍历结果集，赋值
        while (rs.next()) {
            T instance = t.getDeclaredConstructor().newInstance();
            for (Field field : t.getDeclaredFields()) {
                field.setAccessible(true);
                /*
                  要注意：
                        下文将默认认为数据库查询结果集里的列名称与类里的属性名称一致，均为 Java 变量定义的驼峰命名方式。
                        这一前提需要在原始 sql 语句中使用 as 关键字作自动名称转换。
                        如不按照上述做法，可能需要一个类似于 "field2ColumnName()" 的工具方法，且定制化需求频繁，复杂度将大大增加。
                 */
                if (columnNameList.contains(field.getName())) {
                    field.set(instance, rs.getObject(field.getName()));
                }
            }
            result.add(instance);
        }
        return result;
    }

    /**
     * 处理单列的结果集
     *
     * @param rs 结果集
     * @return 列信息
     * @throws SQLException 异常信息
     */
    private List<String> doStringResult(ResultSet rs) throws SQLException {
        List<String> result = new ArrayList<>();
        while (rs.next()) {
            result.add(rs.getString(1));
        }
        return result;
    }
}
