package cn.ikangjia.demo.core.mysql;

import cn.ikangjia.demo.core.entity.DataSourceEntity;
import cn.ikangjia.demo.core.entity.DatabaseEntity;
import cn.ikangjia.demo.core.executor.ExecuteHandler;
import cn.ikangjia.demo.core.sqlbuilder.constant.DatabaseConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/7/18 16:12
 */
public class MySQLDatabaseUtil {

    public static List<DatabaseEntity> listDatabases(DataSourceEntity dataSourceEntity) {
        String sql = DatabaseConstant.DATABASE_SHOW_1;
        ExecuteHandler executeHandler = new ExecuteHandler(dataSourceEntity);
        List<Map<String, Object>> mapList = executeHandler.executeQuery(sql);

        List<DatabaseEntity> result = new ArrayList<>();

        mapList.forEach(map -> {
            DatabaseEntity database = new DatabaseEntity();
            database.setDbName((String) map.get("Database"));
            result.add(database);
        });

        return result;
    }

    public static String getDatabaseCreate(DataSourceEntity dataSourceEntity, String dbName) {
        ExecuteHandler executeHandler = new ExecuteHandler(dataSourceEntity);
        return null;
    }

    public static boolean createDatabase(DataSourceEntity dataSourceEntity, DatabaseEntity databaseEntity) {
        ExecuteHandler executeHandler = new ExecuteHandler(dataSourceEntity);
        return false;
    }

    public static boolean dropDatabase(DataSourceEntity dataSourceEntity, String dbName, boolean judgeExistence) {
        ExecuteHandler executeHandler = new ExecuteHandler(dataSourceEntity);
        return false;
    }

    public static boolean alterDatabase(DataSourceEntity dataSourceEntity, DatabaseEntity databaseEntity) {
        ExecuteHandler executeHandler = new ExecuteHandler(dataSourceEntity);
        return false;
    }
}
