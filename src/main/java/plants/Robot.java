package plants;

import controllers.Controller;

public class Robot extends Plant {

    private double mass, friction, gravity;
    private double maxForce;
    private double velocity;

    public Robot(double mass, double friction, double maxForce, double location, double target) {
        this(mass, friction, 9.8, maxForce, location, target);
    }

    public Robot(double mass, double friction, double maxForce, double location, double target, double gravity) {
        super(location, target);
        this.maxForce = maxForce;
        this.mass = mass;
        this.friction = friction;
        this.gravity = gravity;
    }

    @Override
    public double useFeedback(double feedback) {
        feedback = Math.min(maxForce, feedback);
        double force = feedback * mass - Math.signum(feedback + velocity) * Math.min(friction * mass * gravity, Math.abs(feedback + velocity));
        double acceleration = force / mass;
        velocity = velocity + acceleration;
        return getLocation() + velocity;
    }
}
