package com.inetBanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;

	public ReadConfig() {

		File src = new File("./Configuration/config.properties");

		try {
			FileInputStream fs = new FileInputStream(src);
			pro = new Properties();	
			pro.load(fs);
		} catch (Exception e) {
			System.out.println("Exception is "+e.getMessage());
		}

	}
	
	public String getApplicationURL() {
		String url = pro.getProperty("baseURL");
		return url;
	}
	
	public String getUserName() {
		String uname = pro.getProperty("username");
		return uname;
	}
	
	public String getPassword() {
		String pwd = pro.getProperty("password");
		return pwd;
	}
	
	public String getChromepath() {
		String chromePath = pro.getProperty("chromepath");
		return chromePath;
	}	
	
	public String getFireFoxPath() {
		String fireFoxPath = pro.getProperty("firefoxpath");
		return fireFoxPath;
	}
	public String getMSEdgePath() {
		String msEdgePath = pro.getProperty("msedgepath");
		return msEdgePath;
	}
	
	
	
	
	
	
	
}
