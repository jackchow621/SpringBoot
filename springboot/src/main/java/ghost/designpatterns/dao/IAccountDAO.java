package ghost.designpatterns.dao;

import ghost.designpatterns.bean.Account;

import java.util.List;

public interface IAccountDAO{
	public int add(Account account);

    public int update(Account account);

    public int delete(int id);

    public Account findAccountById(int id);

    public List<Account> findAccountList();
}