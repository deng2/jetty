// Copyright (c) 2015 Vitria Technology, Inc.
// All Rights Reserved.
//
package com.greenbee.cm.start;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Properties;

public class JettyMain {
    public static final String SYS_SE_HOME = "se.home";
    public static final String SYS_JETTY_HOME = "jetty.home";
    public static final String SYS_JETTY_BASE = "jetty.base";
    public static final String SE_PROPERTY_JETTYCLASS = "jetty.class";
    public static final String SE_PROPERTY_JETTYXML = "jetty.xml";
    public static final String SE_PROPERTY_CLASSPATH = "classpath.root";
    public static final String SE_PROPERTY_CLASSPATH_JETTY = "classpath.jetty";
    public static final String SE_HOME = "SE_HOME";
    public static final String SE_PROPERTIES = "se_conf.properties";
    private Properties settings_;
    private String home_;

    public static void main(String[] args) throws Exception {
        JettyMain main = new JettyMain();
        main.run();
    }

    protected void init() throws IOException {
        home_ = System.getenv(SE_HOME);
        home_ = new File(home_).getAbsolutePath();
        System.setProperty(SYS_JETTY_HOME, home_);
        System.setProperty(SYS_JETTY_BASE, home_);
        System.setProperty(SYS_SE_HOME, home_);
        ClassLoader cl = JettyMain.class.getClassLoader();
        settings_ = ResourceLoader.loadResourceAsProperties(cl, SE_PROPERTIES);
    }

    public void run() throws Exception {
        init();
        prepareClassLoader();
        startJettyServer();
    }

    /**
     * JVM class loader
     * ROOT class loader
     * JETTY class loader
     */
    protected void prepareClassLoader() throws Exception {
        String rootClasspath = settings_.getProperty(SE_PROPERTY_CLASSPATH);
        List<URL> rootUrls = ClasspathLib.getClasspathURLs(home_, rootClasspath);
        String jettyClasspath = settings_.getProperty(SE_PROPERTY_CLASSPATH_JETTY);
        List<URL> jettyUrls = ClasspathLib.getClasspathURLs(home_, jettyClasspath);
        //prepare class loader
        ClassLoader parent = JettyMain.class.getClassLoader();
        URLClassLoader root = new URLClassLoader(rootUrls.toArray(new URL[0]), parent);
        URLClassLoader jetty = new URLClassLoader(jettyUrls.toArray(new URL[0]), root);
        Thread.currentThread().setContextClassLoader(jetty);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected void startJettyServer() throws Exception {
        //like etc/jetty.xml,etc/jetty_extend.xml
        String jettyXML = settings_.getProperty(SE_PROPERTY_JETTYXML);
        List<String> apaths = ClasspathLib.split(jettyXML);
        for (int i = 0; i < apaths.size(); i++) {
            String path = apaths.get(i);
            apaths.set(i, FileLib.join(home_, path));
        }
        String jettyClazz = settings_.getProperty(SE_PROPERTY_JETTYCLASS);
        Class jettyClass = ResourceLoader.loadClass(jettyClazz);
        String[] args = apaths.toArray(new String[0]);
        Class[] paramTypes = new Class[] { args.getClass() };
        Object[] params = new Object[] { args };
        Method method = jettyClass.getDeclaredMethod("main", paramTypes);
        method.invoke((Object) null, params);
    }
}
