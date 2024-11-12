import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class Controller {
    Model model;
    View view;
    Timeline timeline;
    

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        setupGameControls();
    }

    public void setupGameControls() {
        animate();
        //setupKeyControls();
    }

    

    public void animate() {
        timeline = new Timeline();
        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.play();
    }

    public void start() {
        timeline.play();
    }
}
