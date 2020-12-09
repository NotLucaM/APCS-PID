import controllers.PIDController;
import plants.Robot;

public class Main {

    public static void main(String[] args) {
        Robot robot = new Robot(10, 0.1, 10, 0, 100);
        System.out.println(robot.simulateWith(new PIDController(5, 0, 0)));
    }
}
