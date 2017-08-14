package com.zgillis.nexochat.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

import com.zgillis.nexochat.Constants;

public class MessageSender implements Runnable
{
	Scanner in;
	DataOutputStream out;
	
	public MessageSender(DataOutputStream outputStream)
	{
		out = outputStream;
		in = new Scanner(System.in);
	}
	
	public void run()
	{
		while(true)
		{
			String outMsg = in.nextLine();
			try
			{
				out.writeUTF(outMsg);
			}
			catch (IOException e)
			{
				logLine(e.getMessage(), Constants.LOG_ERROR);
				break;
			}
		}
	}
	
	public static void logLine(String msg)
	{
		System.out.println("[MessageSender] " + msg);
	}
	public static void logLine(String msg, int log_type)
	{
		switch(log_type)
		{
		case Constants.LOG_MESSAGE:
			System.out.println("[MessageSender] " + msg);
			break;
		case Constants.LOG_ERROR:
			System.err.println("[MessageSender] " + msg);
			break;
		}
	}
}
