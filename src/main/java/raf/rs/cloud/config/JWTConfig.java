package raf.rs.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@CrossOrigin(origins = "http://localhost:4200")
public class JWTConfig {

    @Autowired

    private JWTFilter jwtFilter;

    @Bean
    public FilterRegistrationBean<JWTFilter> filterRegistrationBean() {
        FilterRegistrationBean<JWTFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(jwtFilter);
        filterRegistrationBean.addUrlPatterns("/machine/*");
        return filterRegistrationBean;
    }

}
