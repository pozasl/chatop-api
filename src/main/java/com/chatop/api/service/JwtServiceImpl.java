package com.chatop.api.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.chatop.api.model.CustomUserDetails;

@Service
public class JwtServiceImpl implements JwtService{

    private final JwtEncoder encoder;

    @Autowired
    public JwtServiceImpl(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String generateToken(Authentication auth) {
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String scope = auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));
            return createToken(userDetails, scope, this.encoder);
    }

    public static String createToken(CustomUserDetails userDetails, String scope, JwtEncoder encoder) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(1,ChronoUnit.HOURS))
            .subject(userDetails.getEmail())
            .claim("scope", scope)
            .claim("userId", userDetails.getId())
            .build();
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}