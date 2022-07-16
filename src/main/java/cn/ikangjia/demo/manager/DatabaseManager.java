package cn.ikangjia.demo.manager;

import cn.ikangjia.demo.core.entity.DatabaseEntity;

import java.util.List;

/**
 * 数据库管理接口类
 *
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/7/16 11:59
 */
public interface DatabaseManager {

    List<DatabaseEntity> findDatabases(Long dataSourceId);

    String findDatabaseCreate(Long dataSourceId, String dbName);

    boolean createDatabase(Long dataSourceId, DatabaseEntity databaseEntity);

    boolean dropDatabase(Long dataSourceId, String dbName, boolean judgeExistence);

    boolean alterDatabase(Long dataSourceId, DatabaseEntity databaseEntity);

}
