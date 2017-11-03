package ghost.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeptmentMapper2 {

	int transfer(@Param("num") int num, @Param("id") int id);
}
