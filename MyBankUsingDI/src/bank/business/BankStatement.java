package bank.business;

import java.util.ArrayList;

import bank.dao.AccountMasterDao;
import bank.dao.AccountMasterDaoImpl;
import bank.dao.TransactionDao;
import bank.dao.TransactionDaoImpl;
import bank.entity.AccountMaster;
import bank.entity.Transaction;
import bank.servlet.IExecuteBusiness.ExecuteBankStatement;
import bank.servlet.InsufficientFundException;
import bank.servlet.InvalidAccountException;

public class BankStatement implements ExecuteBankStatement{

	private TransactionDao iTransactionDao;

	//DEPENDENCY INJECTED VIA set METHOD
	public void setTransactionDao(TransactionDao transactionDao){
		this.iTransactionDao = transactionDao;
	}

	@Override
	public ArrayList<Transaction> Execute(int accno) throws InvalidAccountException, InsufficientFundException {

		ArrayList<Transaction> transaction=iTransactionDao.retrieve(accno);

		return transaction;

	}


}
