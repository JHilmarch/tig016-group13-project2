package model;

import java.net.URL;
import java.util.List;

public class User
{
	private String name;
	private List<URL> urlList;
	private List<Period> periodList;
	
	public User(String name, List<URL> urlList, List<Period> periodList)
	{
		this.name = name;
		this.urlList = urlList;
		this.periodList = periodList;
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
}