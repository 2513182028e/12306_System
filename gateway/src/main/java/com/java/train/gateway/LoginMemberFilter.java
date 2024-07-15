package com.java.train.gateway;

import com.auth0.jwt.JWT;
import com.java.train.common.context.LoginMemberContext;
import com.java.train.common.util.JwtUtil;
import jakarta.servlet.annotation.WebFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class LoginMemberFilter implements GlobalFilter, Ordered {

    private static  final Logger LOG= LoggerFactory.getLogger(LoginMemberFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path=exchange.getRequest().getURI().getPath();

        //拦截不需要的请求
        if(path.contains("/admin")
                ||path.contains("/hello")
                ||path.contains("/member/member/login")
                ||path.contains("/member/member/register")
                ||path.contains("/member/member/send-code")
                )
        {
            LOG.info("不需要登陆验证:{}",path);
            return chain.filter(exchange);
        }
        else {
            LOG.info("需要登录验证:{}",path);
        }
        //获取header的token
        String token = exchange.getRequest().getHeaders().getFirst("token");
        LOG.info("会员登录校验开始: Token:{}",token);
        if(token==null||token.isEmpty())
        {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        //校验Token是否有效，包括Token是否被修改过，是否过期等等
        boolean validate= JwtUtil.verify(token);
        if(validate)
        {
            LOG.info("Token有效，放行该请求");
            LoginMemberContext.setMember(JwtUtil.getObject(token));
            return chain.filter(exchange);
        }
        else {
            LOG.warn("Token无效，请求被拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
    @Override
    public int getOrder() {
        return 0;
    }
}
