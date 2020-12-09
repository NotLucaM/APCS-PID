package controllers;

public class PController implements Controller {

    private double p;

    public PController(double p) {
        this.p = p;
    }

    @Override
    public double getFeedback(double current, double target) {
        return p * (target - current);
    }
}
