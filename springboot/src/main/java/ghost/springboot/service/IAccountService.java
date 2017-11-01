package ghost.springboot.service;

import ghost.springboot.bean.Account;

import java.util.List;

public interface IAccountService {
	public int add(Account account);
	
    public int update(Account account);
    
    public int delete(int id);
    
    public Account findAccountById(int id);
    
    public List<Account> findAccountList();
}
