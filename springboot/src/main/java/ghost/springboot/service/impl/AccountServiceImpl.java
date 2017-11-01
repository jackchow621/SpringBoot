package ghost.springboot.service.impl;

import ghost.springboot.bean.Account;
import ghost.springboot.dao.IAccountDAO;
import ghost.springboot.service.IAccountService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements IAccountService {
	
	@Autowired
    IAccountDAO accountDAO;

	public int add(Account account) {
		// TODO Auto-generated method stub
		return accountDAO.add(account);
	}

	public int update(Account account) {
		// TODO Auto-generated method stub
		return accountDAO.update(account);
	}

	public int delete(int id) {
		// TODO Auto-generated method stub
		return accountDAO.delete(id);
	}

	public Account findAccountById(int id) {
		// TODO Auto-generated method stub
		return accountDAO.findAccountById(id);
	}

	public List<Account> findAccountList() {
		// TODO Auto-generated method stub
		return accountDAO.findAccountList();
	}

}
