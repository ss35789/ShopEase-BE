package com.shopease.shop_ease_backend.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static void create(HttpServletResponse response, String name, String value, boolean secure, int maxAge, String path) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(false);
        cookie.setSecure(secure);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    public static void clear(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setHttpOnly(false);
        cookie.setSecure(true);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
