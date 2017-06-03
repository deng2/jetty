// Copyright (c) 2014 Vitria Technology, Inc.
// All Rights Reserved.
//
package com.greenbee.cm.start;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceLoader {

    protected static InputStream loadResourceAsStream(String path) throws IOException {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        return loadResourceAsStream(cl, path);
    }

    protected static InputStream loadResourceAsStream(ClassLoader cl, String path)
            throws IOException {
        InputStream is = cl.getResourceAsStream(path);
        if (is == null) {
            throw new IOException("resource with path '" + path + "' can't located");
        }
        return is;
    }

    protected static byte[] loadResourceAsByteArray(String path) throws IOException {
        InputStream is = loadResourceAsStream(path);
        int len = is.available();
        byte[] rtn = new byte[len];
        is.read(rtn);
        is.close();
        return rtn;
    }

    protected static String loadResourceAsString(String path) throws IOException {
        byte[] resourceBytes = loadResourceAsByteArray(path);
        String res = new String(resourceBytes, "UTF-8");
        return res;
    }

    @SuppressWarnings({ "rawtypes" })
    public static Class loadClass(String clazzName) throws ClassNotFoundException {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        return Class.forName(clazzName, true, cl);
    }

    protected static Properties loadResourceAsProperties(String path) throws IOException {
        Properties env = new Properties();
        InputStream is = loadResourceAsStream(path);
        env.load(is);
        return env;
    }

    public static Properties loadResourceAsProperties(ClassLoader cl, String path)
            throws IOException {
        Properties env = new Properties();
        InputStream is = loadResourceAsStream(cl, path);
        env.load(is);
        return env;
    }
}
