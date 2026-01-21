package com.example;

import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import java.awt.*;

public class TickPulseOverlay extends Overlay {

    private final Client client;
    private final TickPulsePlugin plugin;

    @Inject
    public TickPulseOverlay(Client client, TickPulsePlugin plugin) {
        this.client = client;
        this.plugin = plugin;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (client.getLocalPlayer() == null) {
            return null;
        }
        LocalPoint localLocation = client.getLocalPlayer().getLocalLocation();

        // 2. Calculate the polygon (shape) of the tile on the screen
        Polygon tilePoly = Perspective.getCanvasTilePoly(client, localLocation);

        // 3. Draw it!
        if (tilePoly != null) {
            OverlayUtil.renderPolygon(graphics, tilePoly, Color.CYAN);
        }

        // 1. Setup colors for 3-Tick Rhythm
        // We use Modulo 3 (math trick) to count 0, 1, 2, 0, 1, 2...
        // 3t characters often want the "Special" color on the 3rd tick.
        Color pulseColor = Color.CYAN; // Default
        int tickCycle = plugin.getTickCounter() % 3;

        if (tickCycle == 0) {
            pulseColor = Color.RED;   // The "Action" Tick (Click Spot)
        } else {
            pulseColor = Color.WHITE; // The "Setup" Ticks (Cut/Eat)
        }

        // 3. Calculate Pulse Progress (0% to 100%)
        long timePassed = System.currentTimeMillis() - plugin.getLastTickTime();
        float progress = timePassed / 600f;
        if (progress > 1f) progress = 1f;

        // 4. Draw the Pulsating Circle
        Point canvasPoint = Perspective.localToCanvas(client, localLocation, client.getPlane());
        if (canvasPoint != null) {
            // "50" is roughly the radius of a tile in pixels
            Rectangle bounds = tilePoly.getBounds();
            double maxRadius = bounds.getWidth() / 2;
            int currentRadius = (int) (maxRadius * progress);

            int x = canvasPoint.getX() - currentRadius;
            int y = canvasPoint.getY() - currentRadius;
            int diameter = currentRadius * 2;

            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setColor(pulseColor);
            graphics.setStroke(new BasicStroke(2));
            graphics.drawOval(x, y, diameter, diameter);
        }

        return null;
    }
}