import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;   // Added this line to import the Matcher class for input validation on Monkey acquisition date
import java.util.regex.Pattern;   // Added this line to import the Pattern class for input validation on Monkey acquisition date

public class Driver {
    private static ArrayList<Dog> dogList = new ArrayList<Dog>();
    private static ArrayList<Monkey> monkeyList = new ArrayList<Monkey>();

    public static void main(String[] args) {
        initializeDogList();
        initializeMonkeyList();

        /*  Displays the menu and reads the user's selection
            This method connects to the other methods in the class 
            and includeds input validation and appropriate feedback to the user. 
	        Menu options 4, 5, and 6 connect to the printAnimals() method.
        */ 
        Scanner scanner = new Scanner(System.in);
        String menuSelection = "";

        // Display the menu
        displayMenu();


        do {
            // Display the menu and get the user's selection
            menuSelection = scanner.nextLine().toLowerCase();
            
            // Validate the user selection
            if (!isValidSelection(menuSelection)) {
                // Display error message
                System.out.println("Invalid selection. Please try again.");
            } else { 
                // Process the user selection
                switch (menuSelection) {
                    case "1":
                        intakeNewDog(scanner);
                        break;
                    case "2":
                        intakeNewMonkey(scanner);
                        break;
                    case "3":
                        reserveAnimal(scanner);
                        break;
                    case "4":
                        printAnimals("dog");
                        break;
                    case "5":
                        printAnimals("monkey");
                        break;
                    case "6":
                        printAnimals("available");
                        break;
                    case "q":
                        System.out.println("Exiting the program");
                        break;
                }
            }
        } while (!menuSelection.equals("q"));
    }

    // This method prints the menu optionsQ
    public static void displayMenu() {
        System.out.println("\n\n");
        System.out.println("\t\t\t\tRescue Animal System Menu");
        System.out.println("[1] Intake a new dog");
        System.out.println("[2] Intake a new monkey");
        System.out.println("[3] Reserve an animal");
        System.out.println("[4] Print a list of all dogs");
        System.out.println("[5] Print a list of all monkeys");
        System.out.println("[6] Print a list of all animals that are not reserved");
        System.out.println("[q] Quit application");
        System.out.println();
        System.out.println("Enter a menu selection");
    }

    // Check if the user's selection is valid
    public static boolean isValidSelection(String selection) {
        String[] validSelections = {"1", "2", "3", "4", "5", "6", "q"};
        for (String validSelection : validSelections) {
            if (selection.equals(validSelection)) {
                return true;
            }
        }
        return false;
    }

    // Adds dogs to a list for testing
    // Changed weight argument vaule to type double and age argument to type int
    public static void initializeDogList() {
        Dog dog1 = new Dog("Spot", "German Shepherd", "male", "1", "25.6", "05-12-2019", "United States", "intake", false, "United States");
        Dog dog2 = new Dog("Rex", "Great Dane", "male", "3", "35.2", "02-03-2020", "United States", "Phase I", false, "United States");
        Dog dog3 = new Dog("Bella", "Chihuahua", "female", "4", "25.6", "12-12-2019", "Canada", "in service", false, "Canada");

        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
    }

    // Adds monkeys to a list for testing
    public static void initializeMonkeyList() {
        Monkey monkey1 = new Monkey("George", "male", "5", "25.6", "12-12-2019", "Canada", "in service", false, "Canada", "Capuchin", "12", "24", "36");

        monkeyList.add(monkey1);
    }

