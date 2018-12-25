package bank.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.google.gson.Gson;

import bank.MyDi.BadTypeException;
import bank.MyDi.MyDiFactory;
import bank.business.NewAccount;
import bank.entity.AccountMaster;
import bank.servlet.IExecuteBusiness.ExecuteBalanceCheck;
import bank.servlet.IExecuteBusiness.ExecuteNewAccount;

/**
 * Servlet implementation class NewAccountServlet
 */
@WebServlet("/NewAccountServlet")
public class NewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		Gson g = new Gson();
		AccountMaster accountMaster = g.fromJson(buffer.toString(), AccountMaster.class);

		MyDiFactory myDiFactory = new MyDiFactory();
		IExecuteBusiness.ExecuteNewAccount execute = null;
		try {	
			execute = (ExecuteNewAccount) myDiFactory.getBusinessObject("NewAccount");			
		} catch (InstantiationException | ClassNotFoundException | NoSuchMethodException | SecurityException
				| ParserConfigurationException | SAXException e1) {
			e1.printStackTrace();
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
		try {
			execute.Execute(accountMaster);
		} catch (InvalidAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsufficientFundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
