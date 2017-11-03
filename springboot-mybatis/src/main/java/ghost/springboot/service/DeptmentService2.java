package ghost.springboot.service;

import ghost.springboot.mapper.DeptmentMapper2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptmentService2 {
	@Autowired
	DeptmentMapper2 deptmentMapper2;

	@Transactional
	public void transfer() throws RuntimeException {
		deptmentMapper2.transfer(90, 1);// 用户1减10块 用户2加10块
		int i = 1 / 0;
		deptmentMapper2.transfer(110, 2);
	}
}
