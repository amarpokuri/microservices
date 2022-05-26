package com.goods.order.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

@Configuration
public class OAuth2Config {
	@Value("${clientId}")
	private String clientId;

	@Value("${clientSecret}")
	private String clientSecret;

	@Value("${tokenUrl}")
	private String tokenUrl;

	@Bean
	protected OAuth2ProtectedResourceDetails oauth2Resource() {
		ClientCredentialsResourceDetails clientCredentialsResourceDetails = new ClientCredentialsResourceDetails();
		clientCredentialsResourceDetails.setAccessTokenUri(tokenUrl);
		clientCredentialsResourceDetails.setClientId(clientId);
		clientCredentialsResourceDetails.setClientSecret(clientSecret);
		clientCredentialsResourceDetails.setGrantType("client_credentials"); // this depends on your specific OAuth2
																				// server
		clientCredentialsResourceDetails.setAuthenticationScheme(AuthenticationScheme.header); // this again depends on
																								// the OAuth2 server
																								// specifications
		return clientCredentialsResourceDetails;
	}

	@Bean
	@LoadBalanced
	public OAuth2RestTemplate oauth2RestTemplate() {
		AccessTokenRequest atr = new DefaultAccessTokenRequest();
		OAuth2RestTemplate oauth2RestTemplate = new OAuth2RestTemplate(oauth2Resource(),
				new DefaultOAuth2ClientContext(atr));
		oauth2RestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		return oauth2RestTemplate;
	}

}
