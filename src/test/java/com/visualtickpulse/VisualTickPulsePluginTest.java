package com.visualtickpulse;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class VisualTickPulsePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(VisualTickPulsePlugin.class);
		RuneLite.main(args);
	}
}