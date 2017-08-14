package com.zgillis.nexochat.server;

import java.io.*;
import java.net.*;

public class ChatServer extends Thread
{	
	private ServerSocket serverSocket;
	private int portNumber;
	
	public ChatServer(int port)
	{
		portNumber = port;
		logLine("Chat server starting on port " + port + "...");
		try
		{
			serverSocket = new ServerSocket(port);
			logLine("Listening for clients on port " + serverSocket.getLocalPort() + ".");
		}
		catch (IOException e)
		{
			logLine(e.getMessage(), Constants.LOG_ERROR);
		}
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				Socket clientSocket = serverSocket.accept();
				logLine("Client connected from " + clientSocket.getInetAddress());
				new Thread(new Client(clientSocket)).start();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		case Constants.LOG_MESSAGE:
			System.out.println("[ChatServer] " + msg);
			break;
		case Constants.LOG_ERROR:
			System.err.println("[ChatServer] " + msg);
			break;
		}
	}
}
