package com.zgillis.nexochat.server;

import com.zgillis.nexochat.*;
import java.io.*;
import java.net.*;

public class RemoteClient implements Runnable
{
	private Socket clientSocket;
	private DataInputStream in;
	private DataOutputStream out;
	
	public RemoteClient(Socket client_socket) throws IOException
	{
		clientSocket = client_socket;
		in = new DataInputStream(clientSocket.getInputStream());
		out = new DataOutputStream(clientSocket.getOutputStream());
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				String inMsg = in.readUTF();
				System.out.println("Message: " + inMsg);
				out.writeUTF("(Server) You said: " + inMsg);
			}
			catch (IOException e)
			{
				logLine("Client on " + clientSocket.getInetAddress() + " has disconnected.", Constants.LOG_ERROR);
				break;
			}
		}
		try
		{
			if(!clientSocket.isClosed())
				clientSocket.close();
		}
		catch (IOException e)
		{
			logLine("Unable to close socket for client.", Constants.LOG_ERROR);
		}
	}
	
	
	public static void logLine(String msg)
	{
		System.out.println("[Client] " + msg);
	}
	public static void logLine(String msg, int log_type)
	{
		switch(log_type)
		{
		case Constants.LOG_MESSAGE:
			System.out.println("[Client] " + msg);
			break;
		case Constants.LOG_ERROR:
			System.err.println("[Client] " + msg);
			break;
		}
	}
}