package ghost.designpatterns.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	
	@Value("${my.name}")
	private String name;
	@Value("${my.age}")
	private int age;
	
	@RequestMapping(value = "/jack")
    public String jack(){
        return name+":"+age;
    }
}
