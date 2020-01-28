package raf.rs.cloud.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
//@CrossOrigin(origins = "*")
public class JWTFilter  extends GenericFilterBean {
    @Override
    //@CrossOrigin(origins = "*")
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final String authorization = request.getHeader("authorization");
        System.out.println(authorization);
        if (authorization == null || !authorization.startsWith("Token ")) {
            throw new ServletException("401 - UNAUTHORIZED");
        }
        try {
            final Claims claims = Jwts.parser().setSigningKey("123#&*zcvAWEE999").parseClaimsJws(authorization.substring(6))
                    .getBody();
            request.setAttribute("claims", claims);
        } catch (final SignatureException e) {
            throw new ServletException("401 - UNAUTHORIZED");
        }
        chain.doFilter(req, res);

    }
}
