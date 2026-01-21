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
			keyName = "setuptick1Color",
			name = "Tick 1 Color",
			description = "Color for the normal rhythm tick 1",
			position = 1
	)
	default Color setup1Color()
	{
		return Color.WHITE;
	}

	@Alpha
	@ConfigItem(
			keyName = "setuptick2Color",
			name = "Tick 2 Color",
			description = "Color for the normal rythm tick 2",
			position = 2
	)
	default Color setup2Color()
	{
		return Color.WHITE;
	}

	@Alpha
	@ConfigItem(
			keyName = "actionColor",
			name = "Tick 3 Color",
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

	@Alpha
	@ConfigItem(
			keyName = "borderColor",
			name = "Border Color",
			description = "Color of the static square border",
			position = 4
	)
	default Color borderColor()
	{
		return Color.CYAN;
	}
}
