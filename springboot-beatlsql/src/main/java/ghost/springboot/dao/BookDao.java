package ghost.springboot.dao;

import ghost.springboot.entity.Book;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;

@SqlResource("ghost.springboot")
public interface BookDao extends BaseMapper<Book> {
	
	@SqlStatement(params = "name")
    public Book selectBookByName(String name);
}
