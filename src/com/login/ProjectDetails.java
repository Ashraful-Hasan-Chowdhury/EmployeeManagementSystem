package com.login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class ProjectDetails extends JPanel {
	
	JLabel lblProjectNo=new JLabel("Enter Project NO");
	JLabel lblProjectName=new JLabel("Project Name");
	JLabel lblProjectLocation=new JLabel("Project Location");
	JLabel lblEmployeeName=new JLabel("Employee Name");
	
	JTextField txtProjectNo=new JTextField(10);
	JTextField txtProjectName=new JTextField(10);
	JTextField txtProjectLocation=new JTextField(10);
	JTextField txtEmployeeName=new JTextField(10);
	
	JButton btnSave=new JButton("Save");
	Connection conn=null;
	public ProjectDetails()
	{
		conn=ConnectDB.dbConnector();
		panles();
		buttons();
	}

	private void buttons() {
		txtProjectNo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query="select * from project where number=?";
					PreparedStatement pps=(PreparedStatement) conn.prepareStatement(query);
					pps.setInt(1, Integer.parseInt(txtProjectNo.getText().trim()));
					ResultSet rst=pps.executeQuery();
					while(rst.next())
					{
					txtProjectName.setText(rst.getString("name"));
					txtProjectLocation.setText(rst.getString("location"));
					txtEmployeeName.setText(rst.getString("employee_name"));
					
					}
					rst.close();
					pps.close();
				}
				catch(Exception ee)
				{
					System.err.println(ee.getMessage());
				}
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try
				{
					String query="update project set name=?,Location=?,employee_name=? where number=?";
					PreparedStatement pps=(PreparedStatement) conn.prepareStatement(query);
					
					pps.setString(1, txtProjectName.getText().trim());
					pps.setString(2, txtProjectLocation.getText().trim());
					pps.setString(3, txtEmployeeName.getText().trim());
					pps.setInt(4, Integer.parseInt(txtProjectNo.getText().trim()));
					
					pps.executeUpdate();
					pps.close();
					JOptionPane.showMessageDialog(null,"Successfully saved");
					
				}
				catch(Exception ee)
				{
					System.err.println(ee.getMessage());
				}
				
			}
		});
		
	}

	private void panles() {
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,5);
		c.gridx=0;
		c.gridy=0;
		add(lblProjectNo,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=0;
		add(txtProjectNo,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,5);
		c.gridx=0;
		c.gridy=1;
		add(lblProjectName,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=1;
		add(txtProjectName,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,5);
		c.gridx=0;
		c.gridy=2;
		add(lblProjectLocation,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=2;
		add(txtProjectLocation,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,5);
		c.gridx=0;
		c.gridy=3;
		add(lblEmployeeName,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=3;
		add(txtEmployeeName,c);
		
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,5);
		c.gridx=1;
		c.gridy=4;
		add(btnSave,c);
		
		
		
	}
}
