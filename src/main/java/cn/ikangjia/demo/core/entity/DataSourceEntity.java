package cn.ikangjia.demo.core.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/7/18 14:06
 */
@Data
@Accessors(chain = true)
public class DataSourceEntity {
    private int type;
    private String port;
    private String host;
    private String username;
    private String password;
}
