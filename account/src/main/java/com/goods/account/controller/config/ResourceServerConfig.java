package com.goods.account.controller.config;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

	@Value("${jwtkey}")
	private String jwtkey;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authz -> authz.anyRequest().authenticated())
				.oauth2ResourceServer(oauth2 -> oauth2.jwt());

	}

	@Bean
	public JwtDecoder jwtDecoder() {
		byte[] keyBytes = jwtkey.getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, 0, keyBytes.length, "AES");
		return NimbusJwtDecoder.withSecretKey(secretKeySpec).build();

	}

}
