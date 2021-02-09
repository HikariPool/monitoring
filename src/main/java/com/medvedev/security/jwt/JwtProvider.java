/*
 * Copyright (c) 9.2.2021 Andrei Medvedev.
 */

package com.medvedev.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource("classpath:security.properties")
public class JwtProvider {
    @Value("${jwt.session.secret_key}")
    private String key;
    @Value("${jwt.session.header}")
    private String headerTitle;
    @Value("${jwt.session.identity_claim_name}")
    private String identityClaimName;
    @Autowired
    @Qualifier("userServiceImpl")
    private UserDetailsService userDetailsService;


    public String generateToken(String username) {
        if (username == null) {
            return null;
        }
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("username", username);

        JwtBuilder builder = Jwts.builder();
        builder.addClaims(tokenData);
        return builder.signWith(SignatureAlgorithm.HS512, key).compact();
    }

    public String getTokenFrom(HttpServletRequest request) {
        return request.getHeader(headerTitle);
    }

    public boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(key).parse(token);
            return true;
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        try {
            UserDetails user = userDetailsService.loadUserByUsername(getUsername(token));
            return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
        } catch (UsernameNotFoundException e) {
            return null;
        }
    }

    private String getUsername(String token) {
        DefaultClaims claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
        return claims.get(identityClaimName, String.class);
    }

}
