package ro.dragomiralin.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private static final String[] WHITELIST = {
            "/login",
            "/**",
            "/user/**",
            "/"
    };

    private static final String[] SYSTEM_LIST = {
            "/eureka/**"
    };

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user").password(encoder().encode("user")).roles("USER")
                .and()
            .withUser("admin").password(encoder().encode("admin")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()
//            .defaultSuccessUrl("/home/index.html", true)
            .and()
        .authorizeRequests()
            .antMatchers(WHITELIST).hasAnyRole()
            .antMatchers(SYSTEM_LIST).hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
        .logout()
            .and()
        .csrf().disable();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
