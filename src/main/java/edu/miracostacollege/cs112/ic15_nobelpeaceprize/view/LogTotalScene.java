package edu.miracostacollege.cs112.ic15_nobelpeaceprize.view;


import edu.miracostacollege.cs112.ic15_nobelpeaceprize.controller.Controller;
import edu.miracostacollege.cs112.ic15_nobelpeaceprize.model.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 * The <code>MainScene</code> represents the very first scene for the Nobel Peace Prize application.
 * <p>
 * The <code>MainScene</code> also allows for a user to add a new laureate or remove existing entries.
 */
public class LogTotalScene extends Scene {
    private TableView<Stat> table = new TableView<Stat>();
    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;


    private Label totalLogNumberLabel = new Label("Total Number of Exercises Completed:");
    private Label exerciseIDErrLabel = new Label("Unchanged");

    private TextField dateAttemptedTF = new TextField();


    private Button newMainSceneButton = new Button("Back to Code Log");


    /**
     * Constructs a new <code>MainScene</code>, representing the very first scene for the Nobel Peace Price Laureates application.
     * <p>
     * The <code>MainScene</code> also allows for a user to add a new laureate or remove an existing one.
     */
    public LogTotalScene() {
        super(new GridPane(), WIDTH, HEIGHT);

        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        table.setEditable(false);

        TableColumn<Stat, String> displayNameCol = new TableColumn<>("Website");
        displayNameCol.setCellValueFactory(new PropertyValueFactory<Stat, String>("displayName"));
        TableColumn<Stat, Integer> totalCodesCol = new TableColumn<>("Total Codes");
        totalCodesCol.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("total"));
        TableColumn<Stat, Integer> completedCol = new TableColumn<>("Completed");
        completedCol.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("completed"));

        table.getColumns().addAll(displayNameCol, totalCodesCol, completedCol);

        ObservableList<CodingWebsites> websites = Controller.getInstance().getAllWebsites();
        ObservableList<Stat> data = FXCollections.observableArrayList(
                getStats(websites, "Leet Code"),
                getStats(websites, "Hacker Rank"),
                getStats(websites, "Code Wars"),
                getStats(websites, "Code Chef")
        );

        table.setItems(data);

        pane.add(new Label("Total Number of Exercises Completed:"), 1, 0);

        pane.add(table, 1, 1, 3, 5);

        pane.add(newMainSceneButton, 1, 9);
        newMainSceneButton.setOnAction(e -> sendToMainScene());

        this.setRoot(pane);
    }

    public Stat getStats(ObservableList<CodingWebsites> websites, String displayName) {
        Integer total = 0, completed = 0;
        for (CodingWebsites website : websites) {
            if (website == null || website.getDisplayName() != displayName) {
                continue;
            }
            total++;
            if (website.isCompleted())
                completed++;
        }
        return new Stat(displayName, total, completed);
    }

    public static class Stat {
        private String displayName;
        private int total;
        private int completed;

        public Stat(String displayName, int total, int completed) {
            this.displayName = displayName;
            this.total = total;
            this.completed = completed;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getCompleted() {
            return completed;
        }

        public void setCompleted(int completed) {
            this.completed = completed;
        }

    }

    private void sendToMainScene() {
        ViewNavigator.loadScene("Code Log", new MainScene());
    }

    /**
     * Allows the user to modify an existing influencer by navigating to the ModifyInfluencer scene.
     * However, if the selected influencer is null, just return (do nothing)
     * Make sure to update the display (list view and combo boxes) after modifying the influencer.
     */


    /**
     * Allows the user to add a new influencer review by navigating to the AddInfluencer scene.
     * Make sure to update the display (list view and combo boxes) after adding the new influencer.
     */


    /**
     * Updates the display after adding/modifying a influencer.  The idea being, if the user enters a new
     * location or country, it should appear in the appropriate combo box.  Also, the list view
     * should refresh to show the new/modified influencer.
     */


}
