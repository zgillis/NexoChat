package com.zgillis.nexochat.server;

import com.zgillis.nexochat.*;
import java.io.*;
import java.util.Properties;

public class ServerMain
{
	public static Properties prop = new Properties();
	
	public static void main(String[] args)
	{
		int serverPort = 3086;
		System.out.println("NexoChat Server - Version " + Constants.SERVER_APP_VERSION
				+ " " + Constants.SERVER_APP_RELEASE + "\n");
		
		File ncConfig = new File("nc_config.properties");
		if(ncConfig.exists())
		{
			logLine("Config file found.");
			InputStream propIn = null;
			try
			{
				logLine("Loading 'nc_config.properties'...");
				propIn = new FileInputStream("nc_config.properties");
				prop.load(propIn);
				if(prop.getProperty("server_port").equals(null))
				{
					logLine("Invalid server port in configuration. Using default [3086].", Constants.LOG_ERROR);
				}
				else
				{
					try
					{
						serverPort = Integer.parseInt(prop.getProperty("server_port"));
					}
					catch(NumberFormatException e)
					{
						logLine("Value for 'server_port' in 'nc_config.properties' is invalid. Using default [3086]", Constants.LOG_ERROR);
					}
					logLine("NC configured for port " + serverPort);
				}
			}
			catch(IOException e)
			{
				logLine("Unable to load 'nc_config.properties'. Please check the file or delete it and restart NC Server.");
			}
			catch(NullPointerException e)
			{
				logLine("No value for 'server_port' in 'nc_config.properties'. Using default [3086].", Constants.LOG_ERROR);
			}
		}
		else
		{
			logLine("Config file 'nc_config.properties' not found.");
			OutputStream propOut = null;
			try
			{
				logLine("Creating 'nc_config.properties' with default values...");
				propOut = new FileOutputStream("nc_config.properties");
				prop.setProperty("server_port", "3086");
				prop.setProperty("db_host", "localhost");
				prop.setProperty("db_user", "root");
				prop.setProperty("db_pass", "");
				prop.setProperty("display_msgs", "false");
				prop.store(propOut, null);
				logLine("Config file 'nc_config.properties' created. You will need "
						+ "to modify it with your settings and restart NexoChat Server.");
				exit();
			}
			catch(IOException e)
			{
				logLine(e.getMessage(), Constants.LOG_ERROR);
			}
			finally
			{
				if(propOut != null)
				{
					try
					{
						propOut.close();
					}
					catch(IOException e)
					{
						logLine(e.getMessage(), Constants.LOG_ERROR);
					}
				}
			}
		}
		
		logLine("Creating a new chat server...");
		ChatServer chatServer = new ChatServer(serverPort);
		chatServer.start();
	}
	
	public static void exit()
	{
		logLine("Exiting NC Server...", Constants.LOG_ERROR);
		System.exit(0);
	}
	
	public static void logLine(String msg)
	{
		System.out.println("[Core] " + msg);
	}
	public static void logLine(String msg, int log_type)
	{
		switch(log_type)
		{
		case Constants.LOG_MESSAGE:
			System.out.println("[Core] " + msg);
			break;
		case Constants.LOG_ERROR:
			System.err.println("[Core] " + msg);
			break;
		}
	}
}
