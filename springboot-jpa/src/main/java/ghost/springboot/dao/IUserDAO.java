package ghost.springboot.dao;

import ghost.springboot.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDAO extends JpaRepository<User,Integer>{

}
