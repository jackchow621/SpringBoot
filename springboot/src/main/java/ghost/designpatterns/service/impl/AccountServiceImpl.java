package ghost.designpatterns.service.impl;

import ghost.designpatterns.bean.Account;
import ghost.designpatterns.dao.IAccountDAO;
import ghost.designpatterns.service.IAccountService;

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
