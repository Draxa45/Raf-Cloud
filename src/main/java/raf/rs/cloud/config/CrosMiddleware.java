package raf.rs.cloud.config;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrosMiddleware extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle (
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception
    {
        if (request.getMethod().equals("OPTIONS")) {
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Methods","GET, POST, PUT, OPTIONS, DELETE");
            response.addHeader("Access-Control-Allow-Headers", "DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,Authorization,If-Modified-Since,Cache-Control,Content-Type,Access-Control-Allow-Origin");
            response.addHeader("Access-Control-Max-Age", "3600");
            response.addHeader("charset", "utf-8");
            throw new CrosException();
        }

        return super.preHandle(request, response, handler);
    }
}
