package cn.ikangjia.demo.service.impl;

import cn.ikangjia.demo.api.model.dto.DataSourceDTO;
import cn.ikangjia.demo.api.model.dto.DataSourceListDTO;
import cn.ikangjia.demo.api.model.dto.DataSourceQuery;
import cn.ikangjia.demo.domain.entity.DataSourceDO;
import cn.ikangjia.demo.domain.mapper.DataSourceMapper;
import cn.ikangjia.demo.service.DataSourceService;
import cn.ikangjia.demo.core.datasource.AbstractJdbcConnection;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/11 11:06
 */
@Slf4j
@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DataSourceMapper dataSourceMapper;

    @Override
    public Integer saveDataSource(DataSourceDTO dataSourceDTO) {
        DataSourceDO dataSourceDO = new DataSourceDO();
        BeanUtils.copyProperties(dataSourceDTO, dataSourceDO);
        return dataSourceMapper.insert(dataSourceDO);
    }

    @Override
    public Integer removeDataSource(Long id) {
        return dataSourceMapper.deleteById(id);
    }

    @Override
    public Integer updateDataSource(DataSourceDTO dataSourceDTO) {
        DataSourceDO dataSourceDO = new DataSourceDO();
        BeanUtils.copyProperties(dataSourceDTO, dataSourceDO);
        return dataSourceMapper.updateById(dataSourceDO);
    }

    @Override
    public DataSourceDTO getDataSource(Long id) {
        DataSourceDO dataSourceDO = dataSourceMapper.selectById(id);
        DataSourceDTO result = new DataSourceDTO();

        BeanUtils.copyProperties(dataSourceDO, result);
        return result;
    }

    @Override
    public DataSourceListDTO listDataSources(DataSourceQuery dataSourceQuery) {
        Integer pageNum = dataSourceQuery.getPageNum();
        Long pageSize = dataSourceQuery.getPageSize();
        IPage<DataSourceDO> page = new Page<>(pageNum, pageSize);

        String host = dataSourceQuery.getHost();
        String name = dataSourceQuery.getName();
        Integer type = dataSourceQuery.getType();

        LambdaQueryWrapper<DataSourceDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Objects.nonNull(type), DataSourceDO::getType, type)
                .like(StringUtils.hasLength(name), DataSourceDO::getName, name)
                .like(StringUtils.hasLength(host), DataSourceDO::getHost, host);

        IPage<DataSourceDO> iPage = dataSourceMapper.selectPage(page, wrapper);
        List<DataSourceDO> dataSourceDOList = iPage.getRecords();

        List<DataSourceDTO> dataSourceDTOList = new ArrayList<>();

        dataSourceDOList.forEach(dataSourceDO -> {
            DataSourceDTO dto = new DataSourceDTO();
            BeanUtils.copyProperties(dataSourceDO, dto);
            dataSourceDTOList.add(dto);
        });

        Long total = dataSourceMapper.selectCount(wrapper);

        DataSourceListDTO result = new DataSourceListDTO();
        result.setDataSourceDTOList(dataSourceDTOList);
        result.setTotal(total);
        return result;
    }

    @Override
    public boolean testConnection(DataSourceDTO dataSourceDTO) {
        DataSourceDO dataSourceDO = new DataSourceDO();
        BeanUtils.copyProperties(dataSourceDTO, dataSourceDO);
        int type = dataSourceDTO.getType();
        AbstractJdbcConnection jdbcConnection = new AbstractJdbcConnection();

        switch (type) {
            case 0:
                // mysql
                return jdbcConnection.testConnection(dataSourceDO);
            default:
                break;
        }
        return false;
    }
}
