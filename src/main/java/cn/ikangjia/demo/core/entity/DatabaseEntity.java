package cn.ikangjia.demo.core.entity;

import lombok.Data;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/7/16 11:01
 */
@Data
public class DatabaseEntity {

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 字符集
     */
    private String characterSet;

    /**
     * 排序规则、校验规则
     */
    private String collation;

    /**
     * 是否判断数据库已存在，默认为 true，即需要判断
     */
    private boolean judgedExistence = true;
}
