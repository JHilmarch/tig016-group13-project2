package model;

import gui.LogMessage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import util.HelpFunctions;

/*
 * TIG061 H10 MDI - IT-universitetet i Göteborg
 * @version Prototyp 3
 * @author Jonatan Hilmarch
 * hilmarch@skip.chalmers.se
 */
public class User implements Cloneable
{
	static
	{
		//creates directory tree structure.
		File file = new File("Save" + File.separator);
		file.mkdirs();
		file = new File("Report" + File.separator);
		file.mkdirs();
		file = null;
	}
	
	private String name;
	private ArrayList<URL> urlList;
	private ArrayList<Period> periodList;
	private Period currentPeriod;
	private Document dom;
	private boolean written, readed;
	private StringBuilder log;
	
	public User(String name, ArrayList<URL> urlList, ArrayList<Period> periodList, Period currentPeriod)
	{
		this.name = name;
		this.urlList = urlList;
		this.periodList = periodList;
		this.currentPeriod = currentPeriod;
		this.written = false;
		this.readed = false;
		this.log = new StringBuilder();
	}
	
	public User(String name, ArrayList<URL> urlList, ArrayList<Period> periodList)
	{
		this.name = name;
		this.urlList = urlList;
		this.periodList = periodList;
		this.currentPeriod = new Period("testPeriod");
		this.written = false;
		this.readed = false;
		this.log = new StringBuilder();
	}
	public User(String name, ArrayList<Period> periodList)
	{
		this.name = name;
		this.urlList = new ArrayList<URL>();
		this.periodList = periodList;
		this.currentPeriod = new Period("testPeriod");
		this.written = false;
		this.readed = false;
		this.log = new StringBuilder();
	}
	
	public User()
	{
		this.name = "emptyUser";
		this.urlList = new ArrayList<URL>();
		this.periodList = new ArrayList<Period>();
		this.currentPeriod = new Period("emptyPeriod");
		this.written = false;
		this.readed = false;
		this.log = new StringBuilder();
	}
	
	public Object clone() throws java.lang.CloneNotSupportedException
	{
		User user = (User) super.clone();
		user.currentPeriod = (Period) currentPeriod.clone();
		user.periodList = HelpFunctions.clonePeriodList(periodList);
		//user.urlList = (ArrayList<URL>) urlList.clone();
		return user;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public ArrayList<URL> getUrlList()
	{
		return urlList;
	}

	public void setUrlList(ArrayList<URL> urlList)
	{
		this.urlList = urlList;
	}

	public ArrayList<Period> getPeriodList()
	{
		return periodList;
	}

	public void setPeriodList(ArrayList<Period> periodList)
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
	
	public boolean writeProfileToDisk(UserHandler uh)
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
			
			Element userElement = createUserElement(uh.getCurrentUser());
			rootEle.appendChild(userElement);
			written = true;
		}
		catch(ParserConfigurationException pce)
		{
			written = false;
			JOptionPane.showMessageDialog(null, "Det gÂr inte att bygga ett dokument!  " + pce
					, "DOCUMENT ERROR!", JOptionPane.ERROR_MESSAGE);
		}
		
		TransformerFactory tfactory = TransformerFactory.newInstance();
	    Transformer serializer;
	    
