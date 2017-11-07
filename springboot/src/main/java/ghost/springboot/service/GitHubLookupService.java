package ghost.springboot.service;

import ghost.springboot.bean.User;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubLookupService  {
	
	private static final Logger logger = LoggerFactory.getLogger(GitHubLookupService.class);

//	@Autowired
//    private  RestTemplate restTemplate;
	
	private final RestTemplate restTemplate;

    public GitHubLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


	@Async
	public Future<User> findUser(String user) throws InterruptedException {
		// TODO Auto-generated method stub
		logger.info("Looking up " + user);
        String url = String.format("https://api.github.com/users/%s", user);
        User results = restTemplate.getForObject(url, User.class);
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        return new AsyncResult<User>(results);
	}

}
