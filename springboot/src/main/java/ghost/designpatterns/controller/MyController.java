package ghost.designpatterns.controller;

import ghost.designpatterns.bean.MyBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({MyBean.class})
public class MyController {
	
//	@Value("${my.name}")
//	private String name;
//	@Value("${my.age}")
//	private int age;
	@Autowired
	MyBean myBean;
	
	@RequestMapping(value = "/jack")
    public String jack(){
//        return name+":"+age;
		return myBean.getGreeting()+" >>>>"+myBean.getName()+" >>>>"+ myBean.getUuid()+" >>>>"+myBean.getMax();
    }
}
