package cn.ikangjia.demo.service.impl;

import cn.ikangjia.demo.api.model.dto.UserDTO;
import cn.ikangjia.demo.api.model.dto.UserLoginDTO;
import cn.ikangjia.demo.domain.entity.UserDO;
import cn.ikangjia.demo.domain.mapper.UserMapper;
import cn.ikangjia.demo.infra.exception.BusinessException;
import cn.ikangjia.demo.infra.util.JWTUtil;
import cn.ikangjia.demo.service.UserService;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
    public DefaultKaptcha getCaptcha() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.space", "5");
        properties.put("kaptcha.textproducer.char.string", "123456788");
        properties.put("kaptcha.textproducer.char.length", "4");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    @Override
    public UserDTO doLogin(UserLoginDTO userLoginDTO) {
        String account = userLoginDTO.getAccount();
        String password = userLoginDTO.getPassword();
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDO::getAccount, account)
                .eq(UserDO::getPassword, password);

        List<UserDO> userDOList = userMapper.selectList(wrapper);
        if (userDOList.size() > 1) {
            log.error("?????????????????????[???????????????????????????????????????{}]", account);
            throw new BusinessException("???????????????????????????????????????");
        } else if (userDOList.size() < 1) {
            throw new BusinessException("????????????????????????");
        }

        UserDTO result = new UserDTO();
        BeanUtils.copyProperties(userDOList.get(0), result);

        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(result.getId()));
        String token = JWTUtil.generateToken(map);
        result.setToken(token);

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

    @Override
    public UserDTO getUserByToken(String token) {
        Long id = JWTUtil.getUserIdByToken(token);
        return this.getUser(id);
    }
}
