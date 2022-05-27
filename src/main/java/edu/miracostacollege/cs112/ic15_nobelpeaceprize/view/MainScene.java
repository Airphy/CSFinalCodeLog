package edu.miracostacollege.cs112.ic15_nobelpeaceprize.view;


import edu.miracostacollege.cs112.ic15_nobelpeaceprize.controller.Controller;
import edu.miracostacollege.cs112.ic15_nobelpeaceprize.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.web.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.LinkedHashMap;

/**
 * The <code>MainScene</code> represents the very first scene for the Nobel Peace Prize application.
 * <p>
 * The <code>MainScene</code> also allows for a user to add a new laureate or remove existing entries.
 */
public class MainScene extends Scene {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 1000;

    private ImageView codeLogIV = new ImageView();
    private ComboBox<String> codeLogTypeCB = new ComboBox<>();

    private LinkedHashMap<String, Integer> websitesMap = new LinkedHashMap<>();
    private TextField exerciseIDTF = new TextField();
    private Label exerciseIDLabel = new Label("Exercise ID:");
    private Label exerciseIDErrLabel = new Label("Name is required.");

    private TextField urlTF = new TextField();
    private Label urlLabel = new Label("URL:");
    private Label urlErrLabel = new Label("Valid URL is required.");

    private TextArea submissionTA = new TextArea();
    private Label submissionLabel = new Label("Submission:");

    private TextField dateAttemptedTF = new TextField();
    private Label dateAttemptedErrLabel = new Label("Date is required.");

    private ListView<CodingWebsites> codeLogLV = new ListView<>();

    private Button removeButton = new Button("- Remove Log");
    private Button addButton = new Button("+ Add Log");
    private Button newTotalSceneButton = new Button("See Total");
    private Button newCreditsSceneButton = new Button("Credits");
    private Button newInstructionsSceneButton = new Button("Help");

    private Controller controller = Controller.getInstance();
    private ObservableList<CodingWebsites> codeLogList;
    private CodingWebsites selectedWebsite;

    private WebView webView = new WebView();
    private WebEngine webEngine = webView.getEngine();

    private static final String defaultContent = "<h4>Please enter a valid URL or select a record</h4>";

    private CheckBox completedCB = new CheckBox();

    /**
     * Constructs a new <code>MainScene</code>, representing the very first scene for the Nobel Peace Price Laureates application.
     * <p>
     * The <code>MainScene</code> also allows for a user to add a new laureate or remove an existing one.
     */
    public MainScene() {
        super(new GridPane(), WIDTH, HEIGHT);

        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        int rowIndex = 0;


        codeLogIV.setImage(new Image("codelog.gif"));
        codeLogIV.setFitWidth(WIDTH);
        pane.add(codeLogIV, 0, rowIndex, 3, 1);

        pane.add(new Label("Website:"), 0, ++rowIndex);
        pane.add(codeLogTypeCB, 1, rowIndex);

        websitesMap.put("Leet Code", 0);
        websitesMap.put("Hacker Rank", 1);
        websitesMap.put("Code Wars", 2);
        websitesMap.put("Code Chef", 3);

        // add items to combo box (dropdown)
        codeLogTypeCB.getItems().addAll(websitesMap.keySet());
        // Select individual by default
        codeLogTypeCB.getSelectionModel().select(0);

        // Change the text of the name label
        codeLogTypeCB.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> changeNameLabel(newVal));

        pane.add(exerciseIDLabel, 0, ++rowIndex);
        pane.add(exerciseIDTF, 1, rowIndex);
        pane.add(exerciseIDErrLabel, 2, rowIndex);
        exerciseIDErrLabel.setTextFill(Color.RED);
        exerciseIDErrLabel.setVisible(false);

        pane.add(new Label("Date Attempted:"), 0, ++rowIndex);
        pane.add(dateAttemptedTF, 1, rowIndex);
        pane.add(dateAttemptedErrLabel, 2, rowIndex);
        dateAttemptedErrLabel.setTextFill(Color.RED);
        dateAttemptedErrLabel.setVisible(false);

        pane.add(urlLabel, 0, ++rowIndex);
        pane.add(urlTF, 1, rowIndex,3,1);
        pane.add(urlErrLabel, 4, rowIndex);
        urlErrLabel.setTextFill(Color.RED);
        urlErrLabel.setVisible(false);

        pane.add(submissionLabel, 0, ++rowIndex);
        pane.add(submissionTA, 1, rowIndex, 3, 1);

		pane.add(new Label("Completed:"), 0, ++rowIndex);
		pane.add(completedCB, 1, rowIndex);


        pane.add(removeButton, 0, ++rowIndex);
        removeButton.setOnAction(e -> removeLog());
        removeButton.setDisable(true);

        pane.add(addButton, 1, rowIndex);
        //wire up the add button to the addWebsite method
        addButton.setOnAction(e -> addWebsite());

        pane.add(newTotalSceneButton, 2, rowIndex);
        newTotalSceneButton.setOnAction(e -> sendToTotalScene());

