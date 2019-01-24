package science.freeabyss.template.spa;

import science.freeabyss.template.spa.config.MessageResourceInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@SpringBootApplication
public class QueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryApplication.class, args);
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver slr = new CookieLocaleResolver();
        slr.setCookieMaxAge(3600);
        slr.setCookieName("Language");//设置存储的Cookie的name为Language
        return slr;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            //拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new MessageResourceInterceptor()).addPathPatterns("/**");
                registry.addInterceptor(new LocaleChangeInterceptor()).addPathPatterns("/**");
            }
        };
    }
}

