package com.example.datn.Utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Pham Ngoc Son
 * <p>
 * Oct 22, 2021
 */
public class Utility {
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
