package edu.miracostacollege.cs112.ic15_nobelpeaceprize.view;


import edu.miracostacollege.cs112.ic15_nobelpeaceprize.controller.Controller;
import edu.miracostacollege.cs112.ic15_nobelpeaceprize.model.NobelLaureate;
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

    private ImageView laureateIV = new ImageView();
    private ComboBox<String> laureateTypeCB = new ComboBox<>();
    private TextField nameTF = new TextField();
    private Label nameLabel = new Label("Individual's Name:");
    private Label nameErrLabel = new Label("Name is required.");

    private TextField countryTF = new TextField();
    private Label countryErrLabel = new Label("Country is required.");

    private TextField yearTF = new TextField();
    private Label yearErrLabel = new Label("Year is required.");

    private TextField prizeAmountTF = new TextField();
    private Label prizeAmountErrLabel = new Label("Prize amount is required.");

    private TextField motivationTF = new TextField();
    private Label motivationErrLabel = new Label("Motivation is required :)");

    private ListView<NobelLaureate> laureatesLV = new ListView<>();

    private Button removeButton = new Button("- Remove Laureate");
    private Button addButton = new Button("+ Add Laureate");

    private Controller controller = Controller.getInstance();
    private ObservableList<NobelLaureate> laureatesList;
    private NobelLaureate selectedLaureate;

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
        laureateIV.setImage(new Image("nobel_peace_laureates.png"));
        laureateIV.setFitWidth(WIDTH);
        pane.add(laureateIV, 0, 0, 3, 1);

        pane.add(new Label("Laureate Type:"), 0, 1);
        pane.add(laureateTypeCB, 1, 1);

        // add items to combo box (dropdown)
        laureateTypeCB.getItems().addAll("Individual" , "Organization");
        // Select individual by default
        laureateTypeCB.getSelectionModel().select(0);

        // Change the text of the name label
        laureateTypeCB.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> changeNameLabe(newVal));

        pane.add(nameLabel, 0, 2);
        pane.add(nameTF, 1, 2);
        pane.add(nameErrLabel, 2, 2);
        nameErrLabel.setTextFill(Color.RED);
        nameErrLabel.setVisible(false);

        pane.add(new Label("Award Year:"), 0, 3);
        pane.add(yearTF, 1, 3);
        pane.add(yearErrLabel, 2, 3);
        yearErrLabel.setTextFill(Color.RED);
        yearErrLabel.setVisible(false);

        pane.add(new Label("Motivation:"), 0, 4);
        pane.add(motivationTF, 1, 4);
        pane.add(motivationErrLabel, 2, 4);
        motivationErrLabel.setTextFill(Color.RED);
        motivationErrLabel.setVisible(false);

        pane.add(new Label("Country:"), 0, 5);
        pane.add(countryTF, 1, 5);
        pane.add(countryErrLabel, 2, 5);
        countryErrLabel.setTextFill(Color.RED);
        countryErrLabel.setVisible(false);


        pane.add(new Label("Prize Amount $"), 0, 6);
        pane.add(prizeAmountTF, 1, 6);
        pane.add(prizeAmountErrLabel, 2, 6);
        prizeAmountErrLabel.setTextFill(Color.RED);
        prizeAmountErrLabel.setVisible(false);

        //wire up the add button to the addlaureate method
        addButton.setOnAction(e -> addLaureate());

        pane.add(addButton, 1, 7);
        laureatesLV.setPrefWidth(WIDTH);
        pane.add(laureatesLV, 0, 8, 3, 1);
        pane.add(removeButton, 0, 9);

        removeButton.setOnAction(e -> removeLaureate());

        // TODO: Uncomment when Controller.java is complete
        laureatesList = controller.getAllLaureates();
        laureatesLV.setItems(laureatesList);

        // Wire up an event for the LaureatesLV
        laureatesLV.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> selectedLaureate(newVal));

        removeButton.setDisable(true);

        this.setRoot(pane);
    }

    private void changeNameLabe(String newVal) {
        nameLabel.setText(newVal + "'s Name:");
    }

    private void selectedLaureate(NobelLaureate newVal) {
        selectedLaureate = newVal;
        removeButton.setDisable(selectedLaureate == null);

    }

    /**
     * Allows the user to modify an existing influencer by navigating to the ModifyInfluencer scene.
     * However, if the selected influencer is null, just return (do nothing)
     * Make sure to update the display (list view and combo boxes) after modifying the influencer.
     */
    private void removeLaureate() {
// remove the laureate from the observablelist and update the list view
        laureatesList.remove(selectedLaureate);
        laureatesLV.refresh();
        laureatesLV.getSelectionModel().select(-1);
    }

    /**
     * Allows the user to add a new influencer review by navigating to the AddInfluencer scene.
     * Make sure to update the display (list view and combo boxes) after adding the new influencer.
     */
    private void addLaureate() {
        // Read from all the textfields
        String name = nameTF.getText(), country = countryTF.getText(), motivation = motivationTF.getText();
            nameErrLabel.setVisible(name.isEmpty()); //repeate for the other fields
        int awardYear = Integer.parseInt(yearTF.getText());
        double prizeAmount = Double.parseDouble(prizeAmountTF.getText());
    laureatesList.add(new NobelLaureate(name,awardYear, motivation, country, prizeAmount));
   // laureatesList.add(0, new NobelLaureate(name,awardYear, motivation, country, prizeAmount));
    // now that we have a new laureate, update the list view
        laureatesLV.refresh();
    }

    /**
     * Updates the display after adding/modifying a influencer.  The idea being, if the user enters a new
     * location or country, it should appear in the appropriate combo box.  Also, the list view
     * should refresh to show the new/modified influencer.
     */
    private void updateDisplay()
    {
        laureatesLV.refresh();
    }

}