package com.rafurb.demo2.github;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GithubClient {

	private static final String EVENT_ISSUES_URL = "https://api.github.com/repos/{owner}/{repo}/issues/events";
	
	private RestTemplate restTemplatel;

	public GithubClient(RestTemplateBuilder builder) {
		this.restTemplatel = builder.build();
	}
	
	public ResponseEntity<RepositoryEvent[]> fetchEvents(String orgName, String repoName){
		return restTemplatel.getForEntity(EVENT_ISSUES_URL, RepositoryEvent[].class, orgName, repoName);
	}
	
}
