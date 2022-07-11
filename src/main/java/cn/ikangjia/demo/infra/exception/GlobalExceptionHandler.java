package cn.ikangjia.demo.infra.exception;

import cn.ikangjia.demo.api.rest.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/11 14:07
 */
@Slf4j
@RestControllerAdvice(basePackages = "cn.ikangjia.demo.api.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultVO<Void> globalHandler(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResultVO.error(1, e.getMessage());
    }
}
