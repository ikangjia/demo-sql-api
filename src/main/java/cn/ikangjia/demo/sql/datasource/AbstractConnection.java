package cn.ikangjia.demo.sql.datasource;

import cn.ikangjia.demo.domain.entity.DataSourceDO;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/11 12:11
 */
public abstract class AbstractConnection {

    protected abstract boolean testConnection(DataSourceDO dataSourceDO);

}
