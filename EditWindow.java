import java.awt.*;
import java.util.*;
import javax.swing.*;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JComboBox;

public class EditWindow extends JFrame 
{

	/**
	 * 
	 */	
	//private Methods functions;
	//private MainMenu list;
	//private Item added;


	private JPanel contentPane;
	private JTextField txtName;
	private JComboBox<String> txtItems;
	private JComboBox<String> txtCategory;
	private JTextField txtStock;
	private JTextField txtUsage;
	private JTextField txtComments;

	/**
	 * Launch the application.
	 * @param <EditWindow>
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditWindow frame = new EditWindow(-1,-1, null, null, null, null);
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
	public EditWindow(int row, int itemIndex, Methods list, ArrayList<Item> items, MainMenu main, DefaultTableModel model)  
	{
		setFont(new Font("Dialog", Font.PLAIN, 33));
		setResizable(false);
		setTitle("EDIT ITEMS");		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 461, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//JLabel lblItem = new JLabel("Items:");
		//lblItem.setBounds(34, 21, 46, 14);
		//contentPane.add(lblItem);		

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(34, 39, 76, 14);
		contentPane.add(lblName);

		JLabel lblUsage = new JLabel("Weekly Usage:");
		lblUsage.setBounds(34, 168, 112, 14);
		contentPane.add(lblUsage);

		JLabel lblComments = new JLabel("Comments:");
		lblComments.setBounds(34, 253, 76, 14);
		contentPane.add(lblComments);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(34, 124, 76, 14);
		contentPane.add(lblStock);

		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(34, 80, 92, 14);
		contentPane.add(lblCategory);

		//txtItems = new JComboBox<String>();
		//for(int index = 0; index < items.size(); index++)
		//{
		//	txtItems.addItem(items.get(index).getName());
		//}		
		//txtItems.setSelectedIndex(-1);
		//if(row >= 0)
		//	txtItems.setSelectedIndex(row);
		//txtItems.setBounds(156, 18, 275, 20);
		//contentPane.add(txtItems);


		Item value = items.get(itemIndex);

		txtName = new JTextField();
		//if(row >= 0)
		//	txtName.setText(items.get(row).getName());
		txtName.setText(value.getName());
		txtName.setBounds(156, 36, 275, 20);
		contentPane.add(txtName);		
		txtName.setColumns(10);

		txtCategory = new JComboBox<String>();
		txtCategory.addItem("Meat");
		txtCategory.addItem("Vegetable");
		txtCategory.addItem("Garnishes");
		txtCategory.addItem("Seasoning");	
		txtCategory.addItem("Sauce");		
		txtCategory.addItem("Miscellaneous");
		txtCategory.setSelectedIndex(-1);	

		String category = value.getCategory();
		if(category.equals("Meat"))
			txtCategory.setSelectedIndex(0);
		else if(category.equals("Vegetable"))
			txtCategory.setSelectedIndex(1);
		else if(category.equals("Garnishes"))
			txtCategory.setSelectedIndex(2);
		else if(category.equals("Seasoning"))
			txtCategory.setSelectedIndex(3);
		else if(category.equals("Sauce"))
			txtCategory.setSelectedIndex(4);
		else      //if(category.equals("Miscellaneous"))
			txtCategory.setSelectedIndex(5);


		//txtCategory.setSelectedIndex(txtCategory.getSelectedIndex());
		txtCategory.setBounds(156, 74, 275, 20);
		contentPane.add(txtCategory);

		txtStock = new JTextField();
		//if(row >= 0)
		//	txtStock.setText(Integer.toString(items.get(row).getStock()));
		txtStock.setText(Integer.toString(value.getStock()));
		txtStock.setColumns(10);
		txtStock.setBounds(156, 121, 275, 20);
		contentPane.add(txtStock);		

		txtUsage = new JTextField();
		//if(row >= 0)
		txtUsage.setText(Integer.toString(items.get(itemIndex).getUsage()));
		txtUsage.setColumns(10);
		txtUsage.setBounds(156, 165, 275, 20);
		contentPane.add(txtUsage);

		txtComments = new JTextField();
		//if(row >= 0)
		txtComments.setText(items.get(itemIndex).getComments());
		txtComments.setHorizontalAlignment(SwingConstants.LEFT);
		txtComments.setColumns(10);
		txtComments.setBounds(156, 232, 275, 57);
		contentPane.add(txtComments);

		JLabel itemsError = new JLabel("");
		itemsError.setForeground(Color.RED);
		itemsError.setBounds(156, 11, 275, 14);
		contentPane.add(itemsError);

		JLabel nameError = new JLabel("");
		nameError.setForeground(Color.RED);
		nameError.setBounds(156, 56, 275, 14);
		contentPane.add(nameError);

		JLabel categoryError = new JLabel("");
		categoryError.setForeground(Color.RED);
		categoryError.setBounds(156, 96, 275, 14);
		contentPane.add(categoryError);

		JLabel stockError = new JLabel("");
		stockError.setForeground(Color.RED);
		stockError.setBounds(156, 140, 275, 14);
		contentPane.add(stockError);

		JLabel usageError = new JLabel("");
		usageError.setForeground(Color.RED);
		usageError.setBounds(156, 186, 275, 14);
		contentPane.add(usageError);

		//JLabel commentsError = new JLabel("");
		//commentsError.setForeground(Color.RED);
		//commentsError.setBounds(156, 318, 275, 14);
		//contentPane.add(commentsError);

		/*
		if( txtItems.getSelectedIndex() >= 0)
		{					
			Item values = items.get(txtItems.getSelectedIndex());
			txtName.setText(values.getName());
			String category = values.getCategory();
			if(category.equals("Meat"))
				txtCategory.setSelectedIndex(0);
			else if(category.equals("Vegetable"))
				txtCategory.setSelectedIndex(1);
			else if(category.equals("Garnishes"))
				txtCategory.setSelectedIndex(2);
			else if(category.equals("Seasoning"))
				txtCategory.setSelectedIndex(3);
			else if(category.equals("Sauce"))
				txtCategory.setSelectedIndex(4);
			else      //if(category.equals("Miscellaneous"))
				txtCategory.setSelectedIndex(5);
			txtStock.setText(Integer.toString(values.getStock()));
			txtUsage.setText(Integer.toString(values.getUsage()));
			txtComments.setText(values.getComments());
		}
		 */

