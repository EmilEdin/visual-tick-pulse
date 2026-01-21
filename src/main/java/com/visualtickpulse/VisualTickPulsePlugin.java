package com.visualtickpulse;

import net.runelite.api.events.GameTick;
import lombok.Getter;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
		name = "Visual Tick Pulse"
)
public class VisualTickPulsePlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private VisualTickPulseConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private VisualTickPulseOverlay overlay;

	// 1. Variable to store the time of the last tick
	@Getter
	private long lastTickTime;

	@Getter
	private int tickCounter = 0;

	@Subscribe
	public void onGameTick(GameTick tick)
	{
		// Detect every game tick
		lastTickTime = System.currentTimeMillis();
		tickCounter++; // Increment every tick
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}

	@Provides
	VisualTickPulseConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(VisualTickPulseConfig.class);
	}
}
