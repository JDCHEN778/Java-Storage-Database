public class Item implements Comparable<Object>
{
	private String myName;
	private String myCategory;
	private int myStock;	
	private int myUsage;	
	private String myComments;


	public Item(String name, String type, int stock,
			int usage, String comments)
	{
		myName = name;
		myCategory = type;
		myStock = stock;		
		myUsage = usage; 
		myComments = comments;        
	}


	public String getName()
	{
		return myName;
	}
 
	public void setName(String name)
	{
		this.myName = name;
	}

	public String getCategory()
	{
		return myCategory;
	}

	public void setCategory(String category)
	{
		this.myCategory= category;
	}

	public int getStock()
	{
		return myStock;
	}

	public void setStock(int stock)
	{
		this.myStock = stock;
	}

	public int getUsage()
	{
		return myUsage;
	}

	public void setUsage(int usage)
	{
		this.myUsage = usage;
	}
	
	public String getComments()
	{
		return myComments;
	}

	public void setComments(String comments)
	{
		this.myComments = comments;
	}

	public boolean isLow()
	{
		if(myStock <= myUsage)			
			return true;
		else
			return false;
	}		


	public int compareTo(Object otherObject)
	{
		Item other = (Item)otherObject;        
		return myName.compareToIgnoreCase(other.getName());    
	}


	public boolean equals(Object otherObject)
	{
		return this.compareTo(otherObject) == 0;

	}

	public boolean containsSpecific(String text)
	{
		
		if(myName.toLowerCase().equals(text.toLowerCase()))
				return true;
		else if(myCategory.toLowerCase().equals(text.toLowerCase()))
			return true;
		else if(Integer.toString(myStock).toLowerCase().equals(text.toLowerCase()))
			return true;
		else if(Integer.toString(myUsage).toLowerCase().equals(text.toLowerCase()))
			return true;
		else if(myComments.toLowerCase().equals(text.toLowerCase()))
			return true;
		else
			return false;
	}
	
	public boolean containsGeneric(String text)
	{		
		if(myName.toLowerCase().contains(text.toLowerCase()))
			return true;
		else if(myCategory.toLowerCase().contains(text.toLowerCase()))
			return true;
		else if(Integer.toString(myStock).toLowerCase().contains(text.toLowerCase()))
			return true;
		else if(Integer.toString(myUsage).toLowerCase().contains(text.toLowerCase()))
			return true;
		else if(myComments.toLowerCase().contains(text.toLowerCase()))
			return true;
		else
			return false;
	}	

	public String toString()
	{
		return "Name=" + myName + ", Category=" + myCategory + ", Stock=" + myStock + 
				", Approximate Used Weekly=" + myUsage + ", isLow=" + isLow() + ", Comments=" + myComments ;
	}
}
