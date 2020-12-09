package controllers;

public class PIDController implements Controller {

    private double p, i, d;
    private double lastIntegral, lastError;

    public PIDController(double p, double i, double d) {
        this.p = p;
        this.i = i;
        this.d = d;
    }

    @Override
    public double getFeedback(double current, double target) {
        // delta time is always 1
        double error = target - current;
        double integral = lastIntegral + error;
        double derivative = error - lastError;

        lastError = error;
        lastIntegral = integral;

        return p * error + i * integral + d * derivative;
    }
}
