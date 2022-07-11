package cn.ikangjia.demo.api.model.dto;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/8 17:00
 */
@Data
public class UserLoginDTO {
    private String account;
    private String password;
    private String code;
}
