package edu.miracostacollege.cs112.ic15_nobelpeaceprize.view;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * The <code>MainScene</code> represents the very first scene for the Nobel Peace Prize application.

 * The <code>MainScene</code> also allows for a user to add a new laureate or remove existing entries.
 */
public class InstructionsScene extends Scene {
    public static final int WIDTH = 720;
    public static final int HEIGHT = 250;

    private ImageView instructionsIV = new ImageView();





    /**
     * Constructs a new <code>MainScene</code>, representing the very first scene for the Nobel Peace Price Laureates application.
     *
     * The <code>MainScene</code> also allows for a user to add a new laureate or remove an existing one.
     */
    public InstructionsScene() {
        super(new GridPane(), WIDTH, HEIGHT);

        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        // TODO: Uncomment after configuring res folder


        instructionsIV.setImage(new Image("instructions.png"));
        instructionsIV.setFitWidth(WIDTH);
        pane.add(instructionsIV, 0, 0, 3, 1);

        this.setRoot(pane);
    }





    private void sendToMainScene()
    {
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
