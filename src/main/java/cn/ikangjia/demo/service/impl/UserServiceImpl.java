package cn.ikangjia.demo.service.impl;

import cn.ikangjia.demo.api.model.dto.UserDTO;
import cn.ikangjia.demo.api.model.dto.UserLoginDTO;
import cn.ikangjia.demo.domain.entity.UserDO;
import cn.ikangjia.demo.domain.mapper.UserMapper;
import cn.ikangjia.demo.infra.exception.BusinessException;
import cn.ikangjia.demo.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/11 10:42
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO doLogin(UserLoginDTO userLoginDTO) {
        String code = userLoginDTO.getCode();

        if (!"1234".equals(code)) {
            throw new BusinessException("验证码错误");
        }

        String account = userLoginDTO.getAccount();
        String password = userLoginDTO.getPassword();
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDO::getAccount, account)
                .eq(UserDO::getPassword, password);

        List<UserDO> userDOList = userMapper.selectList(wrapper);
        if (userDOList.size() > 1) {
            log.error("内部数据错误：[用户账号重复，重复账号为：{}]", account);
            throw new BusinessException("内部数据错误，请联系管理员");
        } else if (userDOList.size() < 1) {
            throw new BusinessException("用户名或密码错误");
        }
        UserDTO result = new UserDTO();
        BeanUtils.copyProperties(userDOList.get(0), result);
        return result;
    }

    @Override
    public Integer doRegister(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);
        return userMapper.insert(userDO);
    }

    @Override
    public UserDTO getUser(Long id) {
        UserDO userDO = userMapper.selectById(id);

        UserDTO result = new UserDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }
}
