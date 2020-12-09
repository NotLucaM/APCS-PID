package plants;

import controllers.Controller;

public class Robot extends Plant {

    private double mass, friction, gravity;
    private double velocity;

    public Robot(double mass, double friction, Controller controller) {
        this(mass, friction, 9.8, controller);
    }

    public Robot(double mass, double friction, double gravity, Controller controller) {
        super(controller);
        this.mass = mass;
        this.friction = friction;
        this.gravity = gravity;
    }

    @Override
    public void useFeedback(double feedback) {
        double force = feedback * mass - Math.signum(feedback + velocity) * Math.min(friction * mass * gravity, Math.abs(feedback + velocity));
        double acceleration = force / mass;
        velocity = velocity + acceleration;
    }
}
