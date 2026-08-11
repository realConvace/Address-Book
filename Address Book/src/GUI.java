import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUI extends Application {


    // Address Book.
    private AddressBook book;



    @Override
    public void start(Stage primaryStage) throws Exception {


        // Instantiating book.
        book = new AddressBook();


        // Labels
        Label firstNameLabel = new Label("First Name:");
        firstNameLabel.setPrefWidth(350);
        Label lastNameLabel = new Label("Last Name:");
        Label birthDateLabel = new Label("Birth Date (YYYY-MM-DD):");
        Label phoneLabel = new Label("Phone (###-###-####):");
        Label streetLabel = new Label("Street Address:");
        Label cityLabel = new Label("City:");
        Label stateLabel = new Label("State:");
        Label zipLabel = new Label("Zip Code:");


        // TextFields.
        TextField firstNameField = new TextField();
        firstNameField.setPrefWidth(350);
        TextField lastNameField = new TextField();
        TextField birthDateField = new TextField();
        TextField phoneField = new TextField();
        TextField streetField = new TextField();
        TextField cityField = new TextField();
        TextField stateField = new TextField();
        TextField zipField = new TextField();


        // Buttons
        Button addContact = new Button("Add Contact");
        addContact.setMaxWidth(Double.MAX_VALUE); // Sets to edge of grid.
        Button deleteContact = new Button("Delete Contact");
        deleteContact.setMaxWidth(Double.MAX_VALUE); // Sets to edge of grid.
        Button searchByName = new Button("Search By Name");
        searchByName.setMaxWidth(Double.MAX_VALUE); // Sets to edge of grid.
        Button printContacts = new Button("Print Contacts");
        printContacts.setMaxWidth(Double.MAX_VALUE); // Sets to edge of grid.
        Button findByBirthday = new Button("Find by Birthday");
        findByBirthday.setMaxWidth(Double.MAX_VALUE); // Sets to edge of grid.
        Button findByCity = new Button("Find by City");
        findByCity.setMaxWidth(Double.MAX_VALUE); // Sets to edge of grid.
        Button loadFromFile = new Button("Load from File");
        loadFromFile.setMaxWidth(Double.MAX_VALUE); // Sets to edge of grid.
        Button saveToFile = new Button("Save to File");
        saveToFile.setMaxWidth(Double.MAX_VALUE); // Sets to edge of grid.


        // Text Area
        TextArea area = new TextArea();


        // Grid pane.
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10));



        // Adding to grid pane.

        // Labels
        root.add(firstNameLabel, 0, 0);
        root.add(lastNameLabel, 0, 1);
        root.add(birthDateLabel, 0, 2);
        root.add(phoneLabel, 0, 3);
        root.add(streetLabel, 0, 4);
        root.add(cityLabel, 0, 5);
        root.add(stateLabel, 0, 6);
        root.add(zipLabel, 0, 7);

        // Text Fields.
        root.add(firstNameField, 1, 0);
        root.add(lastNameField, 1, 1);
        root.add(birthDateField, 1, 2);
        root.add(phoneField, 1, 3);
        root.add(streetField, 1, 4);
        root.add(cityField, 1, 5);
        root.add(stateField, 1, 6);
        root.add(zipField, 1, 7);


        // Buttons
        root.add(addContact, 0, 8);
        root.add(deleteContact, 1, 8);
        root.add(searchByName, 0, 9);
        root.add(printContacts, 1, 9);
        root.add(findByBirthday, 0, 10);
        root.add(findByCity, 1, 10);
        root.add(loadFromFile, 0, 11);
        root.add(saveToFile, 1, 11);


        // Text Area
        root.add(area, 0, 12, 2, 1);



        // Registering buttons.
        addContact.setOnAction(e -> {



            try {

                // Get string.
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String birthDate = birthDateField.getText();
                String phoneNumber = phoneField.getText();
                String streetAddress = streetField.getText();
                String city = cityField.getText();
                String state = stateField.getText();
                String zipCode = zipField.getText();


                // Instantiate.
                Contact contact = new Contact(firstName, lastName, birthDate, phoneNumber, streetAddress, city, state, zipCode);

                book.addContact(contact);

                area.setText("Contact added: " + contact);
            } catch (Exception error) {

                area.setText(error.getMessage());
            }

        });


        deleteContact.setOnAction(e -> {

            // Get string.
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();

            // Checking if empty
            if (firstName.isEmpty() || lastName.isEmpty()) {

                area.setText("Error: First or last name text fields are empty!");
                return;
            }


            // Check if deleted and delete if so.
            boolean flag = book.deleteContact(firstName, lastName);

            // if true, then deleted.
            if (flag) {
                area.setText("Contact deleted: " + firstName + " " + lastName);
            }
            else {
                area.setText("Error: Contact was not found: " + firstName + " " + lastName);
            }


        });



        searchByName.setOnAction(e -> {


            // Get string.
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();

            // Checking if empty
            if (firstName.isEmpty() || lastName.isEmpty()) {

                area.setText("Error: First or last name text fields are empty!");
                return;
            }



            // Search
            Contact contact = book.searchByName(firstName, lastName);

            // Found or not?
            if (contact != null) {
                area.setText("Found: " + contact);
            }
            else {
                area.setText("Error: Contact was not found: " + firstName + " " + lastName);
            }




        });



        printContacts.setOnAction(e -> {


            // Get the sorted contacts.
            ArrayList<Contact> contacts = book.getContactsSorted();


            // Checking if empty
            if (contacts.isEmpty()) {

                area.setText("Error: No contacts were found!");
                return;
            }


            StringBuilder temp = new StringBuilder();

            temp.append("All Contacts:\n");

            // Appending for each.
            for (int i = 0; i < contacts.size(); i++) {


                temp.append(contacts.get(i) + "\n");
            }

            area.setText(temp.toString());




        });




        findByBirthday.setOnAction(e -> {


            // Get string.
            String birthday = birthDateField.getText();

            // Checking if empty
            if (birthday.isEmpty()) {

                area.setText("Error: Birthday is empty!");
                return;
            }


            // Gather the contacts.
            ArrayList<Contact> contacts = book.searchByBirthday(birthday);

            // Checking if empty
            if (contacts.isEmpty()) {

                area.setText("Error: No contacts with that birthday were found!");
                return;
            }


            StringBuilder temp = new StringBuilder();

            temp.append("Contacts with birthday on " + birthday + ":\n");

            // Appending for each.
            for (int i = 0; i < contacts.size(); i++) {


                temp.append(contacts.get(i) + "\n");
            }

            area.setText(temp.toString());


        });




        findByCity.setOnAction(e -> {


            // Get string.
            String city = cityField.getText();

            // Checking if empty
            if (city.isEmpty()) {

                area.setText("Error: City is empty!");
                return;
            }


            // Gather the contacts.
            ArrayList<Contact> contacts = book.searchByCity(city);

            // Checking if empty
            if (contacts.isEmpty()) {

                area.setText("Error: No contacts in that city were found!");
                return;
            }


            StringBuilder temp = new StringBuilder();

            temp.append("Contacts in " + city + ":\n");

            // Appending for each.
            for (int i = 0; i < contacts.size(); i++) {


                temp.append(contacts.get(i) + "\n");
            }

            area.setText(temp.toString());




        });




        loadFromFile.setOnAction(e -> {


            book.loadFromFile("addressBookData.txt");

            area.setText("Contacts loaded from file.");


        });



        saveToFile.setOnAction(e -> {



            book.saveToFile("addressBookData.txt");
            area.setText("Contacts saved to file.");

        });


        // Setting the scene.
        Scene scene = new Scene(root, 730, 625);



        // Setting the stage.
        primaryStage.setTitle("Address Book");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
