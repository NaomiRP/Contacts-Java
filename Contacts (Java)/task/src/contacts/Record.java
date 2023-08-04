package contacts;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Record {
    private static final List<String> types = List.of("person", "organization");

    private String number;
    private final LocalDateTime created;
    private LocalDateTime updated;

    protected Record() {
        created = LocalDateTime.now();
        updated = created;
    }

    protected void recordUpdated() {
        updated = LocalDateTime.now();
    }

    public abstract List<String> getFields();

    public abstract boolean setField(String field, String value);

    public static List<String> getTypes() {
        return types;
    }

    public abstract String getSummary();

    protected boolean setNumber(String number) {
        if (validateNumber(number)) {
            this.number = number;
            return true;
        }
        return false;
    }

    private boolean validateNumber(String number) {
        if (number == null)
            return true;
        if (number.startsWith("+"))
            number = number.substring(1);
        String[] groups = number.split("[ -]");
        if (groups.length < 1)
            return false;
        String firstGroup = groups[0];
        String validChars = "[a-zA-Z0-9]";
        Pattern parens = Pattern.compile("\\(.*\\)");
        boolean hasParens = false;
        if (parens.matcher(firstGroup).matches()) {
            hasParens = true;
            firstGroup = firstGroup.substring(1, firstGroup.length() - 1);
        }
        Matcher firstGroupMatcher = Pattern.compile(validChars + "+").matcher(firstGroup);
        if (!firstGroupMatcher.matches())
            return false;
        Pattern groupPattern = Pattern.compile(validChars + "{2,}");
        if (groups.length == 1)
            return true;
        String secondGroup = groups[1];
        if (parens.matcher(secondGroup).matches()) {
            if (hasParens)
                return false;
            secondGroup = secondGroup.substring(1, secondGroup.length() - 1);
        }
        if (!groupPattern.matcher(secondGroup).matches())
            return false;
        for (int i = 2; i < groups.length; i++) {
            if (!groupPattern.matcher(groups[i]).matches())
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Number: " + (number != null ? number : "[no data]") + '\n' +
                "Time created: " + created + '\n' +
                "Time last edit: " + updated;
    }
}
