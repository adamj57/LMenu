package tk.adamj57.Launchpad.LMenu;

import tk.adamj57.Launchpad.LChar.Pixel;

/**
 * Created by Adam on 2016-03-26.
 */
public interface LaunchpadView {
    Pixel[] getView();

    LMenuButton[] getButtons();
}
