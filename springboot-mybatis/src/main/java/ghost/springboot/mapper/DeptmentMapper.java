package ghost.springboot.mapper;

import ghost.springboot.entity.Deptment;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DeptmentMapper {
	@Insert("insert into deptment(name, num) values(#{name}, #{num})")
    int add(@Param("name") String name, @Param("num") int num);

    @Update("update deptment set name = #{name}, num = #{num} where id = #{id}")
    int update(@Param("name") String name, @Param("num") int num, @Param("id") int  id);

    @Delete("delete from deptment where id = #{id}")
    int delete(int id);

    @Select("select id, name as name, num as num from deptment where id = #{id}")
    Deptment findDeptment(@Param("id") int id);

    @Select("select id, name as name, num as num from deptment")
    List<Deptment> findDeptmentList();
}
