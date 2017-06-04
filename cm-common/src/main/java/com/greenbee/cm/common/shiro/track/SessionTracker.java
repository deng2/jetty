package com.greenbee.cm.common.shiro.track;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class SessionTracker implements SessionListener {
    private Log log_ = LogFactory.getLog(SessionTracker.class);

    @Override
    public void onStart(Session session) {
        if (log_.isInfoEnabled()) {
            log_.info("Session started: " + session.getId() + "," + session.getHost());
        }
    }

    @Override
    public void onStop(Session session) {
        if (log_.isInfoEnabled()) {
            log_.info("Session stopped: " + session.getId());
        }
    }

    @Override
    public void onExpiration(Session session) {
        if (log_.isInfoEnabled()) {
            log_.info("Session expired: " + session.getId());
        }
    }

}
