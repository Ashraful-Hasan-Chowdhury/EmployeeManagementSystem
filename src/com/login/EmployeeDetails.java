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



public class EmployeeDetails extends JPanel {
	
	JLabel lblSearch=new JLabel("Enter ID");
	
	JLabel lblJoiningDate=new JLabel("Joining Date");
	JLabel lblName=new JLabel("Name");
	JLabel lblAge=new JLabel("Age");
	JLabel lblDesignation=new JLabel("Designaiton");
	JLabel lblAddress=new JLabel("Address");
	JLabel lblSalary=new JLabel("Salary");
	JLabel lblDepartment=new JLabel("Department");
	
	JTextField txtSearch=new JTextField(10);
	
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
	public EmployeeDetails()
	{
		conn=ConnectDB.dbConnector();
		Address.setLineWrap(true);
		Address.setWrapStyleWord(true);
		//SimpleDateFormat formatter = new SimpleDateFormat("dd - MM - yyyy");
		//txtJoiningDate.setText(formatter.format(new Date()));
		panel();
		buttons();
	}

	private void buttons() {
		txtSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {


				try {
					String query="select * from employee where ID=?";
					PreparedStatement pps=(PreparedStatement) conn.prepareStatement(query);
					pps.setInt(1, Integer.parseInt(txtSearch.getText().trim()));
					ResultSet rst=pps.executeQuery();
					while(rst.next())
					{
					txtName.setText(rst.getString("name"));
					txtAge.setText(rst.getString("age"));
					java.sql.Timestamp oldDate=rst.getTimestamp("dateofjoining");
					SimpleDateFormat formatter = new SimpleDateFormat("dd - MM - yyyy");
					txtJoiningDate.setText(formatter.format(oldDate));
					txtDesignation.setText(rst.getString("designation"));
					Address.setText(rst.getString("address"));
					txtSalary.setText(rst.getString("salary"));
					txtDepartment.setText(rst.getString("department"));
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
					String query="update employee set name=?,age=?,designation=?,address=?,salary=?,department=? where id=?";
					PreparedStatement pps=(PreparedStatement) conn.prepareStatement(query);
					
					pps.setString(1, txtName.getText().trim());
					pps.setInt(2,Integer.valueOf(txtAge.getText().trim()));
					pps.setString(3, txtDesignation.getText().trim());
					pps.setString(4, Address.getText().trim());
					pps.setInt(5,Integer.valueOf(txtSalary.getText().trim()));
					pps.setString(6, txtDepartment.getText().trim());
					pps.setInt(7, Integer.parseInt(txtSearch.getText().trim()));
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

	private void panel() {
		
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,5);
		c.gridx=0;
		c.gridy=0;
		add(lblSearch,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=0;
		add(txtSearch,c);
		
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,5);
		c.gridx=0;
		c.gridy=1;
		add(lblJoiningDate,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=1;
		add(txtJoiningDate,c);
		
		
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-50);
		c.gridx=0;
		c.gridy=3;
		add(lblName,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=3;
		add(txtName,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-50);
		c.gridx=0;
		c.gridy=4;
		add(lblAge,c);
		
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=4;
		add(txtAge,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-50);
		c.gridx=0;
		c.gridy=5;
		add(lblDesignation,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=5;
		add(txtDesignation,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-50);
		c.gridx=0;
		c.gridy=6;
		add(lblAddress,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=6;
		add(txtAddress,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-50);
		c.gridx=0;
		c.gridy=7;
		add(lblSalary,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=7;
		add(txtSalary,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-50);
		c.gridx=0;
		c.gridy=8;
		add(lblDepartment,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=8;
		add(txtDepartment,c);
		
		c.fill=(GridBagConstraints.BOTH);
		c.insets=new Insets(5,5,5,-200);
		c.gridx=1;
		c.gridy=9;
		add(btnSave,c);
		
	}
}
