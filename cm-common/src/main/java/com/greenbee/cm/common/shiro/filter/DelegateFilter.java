package com.greenbee.cm.common.shiro.filter;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.PathMatchingFilter;

public class DelegateFilter extends PathMatchingFilter {

    private String config_;

    @Override
    public Filter processPathConfig(String path, String config) {
        config_ = config;
        return super.processPathConfig(path, config);
    }

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response,
            Object mappedValue) throws Exception {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpReq = (HttpServletRequest) request;
            String path = httpReq.getServletPath();
            if (path.startsWith(config_)) {
                final String delegatePath = path.substring(config_.length());
                request.getRequestDispatcher(delegatePath).forward(request, response);
                return false;
            }
        }
        return true;
    }

}
