import java.util.Date;
import java.util.InputMismatchException;

public class Contact {




    // Data fields
    private String firstName;
    private String lastName;
    private String birthDate;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;



    // Initializing Constructor
    public Contact(String firstName, String lastName, String birthDate, String phoneNumber, String streetAddress, String city, String state, String zipCode) throws NullPointerException, IllegalArgumentException, InputMismatchException{



        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setPhoneNumber(phoneNumber);
        setStreetAddress(streetAddress);
        setCity(city);
        setState(state);
        setZipCode(zipCode);

    }




    // Setters
    public void setFirstName(String firstName) throws NullPointerException, IllegalArgumentException {

        if (firstName == null) {
            throw new NullPointerException("Error: First name is NULL!");
        }
        else if (firstName.isEmpty()) {
            throw new IllegalArgumentException("Error: First name is empty!");
        }

        this.firstName = firstName;
    }

    public void setLastName(String lastName) throws NullPointerException, IllegalArgumentException {

        if (lastName == null) {
            throw new NullPointerException("Error: Last name is NULL!");
        }
        else if (lastName.isEmpty()) {
            throw new IllegalArgumentException("Error: Last name is empty!");
        }

        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) throws NullPointerException, IllegalArgumentException, InputMismatchException {

        if (birthDate == null) {
            throw new NullPointerException("Error: Birth date is NULL!");
        }
        else if (birthDate.isEmpty()) {
            throw new IllegalArgumentException("Error: Birth date is empty!");
        }

        // Check if the format matches.
        if (birthDate.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}") ) {


            // Split the birth date.
            String[] temp = birthDate.split("-");

            int year = Integer.parseInt(temp[0]);
            int month = Integer.parseInt(temp[1]);
            int day = Integer.parseInt(temp[2]);

            // Year validation.
            Date today = new Date();
            if (year > today.getYear() + 1900) {

                throw new InputMismatchException("Error: Birth year is invalid!");
            }


            // Month validation.
            if (month > 12 || month < 1) {

                throw new InputMismatchException("Error: Birth month is invalid!");
            }

            // Day validation
            if (month == 1 && (day < 1 || day > 31) ) {throw new InputMismatchException("Error: Birth day is invalid!");}
            else if (month == 2 && (day < 1 || day > 29) ) {throw new InputMismatchException("Error: Birth day is invalid!");}
            else if (month == 3 && (day < 1 || day > 31) ) {throw new InputMismatchException("Error: Birth day is invalid!");}
            else if (month == 4 && (day < 1 || day > 30) ) {throw new InputMismatchException("Error: Birth day is invalid!");}
            else if (month == 5 && (day < 1 || day > 31) ) {throw new InputMismatchException("Error: Birth day is invalid!");}
            else if (month == 6 && (day < 1 || day > 30) ) {throw new InputMismatchException("Error: Birth day is invalid!");}
            else if (month == 7 && (day < 1 || day > 31) ) {throw new InputMismatchException("Error: Birth day is invalid!");}
            else if (month == 8 && (day < 1 || day > 31) ) {throw new InputMismatchException("Error: Birth day is invalid!");}
            else if (month == 9 && (day < 1 || day > 30) ) {throw new InputMismatchException("Error: Birth day is invalid!");}
            else if (month == 10 && (day < 1 || day > 31) ) {throw new InputMismatchException("Error: Birth day is invalid!");}
            else if (month == 11 && (day < 1 || day > 30) ) {throw new InputMismatchException("Error: Birth day is invalid!");}
            else if (month == 12 && (day < 1 || day > 31) ) {throw new InputMismatchException("Error: Birth day is invalid!");}


            // Didn't validate for leap year, but that's fine.



            this.birthDate = birthDate;




        }
        else {
            throw new InputMismatchException("Error: Birth date format is invalid!");
        }
    }

    public void setPhoneNumber(String phoneNumber) throws NullPointerException, IllegalArgumentException, InputMismatchException {

        if (phoneNumber == null) {
            throw new NullPointerException("Error: Phone number is NULL!");
        }
        else if (phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Error: Phone number is empty!");
        }


        // Validate phone number with two formats.
        if (phoneNumber.matches("[0-9]{3}-[0-9]{3}-[0-9]{4}|\\([0-9]{3}\\)[0-9]{3}-[0-9]{4}")) {

            this.phoneNumber = phoneNumber;
        }
        else {
            throw new InputMismatchException("Error: Phone number format is invalid!");
        }
    }

    public void setStreetAddress(String streetAddress) throws NullPointerException, IllegalArgumentException {

        if (streetAddress == null) {
            throw new NullPointerException("Error: Street address is NULL!");
        }
        else if (streetAddress.isEmpty()) {
            throw new IllegalArgumentException("Error: Street Address is empty!");
        }

        this.streetAddress = streetAddress;
    }

    public void setCity(String city) throws NullPointerException, IllegalArgumentException {

        if (city == null) {
            throw new NullPointerException("Error: City is NULL!");
        }
        else if (city.isEmpty()) {
            throw new IllegalArgumentException("Error: City is empty!");
        }

        this.city = city;
    }

    public void setState(String state) throws NullPointerException, IllegalArgumentException {

        if (state == null) {
            throw new NullPointerException("Error: State is NULL!");
        }
        else if (state.isEmpty()) {
            throw new IllegalArgumentException("Error: State is empty!");
        }

        this.state = state;
    }

    public void setZipCode(String zipCode) throws NullPointerException, IllegalArgumentException, InputMismatchException {

        if (zipCode == null) {
            throw new NullPointerException("Error: Zip code is NULL!");
        }
        else if (zipCode.isEmpty()) {
            throw new IllegalArgumentException("Error: Zip code is empty!");
        }


        // 5 digits or 5 digits - 4 digits
        if (zipCode.matches("[0-9]{5}|[0-9]{5}-[0-9]{4}")) {

            this.zipCode = zipCode;
        }
        else {
            throw new InputMismatchException("Error: Zip code format is invalid!");
        }
    }




    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }




    // The string that is output to the file.
    public String toFileString() {

        return (firstName + "," + lastName + "," + birthDate + "," + phoneNumber + "," + streetAddress + "," + city + "," + state + "," + zipCode);
    }



    // Overriding toString() for text area output.
    @Override
    public String toString() {

        return (firstName + " " + lastName + ", born: " + birthDate + ", phone: " + phoneNumber + ", Address: " + streetAddress + ", " + city + ", " + state + " " + zipCode);
    }
}
















