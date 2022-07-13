package cn.ikangjia.demo.infra.config;

import cn.ikangjia.demo.infra.handler.JWTInterceptors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/13 15:21
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptors())
                .addPathPatterns("/**")
                .excludePathPatterns("/openapi/**", "/user/doLogin");
    }
}
