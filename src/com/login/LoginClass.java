package com.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LoginClass extends JFrame {
	Connection conn=null;
		JPanel pnlMain=new JPanel();
		JPanel pnlNorth=new JPanel();
		JPanel pnlCenter=new JPanel();
		JPanel pnlSouth=new JPanel();
		
		JLabel lblWelcome=new JLabel("Employee Management System");
		JLabel lblUserName=new JLabel("Username");
		JLabel lblPassword=new JLabel("Password");
		
		JTextField txtUserName=new JTextField(15);
		JPasswordField txtPassword=new JPasswordField(15);
		
		JButton btnLogin=new JButton("Login");
	
	public LoginClass()
	{
		conn=ConnectDB.dbConnector();
		cmp();
		panels();
		Buttons();
	}

	private void Buttons() {
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(txtUserName.getText().trim().isEmpty() || txtPassword.getText().trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"All the fields should be filled up!");
				}
				else
				{
					try
					{
						String query="select * from tbadmin where username=? and password=?";
						java.sql.PreparedStatement pps = conn.prepareStatement(query);
						pps.setString(1, txtUserName.getText());
						pps.setString(2, txtPassword.getText());
						ResultSet rs=pps.executeQuery();
						int count=0;
						while(rs.next())
						{
							count++;
						}
						if(count==1)
						{
							
							loginAction();
						}
						else
						{
							txtUserName.setText("");
							txtPassword.setText("");
							JOptionPane.showMessageDialog(null,"wrong username or password!");
						}
						rs.close();
						pps.close();
					}
					catch(Exception ee)
					{
						
					}
						
				}
						
		
					}
				});
		
		txtPassword.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(txtUserName.getText().trim().isEmpty() || txtPassword.getText().trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"All the fields should be filled up!");
				}
				else
				{
					try
					{
						String query="select * from tbadmin where username=? and password=?";
						java.sql.PreparedStatement pps = conn.prepareStatement(query);
						pps.setString(1, txtUserName.getText());
						pps.setString(2, txtPassword.getText());
						ResultSet rs=pps.executeQuery();
						int count=0;
						while(rs.next())
						{
							count++;
						}
						if(count==1)
						{
							
							loginAction();
						}
						else
						{
							txtUserName.setText("");
							txtPassword.setText("");
							JOptionPane.showMessageDialog(null,"wrong username or password!");
						}
						rs.close();
						pps.close();
					}
					catch(Exception ee)
					{
						
					}
						
				}
						
		
					}
				});
		
	}
	
	public void loginAction()
	{
		pnlMain.setVisible(false);
		WorkingPanel wp=new WorkingPanel(this);
		add(wp);
		wp.setVisible(true);
		setSize(1366,730);
		setLocationRelativeTo(null);
		setResizable(true);
		
	}

	private void panels() {
		add(pnlMain);
		BorderLayout b=new BorderLayout();
		pnlMain.setLayout(b);
		pnlMain.add(pnlNorth, b.NORTH);
		pnlMain.add(pnlCenter, b.CENTER);
		pnlMain.add(pnlSouth, b.SOUTH);
		
		
		FlowLayout f=new FlowLayout(1,0,0);
		pnlNorth.add(lblWelcome);
		lblWelcome.setFont(new Font("Courier",Font.BOLD,25));
		pnlNorth.setBackground(Color.LIGHT_GRAY);
		pnlNorth.setBorder(BorderFactory.createBevelBorder(1));
		
		pnlSouth.setPreferredSize(new Dimension(0, 50));
	//	pnlSouth.setBorder(BorderFactory.createBevelBorder(1));
		pnlSouth.setLayout(new FlowLayout(1,0,0));
		pnlSouth.add(btnLogin);
		
		FlowLayout ff=new FlowLayout(1,0,0);
		ff.setVgap(30);
		ff.setHgap(100);
		pnlCenter.setLayout(ff);
		pnlCenter.add(lblUserName);
		pnlCenter.add(txtUserName);
		pnlCenter.add(lblPassword);
		pnlCenter.add(txtPassword);
		
	}

	private void cmp() {
		setVisible(true);
		setSize(550,250);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Employee Management System");
		
	}
}
