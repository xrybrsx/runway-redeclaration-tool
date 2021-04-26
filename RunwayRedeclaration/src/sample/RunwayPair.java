package sample;

//Runway Pair object stores two runways that form a pair at an airport
public class RunwayPair {

    private Runway runway1;
    private Runway runway2;

    public RunwayPair(Runway runway1,Runway runway2){
        this.runway1 = runway1;
        this.runway2 = runway2;
    }

    //getter method for the first Runway in the pair
    public Runway getRunway1() {
        return runway1;
    }

    //getter method for the second Runway in the pair
    public Runway getRunway2() {
        return runway2;
    }
}
