// Copyright (c) 2014 by Vitria Technology, Inc.
// All Rights Reserved.
//
package com.greenbee.cm.start;

import java.io.Closeable;

public class IOUtils {

    public static void close(Closeable ioResource) {
        if (ioResource == null)
            return;
        try {
            ioResource.close();
        } catch (Exception e) {
            //ignore
        }
    }

}
