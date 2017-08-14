package com.zgillis.nexochat.client;

import com.zgillis.nexochat.*;

public class ClientMain
{
	public static void main(String[] args)
	{
		System.out.println("NexoChat Client - Version " + Constants.CLIENT_APP_VERSION
				+ " " + Constants.CLIENT_APP_RELEASE + "\n");
		ChatClient client = new ChatClient(Constants.CLIENT_SERVER_HOST, Constants.CLIENT_SERVER_PORT);
		client.run();
	}
}
