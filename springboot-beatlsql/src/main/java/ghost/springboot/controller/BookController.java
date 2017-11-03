package ghost.springboot.controller;


import org.beetl.sql.core.db.KeyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ghost.springboot.dao.BookDao;
import ghost.springboot.entity.Book;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookDao bookDao;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Book> getBooks() {
		return bookDao.all();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Book getBookById(@PathVariable("id") int id) {
		return bookDao.unique(id);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Book getBookById(@RequestParam("name") String name) {
		return bookDao.selectBookByName(name);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateBook(@PathVariable("id") int id,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "price", required = true) double price) {
		Book Book = new Book();
		Book.setPrice(price);
		Book.setName(name);
		Book.setId(id);
		int t = bookDao.updateById(Book);
		if (t == 1) {
			return Book.toString();
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "",method = RequestMethod.POST)
	    public  String postUser( @RequestParam(value = "name")String name,
	                                 @RequestParam(value = "price" )double price) {
	    	Book Book = new Book();
	    	Book.setPrice(price);
	    	Book.setName(name);
	        KeyHolder t = bookDao.insertReturnKey(Book);
	        if (t.getInt() > 0) {
	            return Book.toString();
	        } else {
	            return "fail";
	        }
	    }
}
