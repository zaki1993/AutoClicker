package com.zaki.autoclicker;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.TimerTask;

public class Clicker extends TimerTask {

    private int x;
    private int y;

    public Clicker(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        try {
            Robot robot = new Robot();

            Point p = MouseInfo.getPointerInfo().getLocation();
            if (p.getX() != x && p.getY() != y) {
                robot.mouseMove(x, y);
                System.out.println("Mouse moved to (" + x + ", " + y + ")");
            }

            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            System.out.println("Mouse clicked at (" + x + ", " + y + ")");
        } catch (Exception e) {
            // TODO
        }
    }
}
