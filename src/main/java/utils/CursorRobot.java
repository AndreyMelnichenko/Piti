package utils;

import java.awt.*;
import java.awt.event.InputEvent;

public class CursorRobot {
    public static void moveMouse() {
        try {
            Robot robot = new Robot();
            robot.mouseMove(2020,290);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseMove(2020,450);
            Thread.sleep(200);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            //----------Revers
            Thread.sleep(2000);
            robot.mouseMove(50,360);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseMove(2050,260);
            Thread.sleep(200);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
