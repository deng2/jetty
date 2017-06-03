// Copyright (c) 2015 Vitria Technology, Inc.
// All Rights Reserved.
//
package com.greenbee.cm.start;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class FileLib {
    public static List<String> listJarFiles(String dirPath, boolean recursive) {
        List<String> paths = new ArrayList<>();
        File dir = new File(dirPath);
        if (!dir.isDirectory())//to exclude none existing path
            return paths;
        FileFilter filter = new JarFileFilter();
        File[] files = dir.listFiles(filter);
        for (File file : files) {
            String path = file.getPath();
            if (file.isFile())
                paths.add(path);
            else if (recursive) {
                List<String> subPaths = listJarFiles(path, recursive);
                paths.addAll(subPaths);
            }
        }
        return paths;
    }

    public static String join(String... paths) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paths.length; i++) {
            if (i != 0)
                sb.append(File.separator);
            sb.append(paths[i]);
        }
        return sb.toString();
    }

    protected static final class JarFileFilter implements FileFilter {
        @Override
        public boolean accept(File file) {
            return file.isDirectory() || (file.isFile() && file.getName().endsWith(".jar"));
        }
    }
}
