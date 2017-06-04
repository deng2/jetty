package com.greenbee.cm.common.shiro.track;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.PrincipalCollection;

public class LoginTracker implements AuthenticationListener {
    private Log log_ = LogFactory.getLog(LoginTracker.class);

    @Override
    public void onSuccess(AuthenticationToken token, AuthenticationInfo info) {
        if (log_.isInfoEnabled()) {
            log_.info("Login success: " + token.getPrincipal());
        }
    }

    @Override
    public void onFailure(AuthenticationToken token, AuthenticationException ae) {
        if (log_.isInfoEnabled()) {
            log_.info("Login failure: " + token.getPrincipal());
        }
    }

    @Override
    public void onLogout(PrincipalCollection principals) {
        if (log_.isInfoEnabled()) {
            log_.info("Logout: " + principals.getPrimaryPrincipal());
        }
    }

}
