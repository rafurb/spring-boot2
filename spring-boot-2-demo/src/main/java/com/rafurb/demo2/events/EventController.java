package com.rafurb.demo2.events;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rafurb.demo2.github.GithubClient;
import com.rafurb.demo2.github.RepositoryEvent;

@RestController
public class EventController {
	
	private final GithubProjectRepository githubProjectRepository;
	
	private final GithubClient githubClient;
	
	public EventController(GithubProjectRepository githubProjectRepository, GithubClient githubClient) {
		this.githubProjectRepository = githubProjectRepository;
		this.githubClient = githubClient;
	}



	@GetMapping("/events/{repoName}")
	@ResponseBody
	public RepositoryEvent[] fetchEvents(@PathVariable String repoName) {
		
		GithubProject githubProject = githubProjectRepository.findByRepoName(repoName);
		
		return githubClient.fetchEvents(githubProject.getOrgName(), githubProject.getRepoName()).getBody();
		
	} 
}
