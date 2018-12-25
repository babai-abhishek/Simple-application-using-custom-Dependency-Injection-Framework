package bank.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import bank.entity.AccountMaster;
import bank.entity.Transaction;

public class TransactionDaoImpl extends AbstractDao implements TransactionDao {

	@Override
	public void insert(Transaction transaction) {
		// TODO Auto-generated method stub
		
		int accno=transaction.getAccno();
		String date=transaction.getDate();
		String type=transaction.getType();
		double amount=transaction.getAmount();
		double currentBalance=transaction.getBalance();
		System.out.println("Curr blnc "+currentBalance);
		Connection con = this.getConnection();	
		String sql ="INSERT INTO Transactions (Accno,Date,Type,Amount,Balance) VALUES (?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
			stmt.setInt(1,accno);
			stmt.setString(2, date);
			stmt.setString(3, type);
			stmt.setDouble(4, amount);
			stmt.setDouble(5, currentBalance);
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Transaction transaction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Transaction> retrieve(int accno) {
		
//		AccountMaster accountMaster=null;
		ArrayList<Transaction> transactionList=new ArrayList<Transaction>();
		
		Connection con = this.getConnection();
		String sql = "select * from Transactions where Accno = "+accno+"";
		
		try {
			java.sql.Statement myStmt=con.createStatement();
			ResultSet rs = myStmt.executeQuery(sql);
			while(rs.next()){
				
				int tid = rs.getInt("Tid");
				int Accno = rs.getInt("Accno");
				String date = rs.getString("Date");
				double amount = rs.getDouble("Amount");
				double balance = rs.getDouble("Balance");
				String type = rs.getString("Type");
							
				Transaction transaction=new Transaction();
				transaction.setAccno(Accno);
				transaction.setAmount(amount);
				transaction.setBalance(balance);
				transaction.setDate(date);
				transaction.setTid(tid);
				transaction.setType(type);
				
				transactionList.add(transaction);
								
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		
			return transactionList;
		}
	}

	@Override
	public void delete(int accno) {
		// TODO Auto-generated method stub
		
	}

}
