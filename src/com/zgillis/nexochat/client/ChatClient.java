package com.zgillis.nexochat.client;

import com.zgillis.nexochat.Constants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient extends Thread
{
	Socket clientSocket;
	DataInputStream in;
	DataOutputStream out;
	
	public ChatClient(String hostname, int port)
	{
		logLine("Connecting to NC server on " + Constants.CLIENT_SERVER_HOST + ":"
				+ Constants.CLIENT_SERVER_PORT + " ...");
		try
		{
			clientSocket = new Socket(hostname, port);
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			logLine("Successfully connected to NC server.");
			out.writeUTF("Hello there!");
		}
		catch(IOException e)
		{
			logLine(e.getMessage(), Constants.LOG_ERROR);
			logLine("Exiting NexoChat client...", Constants.LOG_ERROR);
			System.exit(0);
		}
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				System.out.println(in.readUTF());
			}
			catch(IOException e)
			{
				logLine("You have been disconnected from the NC server.", Constants.LOG_ERROR);
				logLine("Exiting NexoChat client...", Constants.LOG_ERROR);
				break;
			}
		}
	}
	
	public static void logLine(String msg)
	{
		System.out.println("[NC Client] " + msg);
	}
	public static void logLine(String msg, int log_type)
	{
		switch(log_type)
		{
		case Constants.LOG_MESSAGE:
			System.out.println("[NC Client] " + msg);
			break;
		case Constants.LOG_ERROR:
			System.err.println("[NC Client] " + msg);
			break;
		}
	}
}
