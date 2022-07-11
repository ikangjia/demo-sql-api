package cn.ikangjia.demo.api.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/8 17:01
 */
@Data
@Accessors(chain = true)
public class UserDTO {

    private Long id;
    private String name;
    private String account;
    private String password;
    private String avatar;
    private String phone;
    private String email;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private boolean deleted;
}
