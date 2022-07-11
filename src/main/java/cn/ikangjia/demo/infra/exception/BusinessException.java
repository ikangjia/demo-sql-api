package cn.ikangjia.demo.infra.exception;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/11 10:48
 */
public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
