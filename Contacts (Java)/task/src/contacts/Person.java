package contacts;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Person extends Record {
    private static final List<String> fields = List.of("name", "surname", "birth date", "gender", "number");

    private String name;
    private String surname;
    private LocalDate birthDate;
    private Character gender;

    private final List<Character> validGenders = List.of('M', 'F');

    public Person() {
        super();
    }

    public List<String> getFields() {
        return fields;
    }

    public String getSummary() {
        return name + " " + surname;
    }

    public String getSearchData() {
        return name + surname + birthDate + gender + getNumber();
    }

    public boolean setField(String field, String value) {
        field = field.toLowerCase();
        if (!fields.contains(field))
            return false;
        boolean validValue = true;
        switch (field) {
            case "name" -> name = value;
            case "surname" -> surname = value;
            case "birthdate" -> validValue = setBirthDate(value);
            case "gender" -> validValue = setGender(value);
            case "number" -> validValue = setNumber(value);
        }
        if (validValue)
            recordUpdated();
        return validValue;
    }

    private boolean setBirthDate(String value) {
        try {
            birthDate = LocalDate.parse(value);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean setGender(String value) {
        if (value.length() != 1)
            return false;
        Character gender = value.toUpperCase().charAt(0);
        if (validGenders.contains(gender)) {
            this.gender = gender;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return
                "Name: " + name + '\n' +
                "Surname: " + surname + '\n' +
                "Birth date: " + (birthDate != null ? birthDate : "[no data]") + '\n' +
                "Gender: " + (gender != null ? gender : "[no data]") + '\n' +
                super.toString();
    }
}
