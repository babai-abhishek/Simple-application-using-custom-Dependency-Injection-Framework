package bank.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import bank.MyDi.BadTypeException;
import bank.MyDi.MyDiFactory;
import bank.business.Deposit;
import bank.business.Withdraw;
import bank.servlet.IExecuteBusiness.ExecuteBalanceCheck;
import bank.servlet.IExecuteBusiness.ExecuteWithdraw;

/**
 * Servlet implementation class WithdrawServlet
 */
@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request,response);
		} catch (InvalidAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InsufficientFundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request,response);
		} catch (InvalidAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsufficientFundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws InvalidAccountException, InsufficientFundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, BadTypeException {
		// TODO Auto-generated method stub
		
		String accnoStr = request.getParameter("Accno");
		int accno = Integer.parseInt(accnoStr);
		
		String amountStr = request.getParameter("Amount");
		double amount = Double.parseDouble(amountStr);
		
		/*Withdraw withdraw=new Withdraw();
		withdraw.Execute(accno, amount);*/
		
		MyDiFactory myDiFactory = new MyDiFactory();
		Object obj = null;
		IExecuteBusiness.ExecuteWithdraw execute = null;
		try {	
			execute = (ExecuteWithdraw) myDiFactory.getBusinessObject("Withdraw");			
		} catch (InstantiationException | ClassNotFoundException | NoSuchMethodException | SecurityException
				| ParserConfigurationException | SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		execute.Execute(accno, amount);		
	}
}
