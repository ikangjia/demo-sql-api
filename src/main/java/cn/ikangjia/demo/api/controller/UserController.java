package cn.ikangjia.demo.api.controller;

import cn.ikangjia.demo.api.model.dto.UserDTO;
import cn.ikangjia.demo.api.model.dto.UserLoginDTO;
import cn.ikangjia.demo.api.rest.ResultVO;
import cn.ikangjia.demo.infra.exception.BusinessException;
import cn.ikangjia.demo.service.UserService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

    @RequestMapping("/captcha.jpg")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DefaultKaptcha producer = userService.getCaptcha();
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到 session
        HttpSession session = request.getSession();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.close();
    }

    @PostMapping("/doLogin")
    public ResultVO<UserDTO> doLogin(HttpServletRequest request, @RequestBody UserLoginDTO loginDTO) {
        String codee = String.valueOf(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY));
        String code = loginDTO.getCode();

        if (!code.equalsIgnoreCase(codee)) {
            throw new BusinessException("验证码错误");
        }
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

    @GetMapping("/x")
    public ResultVO<UserDTO> getUserByToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return Optional.of(userService.getUserByToken(token))
                .map(ResultVO::success)
                .orElseThrow();
    }
}
