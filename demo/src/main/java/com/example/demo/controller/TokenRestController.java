package com.example.demo.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TokenRestController {

	@PostMapping("/login")
	public String getOauthToken(Model model,HttpServletRequest req) {
		
				// According OAuth documentation we need to send the client id and secret key in the header for authentication
				String credentials = "USER_CLIENT_APP:password";
				String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				headers.add("Authorization", "Basic " + encodedCredentials);

//				HttpEntity<String> request = new HttpEntity<String>(headers);

				/*String access_token_url = "http://localhost:8080/oauth/token";
				access_token_url += "?code=" + code;
				access_token_url += "&grant_type=authorization_code";
				access_token_url += "&redirect_uri=http://localhost:8090/showEmployees";

				response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);*/
				
				headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

				MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
				map.add("username", req.getParameter("username"));
				map.add("password", req.getParameter("password"));
				map.add("grant_type", "password");

				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

				RestTemplate restTemplate = new RestTemplate();
				String access_token_url = "http://localhost:8089/oauth/token";
				ResponseEntity<String> response = restTemplate.postForEntity( access_token_url, request , String.class );

				System.out.println("Access Token Response ---------" + response.getBody());
				return response.getBody();
	}
}
