package cn.ikangjia.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/11 10:32
 */
@Data
@TableName("t_datasource")
@Accessors(chain = true)
public class DataSourceDO {
    private Long id;
    private String name;
    private int type;
    private String port;
    private String host;
    private String username;
    private String password;
    private String description;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "is_deleted")
    private boolean deleted;
}
