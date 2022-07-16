package cn.ikangjia.demo.manager.impl;

import cn.ikangjia.demo.manager.DatabaseManager;
import cn.ikangjia.demo.core.entity.DatabaseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/7/16 12:22
 */
@Service
public class DatabaseManagerImpl implements DatabaseManager {
    @Override
    public List<DatabaseEntity> findDatabases(Long dataSourceId) {
        return null;
    }

    @Override
    public String findDatabaseCreate(Long dataSourceId, String dbName) {
        return null;
    }

    @Override
    public boolean createDatabase(Long dataSourceId, DatabaseEntity databaseEntity) {
        return false;
    }

    @Override
    public boolean dropDatabase(Long dataSourceId, String dbName, boolean judgeExistence) {
        return false;
    }

    @Override
    public boolean alterDatabase(Long dataSourceId, DatabaseEntity databaseEntity) {
        return false;
    }
}
