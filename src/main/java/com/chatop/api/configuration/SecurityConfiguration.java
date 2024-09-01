package com.chatop.api.configuration;

import com.chatop.api.service.CustomUserDetailsService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  private UserDetailsService userDetailsService;
  private final RsaKeyProperties rsaKeys;

  @Autowired
  public SecurityConfiguration(
      CustomUserDetailsService userDetailsService,
      RsaKeyProperties rsaKeys
  ) {
    this.userDetailsService = userDetailsService;
    this.rsaKeys = rsaKeys;
  }

  /**
   * Filter chain definition.
   *
   * @param http http security filter chain builder
   * @return a security filter chain
   * @throws Exception A security exception
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(
          session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authorizeHttpRequests(auth -> {
          auth.requestMatchers("/api/auth/**").permitAll();
          auth.requestMatchers("/v3/api-docs/**").permitAll();
          auth.requestMatchers("/swagger-ui/**").permitAll();
          auth.requestMatchers("/upload/**").permitAll();
          auth.anyRequest().authenticated();
        })
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Authentication manager definition.
   *
   * @param http http security filter chain builder
   * @param passEncoder Password encoder
   * @return an authentication manager
   * @throws Exception Authentication exception
   */
  @Bean
  public AuthenticationManager authenticationManager(
      HttpSecurity http,
      PasswordEncoder passEncoder
  ) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = http
        .getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.userDetailsService(
      userDetailsService).passwordEncoder(passEncoder);
    return authenticationManagerBuilder.build();
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
  }

  @Bean
  JwtEncoder jwtEncoder() {
    JWK jwKeys = new RSAKey.Builder(rsaKeys.publicKey())
        .privateKey(rsaKeys.privateKey())
        .build();
    JWKSource<SecurityContext> jwKeysSrc = new ImmutableJWKSet<>(new JWKSet(jwKeys));
    return new NimbusJwtEncoder(jwKeysSrc);
  }

}
