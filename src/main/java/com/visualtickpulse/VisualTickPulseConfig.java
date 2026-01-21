package com.visualtickpulse;

import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import java.awt.Color;

@ConfigGroup("visualtickpulse")
public interface VisualTickPulseConfig extends Config
{
	@Alpha
	@ConfigItem(
			keyName = "setupColor",
			name = "Setup Tick Color",
			description = "Color for the normal rhythm ticks (1 and 2)",
			position = 1
	)
	default Color setupColor()
	{
		return Color.WHITE;
	}

	@Alpha
	@ConfigItem(
			keyName = "actionColor",
			name = "Action Tick Color",
			description = "Color for the 3rd 'Action' tick",
			position = 2
	)
	default Color actionColor()
	{
		return Color.RED;
	}

	@ConfigItem(
			keyName = "showSquare",
			name = "Show Tile Border",
			description = "Show the static square on the ground",
			position = 3
	)
	default boolean showSquare()
	{
		return true;
	}
}
