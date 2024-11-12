import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;

public class Model {
    ArrayList<Button> buttons = new ArrayList<>();
    ArrayList<String> images = new ArrayList<>();
    int rows = 4, columns = 4;
    int k = 0;
    boolean clicked = false;

    private Button lastClickedButton = new Button();
    private String lastClickedEmoji = null;
    private boolean gameOver = false;

    public Model(View view) {
        addImages();
        view.setRows(rows);
        view.setColumns(columns);
        Collections.shuffle(images);
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                Button newButton = new Button("?");
                newButton.setPrefSize(600/4, 600/4);
                buttons.add(newButton);
                newButton.setStyle("-fx-font-size: 30px;");
                view.buttonGrid.add(newButton, i, j);
                int index = k % images.size();
                newButton.setOnAction(event -> setButtonControls(newButton, images.get(index)));
                k++;
            }
        }
    }

    public void setButtonControls(Button button, String emoji) {
        if (gameOver || button.getText().equals(emoji)) {
            return;  // Ignore clicks if the game is over or button already matched
        }

        button.setText(emoji);  // Show the emoji on the button

        // If this is the first click, store the clicked button and emoji
        if (!clicked) {
            lastClickedButton = button;
            lastClickedEmoji = emoji;
            clicked = true;
        } else {
            // If it's the second click, check if they match
            if (lastClickedEmoji.equals(emoji)) {
                // If matched, disable both buttons permanently
                disableButton(lastClickedButton);
                disableButton(button);
                clicked = false;
            } else {
                clicked = false;
                // If not matched, hide the emoji again after a short delay
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(e -> {
                    lastClickedButton.setText("?");  // Restore the first button
                    button.setText("?");  // Restore the second button
                    lastClickedButton.setDisable(false);  // Enable the first button again
                    button.setDisable(false);  // Enable the second button again
                });
                pause.play();
            }

            // Reset the last clicked button and emoji
            //lastClickedButton = new Button();
            lastClickedEmoji = null;
        }
    }

    private void disableButton(Button button) {
       
        button.setDisable(true);  // Disable the button
    }

    public void addImages() {
        images.add("🦄");
        images.add("🐭");
        images.add("🐣" );
        images.add("🦥");
        images.add("🦈");
        images.add("🐼");
        images.add("🦭");
        images.add("🐹");
        images.add("🦄");
        images.add("🐭");
        images.add("🐣" );
        images.add("🦥");
        images.add("🦈");
        images.add("🐼");
        images.add("🦭");
        images.add("🐹");
    }

}
