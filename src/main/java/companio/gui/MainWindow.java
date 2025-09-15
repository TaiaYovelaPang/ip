package companio.gui;

import companio.Companio;

import companio.CompanioException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Companio companio;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image companioImage = new Image(this.getClass().getResourceAsStream("/images/companio.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Companio instance */
    public void setCompanio(Companio c) {
        companio = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws CompanioException, IOException {
        String input = userInput.getText();
        String response = companio.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCompanioDialog(response, companioImage)
        );
        userInput.clear();
    }
}

