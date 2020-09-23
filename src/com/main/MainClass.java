package com.main;

import javax.swing.UIManager;

import com.login.LoginClass;

public class MainClass  {

	public static void main(String[] args) {
		try 
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception e) {
			
		}
			new LoginClass();

	}

}
