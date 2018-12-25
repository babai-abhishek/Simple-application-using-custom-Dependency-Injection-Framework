package bank.MyDi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import bank.business.BalanceCheck;

public class MyDiFactory {

//	URL url;
//	public MyDiFactory(URL url) {
//		// TODO Auto-generated constructor stub
//		this.url = url;
//	}

	// return the business class object against the tag
	public Object getBusinessObject(String type) throws ParserConfigurationException, SAXException,
			InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException, BadTypeException {

//		MyXmlParser myXmlParser = new MyXmlParser(url);
		
		MyXmlParser myXmlParser = new MyXmlParser();
		BusinessSceinario obj = myXmlParser.getBusinessSceinario(type);

		String businessClass = obj.getBusinessClass();
		ArrayList<String> helperClassNames = obj.getHelperClasses();
		ArrayList<String> helperClassTypes = obj.getHelperClassTypes();

		Class<?> bClass = Class.forName(businessClass);
		Object businessClassObject = bClass.newInstance();

		for (int i = 0; i < helperClassNames.size(); i++) {
			Class<?> helperClass = Class.forName(helperClassNames.get(i));
			Class<?> helperInterface = Class.forName(helperClassTypes.get(i));

			// check the helper-class has implemented the specified type
			if (!helperInterface.isAssignableFrom(helperClass)) 
			{
				// if check fails , throw BadTypeException
				throw new BadTypeException("Bad interface type "+helperInterface.getName());
			}
			Method setMethod = businessClassObject.getClass().getDeclaredMethod("set" + helperInterface.getSimpleName(),
					helperInterface);
			setMethod.invoke(businessClassObject, helperClass.newInstance());
			
		}
		return businessClassObject;

	}
}
