package ghost.designpatterns.dao.impl;

import ghost.designpatterns.bean.Account;
import ghost.designpatterns.dao.IAccountDAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements IAccountDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int add(Account account) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update(
				"insert into account(name, money) values(?, ?)",
				account.getName(), account.getMoney());
	}

	public int update(Account account) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update(
				"UPDATE  account SET NAME=? ,money=? WHERE id=?",
				account.getName(), account.getMoney(), account.getId());
	}

	public int delete(int id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE from TABLE account where id=?", id);
	}

	public Account findAccountById(int id) {
		// TODO Auto-generated method stub
		List<Account> list = jdbcTemplate.query(
				"select * from account where id = ?", new Object[] { id },
				new BeanPropertyRowMapper(Account.class));
		if (list != null && list.size() > 0) {
			Account account = list.get(0);
			return account;
		} else {
			return null;
		}
	}

	public List<Account> findAccountList() {
		// TODO Auto-generated method stub
		List<Account> list = jdbcTemplate.query("select * from account",
				new Object[] {}, new BeanPropertyRowMapper(Account.class));
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return new ArrayList<Account>();
		}
	}

}
