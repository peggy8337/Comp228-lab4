package exercise1.peichitseng_comp228lab4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentInfoApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField nameField = new TextField();
        TextField addressField = new TextField();
        TextField cityField = new TextField();
        TextField provinceField = new TextField();
        TextField postalCodeField = new TextField();
        TextField phoneNumberField = new TextField();
        TextField emailField = new TextField();

        ToggleGroup majorGroup = new ToggleGroup();
        RadioButton csButton = new RadioButton("Computer Science");
        csButton.setToggleGroup(majorGroup);
        RadioButton businessButton = new RadioButton("Business");
        businessButton.setToggleGroup(majorGroup);

        ComboBox<String> courseComboBox = new ComboBox<>();
        ListView<String> courseListView = new ListView<>();
        courseListView.setMaxHeight(150);
        ObservableList<String> csCourses = FXCollections.observableArrayList("Java", "Python", "C#", "JavaScript", "SQL");
        ObservableList<String> businessCourses = FXCollections.observableArrayList("Accounting", "Marketing", "Management", "Finance","Statistics");

        majorGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle == csButton) {
                courseComboBox.setItems(csCourses);
            } else if (newToggle == businessButton) {
                courseComboBox.setItems(businessCourses);
            }
        });

        courseComboBox.setOnAction(e -> {
            String selectedCourse = courseComboBox.getSelectionModel().getSelectedItem();
            if (selectedCourse != null && !courseListView.getItems().contains(selectedCourse)) {
                courseListView.getItems().add(selectedCourse);
            }
        });

        CheckBox studentCouncilCheckBox = new CheckBox("Student Council");
        CheckBox volunteerWorkCheckBox = new CheckBox("Volunteer Work");

        TextArea displayArea = new TextArea();
        displayArea.setEditable(false);
        displayArea.setMinHeight(50);

        Button displayButton = new Button("Display");
        displayButton.setOnAction(e -> {
            if (validateInputs(nameField, addressField, cityField, provinceField, postalCodeField, phoneNumberField, emailField, majorGroup, csButton, businessButton)) {
                String studentInfo = String.format("%s, %s, %s, %s, %s, %s, %s, %s, \nCourses: %s, \nActivities: %s%s",
                        nameField.getText(), addressField.getText(), cityField.getText(), provinceField.getText(), postalCodeField.getText(), phoneNumberField.getText(), emailField.getText(),
                        ((RadioButton) majorGroup.getSelectedToggle()).getText(), courseListView.getItems().toString(),
                        studentCouncilCheckBox.isSelected() ? "Student Council " : "", volunteerWorkCheckBox.isSelected() ? "Volunteer Work" : "");
                displayArea.setText(studentInfo);
            }
        });

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);

        inputGrid.add(new Label("Name:"), 0, 0);
        inputGrid.add(nameField, 1, 0);
        inputGrid.add(new Label("Address:"), 0, 1);
        inputGrid.add(addressField, 1, 1);
        inputGrid.add(new Label("City:"), 0, 2);
        inputGrid.add(cityField, 1, 2);
        inputGrid.add(new Label("Province:"), 0, 3);
        inputGrid.add(provinceField, 1, 3);
        inputGrid.add(new Label("Postal Code:"), 0, 4);
        inputGrid.add(postalCodeField, 1, 4);
        inputGrid.add(new Label("Phone Number:"), 0, 5);
        inputGrid.add(phoneNumberField, 1, 5);
        inputGrid.add(new Label("Email:"), 0, 6);
        inputGrid.add(emailField, 1, 6);

        VBox inputBox = new VBox(10, inputGrid);
        VBox activityBox = new VBox(10, studentCouncilCheckBox, volunteerWorkCheckBox);
        HBox majorBox = new HBox(10, csButton, businessButton);
        VBox courseBox = new VBox(10, new Label("Select Course"), courseComboBox, new Label("Added Courses"), courseListView);
        VBox rightBox = new VBox(10, majorBox, courseBox);

        HBox upperBox = new HBox(10, inputBox, activityBox, rightBox);
        HBox buttonBox = new HBox(10, displayButton);
        buttonBox.setPadding(new Insets(0, 300, 0, 300));
        VBox mainLayout = new VBox(10, upperBox, buttonBox, displayArea);
        mainLayout.setPadding(new Insets(10));

        Scene scene = new Scene(mainLayout, 700, 400);
        primaryStage.setTitle("Student Information Entry");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean validateInputs(TextField nameField, TextField addressField, TextField cityField, TextField provinceField, TextField postalCodeField, TextField phoneNumberField, TextField emailField, ToggleGroup majorGroup, RadioButton csButton, RadioButton businessButton) {
        boolean isValid = true;

        if (nameField.getText().trim().isEmpty()) {
            nameField.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            nameField.setStyle(null);
        }

        if (addressField.getText().trim().isEmpty()) {
            addressField.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            addressField.setStyle(null);
        }

        if (cityField.getText().trim().isEmpty()) {
            cityField.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            cityField.setStyle(null);
        }

        if (provinceField.getText().trim().isEmpty()) {
            provinceField.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            provinceField.setStyle(null);
        }

        if (postalCodeField.getText().trim().isEmpty()) {
            postalCodeField.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            postalCodeField.setStyle(null);
        }

        if (phoneNumberField.getText().trim().isEmpty()) {
            phoneNumberField.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            phoneNumberField.setStyle(null);
        }

        if (emailField.getText().trim().isEmpty() || !emailField.getText().contains("@")) {
            emailField.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            emailField.setStyle(null);
        }

        if (majorGroup.getSelectedToggle() == null) {
            csButton.setStyle("-fx-border-color: red;");
            businessButton.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            csButton.setStyle(null);
            businessButton.setStyle(null);
        }

        return isValid;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
