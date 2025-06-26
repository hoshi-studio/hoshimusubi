package com.hoshimusubi.seunga.security;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class XssFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // request가 HttpServletRequest일 때만 처리 / requestがHttpServletRequestの場合のみ処理
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        
        // HttpServletRequest를 Wrapping 해서 XSS 처리를 할 수 있게 함 / HttpServletRequestをWrappingしてXSS処理ができるようにする
        HttpServletRequest wrappedRequest = new XssRequestWrapper(httpServletRequest);

        // 필터 체인 계속 실행 (가공된 요청을 전달) / フィルターチェーンを継続して実行 (加工されたリクエストを伝達)
        chain.doFilter(wrappedRequest, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필터 초기화 (필요 시) フィルター初期化(必要時)
    }

    @Override
    public void destroy() {
        // 필터 종료 시 처리할 작업 (필요 시)
    }

    // XSS 처리용 HttpServletRequest / Wrapper XSS処理用 HttpServletRequest Wrapper
    public static class XssRequestWrapper extends HttpServletRequestWrapper {

        public XssRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        @Override
        public String[] getParameterValues(String parameter) {
            String[] values = super.getParameterValues(parameter);
            if (values == null) {
                return null;
            }

            // 입력값에서 <script> 태그 제거 / 入力値から「script」タグを削除
            for (int i = 0; i < values.length; i++) {
                values[i] = sanitize(values[i]);
            }
            return values;
        }

        @Override
        public String getParameter(String parameter) {
            String value = super.getParameter(parameter);
            if (value == null) {
                return null;
            }

            // 입력값에서 <script> 태그 제거 / 入力値から「script」タグを削除
            return sanitize(value);
        }

        private String sanitize(String value) {
            if (value == null) {
                return null;
            }
            return value
                    .replaceAll("&", "&amp;")
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&#x27;");
        }
    }
}
