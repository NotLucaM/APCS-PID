package controllers;

import plants.Robot;

public class PTuner {

    private double attempts;


    public PTuner(double attempts) {
        this.attempts = attempts;
    }

    public PController tune(Robot robot) {
        PController bestController = null;
        int bestSteps = Integer.MAX_VALUE;

        for (int i = 0; i < attempts; i++) {
            PController controller = new PController(i / 10.0);
            var result = robot.simulateWith(controller);
            if (result.getValue() < bestSteps && result.getKey() != -1) {
                bestController = controller;
                bestSteps = result.getValue();
            }
        }
        return bestController;
    }
}
