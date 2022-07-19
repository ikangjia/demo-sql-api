package cn.ikangjia.demo.core.mysql;

import cn.ikangjia.demo.core.entity.DataSourceEntity;
import cn.ikangjia.demo.core.executor.ExecuteHandler;
import cn.ikangjia.demo.core.sqlbuilder.constant.TableSelectConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/7/19 10:30
 */
public class MySQLTableUtil {
    public static List<String> listTables(DataSourceEntity dataSourceEntity) {
        ExecuteHandler executeHandler = new ExecuteHandler(dataSourceEntity);

        String sql = String.format(TableSelectConstant.table_show_1, dataSourceEntity.getDbName());
        List<Map<String, Object>> mapList = executeHandler.executeQuery(sql);


        List<String> result = new ArrayList<>();

        mapList.forEach(map -> {
            result.add((String) map.get("tableName"));
        });

        return result;
    }
}