    // intakeNewDog instructions for menu item #1
        // This method prompts the user for the dog's attributes and adds the dog to the godList
        // The method includes input validation
    public static void intakeNewDog(Scanner scanner) {
        Dog newDog = new Dog(null, null, null, null, null, null, null, null, false, null); // Instantiate a new Dog object

        // Prompt for name 
        System.out.println("What is the dog's name?");
        String dogName = scanner.nextLine();

        for(Dog dog: dogList) {
            if(dog.getName().equalsIgnoreCase(dogName)) {
                System.out.println("\n\nThis dog is already in our system\n\n");
                return; //returns to menu
            }
        }

        // Add the code to instantiate a new dog and add it to the appropriate list
        // Instantiate a new Dog object

        // Prompt for breed
        System.out.println("What is the dog's breed?");
        String breed = scanner.nextLine();
        newDog.setBreed(breed);

        // Prompt for gender
        System.out.println("What is the dog's gender?");
        String gender = scanner.nextLine();
        newDog.setGender(gender);

        // Prompt for age
        System.out.println("What is the dog's age?");
        String age = scanner.nextLine();
        newDog.setAge(age);

        // Prompt for weight
        System.out.println("What is the dog's weight?");
        String weight = scanner.nextLine();
        newDog.setWeight(weight);

        // Prompt for acquisition date
        System.out.println("What is the dog's acquisition date? (Please use the format dd-mm-yyyy)");
        String acquisitionDate = scanner.nextLine();
        newDog.setAcquisitionDate(acquisitionDate);

        // Prompt for acquisition country
        System.out.println("What is the dog's acquisition country?");
        String acquisitionCountry = scanner.nextLine();
        newDog.setAcquisitionLocation(acquisitionCountry);

        // Prompt for training status. Default value is "intake" and checks for valid phase
        String trainingStatus = "intake";
        do {
            System.out.println("What is the dog's training status?");
            trainingStatus = scanner.nextLine();
            if (!(trainingStatus.equalsIgnoreCase("intake") || trainingStatus.equalsIgnoreCase("phase i") || 
                trainingStatus.equalsIgnoreCase("phase ii") || trainingStatus.equalsIgnoreCase("phase iii") || 
                trainingStatus.equalsIgnoreCase("phase iv") || trainingStatus.equalsIgnoreCase("phase V") ||
                trainingStatus.equalsIgnoreCase("in service") || trainingStatus.equalsIgnoreCase("farm"))) {
                System.out.println("Invalid training status");
            }
        } while (!(trainingStatus.equalsIgnoreCase("intake") || trainingStatus.equalsIgnoreCase("phase i") ||
                    trainingStatus.equalsIgnoreCase("phase ii") || trainingStatus.equalsIgnoreCase("phase iii") || 
                    trainingStatus.equalsIgnoreCase("phase iv") || trainingStatus.equalsIgnoreCase("phase V") ||
                    trainingStatus.equalsIgnoreCase("in service") || trainingStatus.equalsIgnoreCase("farm"))); 
        newDog.setTrainingStatus(trainingStatus);

        // Prompt for reservation status
        System.out.println("Is the dog reserved? (yes/no)");
        String reservedInput = scanner.nextLine();

        // Parse the input to determine the reservation status
        boolean reserved;
        if (reservedInput.equalsIgnoreCase("yes")) {
            reserved = true;
        } else if (reservedInput.equalsIgnoreCase("no")) {
            reserved = false;
        } else {
            System.out.println("Invalid reserved status, please enter 'yes' or 'no'");
            return; // Return to the menu
        }

        // Set the reserved status of the dog
        newDog.setReserved(reserved);

        // Prompt for in-service country
        System.out.println("What is the dog's in-service country?");
        String inServiceCountry = scanner.nextLine();
        newDog.setInServiceCountry(inServiceCountry);

        // Add the new dog to the dogList
        dogList.add(newDog);
        System.out.println("Dog added successfully");
        displayMenu();
    }

