package cn.ikangjia.demo.service;

import cn.ikangjia.demo.api.model.dto.UserDTO;
import cn.ikangjia.demo.api.model.dto.UserLoginDTO;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/8 17:06
 */
public interface UserService {

    /**
     * 登录
     *
     * @param userLoginDTO 登录信息
     * @return 结果
     */
    UserDTO doLogin(UserLoginDTO userLoginDTO);

    /**
     * 注册
     *
     * @param userDTO 注册信息
     * @return 注册结果
     */
    Integer doRegister(UserDTO userDTO);

    /**
     * 查询指定用户详情
     *
     * @param id 用户 id
     * @return 详情
     */
    UserDTO getUser(Long id);

}
