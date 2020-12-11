import controllers.PController;
import controllers.PIDController;
import controllers.PTuner;
import plants.Robot;

public class Main {

    public static void main(String[] args) {
        PTuner tuner = new PTuner(5000);
        Robot robot = new Robot(5, 0.1, 100, 0, 100);

        PController result = tuner.tune(robot);
        System.out.println(result.toString());
    }
}
