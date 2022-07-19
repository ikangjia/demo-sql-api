package cn.ikangjia.demo.api.model.dto;

import cn.ikangjia.demo.api.model.vo.TreeVO;
import lombok.Data;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/7/19 10:20
 */
@Data
public class TreeLevel3DTO {
    private String dataSourceId;
    private TreeVO parentNode;
    private TreeVO rootNode;

    /*
        0：表
        1：视图
        2：存储过程
        3：函数
     */
    private Integer type;
}
