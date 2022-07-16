package cn.ikangjia.demo.core.sqlbuilder.constant;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/7/16 12:12
 */
public class TableSelectConstant {

    /**
     * 查询指定库下所有表
     * 注意：若要使用 show tables;，需要先进行 use database_name; 操作
     */
    public static final String table_show_1 = "select * from information_schema.tables where table_schema = %s;";
    public static final String table_show_2 = "show tables;";
}
