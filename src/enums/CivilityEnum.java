package enums;

import java.text.MessageFormat;
import java.util.Arrays;

public enum CivilityEnum {
    MADAME("MME"), MADEMOISELLE("MLLE"), MONSIEUR("MR");

    private final String abbreviation;

    CivilityEnum(String abbreviation) {
        this.abbreviation = abbreviation;
    }


    public static CivilityEnum getEnumByValue(String abbrev) {
        return Arrays.stream(CivilityEnum.values()).filter(civilityEnum -> civilityEnum.abbreviation.equals(abbrev)).findFirst()
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("La civilité : {0} n'est pas reconnue", abbrev)));
    }
}
