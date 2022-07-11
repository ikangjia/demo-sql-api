package cn.ikangjia.demo.sql.exception;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/11 12:21
 */
public class DMSException extends RuntimeException{
    public DMSException(String message) {
        super(message);
    }

    public DMSException(String message, Throwable cause) {
        super(message, cause);
    }
}