    // intakeNewMonkey instructions for menu item #2
        // This method prompts the user for the monkey's attributes and adds the monkey to the monkeyList
        // The method includes input validation
    public static void intakeNewMonkey(Scanner scanner) {
        Monkey newMonkey = new Monkey(null, null, null, null, null, null, null, false, null, null, null, null, null); // Instantiate a new Monkey object

        // Prompt for the monkey's name
        System.out.println("What is the monkey's name?");
        String name = scanner.nextLine();
    
        // Check if the monkey already exists in the list
        for (Monkey monkey : monkeyList) {
            if (monkey.getName().equalsIgnoreCase(name)) {
                System.out.println("\n\nThis monkey is already in our system\n\n");
                return; // Return to the menu
            }
        }
    
        // If the monkey is not already in the list, proceed with adding the monkey

        // Prompt for gender
        String gender;
        do {
            System.out.println("What is the monkey's gender?  (male/female)");
            gender = scanner.nextLine().toLowerCase();
            if (!gender.equals("male") && !gender.equals("female")) {
                System.out.println("Invalid input. Please enter male or female.");
            }
        } while (!gender.equals("male") && !gender.equals("female"));
        newMonkey.setGender(gender);

        // Prompt for age
        System.out.println("What is the monkey's age?");
        String age = scanner.nextLine();
        newMonkey.setAge(age);

        // Prompt for weight
        System.out.println("What is the monkey's weight?");
        String weight = scanner.nextLine();
        newMonkey.setWeight(weight);

        // Prompt for acquisition date
        String acquisitionDate;
        String datePattern = "\\d{2}-\\d{2}-\\d{4}";
        Pattern pattern = Pattern.compile(datePattern);
        Matcher matcher;
        do {
            System.out.println("What is the monkey's acquisition date? (Please use the format dd-mm-yyyy)");
            acquisitionDate = scanner.nextLine();

            matcher = pattern.matcher(acquisitionDate);

            if (!pattern.matcher(acquisitionDate).matches()) {
                System.out.println("Invalid date format. Please use the format dd-mm-yyyy");
            }
        } while (!matcher.matches());
        newMonkey.setAcquisitionDate(acquisitionDate);
        //System.out.println("What is the monkey's acquisition date? (Please use the format dd-mm-yyyy)");
        //String acquisitionDate = scanner.nextLine();
        //newMonkey.setAcquisitionDate(acquisitionDate);

        // Prompt for acquisition country
        System.out.println("What is the monkey's acquisition country?");
        String acquisitionCountry = scanner.nextLine();
        newMonkey.setAcquisitionLocation(acquisitionCountry);

        // Prompt for training status. Default value is "intake" and checks for valid phase
        String trainingStatus = "intake";
        do {
            System.out.println("What is the dog's training status?");
            trainingStatus = scanner.nextLine();
            if (!(trainingStatus.equalsIgnoreCase("intake") || trainingStatus.equalsIgnoreCase("phase i") || 
                trainingStatus.equalsIgnoreCase("phase ii") || trainingStatus.equalsIgnoreCase("phase iii") || 
                trainingStatus.equalsIgnoreCase("phase iv") || trainingStatus.equalsIgnoreCase("phase V") ||
                trainingStatus.equalsIgnoreCase("in service") || trainingStatus.equalsIgnoreCase("farm"))) {
                System.out.println("Invalid training status");
            }
        } while (!(trainingStatus.equalsIgnoreCase("intake") || trainingStatus.equalsIgnoreCase("phase i") ||
                    trainingStatus.equalsIgnoreCase("phase ii") || trainingStatus.equalsIgnoreCase("phase iii") || 
                    trainingStatus.equalsIgnoreCase("phase iv") || trainingStatus.equalsIgnoreCase("phase V") ||
                    trainingStatus.equalsIgnoreCase("in service") || trainingStatus.equalsIgnoreCase("farm"))); 
        newMonkey.setTrainingStatus(trainingStatus);

        // Prompt for reservation status
        System.out.println("Is the monkey reserved? (yes/no)");
        String reservedInput = scanner.nextLine();

            // Parse the input to determine the reservation status
        boolean reserved;
        if (reservedInput.equalsIgnoreCase("yes")) {
            reserved = true;
        } else if (reservedInput.equalsIgnoreCase("no")) {
            reserved = false;
        } else {
            System.out.println("Invalid reserved status, please enter 'yes' or 'no'");
            return; // Return to the menu
        }

        // Set the reserved status of the monkey
        newMonkey.setReserved(reserved);

        // Prompt for in-service country
        System.out.println("What is the monkey's in-service country?");
        String inServiceCountry = scanner.nextLine();
        newMonkey.setInServiceCountry(inServiceCountry);

        // Prompt for species until a valid species is entered
        String species;
        do {
            System.out.println("What is the monkey's species?");
            species = scanner.nextLine();
            if (!(species.equalsIgnoreCase("Capuchin") || species.equalsIgnoreCase("Guenon") || 
                species.equalsIgnoreCase("Macaque") || species.equalsIgnoreCase("Marmoset") || 
                species.equalsIgnoreCase("Squirrel Monkey") || species.equalsIgnoreCase("Tamarin"))) {
                System.out.println("Invalid species type");
            }
        } while (!(species.equalsIgnoreCase("Capuchin") || species.equalsIgnoreCase("Guenon") || 
        species.equalsIgnoreCase("Macaque") || species.equalsIgnoreCase("Marmoset") || 
        species.equalsIgnoreCase("Squirrel Monkey") || species.equalsIgnoreCase("Tamarin")));
    
        // Set species using setter from Monkey class
        newMonkey.setSpecies(species);
    
        // Prompt for tail length
        System.out.println("What is the monkey's tail length?");
        String tailLength = scanner.nextLine();
        newMonkey.setTailLength(tailLength);
    
        // Prompt for height
        System.out.println("What is the monkey's height?");
        String height = scanner.nextLine();
        newMonkey.setHeight(height);
    
        // Prompt for body length
        System.out.println("What is the monkey's body length?");
        String bodyLength = scanner.nextLine();
        newMonkey.setBodyLength(bodyLength);
    
        // Adds the input to the monkeyList
        monkeyList.add(newMonkey);
        System.out.println("Monkey added successfully");
        displayMenu();
    }

