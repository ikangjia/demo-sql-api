package cn.ikangjia.demo.service;

import cn.ikangjia.demo.api.model.dto.DataSourceDTO;
import cn.ikangjia.demo.api.model.dto.DataSourceListDTO;
import cn.ikangjia.demo.api.model.dto.DataSourceQuery;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/11 10:58
 */
public interface DataSourceService {

    Integer saveDataSource(DataSourceDTO dataSourceDTO);

    Integer removeDataSource(Long id);

    Integer updateDataSource(DataSourceDTO dataSourceDTO);

    DataSourceDTO getDataSource(Long id);

    DataSourceListDTO listDataSources(DataSourceQuery dataSourceQuery);

    /**
     * 测试该数据源的连通性
     *
     * @param dataSourceDTO 数据源信息
     * @return 是否可连接
     */
    boolean testConnection(DataSourceDTO dataSourceDTO);

}
