package sample;

/* This class represents the "landing over obstacle calculation" in which some runway and obstacle
measurements are used (as well as external measurements added to the calculation).
 This recalculation involves recalculating and updating the LDA of the runway in which there is an obstacle present
 */
public class LandingOverObstacle extends Calculation {

    //parameters to be updated via the recalculation
    private Runway runway;
    private Obstacle obstacle;
    private int newLDA;
    private int als;
    private int blastProtectionAllowance;
    private int finalValueToBeUsed;

    //inherited method from Calculation class
    @Override
    public void recalculate() {}

    public LandingOverObstacle(int als, int blastProtectionAllowance) {
        this.als = als;
        this.blastProtectionAllowance = blastProtectionAllowance;
    }

    /*
    Method recalculates new LDA given the runway take-off run available (TORA), the obstacle distance from the
    threshold and the runway stripEnd.
    The largest of (ALS* the object height), RESA or blast protection value is used as the second
    value subtracted from the tora.
     */
    public int recalculate(int tora, int distanceFromThreshold, int objectHeight, int als, int resa, int stripEnd, int blastProtectionValue ){
        int valueToBeUsed = 0;
        if(als * objectHeight > resa){
            valueToBeUsed = als * objectHeight;
        }
        else{
            valueToBeUsed = resa;
        }
        if(valueToBeUsed > blastProtectionValue){
            finalValueToBeUsed = valueToBeUsed;
        }
        else{
            finalValueToBeUsed = blastProtectionValue;
        }
        return tora - distanceFromThreshold - (finalValueToBeUsed) - stripEnd;
    }

    //Getter method returns new recalculated landing distance available (LDA)
    public int getNewLDA(){
        return newLDA;
    }

    //Getter for ALS
    public int getAls() { return als; }

    //Setter for ALS
    public void setAls(int als) { this.als = als; }

    //Getter for BPA
    public int getBlastProtectionAllowance() { return blastProtectionAllowance; }

    //Setter for BPA
    public void setBlastProtectionAllowance(int blastProtectionAllowance) { this.blastProtectionAllowance = blastProtectionAllowance; }

    public int getFinalValueToBeUsed(){
        return finalValueToBeUsed;
    }
}


