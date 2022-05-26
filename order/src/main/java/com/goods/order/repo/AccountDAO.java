package com.goods.order.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO {

	@Autowired
	OAuth2RestTemplate oauth2RestTemplate;

	public boolean checkAccountExists(Long id) {

		ResponseEntity<String> responseEntity = oauth2RestTemplate.getForEntity("http://account/users/checkidexists/" + id,
				String.class);
		return responseEntity.getStatusCode().is2xxSuccessful();

	}

}
