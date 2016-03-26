package tk.adamj57.Launchpad.LMenu.util;

import java.util.ArrayList;

/**
 * Created by Adam on 2016-03-26.
 */
public class ArrayListU {
    public static <E> E firstElementOf(ArrayList<E> list) {
        return list.get(0);
    }

    public static int lastIndexOf(ArrayList list) {
        return list.size() - 1;
    }
}
