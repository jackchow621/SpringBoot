package ghost.springboot.dao;

import ghost.springboot.entity.Book;

public interface BookRepository {
	public Book getByIsbn(String isbn);

}
