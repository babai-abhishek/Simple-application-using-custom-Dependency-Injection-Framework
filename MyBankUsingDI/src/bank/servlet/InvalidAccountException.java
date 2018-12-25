package bank.servlet;

public class InvalidAccountException extends Exception {

	String msg;
	
	public InvalidAccountException(String msg) {
		// TODO Auto-generated constructor stub
		this.msg = msg;
	}
	
	public String toString(){
		return msg;
		
	}
}
