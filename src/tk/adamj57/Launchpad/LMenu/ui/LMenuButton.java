package tk.adamj57.Launchpad.LMenu.ui;

import com.rngtng.launchpad.Launchpad;
import tk.adamj57.Launchpad.LChar.Pixel;

/**
 * Created by Adam on 2016-03-26.
 */
public class LMenuButton {
    Pixel normal;
    Pixel pressed;

    LMenuButtonListener listener = new LMenuButtonListener() {
        @Override
        public void pressed() {

        }

        @Override
        public void released() {

        }
    };

    public LMenuButton(Pixel normal, Pixel pressed) {
        this.normal = normal;
        this.pressed = pressed;
    }

    public void setButtonListener(LMenuButtonListener listener) {
        this.listener = listener;
    }

    public void pressed(Launchpad lp) {
        changeColor(pressed, lp);
        listener.pressed();
    }

    public void released(Launchpad lp) {
        changeColor(normal, lp);
        listener.released();
    }

    public Pixel getNormalPixel() {
        return normal;
    }

    public Pixel getPressedPixel() {
        return pressed;
    }

    private void changeColor(Pixel pixel, Launchpad lp) {
        if (pixel.isGridPixel) {
            lp.changeGrid(pixel.x, pixel.y, pixel.color);
        } else {
            lp.changeButton(pixel.button, pixel.color);
        }
    }
}