        pane.add(newCreditsSceneButton, 3, rowIndex);
        newCreditsSceneButton.setOnAction(e -> sendToCreditsScene());

//<<<<<<< HEAD
        pane.add(newInstructionsSceneButton, 4, rowIndex);
        newInstructionsSceneButton.setOnAction(e -> sendToInstructionsScene());
//=======
        codeLogLV.setPrefWidth(WIDTH);
        codeLogLV.setPrefHeight(350);
        pane.add(codeLogLV, 0, ++rowIndex, 5, 1);

//>>>>>>> 9d70cf2bfe085c882fe61b448b95fbacb0f86f4a


        removeButton.setOnAction(e -> removeLog());

        codeLogList = controller.getAllWebsites();
        codeLogLV.setItems(codeLogList);

        // Wire up an event for a website
        codeLogLV.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> selectedWebsite(newVal));

        // Wire up an event for web view
        urlTF.textProperty().addListener((observable, oldVal, newVal) -> {
            changedUrl(newVal);
        });

        webEngine.loadContent(defaultContent);

        pane.add(webView, 0, ++rowIndex, 5, 1);
        this.setRoot(pane);
    }

    private void changeNameLabel(String newVal) {
        exerciseIDLabel.setText(newVal + "'s Name:");
    }

    private void selectedWebsite(CodingWebsites newVal) {
        if (newVal == null) {
            removeButton.setDisable(true);
            return;
        }
        removeButton.setDisable(false);
        selectedWebsite = newVal;

        submissionTA.setText(selectedWebsite.getSubmission());

        // load the URL
        webEngine.loadContent("Loading: " + selectedWebsite.getUrl());
        webEngine.load(selectedWebsite.getUrl());
    }

    /**
     * Loads the web page when value is changed
     * @param newVal
     */
    private void changedUrl(String newVal) {
        if (isValidURL(newVal)) {
            webEngine.loadContent("Loading: " + newVal);
            webEngine.load(newVal);
        } else if (!newVal.isEmpty()) {
            // show the error only if the new URL is not empty and is invalid
            webEngine.loadContent(defaultContent);
        }
    }


    /**
        From Stack Over Flow:
        <a href="https://stackoverflow.com/a/41268655">https://stackoverflow.com/a/41268655</a>
     */
    public static boolean isValidURL(String urlString) {
        try {
            URL url = new URL(urlString);
            url.toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void sendToTotalScene()
    {
        ViewNavigator.loadScene("Log Total", new LogTotalScene());
    }
    private void sendToCreditsScene()
    {
        ViewNavigator.loadScene("Developer Credits", new CreditsScene());
    }
    private void sendToInstructionsScene()
    {
        ViewNavigator.loadScene("Instructions", new InstructionsScene());
    }

    /**
     * Allows the user to modify an existing influencer by navigating to the ModifyInfluencer scene.
     * However, if the selected influencer is null, just return (do nothing)
     * Make sure to update the display (list view and combo boxes) after modifying the influencer.
     */
    private void removeLog() {
        codeLogList.remove(selectedWebsite);
        codeLogLV.refresh();
        codeLogLV.getSelectionModel().select(-1);


        webEngine.load(null);

    }

    /**
     * Allows the user to add a new influencer review by navigating to the AddInfluencer scene.
     * Make sure to update the display (list view and combo boxes) after adding the new influencer.
     */
    private void addWebsite() {
        // Read from all the textfields
        String idName = exerciseIDTF.getText(),
                dateAttempted = dateAttemptedTF.getText(),
                url = urlTF.getText(),
                submission = submissionTA.getText();

        exerciseIDErrLabel.setVisible(idName.isEmpty());
        dateAttemptedErrLabel.setVisible(dateAttempted.isEmpty());
        urlErrLabel.setVisible(url.isEmpty() || !isValidURL(url));

        boolean completed;
        exerciseIDErrLabel.setVisible(idName.isEmpty()); //repeat for the other fields
        dateAttemptedErrLabel.setVisible(dateAttempted.isEmpty());

        completed = completedCB.isSelected();


        // laureatesList.add(0, new NobelLaureate(name,awardYear, motivation, country, prizeAmount));
        // now that we have a new laureate, update the list view


        if (exerciseIDErrLabel.isVisible() || dateAttemptedErrLabel.isVisible() || urlErrLabel.isVisible())
            return;


        switch (codeLogTypeCB.getSelectionModel().getSelectedIndex()) {
            case 0:
                codeLogList.add(new LeetCode(idName, dateAttempted, completed, url, submission));
                break;
            case 1:
                codeLogList.add(new HackerRank(idName, dateAttempted, completed, url, submission));
                break;
            case 2:
                codeLogList.add(new CodeWars(idName, dateAttempted, completed, url, submission));
                break;
            case 3:
                codeLogList.add(new CodeChef(idName, dateAttempted, completed, url, submission));
                break;
            default:
                return;
        }


        updateDisplay();
        clearInputs();
    }

    /**
     * Updates the display after adding/modifying a influencer.  The idea being, if the user enters a new
     * location or country, it should appear in the appropriate combo box.  Also, the list view
     * should refresh to show the new/modified influencer.
     */
    private void updateDisplay() {
        FXCollections.sort(codeLogList);
        codeLogLV.refresh();
    }

    private void clearInputs() {
        codeLogTypeCB.getSelectionModel().select(0);
        exerciseIDTF.setText("");
        dateAttemptedTF.setText("");
        urlTF.setText("");
        completedCB.setSelected(false);
    }

}
