package bank.servlet;

public class InsufficientFundException extends Exception {
	String msg;
	
	public InsufficientFundException(String msg) {
		// TODO Auto-generated constructor stub
		this.msg = msg;
	}
	
	public String toString(){
		return msg;
		
	}
} 