package com.example.demo.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthenticationFilter implements Filter {

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化邏輯，可留空
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 檢查 Session 是否存在，以及是否有已登入的用戶
        HttpSession session = httpRequest.getSession(false); // 不自動創建 Session
        String path = httpRequest.getRequestURI();

        if (session == null || session.getAttribute("role") == null) {
            // 如果未登入，跳轉到登入頁面
            httpResponse.sendRedirect("/login");
            return;
        }

        String userRole = (String) session.getAttribute("role");

        // 定義角色與可訪問路徑的對應關係
        Map<String, List<String>> roleAccessMap = new HashMap<>();
        roleAccessMap.put("ADMIN", Arrays.asList("/admin", "/admin/*"));
        roleAccessMap.put("USER", Arrays.asList("/index.html", "/rest/user", "/rest/transaction", "/api/recommend"));

        // 確認角色是否能訪問當前路徑
        List<String> accessiblePaths = roleAccessMap.getOrDefault(userRole, Collections.emptyList());
        boolean hasAccess = accessiblePaths.stream().anyMatch(path::startsWith);

        if (!hasAccess) {
            // 如果沒有訪問權限，重定向到默認頁面
            httpResponse.sendRedirect("/");
            return;
        }

        // 如果角色匹配，繼續請求
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 銷毀邏輯，可留空
    }

}
