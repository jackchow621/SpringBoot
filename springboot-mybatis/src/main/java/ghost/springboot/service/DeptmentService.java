package ghost.springboot.service;

import ghost.springboot.entity.Deptment;
import ghost.springboot.mapper.DeptmentMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptmentService {
	 @Autowired
	    private DeptmentMapper deptmentMapper;

	    public int add(String name, int num) {
	        return deptmentMapper.add(name, num);
	    }
	    public int update(String name, int num, int id) {
	        return deptmentMapper.update(name, num, id);
	    }
	    public int delete(int id) {
	        return deptmentMapper.delete(id);
	    }
	    public Deptment findDeptment(int id) {
	        return deptmentMapper.findDeptment(id);
	    }
	    public List<Deptment> findDeptmentList() {
	        return deptmentMapper.findDeptmentList();
	    }
}
