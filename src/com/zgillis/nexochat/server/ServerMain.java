package com.zgillis.nexochat.server;

public class ServerMain
{
	public static final double APP_VERSION = 0.1;
	public static final String APP_RELEASE = "DEV";
	public static void main(String[] args)
	{
		System.out.println("NexoChat Server - Version " + APP_VERSION + " " + APP_RELEASE + "\n");
		logLine("Creating a new chat server...");
		ChatServer chatServer = new ChatServer(3086);
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
		case ChatServer.LOG_MESSAGE:
			System.out.println("[Core] " + msg);
			break;
		case ChatServer.LOG_ERROR:
			System.err.println("[Core] " + msg);
			break;
		}
	}
}
