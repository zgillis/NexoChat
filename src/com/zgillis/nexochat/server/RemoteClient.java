package com.zgillis.nexochat.server;

import com.zgillis.nexochat.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class RemoteClient implements Runnable
{
	private Socket clientSocket;
	private DataInputStream in;
	private DataOutputStream out;
	private static ArrayList<RemoteClient> connectedClients = new ArrayList<RemoteClient>();
	
	public RemoteClient(Socket client_socket) throws IOException
	{
		clientSocket = client_socket;
		in = new DataInputStream(clientSocket.getInputStream());
		out = new DataOutputStream(clientSocket.getOutputStream());
		connectedClients.add(this);
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				String inMsg = in.readUTF();
				System.out.println("Message: " + inMsg);
				for(RemoteClient client : connectedClients)
				{
					client.getDataOutputStream().writeUTF("<" + getInetAddress() + "> " + inMsg);
				}
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
		
		connectedClients.remove(this);
	}
	
	public DataOutputStream getDataOutputStream()
	{
		return out;
	}
	
	public InetAddress getInetAddress()
	{
		return clientSocket.getInetAddress();
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
