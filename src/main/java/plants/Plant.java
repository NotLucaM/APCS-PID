package plants;

import controllers.Controller;

public abstract class Plant {

    private static final double tolerance = 0.001;

    protected Controller controller;
    protected double location;
    protected double target;

    private int oscillations;
    private boolean reached = false;

    public Plant(Controller controller) {
        this.controller = controller;
    }

    public abstract void useFeedback(double feedback);

    private void update() {
        useFeedback(controller.getFeedback(location, target));
        if (Math.abs(location - target) < tolerance && !reached) {
            reached = true;
            oscillations++;
        } else {
            reached = false;
        }
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
        this.oscillations = 0;
    }

    public final boolean isReached() {
        return reached;
    }

    public final double getOscillations() {
        return oscillations;
    }
}
