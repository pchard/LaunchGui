package tehnut.launchgui.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import tehnut.launchgui.ConfigHandler;
import tehnut.launchgui.utils.LogHelper;
import tehnut.launchgui.utils.Utils;

import java.net.URI;
import java.util.List;

public class GuiUpdate extends GuiScreen {

    @SuppressWarnings("unchecked")
    @Override
    public void initGui() {
        if (!ConfigHandler.disableContinueButtonIfUpdate) {
            this.buttonList.add(new GuiButton(0, this.width / 2 - 154, this.height / 2 + 96, 144, 20, ConfigHandler.continueButtonText));
            this.buttonList.add(new GuiButton(1, this.width / 2 + 10, this.height / 2 + 96, 144, 20, ConfigHandler.updateInformationButtonText));
        } else {
            buttonList.clear();
            this.buttonList.add(new GuiButton(1, this.width / 2 - 144, this.height / 2 + 96, 288, 20, ConfigHandler.updateInformationButtonText));
        }
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        drawDefaultBackground();
        drawCenteredString(this.fontRendererObj, EnumChatFormatting.GREEN + StatCollector.translateToLocal("gui.launchgui.update.avail"), this.width / 2, this.height / 2 - 100, 0xFFFFFF);
        Utils.handleGuiText(ConfigHandler.updateGuiLines, fontRendererObj, this, this.width, this.height);

        super.drawScreen(par1, par2, par3);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0: {
                for (GuiButton b : (List<GuiButton>) buttonList) {
                    b.enabled = false;
                }
                this.mc.displayGuiScreen(null);
                break;
            }
            case 1: {
                try {
                    Utils.browse(new URI(ConfigHandler.updateInformationUrl));
                } catch (Exception exception) {
                    LogHelper.error("Failed to load the page at " + ConfigHandler.updateInformationUrl + "!");
                    exception.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    protected void keyTyped(char par1, int par2) {
        return;
    }
}
