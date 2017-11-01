package ghost.springboot.controller;

import ghost.springboot.bean.MyBean;
import ghost.springboot.bean.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({MyBean.class,User.class})
public class MyController {
	
//	@Value("${my.name}")
//	private String name;
//	@Value("${my.age}")
//	private int age;
	@Autowired
	MyBean myBean;
	@Autowired
	User user;
	
	@RequestMapping(value = "/jack")
    public String jack(){
//        return name+":"+age;
		return myBean.getGreeting()+" >>>>"+myBean.getName()+" >>>>"+ myBean.getUuid()+" >>>>"+myBean.getMax();
    }
	@RequestMapping(value = "/ghost")
	public String ghost(){
        return user.getName()+":"+user.getAge();
	}
}
