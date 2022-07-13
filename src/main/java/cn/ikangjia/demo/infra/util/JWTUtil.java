package cn.ikangjia.demo.infra.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 工具类
 *
 * @author kangJia
 * @email ikangjia@outlook.com
 * @since 2022/7/13 11:14
 */
public class JWTUtil {
    private static final String SECRET = "ikangjia.cn@outlook.com";

    public static String generateToken(Map<String, String> pMap) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);

        // 创建 jwt
        JWTCreator.Builder builder = JWT.create();

        // payload
        pMap.forEach(builder::withClaim);

        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

    public static DecodedJWT getTokenInfo(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

    public static Long getUserIdByToken(String token) {
        String payload = token.split(".")[1];

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(Base64Utils.decode(payload.getBytes(StandardCharsets.UTF_8)))
                    .get("id")
                    .asLong();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        String token = generateToken(map);
        System.out.println(token);

        verify(token);

    }
}
