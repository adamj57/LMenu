package tk.adamj57.Launchpad.LMenu;

import com.rngtng.launchpad.Launchpad;
import com.rngtng.launchpad.LaunchpadListener;
import tk.adamj57.Launchpad.LChar.Pixel;

/**
 * Created by Adam on 2016-03-26.
 */
public class LView implements LaunchpadListener, LaunchpadView {

    private Launchpad lp;
    private LMenuButton[] lMenuButtons;
    private Pixel[] view;

    public LView(Launchpad lp, LMenuButton[] buttons, Pixel[] view) {
        this.lp = lp;
        this.lMenuButtons = buttons;
        this.view = view;
    }

    @Override
    public void launchpadGridPressed(int x, int y) {
        for (LMenuButton btn : lMenuButtons) {
            Pixel pressedPixel = btn.getPressedPixel();
            if (pressedPixel.isGridPixel) {
                if (pressedPixel.getX() == x && pressedPixel.getY() == y) {
                    btn.pressed(lp);
                    break;
                }
            }
        }
    }

    @Override
    public void launchpadGridReleased(int x, int y) {
        for (LMenuButton btn : lMenuButtons) {
            Pixel releasedPixel = btn.getNormalPixel();
            if (releasedPixel.isGridPixel) {
                if (releasedPixel.getX() == x && releasedPixel.getY() == y) {
                    btn.released(lp);
                    break;
                }
            }
        }
    }

    @Override
    public void launchpadButtonPressed(int buttonCode) {
        sideButtonPressed(buttonCode);
    }

    @Override
    public void launchpadButtonReleased(int buttonCode) {
        sideButtonReleased(buttonCode);
    }

    @Override
    public void launchpadSceneButtonPressed(int buttonCode) {
        sideButtonPressed(buttonCode);
    }

    @Override
    public void launchpadSceneButtonReleased(int buttonCode) {
        sideButtonReleased(buttonCode);
    }

    private void sideButtonPressed(int buttonCode) {
        for (LMenuButton btn : lMenuButtons) {
            Pixel pressedPixel = btn.getNormalPixel();
            if (!pressedPixel.isGridPixel) {
                if (pressedPixel.getButton() == buttonCode) {
                    btn.pressed(lp);
                    break;
                }
            }
        }
    }

    private void sideButtonReleased(int buttonCode) {
        for (LMenuButton btn : lMenuButtons) {
            Pixel releasedPixel = btn.getNormalPixel();
            if (!releasedPixel.isGridPixel) {
                if (releasedPixel.getButton() == buttonCode) {
                    btn.released(lp);
                    break;
                }
            }
        }
    }

    @Override
    public Pixel[] getView() {
        return view;
    }

    @Override
    public LMenuButton[] getButtons() {
        return lMenuButtons;
    }
}
