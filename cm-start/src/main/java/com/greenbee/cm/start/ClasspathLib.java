// Copyright (c) 2015 Vitria Technology, Inc.
// All Rights Reserved.
//
package com.greenbee.cm.start;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ClasspathLib {
    public static final String CLASSPATH_DELIMITER = ",";
    public static final String PATH_FOLDER_JARS = "/*";
    public static final String PATH_FOLDER_RECURSIVEJARS = "/**";

    public static List<String> split(String classpath) {
        List<String> paths = new ArrayList<>();
        for (String path : classpath.split(ClasspathLib.CLASSPATH_DELIMITER)) {
            if (StringUtils.isEmpty(path))
                continue;
            paths.add(path.trim());
        }
        return paths;
    }

    /**
     * support three types of class path
     * 1. folder like "etc"
     * 2. jars under the folder like "libs/*"
     * 3. jars under the folder recursively like "app/foudation/**"
     */

    public static List<URL> getClasspathURLs(String home, String classpath) throws Exception {
        List<String> paths = getClasspaths(home, classpath);
        List<File> jars = new ArrayList<>();
        for (String path : paths) {
            jars.add(new File(path));
        }
        ArrayList<URL> urls = new ArrayList<>();
        for (File file : jars) {
            urls.add(file.toURI().toURL());
        }
        return urls;
    }

    protected static List<String> getClasspaths(String home, String classpath) {
        List<String> classpaths = new ArrayList<>();
        for (String path : ClasspathLib.split(classpath)) {
            path = FileLib.join(home, path);
            String dirPath = ClasspathLib.getFolderPath(path);
            if (isFolderJarsPath(path)) {
                List<String> jars = FileLib.listJarFiles(dirPath, false);
                classpaths.addAll(jars);
            } else if (isFolderRecursiveJarsPath(path)) {
                List<String> jars = FileLib.listJarFiles(dirPath, true);
                classpaths.addAll(jars);
            } else {
                classpaths.add(path);
            }
        }
        return classpaths;
    }

    protected static boolean isFolderJarsPath(String path) {
        return path.endsWith(PATH_FOLDER_JARS);
    }

    protected static boolean isFolderRecursiveJarsPath(String path) {
        return path.endsWith(PATH_FOLDER_RECURSIVEJARS);
    }

    protected static String getFolderPath(String path) {
        int len;
        if (isFolderJarsPath(path)) {
            len = PATH_FOLDER_JARS.length();
        } else if (isFolderRecursiveJarsPath(path)) {
            len = PATH_FOLDER_RECURSIVEJARS.length();
        } else {
            len = 0;
        }
        return path.substring(0, path.length() - len);
    }

}
