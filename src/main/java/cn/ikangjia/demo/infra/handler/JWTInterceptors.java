package cn.ikangjia.demo.infra.handler;

import cn.ikangjia.demo.infra.util.JWTUtil;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/13 15:11
 */
public class JWTInterceptors implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equals(request.getMethod().toUpperCase())) {
            return true;
        }

        Map<Object, Object> resultMap = new HashMap<>();

        String token = request.getHeader("Authorization");
        try {
            JWTUtil.verify(token);
            return true;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            resultMap.put("msg", "无效签名！");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            resultMap.put("msg", "Token 已失效！");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            resultMap.put("msg", "Token 算法错误！");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("msg", "Token 无效！");
        }

        resultMap.put("code", 2);
        String json = new ObjectMapper().writeValueAsString(resultMap);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
