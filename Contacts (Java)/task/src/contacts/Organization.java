package contacts;

import java.util.List;

public class Organization extends Record {
    private static final List<String> fields = List.of("organization name", "address", "number");

    private String organizationName;
    private String address;

    public Organization() {
        super();
    }

    public String getSummary() {
        return organizationName;
    }

    public List<String> getFields() {
        return fields;
    }

    public boolean setField(String field, String value) {
        field = field.toLowerCase();
        if (!fields.contains(field))
            return false;
        boolean validValue = true;
        switch (field) {
            case "organization name" -> organizationName = value;
            case "address" -> address = value;
            case "number" -> validValue = setNumber(value);
        }
        if (validValue)
            recordUpdated();
        return validValue;
    }

    @Override
    public String toString() {
        return
                "Organization name: " + organizationName + '\n' +
                "Address: " + address + '\n' +
                super.toString();
    }
}
