package sample;

/*
sample.Runway class for runway redeclaration tool.
As soon as the runway that requires action is entered to the application, a new runway object
will be created. The user will enter details of the runway and these will form the parameters
seen below.
 */
public class Runway {

    //sample.Runway attributes
    private String runwayNumber;
    private int tora;
    private int toda;
    private int asda;
    private int lda;
    private int displacedThreshold;
    private int stripEnd;
    private int clearway;
    private int stopway;
    private int resa;

    //Constructor for the runway class
    public Runway(String runwayNumber, int tora, int toda, int asda, int lda, int displacedThreshold, int stripEnd, int clearway, int stopway, int resa) {
        this.runwayNumber = runwayNumber;
        this.tora = tora;
        this.toda = toda;
        this.asda = asda;
        this.lda = lda;
        this.displacedThreshold = displacedThreshold;
        this.stripEnd = stripEnd;
        this.clearway = clearway;
        this.stopway = stopway;
        this.resa = resa;
    }

    //Getter method for sample.Runway Number
    public String getRunwayNumber() {
        return runwayNumber;
    }

    //Setter method for sample.Runway Number
    public void setRunwayNumber(String runwayNumber) {
        this.runwayNumber = runwayNumber;
    }

    //Getter method for sample.Runway TORA
    public int getTora() {
        return tora;
    }

    //Setter method for sample.Runway TORA
    public void setTora(int tora) {
        this.tora = tora;
    }

    //Getter method for sample.Runway TODA
    public int getToda() {
        return toda;
    }

    //Setter method for sample.Runway TODA
    public void setToda(int toda) {
        this.toda = toda;
    }

    //Getter method for sample.Runway ASDA
    public int getAsda() {
        return asda;
    }

    //Setter method for sample.Runway ASDA
    public void setAsda(int asda) {
        this.asda = asda;
    }

    //Getter method for sample.Runway LDA
    public int getLda() {
        return lda;
    }

    //Setter method for sample.Runway LDA
    public void setLda(int lda) {
        this.lda = lda;
    }

    //Getter method for sample.Runway Displaced Threshold
    public int getDisplacedThreshold() {
        return displacedThreshold;
    }

    //Setter method for sample.Runway Displaced Threshold
    public void setDisplacedThreshold(int displacedThreshold) {
        this.displacedThreshold = displacedThreshold;
    }

    //Getter method for sample.Runway Stopway
    public int getStopway() {
        return stopway;
    }

    //Setter method for sample.Runway Stopway
    public void setStopway(int stopway) {
        this.stopway = stopway;
    }

    //Getter method for sample.Runway Clearway
    public int getClearway() {
        return clearway;
    }

    //Setter method for sample.Runway Clearway
    public void setClearway(int clearway) {
        this.clearway = clearway;
    }

    //Getter method for sample.Runway Strip End
    public int getStripEnd() {
        return stripEnd;
    }

    //Setter method for sample.Runway Strip End
    public void setStripEnd(int stripEnd) {
        this.stripEnd = stripEnd;
    }

    //Getter method for sample.Runway RESA
    public int getResa() {
        return resa;
    }

    //Setter method for sample.Runway RESA
    public void setResa(int resa) {
        this.resa = resa;
    }
}
