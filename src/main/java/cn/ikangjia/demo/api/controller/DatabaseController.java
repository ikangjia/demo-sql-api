package cn.ikangjia.demo.api.controller;

import cn.ikangjia.demo.api.model.vo.TreeVO;
import cn.ikangjia.demo.api.rest.ResultVO;
import cn.ikangjia.demo.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/7/18 16:52
 */
@RequestMapping("/database")
@RestController
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    @GetMapping
    public ResultVO<List<TreeVO>> listDatabases(String dataSourceId) {
        return Optional.of(databaseService.listDatabases(Long.valueOf(dataSourceId)))
                .map(ResultVO::success)
                .orElseThrow();
    }
}
