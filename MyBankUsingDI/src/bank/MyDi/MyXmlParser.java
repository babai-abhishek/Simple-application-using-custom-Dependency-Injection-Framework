package bank.MyDi;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyXmlParser {
//	URL url;
	String businessClassName = null;
//	public MyXmlParser(URL url) {
//		// TODO Auto-generated constructor stub
//		this.url = url;
//	}
	
	public BusinessSceinario getBusinessSceinario(String tag) throws ParserConfigurationException, SAXException{


		final ArrayList<String> helperClassName = new ArrayList<>();
		final ArrayList<String> helperClassInterfaceType = new ArrayList<>();

		String requestType = tag;
		SAXParserFactory factory = SAXParserFactory.newInstance();
		final SAXParser saxParser = factory.newSAXParser();

		final DefaultHandler defaultHandler = new DefaultHandler() {
			boolean businessClass = false;
			boolean helperClass = false;

			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {


				if (qName.equalsIgnoreCase("request") && requestType.equalsIgnoreCase(attributes.getValue("type"))) {
					businessClassName=attributes.getValue("business-class");         
					businessClass = true;
				} 
				else if (qName.equalsIgnoreCase("helper-class")) {
					helperClass = true;
					if(businessClass){
						helperClassInterfaceType.add(attributes.getValue("type"));
					}

				}

			}

			@Override
			public void characters(char[] ch, int start, int length) throws SAXException {
				if(helperClass && businessClass){
					helperClassName.add(new String(ch, start, length));
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				if(qName.equalsIgnoreCase("helper-class")){
					helperClass = false;

				}
				else if(qName.equalsIgnoreCase("request")){
					businessClass = false;
				}
			}

		};

		try {
			URL confFileLocationUrl=getClass().getResource("conf.xml");
		//	saxParser.parse(url.toString(), defaultHandler);
			saxParser.parse(confFileLocationUrl.toString(), defaultHandler);

		} catch (Exception e) {
			e.printStackTrace();
		}
		BusinessSceinario businessSceinario = new BusinessSceinario();
		businessSceinario.setBusinessClass(businessClassName);
		businessSceinario.setHelperClasses(helperClassName);
		businessSceinario.setHelperClassTypes(helperClassInterfaceType);
		return businessSceinario;
	}
}

