package enums;

import java.text.MessageFormat;
import java.util.Arrays;

public enum DataModeEnum {
    MANUAL("Manuel"), PRESET("Prédéfini");
    private final String dataMode;

    DataModeEnum(String dataMode) {
        this.dataMode = dataMode;
    }

    public static DataModeEnum getEnumByValue(String dataMode) {
        return Arrays.stream(DataModeEnum.values()).filter(dataModeEnum -> dataModeEnum.dataMode.equals(dataMode)).findFirst().orElseThrow(() ->
                new RuntimeException(MessageFormat.format("La source : {0} n'est pas reconnue", dataMode)));
    }
}
