package bank.business;

import bank.dao.AccountMasterDao;
import bank.entity.AccountMaster;
import bank.servlet.IExecuteBusiness.ExecuteBalanceCheck;
import bank.servlet.InvalidAccountException;

public class BalanceCheck implements ExecuteBalanceCheck{
	
	private AccountMasterDao iAccountMasterDao;

	//DEPENDENCY INJECTED VIA set METHOD
	public void setAccountMasterDao(AccountMasterDao accountMasterDao){
		this.iAccountMasterDao = accountMasterDao;
	}

	@Override
	public double Execute(int accno) throws InvalidAccountException {
		// TODO Auto-generated method stub
		System.out.println("accno "+accno);
		AccountMaster accountMaster = null;
		accountMaster = iAccountMasterDao.retrieve(accno);	
		double balance = accountMaster.getBalance();
		System.out.println("balance found "+balance);
		
		return balance;
	}
	
	
	


}
