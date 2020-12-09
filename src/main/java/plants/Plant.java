package plants;

import controllers.Controller;

public abstract class Plant {

    private static final double tolerance = 0.001;

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

    private int simulate() {
        double holdLocation = location;
        while (location != lastLocation) {
            lastLocation = location;
            update();
        }
        location = holdLocation;
        return oscillations;
    }

    public int simulateWith(Controller controller) {
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
        this.oscillations = -1;
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
