import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class View extends BorderPane{
    ArrayList<Button> buttons = new ArrayList<>();
    Stage stage;
    GridPane buttonGrid = new GridPane();
    HBox bottomPane = new HBox();
    double sceneWidth = 600, sceneHeight = 600;
    int time = 0;
    int rows = 4, columns = 4;

    /**
     * Setting up the view of the game
     * @param stage
     */
    public View(Stage stage) {
        this.stage = stage;
        displaySetup();
        setGrid();
    }

    /**
     * Displaying the setup of the game
     */
    public void displaySetup() {
        setCenter(buttonGrid);
        setBottom(bottomPane);

        Label myLabel = new Label("Time elapsed: " + time + " s");
        bottomPane.getChildren().add(myLabel);
        bottomPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(this, sceneWidth, sceneHeight);
        stage.setScene(scene);
        stage.setResizable(false);
    }

    /**
     * Setting the button grid
     */
    public void setGrid() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                Button newButton = new Button("?");
                newButton.setPrefSize(600/4, 600/4);
                buttons.add(newButton);
                newButton.setStyle("-fx-font-size: 40px;");
                buttonGrid.add(newButton, i, j);
            }
        }
    }

    /**
     * Returning a specific button
     * @param index - index of the button
     * @return - the button at the given index
     */
    public Button getButton(int index) {
        return buttons.get(index);
    }

    /**
     * Showing the setup
     */
    public void show() {
        stage.show();
        // this will ensure that the methods of the listener will actually work
        stage.requestFocus();
    }

    /**
     * Showing the elapsed time
     * @param timeElapsed - the time elapsed
     */
    public void setLabel(int timeElapsed) {
        Label my_label = (Label)bottomPane.getChildren().get(0);
        my_label.setText("Elapsed time: " + timeElapsed + " s");
    }
}
