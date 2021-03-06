package cn.ikangjia.demo.service.impl;

import cn.ikangjia.demo.api.model.dto.TreeLevel3DTO;
import cn.ikangjia.demo.api.model.vo.TreeVO;
import cn.ikangjia.demo.core.entity.DataSourceEntity;
import cn.ikangjia.demo.core.entity.DatabaseEntity;
import cn.ikangjia.demo.core.mysql.MySQLDatabaseUtil;
import cn.ikangjia.demo.core.mysql.MySQLTableUtil;
import cn.ikangjia.demo.domain.mapper.DataSourceMapper;
import cn.ikangjia.demo.service.DatabaseService;
import cn.ikangjia.demo.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/7/18 16:29
 */
@Slf4j
@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    private DataSourceMapper dataSourceMapper;

    @Override
    public List<TreeVO> listDatabases(Long dataSourceId) {
        List<DatabaseEntity> entityList = MySQLDatabaseUtil.listDatabases(init(dataSourceId));
        return TreeUtil.getTreeLevel1(entityList.stream()
                .map(DatabaseEntity::getDbName)
                .collect(Collectors.toList()));
    }

    @Override
    public List<TreeVO> listTreeLevel2(TreeVO parentNode) {
        return TreeUtil.getTreeLevel2(parentNode);
    }

    @Override
    public List<TreeVO> listTreeLevel3(TreeLevel3DTO level3DTO) {
        String dataSourceId = level3DTO.getDataSourceId();
        TreeVO rootNode = level3DTO.getRootNode();
        TreeVO parentNode = level3DTO.getParentNode();
        Integer type = level3DTO.getType();

        DataSourceEntity dataSourceEntity = init(Long.valueOf(dataSourceId));
        dataSourceEntity.setDbName(rootNode.getLabel());

        List<TreeVO> treeLevel3;

        switch (type) {
            case 0:
                List<String> tableNameList = MySQLTableUtil.listTables(dataSourceEntity);
                treeLevel3 = TreeUtil.getTreeLevel3Tables(tableNameList, parentNode, rootNode);
                break;
            case 1:
            case 2:
            case 3:
            default:
                List<String> tableNameList1 = MySQLTableUtil.listTables(dataSourceEntity);
                treeLevel3 = TreeUtil.getTreeLevel3Tables(tableNameList1, parentNode, rootNode);
                break;
        }
        return treeLevel3;
    }

    @Override
    public String getDatabaseCreate(Long dataSourceId, String dbName) {
        return MySQLDatabaseUtil.getDatabaseCreate(init(dataSourceId), dbName);
    }

    @Override
    public boolean createDatabase(Long dataSourceId, DatabaseEntity databaseEntity) {
        return MySQLDatabaseUtil.createDatabase(init(dataSourceId), databaseEntity);
    }

    @Override
    public boolean dropDatabase(Long dataSourceId, String dbName, boolean judgeExistence) {
        return MySQLDatabaseUtil.dropDatabase(init(dataSourceId), dbName, true);
    }

    @Override
    public boolean alterDatabase(Long dataSourceId, DatabaseEntity databaseEntity) {
        return MySQLDatabaseUtil.alterDatabase(init(dataSourceId), databaseEntity);
    }

    /**
     * ??????????????? id ?????????????????????
     *
     * @param dataSourceId ????????? id
     * @return ???????????????
     */
    private DataSourceEntity init(Long dataSourceId) {
        DataSourceEntity dataSourceEntity = new DataSourceEntity();
        BeanUtils.copyProperties(dataSourceMapper.selectById(dataSourceId), dataSourceEntity);
        return dataSourceEntity;
    }
}
