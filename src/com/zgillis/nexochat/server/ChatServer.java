package com.zgillis.nexochat.server;

import java.io.IOException;
import java.net.*;

public class ChatServer extends Thread
{
	public static final int LOG_MESSAGE = 100;
	public static final int LOG_ERROR = 500;
	
	private ServerSocket serverSocket;
	
	public ChatServer(int port)
	{
		logLine("Chat server starting on port " + port + "...");
		try
		{
			serverSocket = new ServerSocket(port);
			logLine("Waiting for clients on port " + port + ".");
		}
		catch (IOException e)
		{
			logLine(e.getMessage(), LOG_ERROR);
		}
	}
	
	public void run()
	{
		while(true)
		{
			//TODO: Server thread logic to be added.
		}
	}
	
	
	public static void logLine(String msg)
	{
		System.out.println("[ChatServer] " + msg);
	}
	public static void logLine(String msg, int log_type)
	{
		switch(log_type)
		{
		case LOG_MESSAGE:
			System.out.println("[ChatServer] " + msg);
			break;
		case LOG_ERROR:
			System.err.println("[ChatServer] " + msg);
			break;
		}
	}
}
