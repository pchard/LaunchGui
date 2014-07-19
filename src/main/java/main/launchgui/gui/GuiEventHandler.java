package main.launchgui.gui;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import main.launchgui.ConfigHandler;
import net.minecraftforge.client.event.GuiOpenEvent;

public class GuiEventHandler {

	private boolean shouldLoadGUI = true;

	public static GuiEventHandler instance = new GuiEventHandler();

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void openMainMenu(GuiOpenEvent event) {
		if (shouldLoadGUI && ConfigHandler.displayGuiOnLaunch && Loader.isModLoaded("eirairc")) {
			event.gui = new StartupGui();
			shouldLoadGUI = false;
		}
	}
}
