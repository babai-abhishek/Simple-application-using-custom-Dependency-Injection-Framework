package bank.MyDi;

public class BadTypeException extends Exception {
	
	String msg;
	
	public BadTypeException(String msg) {
		// TODO Auto-generated constructor stub
		this.msg = msg;
	}
	
	public String toString(){
		return msg;
		
	}

}
