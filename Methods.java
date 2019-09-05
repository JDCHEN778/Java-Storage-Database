import java.io.*;
import java.util.*;
import java.util.Scanner;

//import javax.swing.JFrame;
//import javax.swing.JOptionPane;

import java.io.File;

public class Methods 
{

	private ArrayList<Item> myList;	



	public Methods(ArrayList<Item> itemList) 
	{		
		myList = itemList;		
	}

	public ArrayList<Item> loadData()
	{
		Scanner inFile;

		String fileName = "ItemList.txt";
		String name;
		String type;
		int stock;    	
		int usage;	
		String comments;   	      

		try
		{
			File file = new File(fileName);
			file.createNewFile();		

		}
		catch(IOException e)
		{
			System.out.println("Error could not create file " + e.getMessage());
		}


		try
		{

			inFile = new Scanner(new File(fileName));

			while(inFile.hasNextLine())
			{ 		   				
				name = inFile.nextLine();
				type = inFile.nextLine();
				stock = Integer.parseInt(inFile.nextLine());        		   
				usage = Integer.parseInt(inFile.nextLine());
				comments = inFile.nextLine();	
				inFile.nextLine();
				myList.add(new Item(name, type, stock, usage, comments));							
			}
			inFile.close();	

		}
		catch(IOException i)
		{
			System.out.println("Error could not find file " + i.getMessage());
		}
		return myList;
	}

	public void mergeSort(ArrayList <Item> list, int first, int last)
	{
		if(first == last)
		{
			return;
		}
		else if (last-first == 1)
		{
			if(list.get(first).compareTo(list.get(last)) > 0)
			{
				ArrayList<Item> var = new ArrayList<Item>();
				var.add(list.get(first));
				list.set(first, list.get(last));
				list.set(last, var.get(0));
			}               
		}
		else
		{
			int mid = (first + last) / 2;
			mergeSort(list, first, mid);
			mergeSort(list, mid+1, last);
			merge(list, first, mid, last);
		}
	}

	// creates a copy of ArrayList list called temp, and uses temp’s values to properly merge(sort) list
	// from first to last 
	public void merge(ArrayList<Item> list, int first, int mid, int last)
	{
		ArrayList<Item> merged = new ArrayList<Item>();
		int index1 = first, index2 = mid + 1;
		for(int i = first; i <= last; i++)
		{
			if (index1 > mid)
			{
				merged.add(list.get(index2));
				index2++;
			}
			else if (index2 > last)
			{
				merged.add(list.get(index1));
				index1++;
			}
			else if (list.get(index1).compareTo(list.get(index2)) < 0)
			{
				merged.add(list.get(index1));
				index1++;
			}
			else
			{
				merged.add(list.get(index2));
				index2++;
			}            
		}
		int mergedIndex = 0;
		for (int j = first; j <= last; j++)
		{
			list.set(j,merged.get(mergedIndex));
			mergedIndex++;
		}
	}

	public void reversemergeSort(ArrayList <Item> list, int first, int last)
	{
		if(first == last)
		{
			return;
		}
		else if (last-first == 1)
		{
			if(list.get(first).compareTo(list.get(last)) < 0)
			{
				ArrayList<Item> var = new ArrayList<Item>();
				var.add(list.get(first));
				list.set(first, list.get(last));
				list.set(last, var.get(0));
			}               
		}
		else
		{
			int mid = (first + last) / 2;
			reversemergeSort(list, first, mid);
			reversemergeSort(list, mid+1, last);
			reversemerge(list, first, mid, last);
		}
	}

	// creates a copy of ArrayList list called temp, and uses temp’s values to properly merge(sort) list
	// from first to last 
	public void reversemerge(ArrayList<Item> list, int first, int mid, int last)
	{
		ArrayList<Item> merged = new ArrayList<Item>();
		int index1 = first, index2 = mid + 1;
		for(int i = first; i <= last; i++)
		{
			if (index1 > mid)
			{
				merged.add(list.get(index2));
				index2++;
			}
			else if (index2 > last)
			{
				merged.add(list.get(index1));
				index1++;
			}
			else if (list.get(index1).compareTo(list.get(index2)) > 0)
			{
				merged.add(list.get(index1));
				index1++;
			}
			else
			{
				merged.add(list.get(index2));
				index2++;
			}            
		}
		int mergedIndex = 0;
		for (int j = first; j <= last; j++)
		{
			list.set(j,merged.get(mergedIndex));
			mergedIndex++;
		}
	}


