package org.purpurmc.purpur.client.gui.screen.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import org.purpurmc.purpur.client.config.options.BooleanOption;

public class BooleanButton extends Button implements Tickable {
    private final BooleanOption option;
    private int tooltipDelay;

    public BooleanButton(int x, int y, int width, int height, BooleanOption option) {
        super(x, y, width, height, option.text(), (button) -> option.toggle(), DEFAULT_NARRATION);
        this.option = option;
    }

    public void renderTooltip(GuiGraphics context, int mouseX, int mouseY) {
        if (this.isHovered && this.tooltipDelay > 15 && Minecraft.getInstance().screen != null) {
            context.renderTooltip(Minecraft.getInstance().font, this.option.tooltip(), mouseX, mouseY);
        }
    }

    @Override
    public Component getMessage() {
        return this.option.text();
    }

    @Override
    public void tick() {
        if (this.isHoveredOrFocused() && this.active) {
            this.tooltipDelay++;
        } else if (this.tooltipDelay > 0) {
            this.tooltipDelay = 0;
        }
    }
}
