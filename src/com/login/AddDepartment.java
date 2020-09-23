package com.login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class AddDepartment extends JPanel {
	Connection conn=null;
	
		JLabel lblID=new JLabel("Departmanet ID:");
		JLabel lblName=new JLabel("Departmanet Name:");
		JLabel lblLocation=new JLabel("Department Location");
		
		JTextField txtID=new JTextField(10);
		JTextField txtName=new JTextField(10);
		JTextField txtLocation=new JTextField(10);
		
		JButton btnSave=new JButton("Save");
		
		public AddDepartment()
		{
			conn=ConnectDB.dbConnector();
			panel();
			ID();
			buttons();
		}
		
		private void buttons() {
			btnSave.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {


					try
					{
						String query="insert into department values(?,?,?)";
						PreparedStatement pps=(PreparedStatement) conn.prepareStatement(query);
						pps.setInt(1, Integer.valueOf(txtID.getText().trim()));
						pps.setString(2, txtName.getText().trim());
						pps.setString(3, txtLocation.getText().trim());
						pps.executeUpdate();
						pps.close();
						JOptionPane.showMessageDialog(null,"Successfully saved");
						ID();
						txtName.setText("");
						txtLocation.setText("");
					}
					catch(Exception ee)
					{
						System.err.println(ee.getMessage());
					}
					
				}
			});
			
		}

		private void ID() {
			try {
				String query="select max(number) as number from department";
				java.sql.PreparedStatement pst=conn.prepareStatement(query);
				ResultSet rst=pst.executeQuery();
				if(rst.next())
				{
					int ID=rst.getInt("number");
					txtID.setText(String.valueOf(ID+1));
				}
				
				rst.close();
				pst.close();
			}
			catch(Exception e)
			{
				System.err.println("Exception Found!");
				System.err.println(e.getMessage());
			}
			
		}

		private void panel() {
			setLayout(new GridBagLayout());
			GridBagConstraints c=new GridBagConstraints();
			c.fill=(GridBagConstraints.BOTH);
			c.insets=new Insets(5,5,5,5);
			c.gridx=0;
			c.gridy=0;
			add(lblID,c);
			
			c.fill=(GridBagConstraints.BOTH);
			c.insets=new Insets(5,5,5,-200);
			c.gridx=1;
			c.gridy=0;
			add(txtID,c);
			
			c.fill=(GridBagConstraints.BOTH);
			c.insets=new Insets(5,5,5,5);
			c.gridx=0;
			c.gridy=1;
			add(lblName,c);
			
			c.fill=(GridBagConstraints.BOTH);
			c.insets=new Insets(5,5,5,-200);
			c.gridx=1;
			c.gridy=1;
			add(txtName,c);
			
			c.fill=(GridBagConstraints.BOTH);
			c.insets=new Insets(5,5,5,5);
			c.gridx=0;
			c.gridy=2;
			add(lblLocation,c);
			
			c.fill=(GridBagConstraints.BOTH);
			c.insets=new Insets(5,5,5,-200);
			c.gridx=1;
			c.gridy=2;
			add(txtLocation,c);
			
			
			
			c.fill=(GridBagConstraints.BOTH);
			c.insets=new Insets(5,5,5,5);
			c.gridx=1;
			c.gridy=3;
			add(btnSave,c);
			
		}
}
