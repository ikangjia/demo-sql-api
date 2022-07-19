package cn.ikangjia.demo.core.sqlbuilder.constant;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/7/16 11:03
 */
public class DatabaseConstant {
    private DatabaseConstant() {
    }

    public static final String DATABASE_USE = "use %s;";

    /**
     * 创建数据库
     * <p>
     * CREATE DATABASE [IF NOT EXISTS] <数据库名>
     * [[DEFAULT] CHARACTER SET <字符集名>] [[DEFAULT] COLLATE <校对规则名>];
     * </p>
     * IF NOT EXISTS：在创建数据库之前进行判断，只有该数据库目前尚不存在时才能执行操作。此选项可以用来避免数据库已经存在而重复创建的错误。
     * [DEFAULT] CHARACTER SET：指定数据库的默认字符集。 default character set utf8
     * [DEFAULT] COLLATE：指定字符集的默认校对规则。 default collate utf8_chinese_ci;
     */
    public static final String DATABASE_CREATE_1 = "create database %s";
    public static final String DATABASE_CREATE_2 = "create database %s if not exists";
    public static final String DATABASE_CREATE_3 = "create database %s if not exists " +
            "default character set %s " +
            "default collate $s;";

    /**
     * 显示建库语句
     */
    public static final String DATABASE_CREATE_SHOW = "show create database %s;";

    /**
     * 修改数据库
     * MySQL 数据库中只能对数据库使用的字符集和校对规则进行修改，数据库的这些特性都储存在 db.opt 文件中。
     * ALTER DATABASE [数据库名] {
     * [ DEFAULT ] CHARACTER SET <字符集名> |
     * [ DEFAULT ] COLLATE <校对规则名>}
     */
    public static final String DATABASE_ALTER = "alter database %s " +
            "default character set %s " +
            "default collate %s;";

    /**
     * 查询所有数据库
     */
    public static final String DATABASE_SHOW_1 = "show databases;";
    public static final String DATABASE_SHOW_2 = "show databases like %s;";
    public static final String DATABASE_SHOW_3 = "SELECT schema_name FROM information_schema.schemata;";


    /**
     * 删除数据库
     * DROP DATABASE [ IF EXISTS ] <数据库名>
     */
    public static final String DATABASE_DROP_1 = "drop database %s;";
    public static final String DATABASE_DROP_2 = "drop database if exists %s;";

}
