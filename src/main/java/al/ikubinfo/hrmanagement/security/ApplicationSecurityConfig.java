package al.ikubinfo.hrmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationErrorHandler;

    // Configure BCrypt password encoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    // Configure paths and requests that should be ignored by Spring Security
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**")
                // allow anonymous resource requests
                .antMatchers( "/swagger-ui/**", "/v3/api-docs/**", "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js", "/h2/**");
    }

    // Configure security settings
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()
                .addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authenticationErrorHandler)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and().headers().frameOptions().sameOrigin()

                // create no session
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/holidays/**").hasAuthority(RoleEnum.ADMIN.name())
                .anyRequest().authenticated().and().apply(securityConfigurerAdapter());
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }
}

