package music;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.cors(Customizer.withDefaults());

        http.securityContext(context -> context
                .requireExplicitSave(true)
                .securityContextRepository(new DelegatingSecurityContextRepository(
                        new RequestAttributeSecurityContextRepository(),
                        new HttpSessionSecurityContextRepository())));

        http.authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/staff/**").hasAuthority("STAFF")
                .requestMatchers("/api/business/**").hasAuthority("BUSINESS")
                .requestMatchers("/api/member/**").hasAuthority("MEMBER")
                .anyRequest().permitAll());

        http.formLogin(login -> login
                .loginPage("/api/login")
                .usernameParameter("loginId")
                .passwordParameter("password")
                .successHandler((req, res, ex) -> {
                    securityContextRepository().saveContext(SecurityContextHolder.getContext(),
                            req, res);
                    log.info("Login succeeded. [{}]",
                            SecurityContextHolder.getContext().getAuthentication().getName());
                    res.setStatus(HttpServletResponse.SC_OK);
                })
                .failureHandler((req, res, ex) -> {
                    log.warn("Login failed. [{}]", ex.getMessage());
                    res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                })
                .permitAll());

        http.logout(logout -> logout
                .logoutUrl("/api/logout")
                .logoutSuccessHandler((req, res, ex) -> {
                    log.info("Logout succeeded.");
                    res.setStatus(HttpServletResponse.SC_OK);
                })
                .permitAll());

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(Arrays.asList("http://localhost*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST"));
        config.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

}
