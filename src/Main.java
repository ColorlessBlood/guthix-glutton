import org.osbot.rs07.script.Script;

import org.osbot.rs07.script.ScriptManifest;

import java.awt.*;

@ScriptManifest(name = "Guthix Glutton 2", author = "Colorless", version = 1.0, info = "", logo = "")

public class Main extends Script
{
    private long brewedTea = 0;
    private long startTime;

    private String formatTime(final long ms)
    {
        long s = ms / 1000, m = s / 60, h = m / 60;
        s %= 60; m %= 60; h %= 24;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    private enum State
    {
        OPEN_BANK,
        MAKE,
        WITHDRAW,
        EXIT
    }

    private State getState()
    {
        if (getBank().isOpen() && getBank().contains("Bowl of hot water", "Guam leaf", "Marrentill", "Empty cup", "Harralander"))
        {
            return State.WITHDRAW;
        }
        if (getInventory().isEmpty() || getInventory().getAmount("Guthix rest(3)") == 4 || !getInventory().contains("Bowl of hot water", "Guam leaf", "Marrentill", "Empty cup", "Harralander"))
        {
            return State.OPEN_BANK;
        }
        if (getInventory().contains("Empty cup") && getInventory().contains("Bowl of hot water") && getInventory().contains("Harralander") && getInventory().contains("Marrentill") && getInventory().contains("Guam leaf"))
        {
            return State.MAKE;
        }
        return State.EXIT;

    }

    @Override
    public void onStart()
    {
        log("Starting: Guthix Glutton");
        startTime = System.currentTimeMillis();

        //Code here will execute before the loop is started

    }

    @Override
    public void onExit()
    {
        getLogoutTab().logOut();
        log("Enjoy your tea!");
        //Code here will execute after the script ends

    }

    @Override
    public int onLoop()
    {
        switch (getState())
        {
            case OPEN_BANK:
                if (!getBank().isOpen())
                {
                    try
                    {
                        getBank().open();
                        sleep(250);
                    }
                    catch (InterruptedException e)
                    {
                        log("Opening bank failed!");
                    }
                }
                getBank().depositAll();
                break;

            case MAKE:
                getInventory().interact("Use", "Bowl of hot water");
                getInventory().interact("Use", "Empty cup");
                try
                {
                    sleep(2000);
                }
                catch (InterruptedException e)
                {
                    log("Sleeping Failed!");
                }

                getInventory().interact("Use", "Harralander");
                getInventory().interact("Use", "Cup of hot water");
                try
                {
                    sleep(2000);
                }
                catch (InterruptedException e)
                {
                    log("Sleeping Failed!");
                }

                getInventory().interact("Use", "Marrentill");
                getInventory().interact("Use", "Herb tea mix");
                try
                {
                    sleep(2000);
                }
                catch (InterruptedException e)
                {
                    log("Sleeping Failed!");
                }

                getInventory().interact("Use", "Guam leaf");
                getInventory().interact("Use", "Herb tea mix");
                try
                {
                    sleep(2000);
                }
                catch (InterruptedException e)
                {
                    log("Sleeping Failed!");
                }

                getInventory().interact("Use", "Guam leaf");
                getInventory().interact("Use", "Herb tea mix");
                try
                {
                    sleep(2000);
                }
                catch (InterruptedException e)
                {
                    log("Sleeping Failed!");
                }
                ++brewedTea;
                break;

            case WITHDRAW:
                getBank().withdraw("Bowl of hot water", 4);
                try
                {
                    sleep(750);
                }
                catch (InterruptedException e)
                {
                    log("Sleeping Failed!");
                }
                getBank().withdraw("Empty cup", 4);
                try
                {
                    sleep(750);
                }
                catch (InterruptedException e)
                {
                    log("Sleeping Failed!");
                }
                getBank().withdraw("Harralander", 4);
                try
                {
                    sleep(750);
                }
                catch (InterruptedException e)
                {
                    log("Sleeping Failed!");
                }
                getBank().withdraw("Marrentill", 4);
                try
                {
                    sleep(750);
                }
                catch (InterruptedException e)
                {
                    log("Sleeping Failed!");
                }
                getBank().withdraw("Guam leaf", 8);
                try
                {
                    sleep(750);
                }
                catch (InterruptedException e)
                {
                    log("Sleeping Failed!");
                }
                getBank().close();
                break;

            case EXIT:
                stop();
                break;

        }

        return 100; //The amount of time in milliseconds before the loop starts over

    }

    @Override
    public void onPaint(Graphics2D g)
    {
        final long runTime = System.currentTimeMillis() - startTime;
        String time = formatTime(runTime);
        g.drawString(time, 35, 300);
        g.drawString("Cups Brewed: " + brewedTea, 35, 320);

        // Get current mouse position
        Point mP = getMouse().getPosition();

        // Draw a line from top of screen (0), to bottom (500), with mouse x coordinate
        g.drawLine(mP.x, 0, mP.x, 500);

        // Draw a line from left of screen (0), to right (800), with mouse y coordinate
        g.drawLine(0, mP.y, 800, mP.y);

        //This is where you will put your code for paint(s)

    }

}
