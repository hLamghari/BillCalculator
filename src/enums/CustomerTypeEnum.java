package enums;

import java.text.MessageFormat;
import java.util.Arrays;

public enum CustomerTypeEnum {
    PRO("Pro"), INDIVIDUAL("Particulier");
    private final String type;

    CustomerTypeEnum(String type) {
        this.type = type;
    }

    public static CustomerTypeEnum getEnumByValue(String value) {
        return Arrays.stream(CustomerTypeEnum.values()).filter(sourceEnum -> sourceEnum.type.equals(value)).findFirst().orElseThrow(() ->
                new RuntimeException(MessageFormat.format("Le type de client : {0} n'est pas reconnu", value)));
    }
}
