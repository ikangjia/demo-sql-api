package cn.ikangjia.demo.service;

import cn.ikangjia.demo.api.model.dto.TreeLevel3DTO;
import cn.ikangjia.demo.api.model.vo.TreeVO;
import cn.ikangjia.demo.core.entity.DatabaseEntity;

import java.util.List;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/7/18 16:21
 */
public interface DatabaseService {
    List<TreeVO> listDatabases(Long dataSourceId);
    List<TreeVO> listTreeLevel2(TreeVO parentNode);
    List<TreeVO> listTreeLevel3(TreeLevel3DTO level3DTO);

    String getDatabaseCreate(Long dataSourceId, String dbName);

    boolean createDatabase(Long dataSourceId, DatabaseEntity databaseEntity);

    boolean dropDatabase(Long dataSourceId, String dbName, boolean judgeExistence);

    boolean alterDatabase(Long dataSourceId, DatabaseEntity databaseEntity);

}
