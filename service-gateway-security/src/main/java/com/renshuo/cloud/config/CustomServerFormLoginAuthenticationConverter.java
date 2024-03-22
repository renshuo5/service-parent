package com.renshuo.cloud.config;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerFormLoginAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @description: 自定义登录获取用户名密码转换
 * @author: renshuo
 * @date: 2023/8/17
 */

public class CustomServerFormLoginAuthenticationConverter extends ServerFormLoginAuthenticationConverter {

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return exchange.getRequest()
                .getBody()
                .reduce(DataBuffer::write)
                .map(mergedBuffer -> {
                    // 处理合并的数据缓冲区
                    byte[] bodyBytes = new byte[mergedBuffer.readableByteCount()];
                    mergedBuffer.read(bodyBytes);

                    DataBufferUtils.release(mergedBuffer);
                    String body = new String(bodyBytes, StandardCharsets.UTF_8);

                    // 解析请求体，提取出username和password参数
                    String username = extractUsernameFromBody(body);
                    String password = extractPasswordFromBody(body);

                    Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
                    return authentication;
                });
    }

    private String extractUsernameFromBody(String body) {
        // 从请求体中解析出username
        try {
            JSONObject json = JSONObject.parseObject(body);
            return json.getString("username");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String extractPasswordFromBody(String body) {
        // 从请求体中解析出password
        try {
            JSONObject json = JSONObject.parseObject(body);
            return json.getString("password");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
