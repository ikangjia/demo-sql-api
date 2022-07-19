package cn.ikangjia.demo.util;

import cn.ikangjia.demo.api.model.vo.TreeVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/7/18 17:03
 */
public class TreeUtil {

    /*
        树的第一层级，%s 指的是数据库名称
        1-d#db_sql
     */
    private static final String LEVEL_1_DB_ID = "1-d#%s";

    /*
        树的第二层级，对应为表、视图、存储过程、函数的目录。 %s 为库名称
        2-t#db_sql
     */
    private static final String LEVEL_2_TABLES_ID = "2-t#%s";
    private static final String LEVEL_2_VIEWS_ID = "2-v#%s";
    private static final String LEVEL_2_PROCEDURES_ID = "2-p#%s";
    private static final String LEVEL_2_FUNCTIONS_ID = "2-f#%s";

    /*
        树的第三层级。第一个 %s 为库名。第二个 %s 为 具体的表名、视图名、存储过程、函数名
        3-t#db_sql#t_user
     */
    private static final String LEVEL_3_TABLE_ID = "3-t#%s#%s";
    private static final String LEVEL_3_VIEW_ID = "3-v#%s#%s";
    private static final String LEVEL_3_PROCEDURE_ID = "3-p#%s#%s";
    private static final String LEVEL_3_FUNCTION_ID = "3-f#%s#%s";

    /**
     * 构造树的第一层级，即数据库名称
     *
     * @param dbNameList 数据库名称列表
     * @return 结果
     */
    public static List<TreeVO> getTreeLevel1(List<String> dbNameList) {
        List<TreeVO> result = new ArrayList<>();
        dbNameList.forEach(dbName -> {
            TreeVO treeVO = new TreeVO();
            treeVO.setParentId(null)
                    .setLevel(1)
                    .setLabel(dbName)
                    .setIcon("el-icon-menu")
                    .setId(String.format(LEVEL_1_DB_ID, dbName));
            result.add(treeVO);
        });
        return result;
    }

    /**
     * 构造树的第二层级，即数据库名称
     *
     * @param parent 父级
     * @return 结果
     */
    public static List<TreeVO> getTreeLevel2(TreeVO parent) {
        List<TreeVO> result = new ArrayList<>();
        TreeVO tablesTreeVO = new TreeVO();
        tablesTreeVO.setParentId(parent.getId())
                .setLevel(2)
                .setLabel("表")
                .setIcon("el-icon-folder")
                .setId(String.format(LEVEL_2_TABLES_ID, parent.getLabel()));

        TreeVO viewsTreeVO = new TreeVO();
        viewsTreeVO.setParentId(parent.getId())
                .setLevel(2)
                .setLabel("视图")
                .setIcon("el-icon-folder")
                .setId(String.format(LEVEL_2_VIEWS_ID, parent.getLabel()));

        TreeVO proceduresTreeVO = new TreeVO();
        proceduresTreeVO.setParentId(parent.getId())
                .setLevel(2)
                .setLabel("存储过程")
                .setIcon("el-icon-folder")
                .setId(String.format(LEVEL_2_PROCEDURES_ID, parent.getLabel()));

        TreeVO functionsTreeVO = new TreeVO();
        functionsTreeVO.setParentId(parent.getId())
                .setLevel(2)
                .setLabel("函数")
                .setIcon("el-icon-folder")
                .setId(String.format(LEVEL_2_FUNCTIONS_ID, parent.getLabel()));

        result.add(tablesTreeVO);
        result.add(viewsTreeVO);
        result.add(proceduresTreeVO);
        result.add(functionsTreeVO);
        return result;
    }

    /**
     * 构造树的第三层级，即具体的表、视图、存储过程、函数
     *
     * @param tableNameList 表名称列表
     * @return 结果
     */
    public static List<TreeVO> getTreeLevel3Tables(List<String> tableNameList, TreeVO parent, TreeVO root) {
        return getTreeVOLevel3(tableNameList, parent, root, LEVEL_3_TABLE_ID);
    }

    /**
     * 构造树的第三层级，即具体的表、视图、存储过程、函数
     *
     * @param viewNameList 视图名称列表
     * @return 结果
     */
    public static List<TreeVO> getTreeLevel3Views(List<String> viewNameList, TreeVO parent, TreeVO root) {
        return getTreeVOLevel3(viewNameList, parent, root, LEVEL_3_VIEW_ID);
    }

    /**
     * 构造树的第三层级，即具体的表、视图、存储过程、函数
     *
     * @param procedureNameList 存储过程名称列表
     * @return 结果
     */
    public static List<TreeVO> getTreeLevel3Procedures(List<String> procedureNameList, TreeVO parent, TreeVO root) {
        return getTreeVOLevel3(procedureNameList, parent, root, LEVEL_3_PROCEDURE_ID);
    }

    private static List<TreeVO> getTreeVOLevel3(List<String> sList, TreeVO parent, TreeVO root, String ex) {
        List<TreeVO> result = new ArrayList<>();
        sList.forEach(s -> {
            TreeVO treeVO = new TreeVO();
            treeVO.setParentId(parent.getId())
                    .setLevel(3)
                    .setLeaf(true)
                    .setLabel(s)
                    .setIcon("el-icon-menu")
                    .setId(String.format(ex, root.getLabel(), s));
            result.add(treeVO);
        });
        return result;
    }
}
