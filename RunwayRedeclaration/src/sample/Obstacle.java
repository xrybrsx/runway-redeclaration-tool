package sample;

/* When a user of the runway redeclaration tool wants to input an obstacle that is on the runway to recalculate the
    runway parameters, they will create a new obstacle object with measurements corresponding to the attributes defined
    in the class below.
     */
public class Obstacle {

    //sample.Runway Attributes
    private String name;
    private int height;
    private int distanceFromCentreLine;
    private int distanceFromThreshold;

    //Constructor for the obstacle class
    public Obstacle(String name, int height, int distanceFromCentreLine, int distanceFromThreshold) {
        this.name = name;
        this.height = height;
        this.distanceFromCentreLine = distanceFromCentreLine;
        this.distanceFromThreshold = distanceFromThreshold;
    }

    //Getter method for obstacle name
    public String getName() {
        return name;
    }

    //Setter method for obstacle name
    public void setName(String name) {
        this.name = name;
    }

    //Getter method for obstacle height
    public int getHeight() {
        return height;
    }

    //Setter method for obstacle height
    public void setHeight(int height) {
        this.height = height;
    }

    //Getter method for obstacle distance from centre line
    public int getDistanceFromCentreLine() {
        return distanceFromCentreLine;
    }

    //Setter method for obstacle distance from centre line
    public void setDistanceFromCentreLine(int distanceFromCentreLine) {
        this.distanceFromCentreLine = distanceFromCentreLine;
    }

    //Getter method for obstacle distance from threshold
    public int getDistanceFromThreshold() {
        return distanceFromThreshold;
    }

    //Setter method for obstacle distance from threshold
    public void setDistanceFromThreshold(int distanceFromThreshold) {
        this.distanceFromThreshold = distanceFromThreshold;
    }
}

