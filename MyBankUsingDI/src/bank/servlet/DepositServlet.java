package bank.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
import bank.servlet.IExecuteBusiness.ExecuteBalanceCheck;
import bank.servlet.IExecuteBusiness.ExecuteDeposit;

/**
 * Servlet implementation class DepositServlet
 */
@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request,response);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
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
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub

		String accnoStr = request.getParameter("Accno");
		int accno = Integer.parseInt(accnoStr);

		String amountStr = request.getParameter("Amount");
		double amount = Double.parseDouble(amountStr);

		/*Deposit deposit=new Deposit();
		deposit.Execute(accno, amount);*/

		MyDiFactory myDiFactory = new MyDiFactory();
		IExecuteBusiness.ExecuteDeposit execute = null;
		try {	
			try {
				execute = (ExecuteDeposit) myDiFactory.getBusinessObject("Deposit");
			} catch (BadTypeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} catch (InstantiationException | ClassNotFoundException | NoSuchMethodException | SecurityException
				| ParserConfigurationException | SAXException e1) {
			e1.printStackTrace();
		}
		try {
			execute.Execute(accno, amount);
		} catch (InvalidAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsufficientFundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
