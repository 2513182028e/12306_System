package com.java.train.common.Interceptor;

import cn.hutool.core.util.StrUtil;
import com.java.train.common.context.LoginMemberContext;
import com.java.train.common.resp.MemberLoginResp;
import com.java.train.common.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class MemberInterceptor implements HandlerInterceptor {


    private static  final Logger logger=LoggerFactory.getLogger(MemberInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
        if(StrUtil.isNotBlank(token))
        {
            logger.info("获取会员登录token:{}",token);
            if(JwtUtil.verify(token))
            {
                logger.info("Token验证通过");
                return  true;
            }
            MemberLoginResp memberLoginResp = JwtUtil.getObject(token);
            logger.info("当前登录会员：{}",memberLoginResp);
            LoginMemberContext.setMember(memberLoginResp);
            return true;
        }
        return false;
    }
}
