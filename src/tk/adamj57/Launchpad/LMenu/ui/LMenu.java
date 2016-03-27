package tk.adamj57.Launchpad.LMenu.ui;

import com.rngtng.launchpad.LButton;
import com.rngtng.launchpad.LColor;
import com.rngtng.launchpad.Launchpad;
import com.rngtng.launchpad.LaunchpadListener;
import tk.adamj57.Launchpad.LChar.LDisplay;
import tk.adamj57.Launchpad.LChar.Pixel;
import tk.adamj57.Launchpad.LMenu.Animation;
import tk.adamj57.Launchpad.LMenu.util.ArrayListU;

import java.awt.*;
import java.util.ArrayList;

public class LMenu {

	private final ArrayList<LMenuItem> items = new ArrayList<>();

    private final int LEFT = 0;
    private final int RIGHT = 1;
	private final int UP = 2;
	private final int DOWN = 3;

	private final LDisplay lDisplay;
    private final ArrayList<Integer> itemsTree = new ArrayList<>();

    private final Pixel[] view = getControlView();

    private final LMenuButton[] buttons = controlButtons();

    private final LaunchpadListener listener = new LView(getLp(), buttons, view);

	public LMenu(Launchpad lp){
        itemsTree.add(0);
        lDisplay = new LDisplay(lp);
	}

    private void changeContainer(int direction) throws InterruptedException {
        switch (direction) {
            case UP:
                itemsTree.add(0);
                displayItem(0);
                break;
            case DOWN:
                if (itemsTree.size() > 1) {
                    itemsTree.remove(ArrayListU.lastIndexOf(itemsTree));
                    displayItem(getIndexOfActiveItem());
                }
                break;
        }
    }

    private void displayItemOn(int direction) throws InterruptedException {
        switch (direction) {
            case LEFT:
                if (getIndexOfActiveItem() - 1 >= 0) {
                    displayItem(getIndexOfActiveItem() - 1);
                }
                break;
            case RIGHT:
                displayItem(getIndexOfActiveItem() + 1);
                break;
        }
    }

    private void selectItem() throws InterruptedException {
        LMenuItem toLaunch = getActiveItem();
        if (toLaunch instanceof LMenuContainer) {
            changeContainer(UP);
        } else {
            if (toLaunch instanceof Launchable) {
                launchActiveItem();
            }
        }
    }

    public Launchpad getLp() {
		return lDisplay.getLp();
	}

	public void setLp(Launchpad lp) {
		lDisplay.setLp(lp);
	}

    public void add(LMenuItem item) {
        items.add(item);
	}

    public void add(LMenuItem item, int pos) {
        items.add(pos, item);
	}

    public void remove(LMenuItem item) {
        items.remove(item);
	}

    public void remove(int pos) {
        items.remove(pos);
	}
	
	public LMenuItem get(int index){
		return items.get(index);
	}

    public void start() throws InterruptedException {
        showControls();
		displayItem(0);
	}

    private void showControls() {
        getLp().addListener(listener);
        lDisplay.display(view);
    }

    private void hideControls() {
        getLp().removeListener(listener);
        lDisplay.clear();
    }

    private void displayItem(int index) throws InterruptedException {
        try {
            itemsTree.set(ArrayListU.lastIndexOf(itemsTree), index);
            LMenuItem toSelect = getActiveItem();

            lDisplay.clear();

            Animation animation = toSelect.getAnimation();
            if (animation.isRaw()) {
                lDisplay.displayRawAnimation(animation.getAnimation(), animation.getSpeed(), animation.getColor());
            } else {
                lDisplay.display(animation.getStringToAnimate(), animation.getSpeed(), animation.getColor());
            }

            lDisplay.clear();

            Point[] logo = toSelect.getLogo();
            lDisplay.display(logo);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error: Index out of bounds. Can't show " + index + "of that list, it don't exist.");
        }
    }

    private LMenuItem getActiveItem() {
        int size = itemsTree.size();
        LMenuItem item = items.get(ArrayListU.firstElementOf(itemsTree));

        for (int i = 1; i < size; i++) {
            item = ((LMenuContainer) item).get(itemsTree.get(i));
        }
        return item;
    }

    private void launchActiveItem() throws InterruptedException {
        lDisplay.clear();
		hideControls();
        LMenuItem toLaunch = getActiveItem();

		((Launchable) toLaunch).launch();
		showControls();
        displayItem(getIndexOfActiveItem());
    }

    private int getIndexOfActiveItem() {
        return itemsTree.get(itemsTree.size() - 1);
    }

    private LMenuButton[] controlButtons() {
        LMenuButton[] buttons = new LMenuButton[6];
        buttons[0] = new LMenuButton(new Pixel(LButton.LEFT, LColor.GREEN_HIGH), new Pixel(LButton.LEFT, LColor.GREEN_LOW));
        buttons[0].setButtonListener(new LMenuButtonListener() {

            @Override
            public void pressed() {

            }

            @Override
            public void released() {
                try {
                    displayItemOn(LEFT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        buttons[1] = new LMenuButton(new Pixel(LButton.RIGHT, LColor.GREEN_HIGH), new Pixel(LButton.RIGHT, LColor.GREEN_LOW));
        buttons[1].setButtonListener(new LMenuButtonListener() {
            @Override
            public void pressed() {

            }

            @Override
            public void released() {
                try {
                    displayItemOn(RIGHT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        buttons[2] = new LMenuButton(new Pixel(LButton.MIXER, LColor.YELLOW_HIGH), new Pixel(LButton.MIXER, LColor.YELLOW_LOW));
        buttons[2].setButtonListener(new LMenuButtonListener() {
            @Override
            public void pressed() {

            }

            @Override
            public void released() {
                try {
                    selectItem();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        buttons[3] = new LMenuButton(new Pixel(LButton.DOWN, LColor.HIGH), new Pixel(LButton.DOWN, LColor.LOW));
        buttons[3].setButtonListener(new LMenuButtonListener() {
            @Override
            public void pressed() {

            }

            @Override
            public void released() {
                try {
                    changeContainer(DOWN);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        buttons[4] = new LMenuButton(new Pixel(LButton.UP, LColor.HIGH), new Pixel(LButton.UP, LColor.LOW));
        buttons[4].setButtonListener(new LMenuButtonListener() {
            @Override
            public void pressed() {

            }

            @Override
            public void released() {
                try {
                    changeContainer(UP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        buttons[5] = new LMenuButton(new Pixel(LButton.SCENE8, LColor.HIGH), new Pixel(LButton.SCENE8, LColor.LOW));
        buttons[5].setButtonListener(new LMenuButtonListener() {
            @Override
            public void pressed() {

            }

            @Override
            public void released() {
                new Thread(() -> {
                    getLp().dispose();
                }).start();
            }
        });
        return buttons;
    }

    private Pixel[] getControlView() {
        return new Pixel[]{
                new Pixel(LButton.LEFT, LColor.GREEN_HIGH),
                new Pixel(LButton.RIGHT, LColor.GREEN_HIGH),
                new Pixel(LButton.MIXER, LColor.YELLOW_HIGH),
                new Pixel(LButton.DOWN, LColor.HIGH),
                new Pixel(LButton.UP, LColor.HIGH),
                new Pixel(LButton.SCENE8, LColor.HIGH)
        };
    }

}
