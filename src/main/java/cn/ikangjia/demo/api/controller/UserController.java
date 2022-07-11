package cn.ikangjia.demo.api.controller;

import cn.ikangjia.demo.api.model.dto.UserDTO;
import cn.ikangjia.demo.api.model.dto.UserLoginDTO;
import cn.ikangjia.demo.api.rest.ResultVO;
import cn.ikangjia.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/8 16:59
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/doLogin")
    public ResultVO<UserDTO> doLogin(@RequestBody UserLoginDTO loginDTO) {
        return Optional.of(userService.doLogin(loginDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @PostMapping("/register")
    public ResultVO<Integer> doRegister(@RequestBody UserDTO userDTO) {
        return Optional.of(userService.doRegister(userDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @GetMapping("/{id}")
    public ResultVO<UserDTO> getUser(@PathVariable Long id) {
        return Optional.of(userService.getUser(id))
                .map(ResultVO::success)
                .orElseThrow();
    }
}
