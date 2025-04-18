package org.zerock.todolist.config; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity 
public class SecurityConfig {

    @Bean // PasswordEncoder 빈 등록
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Spring Security 필터 체인 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // CSRF 보호 비활성화 (API 서버에서는 일반적으로 비활성화합니다)
            .csrf(AbstractHttpConfigurer::disable)
            // 요청 권한 설정
            .authorizeHttpRequests(authorize -> authorize
                // "/auth/" 로 시작하는 모든 요청은 인증 없이 허용합니다.
                .requestMatchers("/auth/**").permitAll()
                // 다른 모든 요청은 개발 단계에서는 일단 허용합니다.
                // TODO: 나중에 인증/인가 필요한 경로는 authenticated() 등으로 변경해야 합니다.
                .anyRequest().permitAll()
            );
            // 폼 로그인, HTTP Basic 등 다른 기본 보안 설정 비활성화 (API 서버에 맞게 조정)
            // 필요에 따라 추가적인 보안 설정을 여기에 합니다.
            // .formLogin(AbstractHttpConfigurer::disable)
            // .httpBasic(AbstractHttpConfigurer::disable);


        return http.build();
    }

    
}