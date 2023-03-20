package userinterface;

import enums.CivilityEnum;
import enums.DataModeEnum;
import enums.CustomerTypeEnum;
import model.Customer;
import model.EnergyConsumption;
import model.IndividualCustomer;
import model.ProCustomer;

import java.text.MessageFormat;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class ConsoleIHM implements Ihm {

    private static final String GREETING_MESSAGE = """
            Bienvenue dans le système de calcul de facture pour les clients d'Ekwateur !\s
            Voulez vous saisir votre client manuellement ou utiliser un client prédéfini ?\s
            3 tentatives permises / Réponses accétptées : Manuel/Prédéfini""";
    private static final String DEFAULT_DATA_MODE_MESSAGE = "Mode client prédéfini activé";
    private static final String WRONG_DATA_MODE = "Vous n'avez pas choisi entre : Manuel/Prédéfini";

    private static final String CUSTOMER_TYPE_MESSAGE = "Veuillez indiquer le type de client \n" +
            "3 tentatives permises / Réponses accétptées : Pro/Particulier";
    private static final String DEFAULT_CUSTOMER_TYPE_MESSAGE = "Mode client particulier activé";
    private static final String WRONG_CUSTOMER_TYPE = "Vous n'avez pas choisi entre : Pro/Particulier";

    private static final String CUSTOMER_REFERENCE_MESSAGE = "Veuillez indiquer la référence du client \n" +
            "3 tentatives permises / Réponses accétptées : EKW + 8 caractères numériques";

    private static final String DEFAULT_CUSTOMER_REFERENCE = "EKW00000001";
    private static final String WRONG_CUSTOMER_REFERENCE = "Vous n'avez pas saisi une référence de type : EKW + 8 caractères numériques";
    public static final String DATA_MODE_PATTERN = "Manuel|Prédéfini";
    public static final String CUSTOMER_TYPE_PATTERN = "Pro|Particulier";
    public static final String CUSTOMER_REFERENCE_PATTERN = "EKW\\d{8}";
    public static final String DEFAULT_CUSTOMER_SIRET = "11112222333344";
    public static final String DEFAULT_CUSTOMER_SIRET_MESSAGE = "SIRET par défaut 11112222333344";
    public static final String CUSTOMER_CIVILITE_PATTERN = "MME|MLLE|MR";
    public static final String NAME_PATTERN = "[a-zA-Z]{3,}";
    public static final String CUSTOMER_SIRET_PATTERN = "\\d{14}";
    public static final String NUMBER_PATTERN = "\\d+";
    private static final String DEFAULT_CUSTOMER_REFERENCE_MESSAGE = "Référence par défaut EKW11111111";
    public static final String DEFAULT_ELECTRICITY_CONSUMPTION_MESSAGE = "Consommation électrique par défaut 1";
    public static final String DEFAULT_GAS_CONSUMPTION_MESSAGE = "Consommation en gaz par défaut 1";

    private final Scanner scanner = new Scanner(System.in);

    private static final String CUSTOMER_CIVILITE = "Veuillez indiquer la civilité du client \n" +
            "3 tentatives permises / Réponses accétptées : MME/MLLE/MR";

    private static final String DEFAULT_CUSTOMER_CIVILITE_MESSAGE = "Civilité par défaut Mr";
    private static final String WRONG_CUSTOMER_CIVILITE = "Vous n'avez pas indiqué une civilité de type : MME/MLLE/MR";

    private static final String CUSTOMER_LAST_NAME = "Veuillez indiquer le nom du client \n" +
            "3 tentatives permises / Réponses accétptées : Minimum trois caractères";
    private static final String DEFAULT_CUSTOMER_LAST_NAME = "LAM";
    private static final String DEFAULT_CUSTOMER_LAST_NAME_MESSAGE = "Nom par défaut LAM";
    private static final String WRONG_CUSTOMER_NAME = "Vous n'avez pas saisi au minimum trois caractères";
    private static final String CUSTOMER_FIRST_NAME = "Veuillez indiquer le prénom du client \n" +
            "3 tentatives permises / Réponses accétptées : Minimum trois caractères";
    private static final String DEFAULT_CUSTOMER_FIRST_NAME = "HAT";
    private static final String DEFAULT_CUSTOMER_FIRST_NAME_MESSAGE = "Prénom par défaut HAT";
    private static final String CUSTOMER_SIRET = "Veuillez indiquer le numéro siret \n" +
            "3 tentatives permises / Réponses accétptées : 14 chiffres";
    private static final String WRONG_CUSTOMER_SIRET = "Vous n'avez pas saisi 14 chiffres";
    private static final String CUSTOMER_COMPANY_NAME = "Veuillez indiquer la rasion sociale du client \n" +
            "3 tentatives permises / Réponses accétptées : Minimum trois caractères";
    private static final String DEFAULT_CUSTOMER_COMPANY_NAME = "LAMTECH";
    private static final String DEFAULT_CUSTOMER_COMPANY_NAME_MESSAGE = "Raison sociale par défaut LAMTECH";
    private static final String DEFAULT_CUSTOMER_REVENUE = "10000";
    private static final String DEFAULT_CUSTOMER_REVENUE_MESSAGE = "CA par défaut 10000";
    private static final String WRONG_CUSTOMER_REVENUE = "Vous n'avez pas saisi que des chiffres";
    private static final String CUSTOMER_REVENUE = "Veuillez indiquer le chiffre d'affaire du client\n" +
            "3 tentatives permises / Réponses accétptées : des chiffres";
    private static final String ELECTRICITY_CONSUMPTION_MESSAGE = "Veuillez indiquer la consommation en électricité \n" +
            "3 tentatives permises / Réponses accétptées : un nombre de kWh";
    private static final String GAZ_CONSUMPTION_MESSAGE = "Veuillez indiquer la consommation en gaz \n" +
            "3 tentatives permises / Réponses accétptées : un nombre de kWh";
    private static final String WRONG_CONSUMPTION = "Vous n'avez pas saisi un nombre";
    private UserInput userInput;

    @Override
    public UserInput getConfigAndDataFromUser() {
        userInput = new UserInput();
        askUser(GREETING_MESSAGE, DATA_MODE_PATTERN, conf -> conf.getCustomerSource() != null, s -> userInput.setCustomerSource(DataModeEnum.getEnumByValue(s)), () -> userInput.setCustomerSource(DataModeEnum.PRESET), DEFAULT_DATA_MODE_MESSAGE, WRONG_DATA_MODE);
        askUser(CUSTOMER_TYPE_MESSAGE, CUSTOMER_TYPE_PATTERN, conf -> conf.getUserType() != null, s -> userInput.setUserType(CustomerTypeEnum.getEnumByValue(s)), () -> userInput.setUserType(CustomerTypeEnum.INDIVIDUAL), DEFAULT_CUSTOMER_TYPE_MESSAGE, WRONG_CUSTOMER_TYPE);
        userInput.setCustomer(userInput.getUserType().equals(CustomerTypeEnum.INDIVIDUAL) ? new IndividualCustomer() : new ProCustomer());
        if (userInput.getCustomerSource().equals(DataModeEnum.MANUAL)) {
            getAllCustomerData();
        } else {
            // en mode clients prédéfinis on n'aura besoin que de la référence client
            askUser(CUSTOMER_REFERENCE_MESSAGE, CUSTOMER_REFERENCE_PATTERN, conf -> conf.getCustomer().getReference() != null, s -> userInput.getCustomer().setReference(s), () -> userInput.getCustomer().setReference(DEFAULT_CUSTOMER_REFERENCE), DEFAULT_CUSTOMER_REFERENCE_MESSAGE, WRONG_CUSTOMER_REFERENCE);
        }


        return userInput;
    }

    public void getAllCustomerData() {
        askUser(CUSTOMER_REFERENCE_MESSAGE, CUSTOMER_REFERENCE_PATTERN, conf -> conf.getCustomer().getReference() != null, s -> userInput.getCustomer().setReference(s), () -> userInput.getCustomer().setReference(DEFAULT_CUSTOMER_REFERENCE), DEFAULT_CUSTOMER_REFERENCE_MESSAGE, WRONG_CUSTOMER_REFERENCE);
        if (userInput.getCustomer() instanceof IndividualCustomer individualCustomer) {
            askUser(CUSTOMER_CIVILITE, CUSTOMER_CIVILITE_PATTERN, conf -> individualCustomer.getCivility() != null, civility -> individualCustomer.setCivility(CivilityEnum.getEnumByValue(civility)), () -> individualCustomer.setCivility(CivilityEnum.MONSIEUR), DEFAULT_CUSTOMER_CIVILITE_MESSAGE, WRONG_CUSTOMER_CIVILITE);
            askUser(CUSTOMER_LAST_NAME, NAME_PATTERN, conf -> individualCustomer.getLastName() != null, individualCustomer::setLastName, () -> individualCustomer.setLastName(DEFAULT_CUSTOMER_LAST_NAME), DEFAULT_CUSTOMER_LAST_NAME_MESSAGE, WRONG_CUSTOMER_NAME);
            askUser(CUSTOMER_FIRST_NAME, NAME_PATTERN, conf -> individualCustomer.getFirstName() != null, individualCustomer::setFirstName, () -> individualCustomer.setFirstName(DEFAULT_CUSTOMER_FIRST_NAME), DEFAULT_CUSTOMER_FIRST_NAME_MESSAGE, WRONG_CUSTOMER_NAME);
        } else if (userInput.getCustomer() instanceof ProCustomer proCustomer) {
            askUser(CUSTOMER_SIRET, CUSTOMER_SIRET_PATTERN, conf -> proCustomer.getSiret() != null, proCustomer::setSiret, () -> proCustomer.setSiret(DEFAULT_CUSTOMER_SIRET), DEFAULT_CUSTOMER_SIRET_MESSAGE, WRONG_CUSTOMER_SIRET);
            askUser(CUSTOMER_COMPANY_NAME, NAME_PATTERN, conf -> proCustomer.getCompanyName() != null, proCustomer::setCompanyName, () -> proCustomer.setCompanyName(DEFAULT_CUSTOMER_COMPANY_NAME), DEFAULT_CUSTOMER_COMPANY_NAME_MESSAGE, WRONG_CUSTOMER_NAME);
            askUser(CUSTOMER_REVENUE, NUMBER_PATTERN, conf -> proCustomer.getRevenue() != null, proCustomer::setRevenue, () -> proCustomer.setRevenue(DEFAULT_CUSTOMER_REVENUE), DEFAULT_CUSTOMER_REVENUE_MESSAGE, WRONG_CUSTOMER_REVENUE);
        }

    }

    public EnergyConsumption getConsumptionFromUser() {
        userInput.setEnergyConsumption(new EnergyConsumption());
        askUser(ELECTRICITY_CONSUMPTION_MESSAGE, NUMBER_PATTERN, conf -> conf.getEnergyConsumption().getElectricityConsumption() != 0, s -> userInput.getEnergyConsumption().setElectricityConsumption(Integer.parseInt(s)), () -> userInput.getEnergyConsumption().setElectricityConsumption(1), DEFAULT_ELECTRICITY_CONSUMPTION_MESSAGE, WRONG_CONSUMPTION);
        askUser(GAZ_CONSUMPTION_MESSAGE, NUMBER_PATTERN, conf -> conf.getEnergyConsumption().getGazConsumption() != 0, s -> userInput.getEnergyConsumption().setGazConsumption(Integer.parseInt(s)), () -> userInput.getEnergyConsumption().setGazConsumption(1), DEFAULT_GAS_CONSUMPTION_MESSAGE, WRONG_CONSUMPTION);
        scanner.close();
        return userInput.getEnergyConsumption();
    }

    @Override
    public void verifyCustomer(Customer customer) {
        if (customer == null) {
            System.out.println(MessageFormat.format("Impossible de récupérer le client, la référence {0} est introuvable", userInput.getCustomer().getReference()));
            getConfigAndDataFromUser();
        }
    }

    private void askUser(String askedMessage, String pattern, Predicate<UserInput> exit, Consumer<String> takeInput, Runnable takeDefault, String defaultDataMessage, String wrongInputMessage) {

        System.out.println(askedMessage);
        IntStream.rangeClosed(1, 3).forEach(

                value -> {
                    if (exit.test(userInput)) return;
                    try {
                        String userInput = scanner.next(pattern);
                        takeInput.accept(userInput);
                    } catch (Exception e) {
                        System.out.println(MessageFormat.format("Tentative {0} échouée", value));
                        System.out.println(wrongInputMessage);
                        scanner.nextLine();
                    } finally {
                        if (value == 3 && !exit.test(userInput)) {
                            takeDefault.run();
                            System.out.println(defaultDataMessage);
                        }
                    }

                }

        );
    }
}
