package cn.ikangjia.demo.api.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/11 10:32
 */
@Data
public class DataSourceDTO {
    private Long id;
    private String name;
    private int type;
    private String port;
    private String host;
    private String username;
    private String password;
    private String description;

    private LocalDateTime updateTime;
    private LocalDateTime createTime;

    private boolean deleted;
}
