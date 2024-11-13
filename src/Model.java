import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Model {
    ArrayList<String> images = new ArrayList<>();
    public int matchedCount;

    /**
     * Setting up the ArrayList of images that will be on the buttons
     * @param view
     */
    public Model() {
        images = new ArrayList<>(List.of("🦄", "🐭", "🐣", "🐶", "🦈", "🐼", "🐰", "🐹", "🦄", "🐭", "🐣", "🐶", "🦈", "🐼", "🐰", "🐹"));
        Collections.shuffle(images);
        matchedCount = 0;
    }

    /**
     * Method that returns the image of a button
     * @param index - the index of the button containing the image
     * @return - a string containing the image of the button
     */
    public String getImage(int index) {
        return images.get(index);
    }

    /**
     * Method to check if all the images have been matched
     * @return - true if all the images have been matched/ false otherwise
     */
    public boolean isMatched() {
        return matchedCount == images.size()/2;
    }

    /**
     * Method to check if the game is over
     * @return - true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return this.isMatched();
    }

}
