package sample;

/* This class represents the "landing towards obstacle calculation" in which some runway and obstacle
measurements are used (as well as external measurements added to the calculation).
 This recalculation involves recalculating and updating the LDA of the runway in which there is an obstacle present
 */
public class LandingTowardsObstacle extends Calculation{
    private int newLDA;

    //Method inherited from abstract calculation class
    @Override
    public void recalculate() {}

    /*
    method recalculates new LDA given the obstacle distance from threshold, the Runway End Safety Area (RESA)
    and the Runway StripEnd
     */
    public int recalculate(int distanceFromThreshold, int resa, int stripEnd){
        return distanceFromThreshold - resa - stripEnd;
    }

    //getter method for the new recalculated Landing Distance Available (LDA)
    public int getNewLDA(){
        return newLDA;
    }
}