	public ArrayList<Item> organizeList()
	{
		for (int outer = 1; outer < myList.size(); outer++)
		{
			int position = outer;
			Item key = myList.get(position);    
			while (position > 0 && myList.get(position - 1).compareTo(key) >= 0)
			{
				myList.set(position, myList.get(position - 1));
				position--;
			}
			myList.set(position, key);
		}
		return myList;
	}	

	public void organizeCategory(ArrayList<Item> array)
	{
		for (int outer = 1; outer < array.size(); outer++)
		{
			int position = outer;
			Item key = array.get(position);    
			while (position > 0 && array.get(position - 1).getCategory().compareTo(key.getCategory()) > 0)
			{
				array.set(position, array.get(position - 1));
				position--;
			}
			array.set(position, key);
		}
		//return myList;
	}

	public void organizeStock(ArrayList<Item> array)
	{
		for (int outer = 1; outer < array.size(); outer++)
		{
			int position = outer;
			Item key = array.get(position);    
			while (position > 0 && array.get(position - 1).getStock() >= (key.getStock()))
			{
				array.set(position, array.get(position - 1));
				position--;
			}
			array.set(position, key);
		}
		//return myList;
	}

	public void organizeUsage(ArrayList<Item> array)
	{
		for (int outer = 1; outer < array.size(); outer++)
		{
			int position = outer;
			Item key = myList.get(position);    
			while (position > 0 && array.get(position - 1).getUsage() >= (key.getUsage()))
			{
				array.set(position, array.get(position - 1));
				position--;
			}
			array.set(position, key);
		}
		//return myList;
	}

	public void modifyFile()
	{
		try
		{    
			PrintWriter fw = new PrintWriter( new FileWriter("ItemList.txt"));	           	           
			for( int a = 0; a < myList.size(); a++)
			{        	   
				fw.println(myList.get(a).getName()); 
				fw.println(myList.get(a).getCategory());
				fw.println(myList.get(a).getStock());					 
				fw.println(myList.get(a).getUsage());
				fw.println(myList.get(a).getComments());
				fw.println();
			}        
			fw.close();    
		}
		catch(Exception e)
		{
			//JFrame frame = new JFrame();
			//JOptionPane.showMessageDialog(frame, "File not found");
			System.out.println(e);
		}    
	}  	

	public Item returnAll(ArrayList<Item> insert)
	{
		Item value = null;
		for(int i = 0; i < myList.size(); i++)
		{
			value = insert.get(i);		
		}
		return value;		
	}


	public ArrayList<Item> addItem(String name, String type, int stock, 
			int usage, String comments)
	{		
		Item added = new Item(name, type, stock, usage, comments); 
		myList.add(added);
		//mergeSort(myList, 0, myList.size());
		organizeList();		
		return myList;
	}	

	public ArrayList<Item> editItem(String old, String name, String type, int stock, 
			int usage, String comments )
	{		
		for(int index = 0; index < myList.size(); index++)
		{
			if(myList.get(index).getName().equals(old))				
				myList.set(index, new Item( name,  type,  stock,  usage,  comments));				
		}
		//mergeSort(myList, 0, myList.size());
		organizeList();
		return myList;
	}	

	public ArrayList<Item> searchItems(String input) //int stock, String type, 
	//int usage, String comments)
	{
		ArrayList<Item> selected = new ArrayList<Item>();
		for(int i = 0; i < myList.size(); i++)
		{
			if(	myList.get(i).getName().indexOf(input) > -1 ||
					myList.get(i).getStock() == Integer.parseInt(input) ||
					myList.get(i).getCategory().indexOf(input) > -1 ||
					myList.get(i).getUsage() == Integer.parseInt(input) ||
					myList.get(i).getComments().indexOf(input) > -1)							
				selected.add(myList.get(i));
		}
		return selected;
	}

	public ArrayList<Item> removeItem(String name)
	{
		for(int i = 0; i < myList.size(); i++)
		{
			if( myList.get(i).getName().equals(name))
			{
				myList.remove(i);
				i--;
			}			
		}
		return myList;					
	}		
}
