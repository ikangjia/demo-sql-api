package cn.ikangjia.demo.api.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/11 11:15
 */
@Data
public class DataSourceListDTO {

    private List<DataSourceDTO> dataSourceDTOList;

    private Long total;
}
