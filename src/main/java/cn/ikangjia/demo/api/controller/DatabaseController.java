package cn.ikangjia.demo.api.controller;

import cn.ikangjia.demo.api.model.dto.TreeLevel3DTO;
import cn.ikangjia.demo.api.model.vo.TreeVO;
import cn.ikangjia.demo.api.rest.ResultVO;
import cn.ikangjia.demo.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/tree/level1")
    public ResultVO<List<TreeVO>> listDatabases(String dataSourceId) {
        return Optional.of(databaseService.listDatabases(Long.valueOf(dataSourceId)))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @GetMapping("/tree/level2")
    public ResultVO<List<TreeVO>> listTreeLevel2(TreeVO parentNode) {
        return Optional.of(databaseService.listTreeLevel2(parentNode))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @PostMapping("/tree/level3")
    public ResultVO<List<TreeVO>> listTreeLevel3(@RequestBody TreeLevel3DTO treeLevel3DTO) {
        return Optional.of(databaseService.listTreeLevel3(treeLevel3DTO))
                .map(ResultVO::success)
                .orElseThrow();
    }
}
