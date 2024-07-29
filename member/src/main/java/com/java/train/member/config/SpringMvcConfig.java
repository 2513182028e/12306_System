package com.java.train.member.config;


import com.java.train.common.Interceptor.MemberInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class SpringMvcConfig implements WebMvcConfigurer {



    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
       registry.addInterceptor(new MemberInterceptor())
               .excludePathPatterns("/member/member/login",
                       "/member/member/send-code",
                       "/member/member/hello")
               .addPathPatterns("/**")
               ;
    }



}
