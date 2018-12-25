package bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import bank.MyDi.BadTypeException;
import bank.MyDi.MyDiFactory;
import bank.business.BankStatement;
import bank.entity.Transaction;
import bank.servlet.IExecuteBusiness.ExecuteBalanceCheck;
import bank.servlet.IExecuteBusiness.ExecuteBankStatement;

/**
 * Servlet implementation class StatementServlet
 */
@WebServlet("/StatementServlet")
public class BankStatementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request,response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | BadTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request,response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | BadTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, BadTypeException {
		// TODO Auto-generated method stub

		String accnoStr=request.getParameter("Accno");
		int accno=Integer.parseInt(accnoStr);

		MyDiFactory myDiFactory = new MyDiFactory();
		IExecuteBusiness.ExecuteBankStatement execute = null;
		try {	
			execute = (ExecuteBankStatement) myDiFactory.getBusinessObject("BankStatement");			
		} catch (InstantiationException | ClassNotFoundException | NoSuchMethodException | SecurityException
				| ParserConfigurationException | SAXException e1) {
			e1.printStackTrace();
		}

		ArrayList<Transaction> transaction = null;
		try {
			transaction = execute.Execute(accno);
		} catch (InvalidAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsufficientFundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();
		String res = gson.toJson(transaction);		
		PrintWriter printWriter = response.getWriter();
		printWriter.println(res);

	}

}
