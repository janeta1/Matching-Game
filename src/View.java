import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class View extends BorderPane{
    Stage stage;
    GridPane buttonGrid = new GridPane();
    HBox bottomPane = new HBox();
    double sceneWidth = 600, sceneHeight = 600;

    public View(Stage stage) {
        this.stage = stage;
        displaySetup();
    }

    public void displaySetup() {
        setCenter(buttonGrid);
        setBottom(bottomPane);

        Label myLabel = new Label("Time elapsed: ");
        bottomPane.getChildren().add(myLabel);
        bottomPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(this, sceneWidth, sceneHeight);
        stage.setScene(scene);
        stage.setResizable(false);
    }

    public void setRows(int rows) {
        for(int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(sceneHeight/rows);
            buttonGrid.getRowConstraints().add(row);
        }
    }

    public void setColumns(int columns) {
        for(int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(sceneWidth/columns);
            buttonGrid.getColumnConstraints().add(column);
        }
    }

    // showing the pane
    public void show() {
        stage.show();
        stage.requestFocus();
    }
}
