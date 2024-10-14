package com.czhhhh.demo.demos.springboot.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 实现Filter接口，编写自己的Filter过滤器（需要在项目入口类加上@ServletComponentScan，过滤器才会自动注册）
 * 过滤器、Servlet、拦截器、监听器差别：（Filter依赖servlet）
 * https://javabetter.cn/springboot/Filter-Interceptor-Listener.html#%E5%89%8D%E8%A8%80
 */
@WebFilter(urlPatterns = "/*",filterName = "myFilter")
public class UseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    //只在doFilter方法中补充了内容
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long time = System.currentTimeMillis();
        System.out.println("【前】经过了过滤器时间====controller前=====)："+(System.currentTimeMillis()-time));
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("【后】经过了过滤器时间====controller后=====)："+(System.currentTimeMillis()-time));
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
