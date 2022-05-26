package edu.miracostacollege.cs112.ic15_nobelpeaceprize.controller;

import edu.miracostacollege.cs112.ic15_nobelpeaceprize.model.CodingWebsites;
import edu.miracostacollege.cs112.ic15_nobelpeaceprize.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The <code>Controller</code> is a Singleton object that relays all commands between the Model and View
 * (and vice versa).  There is only one Controller object, accessible by a call to the static getInstance()
 * method.
 *
 * @author Michael Paulding
 * @version 1.0
 */
public class Controller {

	//1)
	private static Controller theInstance;
	private ObservableList<CodingWebsites> mLogList;

	//2) no one else can use the class constructor
	private Controller ()
	{
		//fill the allLaureatesList with data from Model
		// if the binary file has data, fill with the binary file,


	}

	/**
	 * Gets the one instance of the Controller.
	 * @return The instance
	 */
	// 3) completing the Singleton pattern
	public static Controller getInstance() {
		// if null, new object
		if(theInstance == null) {
			theInstance = new Controller();

			if (Model.binaryFileHasData())
				theInstance.mLogList = Model.populateListFromBinaryFile();
			else
				// empty list
				theInstance.mLogList = FXCollections.observableArrayList();
		}
		// otherwise return the instance
		return theInstance;
	}

	/**
	 * Gets the list of all laureates.
	 * @return The list of all laureates.
	 */
	public ObservableList<CodingWebsites> getAllWebsites() {
		return mLogList;
	}

	/**
	 * Makes a request for the model to save all the laureates data (the list of all laureates) to
	 * a persistent binary file.
	 */
	public void saveData() {
		Model.writeDataToBinaryFile(mLogList);
	}
}
