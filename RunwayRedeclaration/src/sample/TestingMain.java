package sample;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestingMain {
    public static void main(String []args) {
        TestingMain t = new TestingMain();
        t.testForRunwayPairing();
    }
    public void testForRunwayPairing(){
        ArrayList<Runway> runwaysList = new ArrayList<>();
        Runway run1 = new Runway("09L", 3902,3902,3902, 3593, 306, 60, 0, 0,240);
        Runway run2 = new Runway("0", 3902,3902,3902, 3593, 306, 60, 0, 0,240);
        Runway run3 = new Runway("18R", 3902,3902,3902, 3593, 306, 60, 0, 0,240);
        Runway run4 = new Runway("18L", 3902,3902,3902, 3593, 306, 60, 0, 0,240);
        runwaysList.add(run1);
        runwaysList.add(run2);
        runwaysList.add(run3);
        runwaysList.add(run4);
        Runway run5 = new Runway("27R", 3884,3962,3884, 3884, 0, 60, 0, 0,0);
        ResultsController resultsController = new ResultsController();
        resultsController.getOppositeRunway(run5);
    }
}

