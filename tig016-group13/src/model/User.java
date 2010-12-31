package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class User
{
	static
	{	
		//creates tree structure.
		File file = new File("Save\\");
		file.mkdirs();
		file = null;
	}
	
	private String name;
	private List<URL> urlList;
	private List<Period> periodList;
	private Period currentPeriod;
	private Document dom;
	private boolean written, readed;
	
	public User(String name, List<URL> urlList, List<Period> periodList, Period currentPeriod)
	{
		this.name = name;
		this.urlList = urlList;
		this.periodList = periodList;
		this.currentPeriod = currentPeriod;
		this.written = false;
		this.readed = false;
	}
	
	public User(String name, List<URL> urlList, List<Period> periodList)
	{
		this.name = name;
		this.urlList = urlList;
		this.periodList = periodList;
		this.currentPeriod = new Period("testPeriod");
		this.written = false;
		this.readed = false;
	}
	public User(String name, List<Period> periodList)
	{
		this.name = name;
		this.urlList = new ArrayList<URL>();
		this.periodList = periodList;
		this.currentPeriod = new Period("testPeriod");
		this.written = false;
		this.readed = false;
	}
	
	public User()
	{
		this.name = "emptyUser";
		this.urlList = new ArrayList<URL>();
		this.periodList = new ArrayList<Period>();
		this.currentPeriod = new Period("emptyPeriod");
		this.written = false;
		this.readed = false;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<URL> getUrlList()
	{
		return urlList;
	}

	public void setUrlList(List<URL> urlList)
	{
		this.urlList = urlList;
	}

	public List<Period> getPeriodList()
	{
		return periodList;
	}

	public void setPeriodList(List<Period> periodList)
	{
		this.periodList = periodList;
	}
	
	public Period getCurrentPeriod()
	{
		return currentPeriod;
	}
	
	public void setCurrentPeriod(Period currentPeriod)
	{
		this.currentPeriod = currentPeriod;
	}
	
	public boolean writeProfileToDisk(User user)
	{
		written = true;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		try
		{
			DocumentBuilder db = dbf.newDocumentBuilder();
			dom = db.newDocument();
			
			Element rootEle = dom.createElement("profile");
			Text rootText = dom.createTextNode(dateFormat.format(date));
			rootEle.appendChild(rootText);
			dom.appendChild(rootEle);
			
			Element userElement = createUserElement(user);
			rootEle.appendChild(userElement);
			written = true;
		}
		catch(ParserConfigurationException pce)
		{
			written = false;
			JOptionPane.showMessageDialog(null, "Det går inte att bygga ett dokument!  " + pce
					, "DOCUMENT ERROR!", JOptionPane.ERROR_MESSAGE);
		}
		
		TransformerFactory tfactory = TransformerFactory.newInstance();
	    Transformer serializer;
	    
	    try
	    {
	    	OutputStream outputStream = new FileOutputStream("Save\\" + user.getName() +".xml");
	    	serializer = tfactory.newTransformer();
	    	
	        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	        serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	            
	        serializer.transform(new DOMSource(dom), new StreamResult(outputStream));
	        written = true;
	    }
	    catch (TransformerException e)
	    {
	    	written = false;
	        e.printStackTrace(); 
	        throw new RuntimeException(e);
	    }
	    catch (IOException e)
	    {
			e.printStackTrace();
			written = false;
		}

		return written;
	}
	
	private Element createUserElement(User user)
	{
		Element userNode = dom.createElement("user");
		
		//Begin userName
		Element userNameElement = dom.createElement("name");
		Text nameText = dom.createTextNode(user.getName());
		userNameElement.appendChild(nameText);
		userNode.appendChild(userNameElement);
		//End userName
		
		//Begin url
		Element urlsNode = dom.createElement("urls");
		userNode.appendChild(urlsNode);
		
		for(URL url: user.getUrlList())
		{
			Element urlNode = dom.createElement("url");
			urlsNode.appendChild(urlNode);
			
			Element urlPathElement = dom.createElement("path");
			Text urlPathText = dom.createTextNode(url.toString());
			urlPathElement.appendChild(urlPathText);
			urlNode.appendChild(urlPathElement);
		}
		//End url
		
		//Begin period
		Element periodsNode = dom.createElement("periods");
		userNode.appendChild(periodsNode);
		
		for(Period p: user.getPeriodList())
		{
			periodsNode.appendChild(createPeriodElement(p));
		}
		//End period
		
		//Begin currentPeriod
		Element currentPeriodNode = dom.createElement("currentPeriod");
		currentPeriodNode.appendChild(createPeriodElement(user.getCurrentPeriod()));
		userNode.appendChild(currentPeriodNode);
		//End currentPeriod
		
		return userNode;
	}
	
	private Element createPeriodElement(Period period)
	{
		Element periodNode = dom.createElement("period");
		
		Element periodNameElement = dom.createElement("name");
		Text periodNameText = dom.createTextNode(period.getName());
		periodNameElement.appendChild(periodNameText);
		periodNode.appendChild(periodNameElement);
			
		Element incomeBudgetPosts = dom.createElement("incomeBudgetPosts");
		periodNode.appendChild(incomeBudgetPosts);
			
		for(BudgetPost b : period.getIncomeBudgetPostList())
		{
			incomeBudgetPosts.appendChild(createBudgetPostElement(b));
		}
			
		Element expenceBudgetPosts = dom.createElement("expenceBudgetPosts");
		periodNode.appendChild(expenceBudgetPosts);
			
		for(BudgetPost b : period.getExpenceBudgetPostList())
		{
			expenceBudgetPosts.appendChild(createBudgetPostElement(b));
		}
		return periodNode;
	}
	
	private Element createBudgetPostElement(BudgetPost budgetPost)
	{
		Element budgetPostNode = dom.createElement("budgetPost");
		
		Element budgetNameElement = dom.createElement("name");
		Text budgetNameText = dom.createTextNode(budgetPost.getName());
		budgetNameElement.appendChild(budgetNameText);
		budgetPostNode.appendChild(budgetNameElement);
		
		Element amountElement = dom.createElement("amount");
		Text amountText = dom.createTextNode(""+budgetPost.getAmount());
		amountElement.appendChild(amountText);
		budgetPostNode.appendChild(amountElement);
		
		Element outcomeElement = dom.createElement("outcome");
		Text outcomeText = dom.createTextNode(""+budgetPost.getOutcome());
		outcomeElement.appendChild(outcomeText);
		budgetPostNode.appendChild(outcomeElement);
		
		Element verificationsNode = dom.createElement("verifications");
		budgetPostNode.appendChild(verificationsNode);
		
		for(Verification v : budgetPost.getVerificationList())
		{
			verificationsNode.appendChild(createVerificationElement(v));
		}
		
		return budgetPostNode;
	}
	
	private Element createVerificationElement(Verification verification)
	{
		Element verificationNode = dom.createElement("verification");
		
		Element timeStampElement = dom.createElement("timeStamp");
		Text timeStampText = dom.createTextNode(""+verification.getTimeStamp().getTimeInMillis());
		timeStampElement.appendChild(timeStampText);
		verificationNode.appendChild(timeStampElement);
		
		Element noteElement = dom.createElement("note");
		Text noteText = dom.createTextNode(verification.getNote());
		noteElement.appendChild(noteText);
		verificationNode.appendChild(noteElement);
		
		Element amountElement = dom.createElement("amount");
		Text amountText = dom.createTextNode(""+verification.getAmount());
		amountElement.appendChild(amountText);
		verificationNode.appendChild(amountElement);
		
		return verificationNode;
	}
	
	public boolean readUserFromDisk(String filename)
	{
		readed = true;
		String userName = "";
		ArrayList<Period> listOfPeriods = new ArrayList<Period>();
		User user = new User(userName,listOfPeriods);
		
		try
		{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	        Document doc = docBuilder.parse (new File(filename));
	        user = readDocument(doc);
		}
		
		catch (SAXParseException err)
		{
			readed = false;
			JOptionPane.showMessageDialog(null, "Kan inte läsa dokument.\n" +
					"** Parsing error" + ", line " + err.getLineNumber () +
					", uri " + err.getSystemId () + " " + err.getMessage (),
        			"xml-parser ERROR!",JOptionPane.ERROR_MESSAGE);
	    }
		catch (SAXException e)
		{
			readed = false;
	        Exception x = e.getException ();
	        ((x == null) ? e : x).printStackTrace ();

	    }
		catch (Throwable t)
	    {
			readed = false;
	        t.printStackTrace ();
	    }
		
		this.setName(user.name);
		this.setCurrentPeriod(user.currentPeriod);
		this.setPeriodList(user.periodList);
		this.setUrlList(user.urlList);
		return readed;
	}
	
	private User readDocument(Document doc) throws MalformedURLException
	{
		String userName = "";
		ArrayList<Period> listOfPeriods = new ArrayList<Period>();
		Period currentPeriod = new Period("");
		ArrayList<URL> listOfURLs = new ArrayList<URL>();
		User user = new User(userName,listOfPeriods);
		
		// normalize text representation
        doc.getDocumentElement ().normalize ();
        NodeList profileList = doc.getElementsByTagName("profile");

        Node profileNode = profileList.item(0);
        if(profileNode.getNodeType() == Node.ELEMENT_NODE)
        {
            //Saves the attributes in lists for the node profile.
            Element profileElement = (Element)profileNode;
                
            if(profileElement.getElementsByTagName("user").getLength()==0)
            {
            	readed = false;
            	JOptionPane.showMessageDialog(null, "Det saknas attribut i noden profile.\n" +
            			"Profilen kommer inte att läsas in tom!","DOCUMENT WARNING!",JOptionPane.WARNING_MESSAGE);
            }
                
            else
            {
                NodeList nameList = profileElement.getElementsByTagName("name");
                NodeList urlsList = profileElement.getElementsByTagName("urls");
                NodeList periodsList = profileElement.getElementsByTagName("periods");
                NodeList currentPeriodList = profileElement.getElementsByTagName("currentPeriod");
                
                userName = nameList.item(0).getFirstChild().getNodeValue();
                listOfURLs = readURLs(urlsList);
                listOfPeriods = readPeriods(periodsList);
                currentPeriod = readPeriod((Node)currentPeriodList.item(0));
            }
            user = new User(userName,listOfURLs, listOfPeriods, currentPeriod);
        }
		return user;
	}
	
	private ArrayList<URL> readURLs(NodeList urls) throws MalformedURLException
	{
		ArrayList<URL> listOfURLs = new ArrayList<URL>();
        
        for(int i=0; i<urls.getLength() ; i++)
        {
            Node URLNode = urls.item(i);
            if(URLNode.getNodeType() == Node.ELEMENT_NODE)
            {
            	//Saves the attributes in lists for the node url (childnode to urls).
                Element urlElement = (Element)URLNode;
                
                if(urlElement.getElementsByTagName("path").getLength()==0)
                {
                	readed = false;
                	JOptionPane.showMessageDialog(null, "Det saknas attribut i noden path.\n",
                			"URL WARNING!",JOptionPane.WARNING_MESSAGE);
                }
                
                else
                {
                	NodeList contactNameList = urlElement.getElementsByTagName("path");
                    
                    String urlPath = contactNameList.item(0).getFirstChild().getNodeValue();
                    URL url = new URL(urlPath);
                    listOfURLs.add(url);
                }      
            }
        }
		return listOfURLs;
	}
	
	private ArrayList<BudgetPost> readBudgetPosts(NodeList budgetPosts)
	{
		ArrayList<BudgetPost> listOfBudgetPosts = new ArrayList<BudgetPost>();
		
		for(int i=0; i<budgetPosts.getLength() ; i++)
        {
			BudgetPost budgetPost = readBudgetPost((Node)budgetPosts.item(i));
			listOfBudgetPosts.add(budgetPost);
        }
		return listOfBudgetPosts;
	}

	private BudgetPost readBudgetPost(Node b)
	{
		Element budgetElement = (Element)b;
		
        ArrayList<Verification> listOfVerifications = new ArrayList<Verification>();
        BudgetPost budgetPost = new BudgetPost("emptyVer", 0, 0, listOfVerifications);
        
        if(budgetElement.getElementsByTagName("name").getLength()==0 ||
        		budgetElement.getElementsByTagName("amount").getLength()==0 ||
        		budgetElement.getElementsByTagName("outcome").getLength()==0 ||
        		budgetElement.getElementsByTagName("verifications").getLength()==0)
        {
        	readed = false;
        	JOptionPane.showMessageDialog(null, "Det saknas attribut en budgetpost.\n" +
        			"En budgetpost kommer inte att läsas in!","BUDGETPOST WARNING!",JOptionPane.WARNING_MESSAGE);
        }
        
        else
        {
        	NodeList budgetPostNameList = budgetElement.getElementsByTagName("name");
        	NodeList budgetPostAmountList = budgetElement.getElementsByTagName("amount");
        	NodeList budgetPostOutcomeList = budgetElement.getElementsByTagName("outcome");
        	NodeList budgetPostVerificationsList = budgetElement.getElementsByTagName("verifications");
        	
        	String budgetPostName = budgetPostNameList.item(0).getFirstChild().getNodeValue();
        	
        	double amount;
            try
            {
            	amount = Double.parseDouble(budgetPostAmountList.item(0).getFirstChild().getNodeValue());
            }
            catch(Exception e)
            {
            	readed = false;
            	JOptionPane.showMessageDialog(null, "Värdet \"amount\" är inte numeriskt!" +
            			"Värdet skrivs till noll.","BUDGET WARNING!",JOptionPane.WARNING_MESSAGE);
            	amount = 0;
            }
            
            double outcome;
            try
            {
            	outcome = Double.parseDouble(budgetPostOutcomeList.item(0).getFirstChild().getNodeValue());
            }
            catch(Exception e)
            {
            	readed = false;
            	JOptionPane.showMessageDialog(null, "Fel i en budgetpost: Värdet \"outcome\" är inte numeriskt!" +
            			"Värdet skrivs till noll.","BUDGET WARNING!",JOptionPane.WARNING_MESSAGE);
            	outcome = 0;
            }
        	
        	listOfVerifications = readVerifications(budgetPostVerificationsList);
        	
        	budgetPost = new BudgetPost(budgetPostName, amount, outcome, listOfVerifications);
        }
        return budgetPost;
	}
	
	private ArrayList<Period> readPeriods(NodeList periods)
	{
		ArrayList<Period> listOfPeriods = new ArrayList<Period>();
		
		for(int i=0; i<periods.getLength() ; i++)
        {
			Period period = readPeriod((Node)periods.item(i));
            listOfPeriods.add(period);
        }		
		return listOfPeriods;
	}
	
	private Period readPeriod(Node p)
	{
		Element periodElement = (Element)p;
        ArrayList<BudgetPost> currentPeriodIncome = new ArrayList<BudgetPost>();
        ArrayList<BudgetPost> currentPeriodExpence = new ArrayList<BudgetPost>();
        Period period = new Period("emptyPeriod", currentPeriodIncome, currentPeriodExpence);
        
        if(periodElement.getElementsByTagName("name").getLength()==0)
        {
        	readed = false;
        	JOptionPane.showMessageDialog(null, "Det saknas attribut i en period.\n" +
        			"Perioden kommer att läsas in tom!","PERIOD WARNING!",JOptionPane.WARNING_MESSAGE);
        }
        
        else
        {
        	NodeList currentPeriodNameList = periodElement.getElementsByTagName("name");
        	NodeList currentPeriodIncomeList = periodElement.getElementsByTagName("incomeBudgetPosts");
        	NodeList currentPeriodExpenceList = periodElement.getElementsByTagName("expenceBudgetPosts");
        	
        	String currentPeriodName = currentPeriodNameList.item(0).getFirstChild().getNodeValue();
        	currentPeriodIncome = readBudgetPosts(currentPeriodIncomeList);
        	currentPeriodExpence = readBudgetPosts(currentPeriodExpenceList);
        	
        	period = new Period(currentPeriodName, currentPeriodIncome, currentPeriodExpence);
        }
        return period;
	}
	
	private ArrayList<Verification> readVerifications(NodeList verifications)
	{
		ArrayList<Verification> listOfVerifications = new ArrayList<Verification>();
		
		for(int i=0; i<verifications.getLength() ; i++)
        {
			Verification verification = readVerification((Node)verifications.item(i));
			listOfVerifications.add(verification);
        }		
		return listOfVerifications;
	}
	
	private Verification readVerification(Node v)
	{
		Element verificationElement = (Element)v;
        
		TimeZone timeZone = TimeZone.getDefault();
		Verification verification = new Verification(0, timeZone, "emptyVer", 0);
        
        if(verificationElement.getElementsByTagName("timeStamp").getLength()==0 ||
        		verificationElement.getElementsByTagName("note").getLength()==0 ||
        		verificationElement.getElementsByTagName("amount").getLength()==0)
        {
        	readed = false;
        	JOptionPane.showMessageDialog(null, "Det saknas attribut en verifikation.\n" +
        			"Verifikationen kommer att läsas in tom!","VERIFICATION WARNING!",JOptionPane.WARNING_MESSAGE);
        }
        
        else
        {
        	NodeList timeStampList = verificationElement.getElementsByTagName("timeStamp");
        	NodeList noteList = verificationElement.getElementsByTagName("note");
        	NodeList amountList = verificationElement.getElementsByTagName("amount");
        	
        	long timeStampRepresentation;
            try
            {
            	timeStampRepresentation = Long.parseLong(timeStampList.item(0).getFirstChild().getNodeValue());
            }
            catch(Exception e)
            {
            	readed = false;
            	JOptionPane.showMessageDialog(null, "Värdet \"timeStamp\" är inte numeriskt!" +
            			"Värdet skrivs till noll.","VERIFICATION WARNING!",JOptionPane.WARNING_MESSAGE);
            	timeStampRepresentation = 0;
            }
            
            double amount;
            try
            {
            	amount = Double.parseDouble(amountList.item(0).getFirstChild().getNodeValue());
            }
            catch(Exception e)
            {
            	readed = false;
            	JOptionPane.showMessageDialog(null, "Värdet \"amount\" är inte numeriskt!" +
            			"Värdet skrivs till noll.","VERIFICATION WARNING!",JOptionPane.WARNING_MESSAGE);
            	amount = 0;
            }
            
        	String note = noteList.item(0).getFirstChild().getNodeValue();
        	
        	verification = new Verification(timeStampRepresentation, timeZone, note, amount);
        }
        return verification;
	}
}