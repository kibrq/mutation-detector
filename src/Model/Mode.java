package Model;

import Global.Variables;

public enum Mode {
    NORMAL,
    MASS_DIFFERENCE;

    public static void next() {
        Mode[] mds = Mode.values();
        for (int i = 0; i < mds.length; i++) {
            if (Variables.getMode() == mds[i]) {
                System.out.println(i);
                Variables.setMode(mds[(i + 1) % mds.length]);
                break;
            }
        }
    }
}
