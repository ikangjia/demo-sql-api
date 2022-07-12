package cn.ikangjia.demo.api.controller;

import cn.ikangjia.demo.api.model.dto.DataSourceDTO;
import cn.ikangjia.demo.api.model.dto.DataSourceListDTO;
import cn.ikangjia.demo.api.model.dto.DataSourceQuery;
import cn.ikangjia.demo.api.rest.ResultVO;
import cn.ikangjia.demo.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/11 13:33
 */
@RestController
@RequestMapping("/dataSource")
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;

    @PostMapping
    public ResultVO<Integer> saveDataSource(@RequestBody DataSourceDTO dataSourceDTO) {
        return Optional.of(dataSourceService.saveDataSource(dataSourceDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    public ResultVO<Integer> removeDataSource(@PathVariable Long id) {
        return Optional.of(dataSourceService.removeDataSource(id))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @PutMapping
    public ResultVO<Integer> updateDataSource(@RequestBody DataSourceDTO dataSourceDTO) {
        return Optional.of(dataSourceService.updateDataSource(dataSourceDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @GetMapping("/{id}")
    public ResultVO<DataSourceDTO> getDataSource(@PathVariable Long id) {
        return Optional.of(dataSourceService.getDataSource(id))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @GetMapping
    public ResultVO<DataSourceListDTO> listDataSources(DataSourceQuery dataSourceQuery) {
        return Optional.of(dataSourceService.listDataSources(dataSourceQuery))
                .map(ResultVO::success)
                .orElseThrow();
    }

    /**
     * 测试该数据源的连通性
     *
     * @param dataSourceDTO 数据源信息
     * @return 是否可连接
     */
    @PostMapping("/testConnection")
    public ResultVO<Boolean> testConnection(@RequestBody DataSourceDTO dataSourceDTO) {
        return Optional.of(dataSourceService.testConnection(dataSourceDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }

}
