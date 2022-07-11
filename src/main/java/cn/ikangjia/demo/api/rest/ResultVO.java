package cn.ikangjia.demo.api.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/7/11 10:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    // 默认成功码
    public static final int SUCCESS_CODE = 0;

    // 默认失败码
    public static final int ERROR_CODE = 1;

    private int code = SUCCESS_CODE;

    private T data;

    private String msg;

    public static <T> ResultVO<T> success(int code, T data, String msg) {
        return new ResultVO<>(code, data, msg);
    }

    public static <T> ResultVO<T> success(T data, String msg) {
        return new ResultVO<>(SUCCESS_CODE, data, msg);
    }

    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(SUCCESS_CODE, data, "成功");
    }

    public static <T> ResultVO<T> success(String msg) {
        return new ResultVO<>(SUCCESS_CODE, null, msg);
    }

    public static <T> ResultVO<T> error(int code, String msg) {
        return new ResultVO<>(code, null, msg);
    }

    public static <T> ResultVO<T> error(String msg) {
        return new ResultVO<>(ERROR_CODE, null, msg);
    }

}
