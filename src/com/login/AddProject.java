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



public class AddProject extends JPanel {
	
	JLabel lblProjectNo=new JLabel("Project NO");
	JLabel lblProjectName=new JLabel("Project Name");
	JLabel lblProjectLocation=new JLabel("Project Location");
	JLabel lblEmployeeName=new JLabel("Employee Name");
	
	JTextField txtProjectNo=new JTextField(10);
	JTextField txtProjectName=new JTextField(10);
	JTextField txtProjectLocation=new JTextField(10);
	JTextField txtEmployeeName=new JTextField(10);
	
	JButton btnSave=new JButton("Save");
	
	Connection conn=null;
	public AddProject()
	{
		conn=ConnectDB.dbConnector();
		panles();
		ID();
		buttons();
	}
	
	private void buttons() {
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try
				{
					String query="insert into project values(?,?,?,?)";
					PreparedStatement pps=(PreparedStatement) conn.prepareStatement(query);
					pps.setInt(1, Integer.valueOf(txtProjectNo.getText().trim()));
					pps.setString(2, txtProjectName.getText().trim());
					pps.setString(3, txtProjectLocation.getText().trim());
					pps.setString(4, txtEmployeeName.getText().trim());
					pps.executeUpdate();
					pps.close();
					JOptionPane.showMessageDialog(null,"Successfully saved");
					ID();
					txtProjectName.setText("");
					txtProjectLocation.setText("");
					txtEmployeeName.setText("");
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
			String query="select max(number) as number from project";
			java.sql.PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rst=pst.executeQuery();
			if(rst.next())
			{
				int ID=rst.getInt("number");
				txtProjectNo.setText(String.valueOf(ID+1));
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
