package com.zaijiadd.app.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class CharacterEncodingFilter implements Filter {

    private final static Logger log= Logger.getLogger(CharacterEncodingFilter.class);

    private String characterEncoding; //编码方式配置在web.xml文件中
    private boolean enabled;   //是否启用此Filter，配置在web.xml中

    @Override
    public void destroy() {
        characterEncoding = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (enabled || characterEncoding != null) {
            request.setCharacterEncoding(characterEncoding);
            response.setCharacterEncoding(characterEncoding);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        characterEncoding = config.getInitParameter("characterEncoding");
        enabled = "true".equalsIgnoreCase(config.getInitParameter("enabled").trim());
    }
}