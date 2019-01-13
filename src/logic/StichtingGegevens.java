package logic;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import logic.Stichting;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

public class StichtingGegevens {
	
	/* Produces an XML to store the name of Stichting and the balance.
	 * When the program is started, the XML file is being read and the balance is put in the orderscreen
	 * when adding new orders, the balance will be updated and when closing the application the new balance
	 * is overwritten in the xml file
	 */
	
	
	public Stichting readXML(String xml) {
        String stichtingNaam = "Yoga B.V.";
        String balance = "25.10";
        ArrayList<String> rolev;
        rolev = new ArrayList<String>();
        Document dom;
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the    
            // XML file
            dom = db.parse(xml);

            Element doc = dom.getDocumentElement();
            

            stichtingNaam = getTextValue(stichtingNaam, doc, "stichtingNaam");
            if (stichtingNaam != null) {
                if (!stichtingNaam.isEmpty())
                    rolev.add(stichtingNaam);
            }
            balance = getTextValue(balance, doc, "balance");
            if (balance != null) {
                if (!balance.isEmpty())
                    rolev.add(balance);
            }
            
           Stichting newStichting = new Stichting(stichtingNaam, Double.parseDouble(balance));
           
           return newStichting;

        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
		return null;

       
    }
	
	private String getTextValue(String def, Element doc, String tag) {
	    String value = def;
	    NodeList nl;
	    nl = doc.getElementsByTagName(tag);
	    if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
	        value = nl.item(0).getFirstChild().getNodeValue();
	    }
	    return value;
	}

	public boolean saveToXML(String xml, String stichtingNaam, String balance) {
	    Document dom;
	    Element e = null;

	    // instance of a DocumentBuilderFactory
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    try {
	        // use factory to get an instance of document builder
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        // create instance of DOM
	        dom = db.newDocument();

	        // create the root element
	        Element rootEle = dom.createElement("roles");

	        // create data elements and place them under root
	        e = dom.createElement("StichtingNaam");
	        e.appendChild(dom.createTextNode(stichtingNaam));
	        rootEle.appendChild(e);

	        e = dom.createElement("Balans");
	        e.appendChild(dom.createTextNode(balance));
	        rootEle.appendChild(e);

	        dom.appendChild(rootEle);

	        try {
	            Transformer tr = TransformerFactory.newInstance().newTransformer();
	            tr.setOutputProperty(OutputKeys.INDENT, "yes");
	            tr.setOutputProperty(OutputKeys.METHOD, "xml");
	            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            //tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
	            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	            // send DOM to file
	            tr.transform(new DOMSource(dom), 
	                                 new StreamResult(new FileOutputStream(xml)));

	        } catch (TransformerException te) {
	            System.out.println(te.getMessage());
	            return false;
	        } catch (IOException ioe) {
	            System.out.println(ioe.getMessage());
	            return false;
	        }
	    } catch (ParserConfigurationException pce) {
	        System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
	        return false;
	    }
		return true;
	}
}
	


