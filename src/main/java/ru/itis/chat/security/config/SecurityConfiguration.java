package ru.itis.chat.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.itis.chat.security.filters.TokenAuthenticationFilter;
import ru.itis.chat.security.providers.TokenAuthenticationProvider;

@Configuration // говорим, что у нас конфигурационный класс
@EnableGlobalMethodSecurity(prePostEnabled = true) // включаем проверку безопасности через аннотации
@EnableWebSecurity // включаем безопасность
@ComponentScan("ru.itis.chat") // говорим, чтобы искал все компоненты в наших пакетах
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Qualifier("customUserDetailsService")
//    @Autowired
//    private UserDetailsService userDetailsService;

    // подключем провайдер, который мы написали
    @Autowired
    private TokenAuthenticationProvider provider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // конфигурируем AuthenticationManager
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        // прикручиваем наш провайдер
        auth.authenticationProvider(provider);
    }

//    @Bean
//    public PersistentTokenRepository tokenRepository() {
//        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//        tokenRepository.setDataSource(dataSource);
//        return tokenRepository;
//    }

    // конфигурирем саму безопасность
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // отключаем csrf
        http.csrf().disable();
        // отключаем сессии
        http.sessionManagement().disable();
        // добавляем наш фильтр
        http.addFilterBefore(new TokenAuthenticationFilter(), BasicAuthenticationFilter.class);
        // говорим, что разрешаем Swagger
        http.authorizeRequests().antMatchers("/swagger-ui.html#/**").permitAll();
//        .antMatchers("/signUp/**").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/chat").authenticated()
//                .antMatchers("/css/**").permitAll()
//                .anyRequest().authenticated()
//        .and()
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error")
//                .permitAll()
//                .defaultSuccessUrl("/chat");
    }
}