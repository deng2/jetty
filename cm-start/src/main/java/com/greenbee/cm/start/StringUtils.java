// Copyright (c) 2014 by Vitria Technology, Inc.
// All Rights Reserved.
//
package com.greenbee.cm.start;

public class StringUtils {

    public static boolean isEmpty(String str) {
        if (str == null)
            return true;
        if (str.trim().length() == 0)
            return true;
        return false;
    }

}