    // reserveAnimal instructions for menu item #3
        // You will need to find the animal by animal type and in service country
    public static void reserveAnimal(Scanner scanner) {
        // Prompt for animal type
        System.out.println("Enter the desired animal type. (dog/monkey):");
        String animalType = scanner.nextLine().toLowerCase();

        // Prompt for in-service country
        System.out.println("Enter the desired in-service country:");
        String inServiceCountry = scanner.nextLine();

        if (animalType.equals("dog")) {
            for (Dog dog : dogList) {
                if (dog.getInServiceLocation().equalsIgnoreCase(inServiceCountry) && !dog.getReserved()) {
                    dog.setReserved(true);
                    System.out.println("Dog reserved successfully");
                    break;
                }
            }
        } else if (animalType.equals("monkey")) {
            for (Monkey monkey : monkeyList) {
                if (monkey.getInServiceLocation().equalsIgnoreCase(inServiceCountry) && !monkey.getReserved()) {
                    monkey.setReserved(true);
                    System.out.println("Monkey reserved successfully");
                    break;
                }
            }
        } else {
            System.out.println("Invalid animal type");
        }
        displayMenu();
    }
        
    // printAnimals instructions for menu items 4, 5, and 6
        // This method prints the list of animals based on the animal type
        // The method includes input validation
    public static void printAnimals(String listType) {
        // Menue item 4
        if (listType.equals("dog")) {
            for (Dog dog : dogList) {
                System.out.println("Name: " + dog.getName() + " | Status: " + dog.getTrainingStatus() + " | Acquisition Country: " + dog.getAcquisitionLocation() + " | Reserved: " + dog.getReserved());
            }
        // Menu item 5
        } else if (listType.equals("monkey")) {
            for (Monkey monkey : monkeyList) {
                System.out.println("Name: " + monkey.getName() + " | Status: " + monkey.getTrainingStatus() + " | Acquisition Country: " + monkey.getAcquisitionLocation() + " | Reserved: " + monkey.getReserved());
            }
        // Menu item 6
        } else if (listType.equals("available")) {
            System.out.println("Available animals:");
            for (Dog dog : dogList) {
                if (dog.getTrainingStatus().equalsIgnoreCase("in service") && !dog.getReserved()) {
                    System.out.println("Animal type: Dog " + " | Name: " + dog.getName() + " | Status: " + dog.getTrainingStatus() + " | Acquisition Country: " + dog.getAcquisitionLocation() + " | Reserved: " + dog.getReserved());
                }
            }
            for (Monkey monkey : monkeyList) {
                if (monkey.getTrainingStatus().equalsIgnoreCase("in service") && !monkey.getReserved()) {
                    System.out.println("Animal type: Monkey " + " | Name: " + monkey.getName() + " | Status: " + monkey.getTrainingStatus() + " | Acquisition Country: " + monkey.getAcquisitionLocation() + " | Reserved: " + monkey.getReserved());
                }
            }
        }
        displayMenu();
    }    
}   