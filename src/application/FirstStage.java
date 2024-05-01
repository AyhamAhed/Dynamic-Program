package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FirstStage extends Main {

	static int counter = 0;

	// stages and scenes
	Stage mainStage = new Stage();
	Scene mainScene;
	Scene Scene2;

	MaxLedLighting ObjTest = new MaxLedLighting();
	Pairs objPairsTest = new Pairs();

	// buttons
	Button Lightbt;
	Button prevbt;
	Button Nextbt;
	Button GetTable;
	Button Calculatebt;
	Button btBack = new Button("Back");
	Button buttonToStage;

	// array list has objects from lines type "class lines"
	ArrayList<Lines> linesArrayList = new ArrayList<>();

	// create file and file chooser
	FileChooser fileChooser = new FileChooser();
	File file;

	public FirstStage() {

		// #labels && TextFields
		Label welcomeLabel = new Label("Welcome to Max LED lighting App");// welcome label
		welcomeLabel.setStyle(
				"-fx-background-radius: 50 50 50 50;-fx-background-color: #808080; -fx-text-fill: black; -fx-font-size: 16px; -fx-padding: 20px 40px;");
		Label descriptionLabel = new Label("\t\t\t\t\t\t\t\t\tThe input file has two line, \n\n"
				+ "First line is the number of LEDs, second line is a permutation of numbers that represent the ordering of the LEDs on the board.\n\n\t\t\t\t\tThe output will be the maximum number of LEDs that you can light without crossing");
		Label opLabel = new Label("Please upload the cases file:");

		// Buttons
		buttonToStage = new Button("Browse the file");
		Calculatebt = new Button("Calculate the Max Number");
		Calculatebt.setDisable(true);

		// #layouts
		HBox hBoxOperations = new HBox(15);
		hBoxOperations.getChildren().addAll(opLabel, buttonToStage, Calculatebt);
		hBoxOperations.setAlignment(Pos.CENTER);
		BorderPane mainBorder = new BorderPane();
		mainBorder.setAlignment(welcomeLabel, Pos.TOP_CENTER);
		mainBorder.setPadding(new Insets(25));
		mainBorder.setTop(welcomeLabel);
		mainBorder.setCenter(descriptionLabel);
		mainBorder.setBottom(hBoxOperations);
		mainScene = new Scene(mainBorder, 750, 500);

		Calculatebt.setOnAction(e -> {// calculate button when i click on it.
			SecondScene();
		});

		buttonToStage.setOnAction(e -> {// invoke this method when i click on openFileButton button
			linesArrayList.clear();
			FileChooser();
		});

		// show first stage
		mainStage.setScene(mainScene);
		mainStage.show();
		mainStage.setTitle("Welcome to Max Led Lighting");

	}

	public void FileChooser() {
		fileChooser.setTitle("Open File");
		file = fileChooser.showOpenDialog(mainStage);
		if (file != null) {// check if this file is empty or not
			try {
				readFile();
				for (Lines line : linesArrayList) {
					System.out.println(line);
				}
				System.out.println(linesArrayList.size());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Done Read");
			alert.setHeaderText(null);
			alert.setContentText("Thanks, the file has info.");
			alert.showAndWait();
//			for (Lines line : linesArrayList) {
//				System.out.println(line.toString());
//			}
			buttonToStage.setDisable(true);
			Calculatebt.setDisable(false);
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Soooooory");
			alert.setHeaderText(null);
			alert.setContentText("This file is empty whooooooooo.");
			alert.showAndWait();
		}
	}

	public void SecondScene() {

		Label welcomeLabel = new Label("Welcome to Calculate Scene");
		welcomeLabel.setStyle(
				"-fx-background-radius: 50 50 50 50;-fx-background-color: #999999; -fx-text-fill: black; -fx-font-size: 16px; -fx-padding: 20px 40px;");

		Button btShow = new Button("Show the permutation");
		Button btCal = new Button("Calculate");

//		Button btBack = new Button("Back");
		BorderPane bpane = new BorderPane();
		bpane.setPadding(new Insets(25));
		bpane.setAlignment(welcomeLabel, Pos.CENTER);
		bpane.setTop(welcomeLabel);
		HBox hpane = new HBox(25);

		btShow.setOnAction(e -> {//Show Permutation 
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("This is all number of LEDs with permutation, and how many tests");
			alert.setHeaderText(null);
			alert.setContentText(getTextContents().getText());
			// Get the dialog pane
			DialogPane dialogPane = alert.getDialogPane();
			// Set the preferred size (you can adjust width and height as needed)
			dialogPane.setPrefSize(550, 300);
			// Show the alert
			alert.showAndWait();
		});

		Lightbt = new Button("Show Light Leds");
		Lightbt.setOnAction(e -> {
			
			if(objPairsTest.pairs != null) {
				Scene ScrolScene = new Scene(createLEDScroll(linesArrayList.get(counter).n, linesArrayList.get(counter).permutation), 500, 500);
				mainStage.setScene(ScrolScene);
			}
			
		});

		prevbt = new Button("Previous Permutation");
		prevbt.setOnAction(e -> {
			if (counter > 0) {
				counter--;
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Done Decrement");
				alert.setHeaderText(null);
				alert.setContentText("\t\t\t Done Decrement the counter ");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("The end");
				alert.setHeaderText(null);
				alert.setContentText("\t\t\t The begin of tests ");
				alert.showAndWait();
			}
		});
		Nextbt = new Button("Next Permutation");
		Nextbt.setOnAction(e -> {
			if (counter < linesArrayList.size() - 1) {
				counter++;
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Done Increment");
				alert.setHeaderText(null);
				alert.setContentText("\t\t\t Done Increment the counter ");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("The end");
				alert.setHeaderText(null);
				alert.setContentText("\t\t\t The end of tests ");
				alert.showAndWait();
			}
		});

		GetTable = new Button("Get The Table result");
		GetTable.setOnAction(e -> {
			TextArea text = new TextArea();
			text = ObjTest.printDPTable(linesArrayList.get(counter).n, linesArrayList.get(counter).permutation);
			ObjTest.maxLedLighting(linesArrayList.get(counter).n, linesArrayList.get(counter).permutation);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("This is all number of LEDs with permutation, and how many tests");
			alert.setHeaderText(null);
			alert.setContentText(text.getText());
			// Get the dialog pane
			DialogPane dialogPane = alert.getDialogPane();
			// Set the preferred size (you can adjust width and height as needed)
			dialogPane.setPrefSize(650, 450);
			// Show the alert
			alert.showAndWait();

		});
		hpane.getChildren().addAll(prevbt, btCal, Nextbt, GetTable, Lightbt);
		bpane.setAlignment(btShow, Pos.CENTER);
		bpane.setAlignment(btBack, Pos.CENTER);
		hpane.setAlignment(Pos.CENTER);
		bpane.setLeft(btShow);
		bpane.setRight(btBack);
		bpane.setBottom(hpane);
		bpane.setCenter(new Text("You have " + Integer.toString(linesArrayList.size()) + " Tests"));

		Scene2 = new Scene(bpane, 750, 500);

		btCal.setOnAction(e -> {
//			linesArrayList.get(0);
			objPairsTest = ObjTest.maxLedLighting(linesArrayList.get(counter).n,
					linesArrayList.get(counter).permutation);
			if (counter < linesArrayList.size()) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Result of test");
				alert.setHeaderText(null);
				alert.setContentText("\t\t\t Test Result number (" + (counter + 1) + ")\n\n"
						+ linesArrayList.get(counter).toString() + "\n\n" + objPairsTest);
				alert.showAndWait();

			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Finish");
				alert.setHeaderText(null);
				alert.setContentText("\t\t\t end of the test ");
				alert.showAndWait();
//				counter = 0;
			}
//			System.out.println(ObjTest.maxLedLighting(linesArrayList.get(0).n, linesArrayList.get(0).permutation));

		});

		btBack.setOnAction(e -> {
			mainStage.setScene(mainScene);
			buttonToStage.setDisable(false);
		});

		mainStage.setScene(Scene2);
	}

	public TextArea getTextContents() {
		TextArea areaText = new TextArea();
		for (Lines line : linesArrayList) {
			areaText.setText(areaText.getText() + "\n" + line.toString() + "\n");
		}
		return areaText;
	}

	public void readFile() throws ParseException {
		try {
			Scanner scanner = new Scanner(file);
			int subcounter = 0;

			while (scanner.hasNextInt()) {
				subcounter += 2;
				int n = scanner.nextInt();

				// Consume the newline character after reading n
				scanner.nextLine();

				if (scanner.hasNextLine()) {
					// Read the next line as a string
					String data = scanner.nextLine().trim();

					// Split the string into an array of integers
					String[] dataArrStrings = data.split("\\s+");

					// Convert the array of strings to an array of integers
					int[] dataArr = new int[dataArrStrings.length];
					boolean[] seenNumbers = new boolean[n + 1];

					boolean hasError = false;

					// Check if the permutation size is not equal to the number of LEDs
					if (dataArrStrings.length != n) {
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Mismatch");
						alert.setHeaderText(null);
						alert.setContentText(" Mismatch in the number of LEDs! at line " + (subcounter - 1)
								+ " and line " + (subcounter));
						alert.showAndWait();
						hasError = true;
						continue;
					}

					for (int i = 0; i < dataArrStrings.length; i++) {
						dataArr[i] = Integer.parseInt(dataArrStrings[i]);

						// Check for redundancy
						if (seenNumbers[dataArr[i]]) {
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Redundancy");
							alert.setHeaderText(null);
							alert.setContentText("Redundancy found in permutation! at line " + (subcounter - 1)
									+ " and line " + (subcounter));
							alert.showAndWait();
							hasError = true;
							break; // Exit the loop if redundancy is found
						} else {
							seenNumbers[dataArr[i]] = true;
						}
					}

					// Check if the permutation has numbers more than the number of LEDs
					if (Arrays.stream(dataArr).max().orElse(0) > n) {
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Permutation Error");
						alert.setHeaderText(null);
						alert.setContentText("Permutation has numbers greater than the number of LEDs! at line "
								+ (subcounter - 1) + " and line " + (subcounter));
						alert.showAndWait();
						hasError = true;
					}

					if (!hasError) {
						Lines linesObject = new Lines(n, dataArr);
						linesArrayList.add(linesObject);
					}
				}
			}

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public ScrollPane createLEDScroll(int numberOfLEDs, int[] permutation ) {
        // Create an HBox to hold two VBoxes side by side
        HBox mainHBox = new HBox();
        mainHBox.setSpacing(20);
        mainHBox.setPadding(new Insets(10));

        // First VBox for the number of LEDs
        VBox numberVBox = new VBox();
        numberVBox.setSpacing(10);

        Label numberLabel = new Label("Number of LEDs: " + numberOfLEDs);
        numberVBox.getChildren().add(numberLabel);

        // Create a ToggleGroup to ensure only one RadioButton is selected at a time
//        ToggleGroup toggleGroupNumber = new ToggleGroup();

        // Populate the number VBox with LEDs and RadioButtons
        for (int i = 0, j = 0; i < numberOfLEDs; i++) {
            int ledNumber = i + 1; // LED numbering starts from 1
            RadioButton radioButton = new RadioButton("LED " + ledNumber);
//            radioButton.setToggleGroup(toggleGroupNumber);
            numberVBox.getChildren().add(radioButton);

            // Disable the radio button and set it as non-interactive
            if (j < objPairsTest.pairs.size() && objPairsTest.pairs.get(j).source == (i + 1)) {
                radioButton.setSelected(true);
//                radioButton.setDisable(true);
                radioButton.setMouseTransparent(true);
                j++;
            }
            else {
            	radioButton.setDisable(true);
//                radioButton.setMouseTransparent(true);
               
            }
        }

        // Second VBox for the permutation
        VBox permutationVBox = new VBox();
        permutationVBox.setSpacing(10);
        Label perLabel = new Label("Permutations : ");
        permutationVBox.getChildren().add(perLabel);

        // Create a ToggleGroup for permutation RadioButtons
//        ToggleGroup toggleGroupPermutation = new ToggleGroup();

        // Populate the permutation VBox with LEDs and RadioButtons
        for (int i = 0, j = 0; i < permutation.length; i++) {
            int ledNumber = permutation[i];
            RadioButton radioButton = new RadioButton("LED " + ledNumber);
//            radioButton.setToggleGroup(toggleGroupPermutation);
            permutationVBox.getChildren().add(radioButton);

            // Disable the radio button and set it as non-interactive
            if (j < objPairsTest.pairs.size() && objPairsTest.pairs.get(j).LED == (i + 1)) {
                radioButton.setSelected(true);
//                radioButton.setDisable(true);
                radioButton.setMouseTransparent(true);
                j++;
            }
            else {
            	radioButton.setDisable(true);
//                radioButton.setMouseTransparent(true);
                
            }
        }

        Button btBack = new Button("Back");
        // Add the VBoxes to the main HBox
        mainHBox.getChildren().addAll(numberVBox, permutationVBox, btBack);
        btBack.setOnAction(e -> {
            mainStage.setScene(Scene2);
        });

        // Create a ScrollPane and set its content to the main HBox
        ScrollPane scrollPane = new ScrollPane(mainHBox);
        scrollPane.setFitToWidth(true);

        return scrollPane;
    }
}
