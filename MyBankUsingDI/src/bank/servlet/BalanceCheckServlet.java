package bank.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import bank.MyDi.BadTypeException;
import bank.MyDi.MyDiFactory;
import bank.business.BalanceCheck;
import bank.servlet.IExecuteBusiness.ExecuteBalanceCheck;


@WebServlet("/BalanceCheckServlet")
public class BalanceCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws 
	ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request,response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAccountException e) {
			// TODO Auto-generated catch block
			System.out.println("in servlet "+e);
		}
		catch (BadTypeException e){
			System.out.println(e);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request,response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAccountException e) {
			// TODO Auto-generated catch block
			System.out.println("in servlet post "+e);
		} catch (BadTypeException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws 
	IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException,
	InvalidAccountException, BadTypeException {

		// Get the user Data
		String accnoStr=request.getParameter("Accno");
		int accno=Integer.parseInt(accnoStr);

//		URL resourceUrl = getConfigFileLocation();

//		MyDiFactory myDiFactory = new MyDiFactory(resourceUrl);
		
		MyDiFactory myDiFactory = new MyDiFactory();
		IExecuteBusiness.ExecuteBalanceCheck execute = null;
		try {	
			execute = (ExecuteBalanceCheck) myDiFactory.getBusinessObject("BalanceCheck");			
		} catch (InstantiationException | ClassNotFoundException | NoSuchMethodException | SecurityException
				| ParserConfigurationException | SAXException e1) {
			e1.printStackTrace();
		}
		double balance = execute.Execute(accno);
		System.out.println(balance);

	}


//	private URL getConfigFileLocation() {
//		ServletContext context = getServletContext();
//		URL resourceUrl=null;
//		try {
//			resourceUrl= context.getResource("/WEB-INF/config.xml");
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return resourceUrl;
//	}

}
