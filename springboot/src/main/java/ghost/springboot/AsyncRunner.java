package ghost.springboot;

import ghost.springboot.bean.User;
import ghost.springboot.service.GitHubLookupService;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AsyncRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AsyncRunner.class);

	@Autowired
    private GitHubLookupService gitHubLookupService;


    @Override
    public void run(String... args) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        Future<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
        Future<User> page2 = gitHubLookupService.findUser("CloudFoundry");
        Future<User> page3 = gitHubLookupService.findUser("Spring-Projects");
        Future<User> page4 = gitHubLookupService.findUser("jackchow621");

        // Wait until they are all done
        while (!(page1.isDone() && page2.isDone() && page3.isDone()&& page4.isDone())) {
            Thread.sleep(10); //10-millisecond pause between each check
        }

        // Print results, including elapsed time
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        logger.info("--> " + page1.get());
        logger.info("--> " + page2.get());
        logger.info("--> " + page3.get());
        logger.info("--> " + page4.get());
    }

}
