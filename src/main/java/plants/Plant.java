package plants;

import controllers.Controller;

public abstract class Plant {

    protected Controller controller;
    protected double location;
    protected double target;

    public Plant(Controller controller) {
        this.controller = controller;
    }

    public abstract void useFeedback(double feedback);

    public void update() {
        useFeedback(controller.getFeedback(location, target));
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
}
