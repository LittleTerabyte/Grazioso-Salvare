public class Monkey extends RescueAnimal{
    // Attributes specific to Monkey
    private String tailLength;
    private String height;
    private String bodyLength;
    private String species;

    // Detailed Constructor
    public Monkey(String name, String gender, String age,
    String weight, String acquisitionDate, String acquisitionCountry,
	String trainingStatus, boolean reserved, String inServiceCountry, String species, String tailLength,
    String height, String bodyLength) {
        /*  Explaination based on the last graded assignment for project 1. Where you said these are not needed.
                I set general attributes inherited from RescueAnimal class | note: the animalType is set to "Monkey"
                These attributes are needed to add the Monkey object to the ArrayList of RescueAnimal objects
                The name, gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus, reserved, and inServiceCountry
                are all required baded on the RescueAnimal class and the company's specification document. 
                In addition the four monkey specific attributes are also required based on the company's specification document.
                If I don't set these attributes here, I can't add this data to the ArrayList for the monkey I define in the program.

                From the specification Document:

                "There are important data elements for monkeys in ** addition ** to what they use for dogs 
                (these attributes are also defined in the Dog class you provided). These include tail length, height, body length, and species."    
        */ 

        setName(name);
        setGender(gender);
        setAge(age);
        setWeight(weight);
        setAcquisitionDate(acquisitionDate);
        setAcquisitionLocation(acquisitionCountry);
        setTrainingStatus(trainingStatus);
        setReserved(reserved);
        setInServiceCountry(inServiceCountry);
        // Set Monkey specific attributes
        setSpecies(species);
        setTailLength(tailLength);
        setHeight(height);
        setBodyLength(bodyLength);
    }

    // Accessor Methods

    public String getTailLength() {
        return tailLength;
    }
    public void setTailLength(String tailLength2) {
        this.tailLength = tailLength2;
    }
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getBodyLength() {
        return bodyLength;
    }
    public void setBodyLength(String bodyLength) {
        this.bodyLength = bodyLength;
    }
    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }

}
