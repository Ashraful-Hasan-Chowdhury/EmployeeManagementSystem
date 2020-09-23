package com.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class WorkingPanel extends JPanel {
	JPanel pnlMain=new JPanel();
	JPanel pnlHome=new JPanel();
	AddEmployee pnlAddEmployee=new AddEmployee();
	EmployeeDetails pnlEmployeeDetails=new EmployeeDetails();
	AddProject pnlAddProject=new AddProject();
	ProjectDetails pnlProjectDetails=new ProjectDetails();
	AddDepartment pnlAddDepartment=new AddDepartment();
	
	JTabbedPane tb=new JTabbedPane();
	
	JLabel lblWelcome=new JLabel(new ImageIcon("Images/Welcome.png"));
	
	JButton btnLogout=new JButton("Logout");
	
	JFrame frm;
	public WorkingPanel(JFrame frame) {
		panels();
		frame.add(tb);
		this.frm=frame;
		buttons();
	}

	private void buttons() {
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int b =JOptionPane.showConfirmDialog(null, "Sure to logout?", "Confirmation!", JOptionPane.YES_NO_OPTION);
				if(b==JOptionPane.YES_OPTION)
				{
					frm.setVisible(false);
					LoginClass lc=new LoginClass();
				}
				
			}
		});
		
	}

	private void panels() {
		tb.setBounds(0, 0, 1366, 730);
		tb.add("Home",pnlHome);
		tb.add("New Employee",pnlAddEmployee);
		tb.add("Employee Details",pnlEmployeeDetails);
		tb.add("Add Project",pnlAddProject);
		tb.add("Project Details",pnlProjectDetails);
		tb.add("Add Department",pnlAddDepartment);
		tb.addTab("Logout",btnLogout);
		pnlHome();
		
	}


	private void pnlHome() {
		FlowLayout f=new FlowLayout();
		pnlHome.setLayout(f);
		f.setVgap(0);
		pnlHome.add(lblWelcome);
		
	}

}
