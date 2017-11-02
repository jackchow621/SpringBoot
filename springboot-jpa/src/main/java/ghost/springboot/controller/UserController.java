package ghost.springboot.controller;

import ghost.springboot.dao.IUserDAO;
import ghost.springboot.entity.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserDAO userDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getUser() {
        return userDao.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") int id) {
        return userDao.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateAccount(@PathVariable("id") int id, @RequestParam(value = "name", required = true) String name,
                                @RequestParam(value = "age", required = true) int age) {
    	User user = new User();
    	user.setAge(age);
        user.setName(name);
        user.setId(id);
        User user1 = userDao.saveAndFlush(user);

        return user1.toString();

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postAccount(@RequestParam(value = "name") String name,
                              @RequestParam(value = "age") int age) {
        User user = new User();
        user.setAge(age);
        user.setName(name);
        User user1 = userDao.save(user);
        return user1.toString();

    }


}
