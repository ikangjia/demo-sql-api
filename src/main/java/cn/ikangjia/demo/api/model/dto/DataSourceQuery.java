package cn.ikangjia.demo.api.model.dto;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/11 11:10
 */
@Data
public class DataSourceQuery {
    private String name;
    private Integer type;
    private Long pageSize;
    private Integer pageNum;

    // 根据主机 ip 做筛选
    private String host;
}
