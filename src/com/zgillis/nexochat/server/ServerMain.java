package com.zgillis.nexochat.server;

import com.zgillis.nexochat.*;

public class ServerMain
{	
	public static void main(String[] args)
	{
		System.out.println("NexoChat Server - Version " + Constants.APP_VERSION
				+ " " + Constants.APP_RELEASE + "\n");
		logLine("Creating a new chat server...");
		ChatServer chatServer = new ChatServer(Constants.SERVER_PORT);
		chatServer.start();
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
