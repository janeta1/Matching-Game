import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Controller {
    Model model;
    View view;
    Timeline timeline;
    private IntegerProperty elapsedTime = new SimpleIntegerProperty(0);
    private int lastClickedButton = -1;

    /*
     * Setting up the game
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        setupGameControls();
    }

    /**
     * Calling all the methods that help setting up the game
     */
    public void setupGameControls() {
        animate();
        setupCounter();
        setupButtonControlls();
    }

    /**
     * Setting the action controls for each button
     */
    public void setupButtonControlls() {
        for (int i = 0; i < 16; i++) {
            int index = i;
            view.getButton(i).setOnAction(e -> setButtonClick(index));
        }
    }

    public void setButtonClick(int index) {
        // setting the image for the button and disabling it so that it cannot
        // be clicked again in a row
        view.getButton(index).setText(model.getImage(index)); 
        disableButton(view.getButton(index));

        // when it's the first click we store the index of the button
        if(lastClickedButton == -1) {
            lastClickedButton = index;
        } else { // if it's the second click we look if it's a match
            checkIfMatched(lastClickedButton, index);
            lastClickedButton = -1;
        }
    }

    /**
     * Method to check for matches and handle each case
     * @param firstButton - index of the first button
     * @param secondButton - index of the second button
     */
    public void checkIfMatched(int firstButton, int secondButton) {
        // it's a match
        if(model.getImage(firstButton).equals(model.getImage(secondButton))) {
            // increasing the match count
            model.matchedCount++;
            //ending the game if every image has been matched
            if(model.isGameOver()) {
                endGame();
            }
        } else { // reseting the buttons otherwise
            resetButtons(firstButton, secondButton);
        }
    }

    /**
     * Method to restore the buttons to their original state
     * @param firstButton - the index of the first button that was clicked
     * @param secondButton - the index of the second button that was clicked
     */
    public void resetButtons(int firstButton, int secondButton) {
        // delaying the restoring of the buttons by 1 second
        Timeline pause = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            view.getButton(firstButton).setText("?");
            view.getButton(secondButton).setText("?");
            // enabling back the buttons
            enableButtons(view.getButton(firstButton));
            enableButtons(view.getButton(secondButton));
        }));
        pause.play();
    }

    /**
     * Method to disable the button
     * @param button - the button to be disabled
     */
    private void disableButton(Button button) {
        button.setDisable(true); 
    }

    /**
     * Method to enable back the buttons
     * @param button - the button to be enabled
     */
    private void enableButtons(Button button) {
        button.setDisable(false);
    }

    /**
     * Animating the game/Starting the timeline of the game
     */
    public void animate() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> elapsedTime.set(elapsedTime.get() + 1)));
        timeline.setCycleCount(timeline.INDEFINITE);
    }

    /**
     * Counting the elapsed time
     */
    public void setupCounter() {
        elapsedTime.addListener(ov -> {
            view.setLabel(elapsedTime.getValue());
        });
    }

    /**
     * Starting the game
     */
    public void startGame() {
        timeline.play();
    }

    /**
     * Ending the game
     */
    public void endGame() {
        timeline.stop();
        view.bottomPane.getChildren().clear();
        Label myLabel = new Label("You cleared the game in " + elapsedTime.getValue() + " seconds. Congratulations!");
        view.bottomPane.getChildren().add(myLabel);
    }
}
