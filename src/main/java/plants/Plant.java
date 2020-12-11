package plants;

import controllers.Controller;

import java.util.AbstractMap;

public abstract class Plant {

    private static final double tolerance = 0.001;
    private static final int maxSteps = 5000;

    // Private not protected in order to avoid potential bugs with classes implementing useFeedback wrong
    private Controller controller;
    private double target;
    private double location;
    private double lastLocation;
    private int oscillations;
    private boolean reached = false;

    public Plant(double location, double target) {
        this.location = location;
        this.target = target;
    }

    public abstract double useFeedback(double feedback);

    private void update() {
        location = useFeedback(controller.getFeedback(location, target));
        if (Math.abs(location - target) < tolerance && !reached) {
            reached = true;
            if (oscillations == -1) {
                oscillations = 0;
            }
            oscillations++;
        } else {
            reached = false;
        }
    }

    private AbstractMap.SimpleEntry<Integer, Integer> simulate() {
        double holdLocation = location;
        lastLocation = Integer.MAX_VALUE;
        oscillations = -1;
        int steps = 0;
        while (Math.abs(location - lastLocation) >= tolerance) {
            lastLocation = location;
            update();
            steps++;
            if (steps >= maxSteps) { // TODO: make this better
                return new AbstractMap.SimpleEntry<>(-1, -1);
            }
        }
        location = holdLocation;
        return new AbstractMap.SimpleEntry<>(oscillations, steps);
    }

    public AbstractMap.SimpleEntry<Integer, Integer> simulateWith(Controller controller) {
        this.controller = controller;
        return simulate();
    }

    public final Controller getController() {
        return controller;
    }

    public final double getLocation() {
        return location;
    }

    public final double getTarget() {
        return target;
    }

    public final void setTarget(double target) {
        this.target = target;
    }

    public final void setController(Controller controller) {
        this.controller = controller;
    }

    public final boolean isReached() {
        return reached;
    }

    public final double getOscillations() {
        return oscillations;
    }
}
