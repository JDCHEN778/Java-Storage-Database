import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

//import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import java.awt.Window.Type;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;


// USE MERGESORT


public class MainMenu extends JFrame
{

	private JPanel contentPane;
	private JTextField textSearch;

	private Methods functions;
	private ArrayList<Item> items;	
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() 
	{
		setForeground(Color.WHITE);
		items = new ArrayList<Item>();	
		functions = new Methods(items);		
		items = functions.loadData();		
	
		setResizable(false);
		setTitle("ITEM DATABASE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, -17, 950, 652);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 222, 179));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 10, 900, 405);
		contentPane.add(scrollPane);

		DefaultTableModel model = new DefaultTableModel(); 		
		model.addColumn("Name");
		model.addColumn("Category");
		model.addColumn("Stock");
		model.addColumn("Weekly Usage");
		model.addColumn("Comments");

		for(int index = 0; index < items.size(); index++)
		{
			Item value = items.get(index);
			model.addRow(new Object[] {value.getName(), value.getCategory(), value.getStock(), value.getUsage(), value.getComments()  });			
		}
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);		
		table.getTableHeader().setReorderingAllowed(false);
		table.setDragEnabled(true);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(400);	
		scrollPane.setViewportView(table);

		textSearch = new JTextField();
		textSearch.setBounds(20, 502, 317, 27);
		contentPane.add(textSearch);
		textSearch.setColumns(10);		

		JButton btnAdd = new JButton("ADD");
		btnAdd.setToolTipText("Add an Item to the Main Menu\r\n");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{				
				AddWindow addFunction = new AddWindow(functions, items, MainMenu.this, model);	
				//sortButton.setSelected(false);
				MainMenu.this.setVisible(false);	
				addFunction.setVisible(true);															
			}			
		});
		btnAdd.setBounds(20, 426, 140, 50);
		contentPane.add(btnAdd);

		JButton btnEdit = new JButton("EDIT");
		btnEdit.setToolTipText("Select an Item from the table to Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(items.size() == 0)
				{
					JOptionPane.showMessageDialog(null,"LIST IS EMPTY, NO ITEMS TO EDIT");
				}		
				else if(table.getSelectedRow() == -1)				
				{
					JOptionPane.showMessageDialog(null,"PLEASE SELECT A ROW TO EDIT");
				}
				else
				{
					int row = table.getSelectedRow();
					int itemIndex = 0;

					String name = (String)model.getValueAt(row, 0);
					String category = (String)model.getValueAt(row, 1);
					int stock = (int)model.getValueAt(row, 2);
					int usage = (int)model.getValueAt(row, 3);
					String comments = (String)model.getValueAt(row, 4);

					Item rowValue = new Item(name,category,stock,usage,comments);
					for(int index = 0; index < items.size(); index++)
					{
						if( items.get(index).compareTo(rowValue) == 0)
						{
							itemIndex = index;
						}								
					}				

					EditWindow editFunction = new EditWindow(row, itemIndex, functions, items, MainMenu.this, model);	
					//sortButton.setSelected(false);
					MainMenu.this.setVisible(false);
					editFunction.setVisible(true);
				}				
			}
		});
		btnEdit.setBounds(197, 426, 140, 50);
		contentPane.add(btnEdit);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.setToolTipText("Select an Item from the table to Delete");
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{			
				if(items.size() == 0)
				{
					JOptionPane.showMessageDialog(null,"LIST IS EMPTY, NO ITEMS TO DELETE");
				}		
				else if(table.getSelectedRow() == -1)				
				{
					JOptionPane.showMessageDialog(null,"PLEASE SELECT A ROW TO DELETE");
				}
				else
				{
					int row = table.getSelectedRow();	

					String name = (String)model.getValueAt(row, 0);
					String category = (String)model.getValueAt(row, 1);
					int stock = (int)model.getValueAt(row, 2);
					int usage = (int)model.getValueAt(row, 3);
					String comments = (String)model.getValueAt(row, 4);

					int prompt = JOptionPane.showConfirmDialog(null, "Are Sure You Want to Delete Item? \n\nName: " + name + "\nCategory: " + category + "\nStock: " + stock + "\nUsage: " + usage + "\nComments: " + comments
							, "DOUBLE CHECK DELETE PROMPT", JOptionPane.YES_NO_OPTION);
					if(prompt == JOptionPane.YES_OPTION)
					{
						Item rowValue = new Item(name,category,stock,usage,comments);
						for(int index = 0; index < items.size(); index++)
						{
							if( items.get(index).compareTo(rowValue) == 0)
							{
								functions.removeItem(items.get(index).getName());												
								functions.modifyFile();
								functions.organizeList();
								model.removeRow(row);
							}								
						}											
					}					
					//DeleteWindow.this.setVisible(false);	
					//main.setVisible(true);		

					//DeleteWindow deleteFunction = new DeleteWindow(row, functions, items, MainMenu.this, model);	
					//sortButton.setSelected(false);
					//MainMenu.this.setVisible(false);
					//deleteFunction.setVisible(true);
				}
			}
		});
		btnDelete.setBounds(374, 426, 140, 50);
		contentPane.add(btnDelete);

		JRadioButton rdbtnSearch = new JRadioButton("SPECIFIC SEARCH");	
		rdbtnSearch.setToolTipText("Searches using full Words and not just fragments. ");
		rdbtnSearch.setBackground(new Color(245, 222, 179));
		rdbtnSearch.setBounds(374, 564, 140, 20);
		contentPane.add(rdbtnSearch);

		JButton btnSearch = new JButton("SEARCH ");
		btnSearch.setToolTipText("Searches for an Item based off text entered in Textbox to the Left");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				String toSearch = textSearch.getText();				
				if(items.size() == 0)
				{
					JOptionPane.showMessageDialog(null,"LIST IS EMPTY, NO ITEMS TO SEARCH");
				}		
				else if(toSearch.length() <= 0)
				{
					JOptionPane.showMessageDialog(null, "PLEASE ENTER TEXT TO SEARCH");
				}				
				else
				{										
					model.setRowCount(0);
					for(int index = 0; index < items.size(); index++)
					{
						Item value = items.get(index);						
						if(rdbtnSearch.isSelected())
						{
							if(value.containsSpecific(toSearch))
							{						
								model.addRow(new Object[] {value.getName(), value.getCategory(), value.getStock(), value.getUsage(), value.getComments()  });		
							}			
						}
						else
						{
							if(value.containsGeneric(toSearch))
							{						
								model.addRow(new Object[] {value.getName(), value.getCategory(), value.getStock(), value.getUsage(), value.getComments()  });		
							}
						}

					}					
				}
			}			
		});
		btnSearch.setBounds(374, 490, 140, 50);
		contentPane.add(btnSearch);

		JComboBox<String> sortList = new JComboBox<String>();		
		sortList.addItem("BY NAME ALPHABETICAL (A-Z)");		
		sortList.addItem("BY NAME REVERSE ALPHABETICAL (Z-A)");
		sortList.addItem("LOW STOCKED ITEMS");
		sortList.addItem("BY CATEGORY");		
		sortList.addItem("BY STOCK (INCREASING)");
		sortList.addItem("BY USAGE (INCREASING)");
		sortList.setSelectedIndex(0);
		sortList.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) 
			{
				if(items.size() == 0)
				{
					JOptionPane.showMessageDialog(null,"LIST IS EMPTY, NO ITEMS TO SORT");
				}		
				else
				{
					int sorted = sortList.getSelectedIndex();

					if(sorted == 0)
					{
						model.setRowCount(0);
						for(int index = 0; index < items.size(); index++)
						{
							Item value = items.get(index);					
							model.addRow(new Object[] {value.getName(), value.getCategory(), value.getStock(), value.getUsage(), value.getComments()  });		
						}	
					}
					else if(sorted == 1)
					{
						model.setRowCount(0);
						ArrayList<Item> reverseAlphabetical = new ArrayList<Item>();	
						for(int index = 0; index < items.size(); index++)
						{
							Item values = items.get(index);
							reverseAlphabetical.add(values);		
						}

						functions.reversemergeSort(reverseAlphabetical, 0, reverseAlphabetical.size()-1);					

						for(int index = 0; index < reverseAlphabetical.size(); index++)
						{
							Item wholeList = reverseAlphabetical.get(index);
							model.addRow(new Object[] {wholeList.getName(), wholeList.getCategory(), wholeList.getStock(), wholeList.getUsage(), wholeList.getComments()  });			
						}
					}
					else if(sorted == 2)
					{
						ArrayList<Item> lowItems = new ArrayList<Item>();				
						for(Item value : items)
						{
							if(value.isLow())
								lowItems.add(value);		
						}					
						model.setRowCount(0);
						for( int index = 0; index < lowItems.size(); index++)
						{
							Item low = lowItems.get(index);
							model.addRow(new Object[] {low.getName(), low.getCategory(), low.getStock(), low.getUsage(), low.getComments()  });
						}
					}				
					else if(sorted == 3)
					{
						model.setRowCount(0);
						ArrayList<Item> category = new ArrayList<Item>();	
						for(int index = 0; index < items.size(); index++)
						{
							Item values = items.get(index);
							category.add(values);		
						}
						functions.organizeCategory(category);

						for(int index = 0; index < category.size(); index++)
						{
							Item wholeList = category.get(index);
							model.addRow(new Object[] {wholeList.getName(), wholeList.getCategory(), wholeList.getStock(), wholeList.getUsage(), wholeList.getComments()  });			
						}
					}		
					else if(sorted == 4)
					{
						model.setRowCount(0);
						ArrayList<Item> stock = new ArrayList<Item>();	
						for(int index = 0; index < items.size(); index++)
						{
							Item values = items.get(index);
							stock.add(values);		
						}
						functions.organizeStock(stock);

						for(int index = 0; index < stock.size(); index++)
						{
							Item wholeList = stock.get(index);
							model.addRow(new Object[] {wholeList.getName(), wholeList.getCategory(), wholeList.getStock(), wholeList.getUsage(), wholeList.getComments()  });			
						}
					}				
					else //if(sorted == 5)
					{
						model.setRowCount(0);
						ArrayList<Item> usage = new ArrayList<Item>();	
						for(int index = 0; index < items.size(); index++)
						{
							Item values = items.get(index);
							usage.add(values);		
						}
						functions.organizeUsage(usage);

						for(int index = 0; index < usage.size(); index++)
						{
							Item wholeList = usage.get(index);
							model.addRow(new Object[] {wholeList.getName(), wholeList.getCategory(), wholeList.getStock(), wholeList.getUsage(), wholeList.getComments()  });			
						}
					}
				}
			}		
		});
		sortList.setBounds(603, 426, 317, 20);
		contentPane.add(sortList);		

		JButton btnRefresh = new JButton("REFRESH MAIN MENU");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				model.setRowCount(0);                                                                            
				for(int index = 0; index < items.size(); index++)
				{
					Item value = items.get(index);					
					model.addRow(new Object[] {value.getName(), value.getCategory(), value.getStock(), value.getUsage(), value.getComments()  });		
				}
				sortList.setSelectedIndex(0);		
				textSearch.setText("");
				rdbtnSearch.setSelected(false);
			}
		});
		btnRefresh.setBounds(20, 563, 317, 50);
		contentPane.add(btnRefresh);

		JButton btnTxt = new JButton("CREATE NEW PRINTABLE TEXT FILE");
		btnTxt.setToolTipText("Opens viewable textfile sorted according to the combolist above, which the user can then print.");
		btnTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{		
				if(model.getRowCount() == 0)					
				{
					JOptionPane.showMessageDialog(null,"MAIN MENU IS EMPTY, NO ITEMS TO RETRACT");
				}
				else
				{

					String input = JOptionPane.showInputDialog("Please Label the Textfile");
					if(input.length() == 0)
					{
						JOptionPane.showMessageDialog(null,"Please Label the Textfile");
					}					
					else //if(input != null)
					{
						String textFileName = input + ".txt";

						String name = "";
						String category = "";
						int stock = 0;
						int usage = 0;
						String comments = "";


						ArrayList<Item> currentMenu = new ArrayList<Item>();
						for( int index = 0; index < model.getRowCount(); index++)
						{
							name = (String)model.getValueAt(index, 0);
							category = (String)model.getValueAt(index, 1);
							stock = (int)model.getValueAt(index, 2);
							usage = (int)model.getValueAt(index, 3);
							comments = (String)model.getValueAt(index, 4);
							Item temp = new Item(name, category, stock, usage, comments);

							currentMenu.add(temp);
						}


						try
						{
							File newFile = new File(textFileName);
							FileWriter write = new FileWriter(newFile);
							PrintWriter editor = new PrintWriter(write);

							for( int index = 0; index < currentMenu.size(); index++)
							{        	   
								editor.println("NAME: " + currentMenu.get(index).getName()); 
								editor.println("CATEGORY: " + currentMenu.get(index).getCategory());
								editor.println("STOCK: " + currentMenu.get(index).getStock());					 
								editor.println("USAGE: " + currentMenu.get(index).getUsage());
								editor.println("COMMENTS: " + currentMenu.get(index).getComments());
								editor.println();
							}        
							editor.close();    
						}
						catch(Exception e)
						{
							JFrame frame = new JFrame();
							JOptionPane.showMessageDialog(frame, "File not found");
							//System.out.println(e);
						}    

						try
						{
							Desktop.getDesktop().open(new java.io.File(textFileName));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}		

					}		
				}
			}
		});
		btnTxt.setBounds(603, 563, 317, 50);
		contentPane.add(btnTxt);		
		
		//JLabel imageLogo = new JLabel("");
		//imageLogo.setIcon(new ImageIcon("savouryfusiongrilllogo3.png"));		
		//imageLogo.setBounds(633, 457, 250, 95);
		//contentPane.add(imageLogo);
	}		
}
