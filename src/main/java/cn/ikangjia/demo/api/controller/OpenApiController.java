package cn.ikangjia.demo.api.controller;

import cn.ikangjia.demo.api.rest.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/13 15:31
 */
@RequestMapping("/openapi")
@RestController
public class OpenApiController {

    @GetMapping("/test")
    public ResultVO<String> testOpenApi() {
        return ResultVO.success("我不需要测试");
    }
}
