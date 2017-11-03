package ghost.springboot.controller;

import ghost.springboot.entity.Deptment;
import ghost.springboot.service.DeptmentService;
import ghost.springboot.service.DeptmentService2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deptment")
public class DeptmentController {
	@Autowired
	DeptmentService deptmentService;

	@Autowired
	DeptmentService2 deptmentService2;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Deptment> getDeptments() {
		return deptmentService.findDeptmentList();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Deptment getDeptmentById(@PathVariable("id") int id) {
		return deptmentService.findDeptment(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateDeptment(@PathVariable("id") int id,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "num", required = true) int num) {
		int t = deptmentService.update(name, num, id);
		if (t == 1) {
			return "success";
		} else {
			return "fail";
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable(value = "id") int id) {
		int t = deptmentService.delete(id);
		if (t == 1) {
			return "success";
		} else {
			return "fail";
		}

	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postDeptment(@RequestParam(value = "name") String name,
			@RequestParam(value = "num") int num) {

		int t = deptmentService.add(name, num);
		if (t == 1) {
			return "success";
		} else {
			return "fail";
		}

	}

	@RequestMapping(value = "transfer", method = RequestMethod.POST)
	public void transfer() {
		deptmentService2.transfer();
	}
}
