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

public class AddWindow extends JFrame 
{

	/**
	 * 
	 */	
	//private Methods functions;
	//private MainMenu list;
	//private Item added;

	private JPanel contentPane;
	private JTextField txtName;
	private JComboBox<String> txtCategory;
	private JTextField txtStock;
	private JTextField txtUsage;
	private JTextField txtComments;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddWindow frame = new AddWindow(null, null, null, null);
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
	public AddWindow(Methods list,  ArrayList<Item> menu, MainMenu main, DefaultTableModel model)  
	{
		setFont(new Font("Dialog", Font.PLAIN, 33));
		setResizable(false);
		setTitle("ADD ITEMS");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 462, 399);
		contentPane = new JPanel();		
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(35, 25, 76, 14);
		contentPane.add(lblName);

		JLabel lblUsage = new JLabel("Weekly Usage:");
		lblUsage.setBounds(35, 154, 112, 14);
		contentPane.add(lblUsage);

		JLabel lblComments = new JLabel("Comments:");
		lblComments.setBounds(35, 239, 76, 14);
		contentPane.add(lblComments);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(35, 110, 76, 14);
		contentPane.add(lblStock);

		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(35, 66, 92, 14);
		contentPane.add(lblCategory);

		txtName = new JTextField();
		txtName.setBounds(157, 22, 275, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtCategory = new JComboBox<String>();
		txtCategory.setToolTipText("");
		txtCategory.addItem("Meat");
		txtCategory.addItem("Vegetable");
		txtCategory.addItem("Garnishes");
		txtCategory.addItem("Seasoning");	
		txtCategory.addItem("Sauce");		
		txtCategory.addItem("Miscellaneous");
		txtCategory.setSelectedIndex(-1);	
		txtCategory.setBounds(157, 60, 275, 20);
		contentPane.add(txtCategory);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(157, 107, 275, 20);
		contentPane.add(txtStock);

		txtUsage = new JTextField();
		txtUsage.setColumns(10);
		txtUsage.setBounds(157, 151, 275, 20);
		contentPane.add(txtUsage);

		txtComments = new JTextField();
		txtComments.setHorizontalAlignment(SwingConstants.LEFT);
		txtComments.setColumns(10);
		txtComments.setBounds(157, 218, 275, 57);
		contentPane.add(txtComments);

		JLabel nameError = new JLabel("");
		nameError.setForeground(Color.RED);
		nameError.setBounds(157, 42, 275, 14);
		contentPane.add(nameError);

		JLabel categoryError = new JLabel("");
		categoryError.setForeground(Color.RED);
		categoryError.setBounds(157, 82, 275, 14);
		contentPane.add(categoryError);

		JLabel stockError = new JLabel("");
		stockError.setForeground(Color.RED);
		stockError.setBounds(157, 126, 275, 14);
		contentPane.add(stockError);

		JLabel usageError = new JLabel("");
		usageError.setForeground(Color.RED);
		usageError.setBounds(157, 172, 275, 14);
		contentPane.add(usageError);

		//JLabel commentsError = new JLabel("");
		//commentsError.setForeground(Color.RED);
		//commentsError.setBounds(157, 277, 275, 14);
		//contentPane.add(commentsError);

		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 			
			{						

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
							if(	Integer.parseInt(txtStock.getText()) < 0 )//|| Integer.parseInt(txtStock.getText()) > 200)
							{
								stockError.setText("Please Enter in a Positive Integer");
							}
							else
							{
								stockError.setText("");
								try 
								{
									Integer.parseInt(txtUsage.getText());
									if(	Integer.parseInt(txtUsage.getText()) < 0 )//|| Integer.parseInt(txtUsage.getText()) > 200)
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
													list.addItem(txtName.getText(), txtCategory.getSelectedItem().toString(), Integer.parseInt(txtStock.getText()),
															Integer.parseInt(txtUsage.getText()), txtComments.getText());												
													list.modifyFile();
													list.organizeList();
													model.addRow(new Object[] {txtName.getText(), txtCategory.getSelectedItem().toString(), Integer.parseInt(txtStock.getText()),
															Integer.parseInt(txtUsage.getText()), txtComments.getText()  });	
													AddWindow.this.setVisible(false);	
													main.setVisible(true);																							
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
												list.addItem(txtName.getText(), txtCategory.getSelectedItem().toString(), Integer.parseInt(txtStock.getText()),
														Integer.parseInt(txtUsage.getText()), txtComments.getText());												
												list.modifyFile();
												list.organizeList();
												model.addRow(new Object[] {txtName.getText(), txtCategory.getSelectedItem().toString(), Integer.parseInt(txtStock.getText()),
														Integer.parseInt(txtUsage.getText()), txtComments.getText()  });	
												AddWindow.this.setVisible(false);	
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
				}
			}
		});
		btnAdd.setBounds(50, 300, 150, 50);
		contentPane.add(btnAdd);			

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				AddWindow.this.setVisible(false);
				main.setVisible(true);
			}
		});
		btnCancel.setBounds(250, 300, 150, 50);
		contentPane.add(btnCancel);
	}	
}
