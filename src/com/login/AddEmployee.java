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
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;




public class AddEmployee extends JPanel {
	
	JLabel lblID=new JLabel("ID:");
	JLabel lblJoiningDate=new JLabel("Joining Date");
	JLabel lblName=new JLabel("Name");
	JLabel lblAge=new JLabel("Age");
	JLabel lblDesignation=new JLabel("Designaiton");
	JLabel lblAddress=new JLabel("Address");
	JLabel lblSalary=new JLabel("Salary");
	JLabel lblDepartment=new JLabel("Department");
	
	JTextField txtID=new JTextField(10);
	JTextField txtJoiningDate=new JTextField(10);
	JTextField txtName=new JTextField(10);
	JTextField txtAge=new JTextField(10);
	JTextField txtDesignation=new JTextField(10);
	JTextField txtSalary=new JTextField(10);
	JTextField txtDepartment=new JTextField(10);
	
	JTextArea Address=new JTextArea(5,5);
	JScrollPane txtAddress=new JScrollPane(Address);
	
	JButton btnSave=new JButton("Save");
	Connection conn=null;
	public AddEmployee()
	{
		conn=ConnectDB.dbConnector();
		Address.setLineWrap(true);
		Address.setWrapStyleWord(true);
		SimpleDateFormat formatter = new SimpleDateFormat("dd - MM - yyyy");
		txtJoiningDate.setText(formatter.format(new Date()));
		panel();
		buttons();
		ID();
	}
	
	private void ID() {
		try {
			String query="select max(ID) as id from employee";
			java.sql.PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rst=pst.executeQuery();
			if(rst.next())
			{
				int ID=rst.getInt("id");
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

	private void buttons() {
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String query="insert into employee values(?,?,?,?,?,?,?,?)";
					PreparedStatement pps=(PreparedStatement) conn.prepareStatement(query);
					java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
					pps.setTimestamp(1, date);
					pps.setInt(2, Integer.valueOf(txtID.getText().trim()));
					pps.setString(3, txtName.getText().trim());
					pps.setInt(4,Integer.valueOf(txtAge.getText().trim()));
					pps.setString(5, txtDesignation.getText().trim());
					pps.setString(6, Address.getText().trim());
					pps.setInt(7,Integer.valueOf(txtSalary.getText().trim()));
					pps.setString(8, txtDepartment.getText().trim());
					pps.executeUpdate();
					pps.close();
					JOptionPane.showMessageDialog(null,"Successfully saved");
					ID();
					txtName.setText("");
					txtDesignation.setText("");
					txtAge.setText("");
					txtDesignation.setText("");
					Address.setText("");
					txtSalary.setText("");
					txtDepartment.setText("");
				}
				catch(Exception e)
				{
					System.err.println(e.getMessage());
				}
				
			}
		});
		
	}

	private void panel() {
		
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,5);
		c.gridx=0;
		c.gridy=0;
		add(lblJoiningDate,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=0;
		add(txtJoiningDate,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-50);
		c.gridx=0;
		c.gridy=1;
		add(lblID,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=1;
		add(txtID,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-50);
		c.gridx=0;
		c.gridy=2;
		add(lblName,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=2;
		add(txtName,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-50);
		c.gridx=0;
		c.gridy=3;
		add(lblAge,c);
		
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=3;
		add(txtAge,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-50);
		c.gridx=0;
		c.gridy=4;
		add(lblDesignation,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=4;
		add(txtDesignation,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-50);
		c.gridx=0;
		c.gridy=5;
		add(lblAddress,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=5;
		add(txtAddress,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-50);
		c.gridx=0;
		c.gridy=6;
		add(lblSalary,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=6;
		add(txtSalary,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-50);
		c.gridx=0;
		c.gridy=7;
		add(lblDepartment,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=7;
		add(txtDepartment,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=8;
		add(btnSave,c);
		
	}
}