		JButton btnAdd = new JButton("EDIT");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 			
			{	
				//if( txtItems.getSelectedIndex() == -1)
				//{					
				//	itemsError.setText("Empty Slot: Please Select a Category");	
				//}			
				//else
				//{	 
				//	itemsError.setText("");
				if( txtName.getText().length() == 0) 
				{
					nameError.setText("Empty Slot: Please Enter Name");	
				}		
				else
				{
					nameError.setText("");					
					if( txtCategory.getSelectedIndex() == -1)
					{					
						categoryError.setText("Empty Slot: Please Select a Category");	
					}			
					else
					{
						categoryError.setText("");
						try 
						{
							Integer.parseInt(txtStock.getText());
							if(	Integer.parseInt(txtStock.getText()) < 0 || Integer.parseInt(txtStock.getText()) > 200)
							{
								stockError.setText("Please Enter in a Positive Integer");
							}
							else
							{
								stockError.setText("");
								try 
								{
									Integer.parseInt(txtUsage.getText());
									if(	Integer.parseInt(txtUsage.getText()) < 0 || Integer.parseInt(txtUsage.getText()) > 200)
									{
										usageError.setText("Please Enter in a Positive Integer");
									}
									else
									{
										usageError.setText("");											
										if( txtComments.getText().length() == 0) 
										{
											int prompt = JOptionPane.showConfirmDialog(null, "Comments Section Blank, Leave Blank?", "COMMENT CHECKER", JOptionPane.YES_NO_OPTION);
											if(prompt == JOptionPane.YES_OPTION)
											{
												try
												{												
													list.editItem(items.get(itemIndex).getName(),txtName.getText(), txtCategory.getSelectedItem().toString(), 
															Integer.parseInt(txtStock.getText()), Integer.parseInt(txtUsage.getText()), txtComments.getText());												
													list.modifyFile();			
													list.organizeList();
													model.removeRow(row);
													model.addRow(new Object[] {txtName.getText(), txtCategory.getSelectedItem().toString(), Integer.parseInt(txtStock.getText()),
															Integer.parseInt(txtUsage.getText()), txtComments.getText()  });																				
													EditWindow.this.setVisible(false);	
													main.setVisible(true);	

													/*
											list.editItem(txtItems.getSelectedItem().toString(),txtName.getText(), txtCategory.getSelectedItem().toString(), 
													Integer.parseInt(txtStock.getText()), Integer.parseInt(txtUsage.getText()), txtComments.getText());												
											list.modifyFile();			
											list.organizeList();
											model.removeRow(txtItems.getSelectedIndex());
											model.addRow(new Object[] {txtName.getText(), txtCategory.getSelectedItem().toString(), Integer.parseInt(txtStock.getText()),
													Integer.parseInt(txtUsage.getText()), txtComments.getText()  });														
											//model.deleteRow(new Object[] {txtName.getText(), txtCategory.getSelectedItem().toString(), Integer.parseInt(txtStock.getText()),
											//		Integer.parseInt(txtUsage.getText()), txtComments.getText()  });	
											EditWindow.this.setVisible(false);	
											main.setVisible(true);	
													 */
												}
												catch(Exception e)
												{
													JOptionPane.showMessageDialog(null,"Error!!!");
												}		
											}									
										}
										else
										{																					
											try
											{												
												list.editItem(items.get(itemIndex).getName(),txtName.getText(), txtCategory.getSelectedItem().toString(), 
														Integer.parseInt(txtStock.getText()), Integer.parseInt(txtUsage.getText()), txtComments.getText());												
												list.modifyFile();			
												list.organizeList();
												model.removeRow(row);
												model.addRow(new Object[] {txtName.getText(), txtCategory.getSelectedItem().toString(), Integer.parseInt(txtStock.getText()),
														Integer.parseInt(txtUsage.getText()), txtComments.getText()  });																				
												EditWindow.this.setVisible(false);	
												main.setVisible(true);																								
											}
											catch(Exception e)
											{
												JOptionPane.showMessageDialog(null,"Error!!!");
											}	
										}
									}
								}
								catch (NumberFormatException e)
								{
									usageError.setText("Please Enter in a Valid Integer");
								}
							}
						} 
						catch (NumberFormatException e)
						{
							stockError.setText("Please Enter in a Valid Integer");
						}
					}			
					//	}
				}
			}
		});
		btnAdd.setBounds(50, 313, 150, 50);
		contentPane.add(btnAdd);

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EditWindow.this.setVisible(false);
				main.setVisible(true);
			}
		});
		btnCancel.setBounds(250, 313, 150, 50);
		contentPane.add(btnCancel);			
	}
}