package com.tradinsimulator.tradingsimulator.config;


import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.tradinsimulator.tradingsimulator.services.JwtService;
import com.tradinsimulator.tradingsimulator.services.KeyParser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;

// JwtConfig.java
@Configuration
@Setter
@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private String readKeyFromClasspath(String path) throws IOException {
        String resolvedPath = path.replaceFirst("classpath:", "");
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resolvedPath)) {
            if (is == null) throw new FileNotFoundException("Key not found: " + path);
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private RSAPublicKey loadPublicKey() throws Exception {
        String key = new String(publicKeyResource.getInputStream().readAllBytes());
        return KeyParser.parseRSAPublicKey(key);
    }

    private RSAPrivateKey loadPrivateKey() throws Exception {
        String key = new String(privateKeyResource.getInputStream().readAllBytes());
        return KeyParser.parseRSAPrivateKey(key);
    }

    @Value("classpath:jwt/app.key")
    private Resource privateKeyResource;

    @Value("classpath:jwt/app.pub")
    private Resource publicKeyResource;

    @Value("15m")
    private Duration ttl;

    @Bean
    public JwtEncoder jwtEncoder() throws Exception{

        System.out.println("Private");
        System.out.println(privateKeyResource);
        System.out.println("Public");
        System.out.println(publicKeyResource);
        System.out.println("=================");
        var pub = loadPublicKey();
        var priv = loadPrivateKey();

        final var jwk = new RSAKey.Builder(pub)
                .privateKey(priv).build();

        return new NimbusJwtEncoder(
                new ImmutableJWKSet<>(new JWKSet(jwk)));
    }

    @Bean
    public JwtDecoder jwtDecoder() throws Exception{
        var pub = loadPublicKey();
        return NimbusJwtDecoder.withPublicKey(pub).build();
    }

    @Bean
    public JwtService jwtService(
            @Value("${spring.application.name}") final String appName,
            final JwtEncoder jwtEncoder) {

        return new JwtService(appName, ttl, jwtEncoder);
    }

}