	    try
	    {
	    	OutputStream outputStream = new FileOutputStream("Save" + File.separator + getName() +".xml");
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
		log = new StringBuilder();
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
			JOptionPane.showMessageDialog(null, "Kan inte l‰sa dokument.\n" +
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
		
		if(log.length() > 0)
		{
			log.append("------------------------\nVarningar behöver inte betyda att profilen har ändrats" +
					" \neller att någonting är fel. " +
					"Applikationen är under utveckling \noch inläsningsfunktionen skall testas ytterligare.");
			LogMessage logMessage = new LogMessage(log.toString(), "Inläsningslogg");
		}
		
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
            	log.append("VARNING: Det saknas attribut i noden \"profile\".\n");
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
	
	private ArrayList<URL> readURLs(NodeList urlsNodeList) throws MalformedURLException
	{
		ArrayList<URL> listOfURLs = new ArrayList<URL>();
		
		Node urlsNode = urlsNodeList.item(0);
        if(urlsNode.getNodeType() == Node.ELEMENT_NODE)
        {
        	Element urlsElement = (Element)urlsNode;
        	
        	if(urlsElement.getElementsByTagName("url").getLength()==0)
        	{
        		readed = false;
            	log.append("VARNING: Det saknas attribut i noden \"urls\".\n");
        	}
        	
        	else
        	{
        		NodeList urls = urlsElement.getElementsByTagName("url");
                
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
                        	log.append("VARNING: Det saknas attribut i noden \"path\".\n");
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
        	}
        }
		return listOfURLs;
	}
	
	private ArrayList<BudgetPost> readBudgetPosts(NodeList budgetPostsNodeList)
	{
		ArrayList<BudgetPost> listOfBudgetPosts = new ArrayList<BudgetPost>();
		
		Node budgetPostsNode = budgetPostsNodeList.item(0);
        if(budgetPostsNode.getNodeType() == Node.ELEMENT_NODE)
        {
        	Element budgetPostsElement = (Element)budgetPostsNode;
        	
        	if(budgetPostsElement.getElementsByTagName("budgetPost").getLength()==0)
        	{
        		readed = false;
        		log.append("VARNING: Det saknas attribut i noden \"budgetPost\".\n");
        	}
        	
        	else
        	{
        		NodeList budgetPosts = budgetPostsElement.getElementsByTagName("budgetPost");
        		for(int i=0; i<budgetPosts.getLength() ; i++)
                {
        			BudgetPost budgetPost = readBudgetPost((Node)budgetPosts.item(i));
        			listOfBudgetPosts.add(budgetPost);
                }
        	}
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
        	log.append("VARNING: Det saknas attribut i en budgetpost eller så är listan tom.\n");
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
            	log.append("FEL: Värdet (Budgetpost) \"amount\" är inte numeriskt och skrivs till noll!.\n");
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
            	log.append("FEL: Värdet (Budgetpost) \"outcome\" är inte numeriskt och skrivs till noll!.\n");
            	outcome = 0;
            }
        	
        	listOfVerifications = readVerifications(budgetPostVerificationsList);
        	
        	budgetPost = new BudgetPost(budgetPostName, amount, outcome, listOfVerifications);
        }
        return budgetPost;
	}
	
	private ArrayList<Period> readPeriods(NodeList periodsNodeList)
	{
		
		ArrayList<Period> listOfPeriods = new ArrayList<Period>();
		
		Node periodsNode = periodsNodeList.item(0);
        if(periodsNode.getNodeType() == Node.ELEMENT_NODE)
        {
        	Element periodsElement = (Element)periodsNode;
        	
        	if(periodsElement.getElementsByTagName("period").getLength()==0)
        	{
        		readed = false;
        		log.append("VARNING: Det saknas attribut i noden \"period\".\n");
        	}
        	
        	else
        	{
        		NodeList periods = periodsElement.getElementsByTagName("period");
        		for(int i=0; i<periods.getLength() ; i++)
                {
        			Period period = readPeriod((Node)periods.item(i));
                    listOfPeriods.add(period);
                }	
        	}
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
        	log.append("VARNING: Det saknas attribut i en period.\n");
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
	//asdasd
	private ArrayList<Verification> readVerifications(NodeList verificationsNodeList)
	{
		ArrayList<Verification> listOfVerifications = new ArrayList<Verification>();
		
		Node verificationsNode = verificationsNodeList.item(0);
        if(verificationsNode.getNodeType() == Node.ELEMENT_NODE)
        {
        	Element verificationsElement = (Element)verificationsNode;
        	
        	if(verificationsElement.getElementsByTagName("verification").getLength()==0)
        	{
        		readed = false;
        		log.append("VARNING: Det saknas attribut i noden \"verification\".\n");
        	}
        	
        	else
        	{
        		NodeList verifications = verificationsElement.getElementsByTagName("verification");
        		
        		for(int i=0; i<verifications.getLength() ; i++)
                {
        			Verification verification = readVerification((Node)verifications.item(i));
        			listOfVerifications.add(verification);
                }		
        	}
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
        	log.append("VARNING: Det saknas attribut i en verifikation.\n");
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
            	log.append("FEL: Värdet (Verifikation) \"timeStamp\" är inte numeriskt och skrivs därför till noll!\n");
            	timeStampRepresentation = 0;
            }
            
            double amount;
            try
            {
            	amount = Double.parseDouble((amountList.item(0).getFirstChild().getNodeValue()));
            }
            catch(Exception e)
            {
            	readed = false;
            	log.append("FEL: Värdet (Verifikation) \"amount\" är inte numeriskt och skrivs därför till noll!\n");
            	amount = 0;
            }
            
        	String note = noteList.item(0).getFirstChild().getNodeValue();
        	
        	verification = new Verification(timeStampRepresentation, timeZone, note, amount);
        }
        return verification;
	}
	
	public void createNewPeriod(UserHandler uh)
	{
		String name = JOptionPane.showInputDialog("Var snäll och mata in periodens namn: ");
		
		if(name !=null)
		{
			if(name.length() >= 1)
			{
				try {
					uh.setLastUser((User) uh.getCurrentUser().clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Period period = new Period(name);
				periodList.add(period);
				currentPeriod = period;
			}		
		}
	}
	
	public void openPeriod(UserHandler uh)
	{
		Object[] data = new Object[periodList.size()];
		for(int i = 0; i < uh.getCurrentUser().getPeriodList().size(); i++)
		{ 
		    Period p = periodList.get(i);
			data[i] = p.getName();
		}
		
		String s = (String)JOptionPane.showInputDialog(
                null,
                "Var vänlig och välj period: ",
                "Öppna period",
                JOptionPane.PLAIN_MESSAGE,
                null, data,
                data[0]);
		
		if(s != null)
		{
			for(Period p : periodList)
			{
				if(p.getName().equals(s))
				{
					try {
						uh.setLastUser((User) uh.getCurrentUser().clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					currentPeriod = p;
				}
			}
		}
	}
	
	public boolean writeReportToDisk()
	{
		boolean written = false;
		double totalIncomeAmount = 0;
		double totalExpenceAmount = 0;
		double totalIncomeOutcome = 0;
		double totalExpenceOutcome = 0;
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		File dir = new File(".");
		String dirText = "";
		try
		{
			dirText = dir.getCanonicalPath();
		}
		catch(IOException exception)
		{
			dirText = "FEL: Aktuell katalog hittades inte!";
		}
		
		try
		{	
		    DateFormat df1 = new SimpleDateFormat("yyyyMMdd");
		    DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
		    Date today = Calendar.getInstance().getTime();        
		    String reportDate1 = df1.format(today);
		    String reportDate2 = df2.format(today);
		    
		    String filename = "Report" + File.separator + 
		    name + "_" + reportDate1;
		    File file = new File(filename +  ".txt");
		 
			int i = 1;
		    while(file.exists())
		    {
		   	 	file = new File(filename + "_" + i + ".txt");
		    	i++;
		    }
		     
			FileWriter fstream = new FileWriter(file);
			 
			BufferedWriter out = new BufferedWriter(fstream);
			StringBuilder sb = new StringBuilder();
			sb.append("Rapport för användare " + name + " skriven till fil!\n" +
					"Datum: " + reportDate2 + "\n" +
					"Katalog: " + dirText + "\n----\n");
			 
			for(Period period : periodList)
			{
				sb.append("\n" + period.getName());
				if(!period.getIncomeBudgetPostList().isEmpty())
				{
					sb.append("\nInkomster\nBudgetpost\tBelopp\tUtfall");
					 
					for(BudgetPost bp : period.getIncomeBudgetPostList())
					{
						 totalIncomeAmount += bp.getAmount();
						 totalIncomeOutcome += bp.getOutcome();
						 sb.append(bp.toString());
					}
					 
					sb.append("\n\nTOTALT INKOMSTER:\tBelopp: " + df.format(totalIncomeAmount) + "\tUtfall: " + df.format(totalIncomeOutcome));
				}
				 
				if(!period.getExpenceBudgetPostList().isEmpty())
				{
					sb.append("\n\nUtgifter\nBudgetpost\tBelopp\tUtfall");
					 
					for(BudgetPost bp : period.getExpenceBudgetPostList())
					{
						totalExpenceAmount += bp.getAmount();
						totalExpenceOutcome += bp.getOutcome();
						sb.append(bp.toString());
					}
					 
					sb.append("\n\nTOTALT UTGIFTER:\tBelopp: " + df.format(totalExpenceAmount) + "\tUtfall: " + df.format(totalExpenceOutcome));
				}
				 
				sb.append("\n\nSammanställt utgifter och inkomster:\nBelopp: " + df.format(totalIncomeAmount - totalExpenceAmount) +
						"\tUtfall: " + df.format(totalExpenceOutcome - totalExpenceOutcome) + "\n----");
						 
			}
			 
			out.write(sb.toString());
			 
			out.close();
			written = true;
			 
			LogMessage logg = new LogMessage(sb.toString(), "Rapport skriven till fil!");
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}
		
		return written;
	}
}