package com.zgillis.nexochat.client;

import com.zgillis.nexochat.*;

public class ClientMain
{
	static int port = 3086;
	static String host = "localhost";
	
	public static void main(String[] args)
	{
		if(args.length == 1)
			host = args[0];
		else if(args.length == 2)
		{
			host = args[0];
			try
			{
				port = Integer.parseInt(args[1]);
			}
			catch(NumberFormatException e)
			{
				System.err.println("Invalid port. Using default [3086].");
			}
		}
		if(host.equals("-h") || host.equals("--help"))
		{
			System.out.println("Usage:\tjava -cp . com.zgillis.nexochat.ClientMain [host]\n\t" + 
								"...ClientMain [host] [port]\n");
			System.exit(0);
		}
		System.out.println("NexoChat Client - Version " + Constants.CLIENT_APP_VERSION
				+ " " + Constants.CLIENT_APP_RELEASE + "\n");
		ChatClient client = new ChatClient(host, port);
		client.run();
	}
}
