import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Model {
    double centerX, centerY, radius, speed = 1;
    int dirX = 1, dirY = 1, bounce = 0;
    IntegerProperty bounce_property = new SimpleIntegerProperty(0);
    double paddleX, paddleY;
    double sceneWidth, sceneHeight, paddleWidth;
    
    double paddleSpeed = 2;

    public Model(View view) {
        this.centerX = view.getBall().getCenterX();
        this.centerY = view.getBall().getCenterY();
        this.radius = view.getBall().getRadius();
        this.paddleX = view.getPaddleX();
        this.paddleY = view.getPaddleY();
        this.paddleWidth = view.getPaddleWidth();

        this.sceneWidth = view.getSceneWidth();
        this.sceneHeight = view.getSceneHeight();
    }

    public boolean ballHitsPaddle(Circle ball, Rectangle paddle) {
        // VERY IMPORTANT FOR HW
        return ball.intersects(paddle.getBoundsInParent());

    }

    public void moveBall(double sceneWidth, double sceneHeight, Circle ball, Rectangle paddle) {
            // Bouncing ball
        if(centerY >= sceneHeight - radius || centerY < radius) {
            dirY *= -1;
            bounce_property.setValue(bounce_property.getValue() + 1);;
        }
        if(centerX >= sceneWidth - radius || centerX < radius) {
            dirX *= -1;
            bounce_property.setValue(bounce_property.getValue() + 1);;
        }

        if(ballHitsPaddle(ball, paddle)) {
            dirY *= -1;
        }

        centerX = centerX + dirX * speed;
        centerY = centerY + dirY * speed;
    }

    public void movePaddle(int paddleDir) {
        if(paddleX >= 0 && paddleX + paddleWidth <= sceneWidth) {
            paddleX = paddleX + paddleDir * paddleSpeed;
        } else if (paddleX < 0) {
            paddleX = 0;
        } else if(paddleX + paddleWidth <= sceneWidth) {
            paddleX = sceneWidth - paddleWidth;
        }
    }

    public double getPaddleX() {
        return paddleX;
    }

    public double getPaddleY() {
        return paddleY;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public IntegerProperty bounceProperty() {
        return bounce_property;
    }
}
