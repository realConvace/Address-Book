import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AddressBook {



    // Data fields.
    private ArrayList<Contact> contacts;




    // No-arg constructor
    public AddressBook() {
        contacts = new ArrayList<>();

    }



    // Add a contact to the book.
    public void addContact(Contact contact) {

        contacts.add(contact);
    }


    // Delete contact found by first and last name.
    public boolean deleteContact(String firstName, String lastName) {


        // Get the original size of the list.
        int originalSize = contacts.size();


        // Search for the contact.
        for (int i = 0; i < contacts.size(); i++) {

            // If found.
            if (contacts.get(i).getFirstName().equalsIgnoreCase(firstName) && contacts.get(i).getLastName().equalsIgnoreCase(lastName)) {

                contacts.remove(i);
                break;
            }
        }

        // Return if size changed, therefore it was found.
        return contacts.size() < originalSize;
    }



    // Search by name.
    public Contact searchByName(String firstName, String lastName) {


        // Search for the contact.
        for (int i = 0; i < contacts.size(); i++) {

            // If found.
            if (contacts.get(i).getFirstName().equalsIgnoreCase(firstName) && contacts.get(i).getLastName().equalsIgnoreCase(lastName)) {

                return contacts.get(i); // Return a reference to that contact.
            }
        }

        // If failed, return null.
        return null;
    }




    // Search by birthday.
    public ArrayList<Contact> searchByBirthday(String birthday) {

        ArrayList<Contact> lst = new ArrayList<>();

        // Search for the birthday.
        for (int i = 0; i < contacts.size(); i++) {

            // If found.
            if (contacts.get(i).getBirthDate().equalsIgnoreCase(birthday)) {

                // Add it.
                lst.add(contacts.get(i));
            }
        }

        return lst;
    }



    // Search by city.
    public ArrayList<Contact> searchByCity(String city) {

        ArrayList<Contact> lst = new ArrayList<>();

        // Search for the birthday.
        for (int i = 0; i < contacts.size(); i++) {

            // If found.
            if (contacts.get(i).getCity().equalsIgnoreCase(city)) {

                // Add it.
                lst.add(contacts.get(i));
            }
        }

        return lst;
    }




    // Gets the contacts sorted by last name, like in the text area example.
    public ArrayList<Contact> getContactsSorted() {


        ArrayList<Contact> sorted = new ArrayList<>(contacts); // Copy constructor.

        // Sort by last name.
        Collections.sort(sorted, (contactOne, contactTwo) -> {


                    // Try to sort by last name.
                    int val = contactOne.getLastName().compareToIgnoreCase(contactTwo.getLastName());

                    // If they're not equal.
                    if (val != 0) {
                        return val;
                    }

                    // If they're equal, sort by first name.
                    return contactOne.getFirstName().compareToIgnoreCase(contactTwo.getFirstName());
                }
        );

        return sorted;
    }



    // Load contacts from file.
    public void loadFromFile(String fileName) {


        // Delete the contacts.
        contacts.clear();


        // Make file.
        File file = new File(fileName);

        // If the file does not exist.
        if (!file.exists()) {

            System.out.println("Error: File does not exist!");
            System.exit(1);
        }


        // Deal with reading.
        try (Scanner input = new Scanner(file)) {


            while (input.hasNextLine()) {

                String line = input.nextLine();

                // Skip if line is empty.
                if (line.isEmpty()) {
                    continue;
                }

                // Split the string.
                String[] temp = line.split(",");

                // Skip the line if not formatted correctly.
                if (temp.length != 8) {
                    continue;
                }

                // Setting strings.
                String firstName = temp[0];
                String lastName = temp[1];
                String birthDate = temp[2];
                String phoneNumber = temp[3];
                String streetAddress = temp[4];
                String city = temp[5];
                String state = temp[6];
                String zipCode = temp[7];

                // Add the contact.
                Contact contact = new Contact(firstName, lastName, birthDate, phoneNumber, streetAddress, city, state, zipCode);

                // Add the contact.
                contacts.add(contact);
            }
        }
        catch (FileNotFoundException e) {

            System.out.println(e.getMessage());
            System.exit(1);
        }






    }


    // Save contacts to file.
    public void saveToFile(String fileName) {


        // Create a file.
        File file = new File(fileName);


        try (PrintWriter write = new PrintWriter(file)) {


            // Just call the print method.
            for (int i = 0; i < contacts.size(); i++) {

                write.println(contacts.get(i).toFileString());
            }

        }
        catch (FileNotFoundException e) {

            System.out.println(e.getMessage());
            System.exit(1);
        }




    }



    // Return a reference to the contacts.
    public ArrayList<Contact> getContacts() {

        return contacts;
    }












































}
