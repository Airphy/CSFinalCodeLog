package edu.miracostacollege.cs112.ic15_nobelpeaceprize.view;


import edu.miracostacollege.cs112.ic15_nobelpeaceprize.controller.Controller;
import edu.miracostacollege.cs112.ic15_nobelpeaceprize.model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * The <code>MainScene</code> represents the very first scene for the Nobel Peace Prize application.

 * The <code>MainScene</code> also allows for a user to add a new laureate or remove existing entries.
 */
public class MainScene extends Scene {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;

    private ImageView codeLogIV = new ImageView();
    private ComboBox<String> codeLogTypeCB = new ComboBox<>();
    private TextField exerciseID = new TextField();
    private Label exerciseIDLabel = new Label("Exercise ID:");
    private Label exerciseIDErrLabel = new Label("Name is required.");

    private TextField dateAttemptedTF = new TextField();
    private Label dateAttemptedErrLabel = new Label("Date is required.");

    private ListView<CodingWebsites> codeLogLV = new ListView<>();

    private Button removeButton = new Button("- Remove Log");
    private Button addButton = new Button("+ Add Log");

    private Controller controller = Controller.getInstance();
    private ObservableList<CodingWebsites> codeLogList;
    private CodingWebsites selectedWebsite;

    private CheckBox completedCB = new CheckBox();

    /**
     * Constructs a new <code>MainScene</code>, representing the very first scene for the Nobel Peace Price Laureates application.
     *
     * The <code>MainScene</code> also allows for a user to add a new laureate or remove an existing one.
     */
    public MainScene() {
        super(new GridPane(), WIDTH, HEIGHT);

        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        // TODO: Uncomment after configuring res folder
        codeLogIV.setImage(new Image("codelogphoto1.png"));
        codeLogIV.setFitWidth(WIDTH);
        pane.add(codeLogIV, 0, 0, 3, 1);

        pane.add(new Label("Website:"), 0, 1);
        pane.add(codeLogTypeCB, 1, 1);

        // add items to combo box (dropdown)
        codeLogTypeCB.getItems().addAll("Leet Code" , "Hacker Rank", "Code Wars", "Code Chef");
        // Select individual by default
        codeLogTypeCB.getSelectionModel().select(0);

        // Change the text of the name label
        codeLogTypeCB.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> changeNameLabel(newVal));

        pane.add(exerciseIDLabel, 0, 2);
        pane.add(exerciseID, 1, 2);
        pane.add(exerciseIDErrLabel, 2, 2);
        exerciseIDErrLabel.setTextFill(Color.RED);
        exerciseIDErrLabel.setVisible(false);

        pane.add(new Label("Date Attempted:"), 0, 4);
        pane.add(dateAttemptedTF, 1, 4);
        pane.add(dateAttemptedErrLabel, 2, 4);
        dateAttemptedErrLabel.setTextFill(Color.RED);
        dateAttemptedErrLabel.setVisible(false);

        pane.add(new Label("Completed:"), 0, 5);
        pane.add(completedCB, 1, 5);


        //wire up the add button to the addlaureate method
        addButton.setOnAction(e -> addWebsite());

        pane.add(addButton, 1, 7);
        codeLogLV.setPrefWidth(WIDTH);
        pane.add(codeLogLV, 0, 8, 3, 1);
        pane.add(removeButton, 0, 9);

        removeButton.setOnAction(e -> removeLog());

        // TODO: Uncomment when Controller.java is complete
        codeLogList = controller.getAllWebsites();
        codeLogLV.setItems(codeLogList);

        // Wire up an event for the LaureatesLV
        codeLogLV.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> selectedWebsite(newVal));

        removeButton.setDisable(true);

        this.setRoot(pane);
    }

    private void changeNameLabel(String newVal) {
        exerciseIDLabel.setText(newVal + "'s Name:");
    }

    private void selectedWebsite(CodingWebsites newVal) {
        selectedWebsite = newVal;
        removeButton.setDisable(selectedWebsite == null);

    }

    /**
     * Allows the user to modify an existing influencer by navigating to the ModifyInfluencer scene.
     * However, if the selected influencer is null, just return (do nothing)
     * Make sure to update the display (list view and combo boxes) after modifying the influencer.
     */
    private void removeLog() {
// remove the laureate from the observablelist and update the list view
        codeLogList.remove(selectedWebsite);
        codeLogLV.refresh();
        codeLogLV.getSelectionModel().select(-1);
    }

    /**
     * Allows the user to add a new influencer review by navigating to the AddInfluencer scene.
     * Make sure to update the display (list view and combo boxes) after adding the new influencer.
     */
    private void addWebsite() {
        // Read from all the textfields
        String idName = exerciseID.getText(), dateAttempted = dateAttemptedTF.getText();
        boolean completed;
        exerciseIDErrLabel.setVisible(idName.isEmpty()); //repeate for the other fields
        dateAttemptedErrLabel.setVisible(dateAttempted.isEmpty());

        completed = completedCB.isSelected();


   // laureatesList.add(0, new NobelLaureate(name,awardYear, motivation, country, prizeAmount));
    // now that we have a new laureate, update the list view


        if (exerciseIDErrLabel.isVisible() || dateAttemptedErrLabel.isVisible())
            return;


        switch(codeLogTypeCB.getSelectionModel().getSelectedIndex())
        {
            case 0:
                codeLogList.add(new HackerRank(idName,dateAttempted, completed));
                break;
            case 1:
                codeLogList.add(new LeetCode(idName,dateAttempted, completed));
                break;
            case 2:
                codeLogList.add(new CodeWars(idName,dateAttempted, completed));
                break;
            case 3:
                codeLogList.add(new CodeChef(idName,dateAttempted, completed));
                break;
        }


        updateDisplay();
        clearInputs();
    }

    /**
     * Updates the display after adding/modifying a influencer.  The idea being, if the user enters a new
     * location or country, it should appear in the appropriate combo box.  Also, the list view
     * should refresh to show the new/modified influencer.
     */
    private void updateDisplay()
    {
        codeLogLV.refresh();
    }

    private void clearInputs() {


        codeLogTypeCB.getSelectionModel().select(0);
        exerciseID.setText("");
        dateAttemptedTF.setText("");
        completedCB.setSelected(false);
    }

}
