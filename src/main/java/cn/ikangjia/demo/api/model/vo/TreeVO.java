package cn.ikangjia.demo.api.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/7/18 17:03
 */
@Data
@Accessors(chain = true)
public class TreeVO {
    private Integer level;
    private String parentId;
    private String id;
    private String label;
    private String icon;

    private boolean isLeaf;
}
