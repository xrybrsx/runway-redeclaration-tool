package sample;

import javafx.stage.FileChooser;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.rules.TemporaryFolder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class testCalculate {

    //example-test to calculatecheck Landing over Obstacle
    @Test
    public void testCalculateLOO() {
        Runway run1 = new Runway("27R", 3884,3962,3884, 3884, 0, 60, 0, 0,500);
        int rLDA= new LandingOverObstacle(50, 1250).recalculate(3884,500, 25, 50, 500, 60, 1250);
        assertEquals(2074, rLDA, 0.0);
    }

    //example-test to check Landing Towards Obstacle
    @Test
    public void testCalculateLTO() {
        Runway run1 = new Runway("09L", 3902,3902,3902, 3595, 306, 60, 0, 0,240);
        int lLTO= new LandingTowardsObstacle().recalculate(2600, 240, 60);
        assertEquals(2300, lLTO, 0.0);
    }

    //example-test to check take away from Obstacle
    @Test
    public void testCalculateTOAFO() {
        Runway run1 = new Runway("27R", 3884,3962,3884, 3884, 0, 60, 0, 0,0);
        TakeOffAwayFromObstacle rTOAFO = new TakeOffAwayFromObstacle(300);
        rTOAFO.recalculate(3884,3962,3884,500,300,0,0);
        int tora = rTOAFO.getNewTora();
        int toda = rTOAFO.getNewToda();
        int asda = rTOAFO.getNewAsda();
        assertEquals(3084, tora, 0.0);
        assertEquals(3162, toda, 0.0);
        assertEquals(3084, asda, 0.0);
    }

    //example-test to check Landing Towards Obstacle
    @Test
    public void testCalculateTOTO() {
        Runway run1 = new Runway("09L", 3884,3962,3884, 3884, 0, 60, 0, 0,500);
        int lTOTO= new TakeOffTowardsObstacle(50).recalculate(2500, 306, 25, 50, 240, 60);
        assertEquals(1496, lTOTO, 0.0);
    }

    //scenario1-12m tall obstacle, on the centreline, 50m before the 09L threshold,
    //i.e. to the west of the threshold. The same obstacle is 3646m from the 27R threshold.
    //test to calculatecheck Landing over Obstacle
    @Test
    public void testCalculateLOO1() {
        Runway run = new Runway("09L", 3902,3902,3902, 3595, 306, 60, 0, 0,240);
        int rLDA= new LandingOverObstacle(50, 300).recalculate(3595,-50, 12, 50, 500, 60, 300);
        assertEquals(2985, rLDA, 0.0);
    }

    //scenario1-test to check Landing Towards Obstacle
    @Test
    public void testCalculateLTO1() {
        Runway run  = new Runway("27R", 3884,3962,3884, 3884, 0, 60, 0, 0,240);
        int lLTO = new LandingTowardsObstacle().recalculate(3646, 240, 60);
        assertEquals(3346, lLTO, 0.0);
    }

    //scenario1-test to check take away from Obstacle
    @Test
    public void testCalculateTOAFO1() {
        Runway run1 = new Runway("09L", 3902,3902,3902, 3595, 306, 60, 0, 0,240);
        TakeOffAwayFromObstacle rTOAFO = new TakeOffAwayFromObstacle(300);
        rTOAFO.recalculate(3902,3902,3902,-50+306,300,0,0);
        int tora = rTOAFO.getNewTora();
        int toda = rTOAFO.getNewToda();
        int asda = rTOAFO.getNewAsda();
        assertEquals(3346, tora, 0.0);
        assertEquals(3346, toda, 0.0);
        assertEquals(3346, asda, 0.0);
    }

    //scenario1-test to check Landing Towards Obstacle
    @Test
    public void testCalculateTOTO1() {
        Runway run1 = new Runway("27R", 3884,3962,3884, 3884, 0, 60, 0, 0,240);
        int lTOTO= new TakeOffTowardsObstacle(50).recalculate(3646, 0, 12, 50, 240, 60);
        assertEquals(2986, lTOTO, 0.0);
    }

    //scenario 225m tall obstacle, 20m south of the centerline,
    // 500m from the 27L threshold and 2853m from 09R threshold.
    // -test to check calculate of Landing over Obstacle
    @Test
    public void testCalculateLOO2() {
        Runway run1 = new Runway("27L", 3884,3962,3884, 3884, 0, 60, 0, 0,500);
        int rLDA= new LandingOverObstacle(50, 300).recalculate(3660,500, 25, 50, 240, 60, 306);
        assertEquals(1850, rLDA, 0.0);
    }

    //scenario2-test to check Landing Towards Obstacle
    @Test
    public void testCalculateLTO2() {
        Runway run1 = new Runway("09R", 3660,3660,3660, 3353, 307, 60, 0, 0,240);
        int lLTO= new LandingTowardsObstacle().recalculate(2853, 240, 60);
        assertEquals(2553, lLTO, 0.0);
    }

    //scenario2-test to check take away from Obstacle
    @Test
    public void testCalculateTOAFO2() {
        Runway run1 = new Runway("27L", 3660,3660,3660, 3660, 0, 60, 0, 0,240);
        TakeOffAwayFromObstacle rTOAFO = new TakeOffAwayFromObstacle(240);
        rTOAFO.recalculate(3660,3660,3660,500,240+60,0,0);
        int tora = rTOAFO.getNewTora();
        int toda = rTOAFO.getNewToda();
        int asda = rTOAFO.getNewAsda();
        assertEquals(2860, tora, 0.0);
        assertEquals(2860, toda, 0.0);
        assertEquals(2860, asda, 0.0);
    }

    //scenario2-test to check Landing Towards Obstacle
    @Test
    public void testCalculateTOTO2() {
        Runway run1 = new Runway("09R", 3660,3660,3660, 3353, 307, 60, 0, 0,240);
        int lTOTO= new TakeOffTowardsObstacle(50).recalculate(2853, 307, 25, 50, 240, 60);
        assertEquals(1850, lTOTO, 0.0);
    }

    //scenario3-15m tall obstacle, 60m north of centreline,
    //150m from 09R threshold and 3203m from 27L threshold.
    //test to calculatecheck Landing over Obstacle
    @Test
    public void testCalculateLOO3() {
        Runway run1 = new Runway("09R", 3660,3660,3660, 3660, 307, 60, 0, 0,240);
        int rLDA= new LandingOverObstacle(50, 0).recalculate(3353,150, 15, 50, 0, 60, 0);
        assertEquals(2393, rLDA, 0.0);
    }

    //scenario3-test to check Landing Towards Obstacle
    @Test
    public void testCalculateLTO3() {
        Runway run1 = new Runway("27L", 3660,3660,3660, 3660, 0, 60, 0, 0,500);
        int lLTO= new LandingTowardsObstacle().recalculate(3203, 240, 60);
        assertEquals(2903, lLTO, 0.0);
    }

    //scenario3-test to check take away from Obstacle
    @Test
    public void testCalculateTOAFO3() {
        Runway run1 = new Runway("09R", 3660,3660,3660, 3660, 307, 60, 0, 0,240);
        TakeOffAwayFromObstacle rTOAFO = new TakeOffAwayFromObstacle(300);
        rTOAFO.recalculate(3660,3660,3660,150,300+307,0,0);
        int tora = rTOAFO.getNewTora();
        int toda = rTOAFO.getNewToda();
        int asda = rTOAFO.getNewAsda();
        assertEquals(2903, tora, 0.0);
        assertEquals(2903, toda, 0.0);
        assertEquals(2903, asda, 0.0);
    }


    //scenario3-test to check Landing Towards Obstacle
    @Test
    public void testCalculateTOTO3() {
        Runway run1 = new Runway("27L", 3660,3660,3660, 3660, 0, 60, 0, 0,240);
        int lTOTO= new TakeOffTowardsObstacle(50).recalculate(3203, 0, 0, 50, 240, 60);
        assertEquals(2903, lTOTO, 0.0);
    }

    //scenario4-20m tall obstacle, 20m right of centreline (to the north),
    // 50m from 27R threshold and 3546mfrom 09L threshold.
    // test to check calculate Landing over Obstacle
    @Test
    public void testCalculateLOO4() {
        Runway run1 = new Runway("27R", 3884,3962,3884, 3884, 0, 60, 0, 0,0);
        int rLDA= new LandingOverObstacle(50, 0).recalculate(3884,50, 20, 50, 0, 60, 0);
        assertEquals(2774, rLDA, 0.0);
    }

    //scenario4-test to check Landing Towards Obstacle
    @Test
    public void testCalculateLTO4() {
        Runway run1 = new Runway("09L", 3902,3902,3902, 3593, 306, 60, 0, 0,240);
        int lLTO= new LandingTowardsObstacle().recalculate(3546, 240, 60);
        assertEquals(3246, lLTO, 0.0);
    }

    //scenario4-test to check take away from Obstacle
    @Test
    public void testCalculateTOAFO4() {
        Runway run1 = new Runway("27R", 3884,3962,3884, 3884, 0, 60, 0, 0,0);
        TakeOffAwayFromObstacle rTOAFO = new TakeOffAwayFromObstacle(300);
        rTOAFO.recalculate(3884,3962,3884,50,240+60,0,0);
        int tora = rTOAFO.getNewTora();
        int toda = rTOAFO.getNewToda();
        int asda = rTOAFO.getNewAsda();
        assertEquals(3534, tora, 0.0);
        assertEquals(3612, toda, 0.0);
        assertEquals(3534, asda, 0.0);
    }

    //scenario4-test to check Landing Towards Obstacle
    @Test
    public void testCalculateTOTO4() {
        Runway run1 = new Runway("09L", 3902,3902,3902, 3593, 306, 60, 0, 0,240);
        int lTOTO= new TakeOffTowardsObstacle(50).recalculate(3546, 306, 20, 50, 240, 60);
        assertEquals(2792, lTOTO, 0.0);
    }

    //check method determining if a runway name has a letter as its last char
    @Test
    public void testCharMethodTrue(){
        Runway run1 = new Runway("27R", 3884,3962,3884, 3884, 0, 60, 0, 0,0);
        ResultsController resultsController = new ResultsController();
        assertTrue(resultsController.checkForRunwayCouple(run1));
    }

    //check method determining if a runway name has a letter as it last char
    @Test
    public void testCharMethodFalse(){
        Runway run1 = new Runway("27", 3884,3962,3884, 3884, 0, 60, 0, 0,0);
        ResultsController resultsController = new ResultsController();
        assertFalse(resultsController.checkForRunwayCouple(run1));
    }

    //check method returns string of opposite runway with a zero in front if that runway number contains only a single number.
    @Test
    public void testForOppositeRunway1(){
        Runway run1 = new Runway("27R", 3884,3962,3884, 3884, 0, 60, 0, 0,0);
        ResultsController resultsController = new ResultsController();
        assertEquals("09L",resultsController.checkForPair(run1));
        assertNotEquals("9L",resultsController.checkForPair(run1));
    }

    //check method returns string of opposite runway
    @Test
    public void testForOppositeRunway2(){
        Runway run1 = new Runway("09L", 3902,3902,3902, 3593, 306, 60, 0, 0,240);
        ResultsController resultsController = new ResultsController();
        assertEquals("27R",resultsController.checkForPair(run1));
    }

    //check method for multiple letters in the runway name. Throws a NumberFormatException because it rightfully
    //fails to convert a string containing both numbers and letters to an integer
    @Test
    public void testForOppositeRunway3(){
        Runway run1 = new Runway("09LR", 3902,3902,3902, 3593, 306, 60, 0, 0,240);
        ResultsController resultsController = new ResultsController();
        assertThrows(NumberFormatException.class, () -> {
            resultsController.checkForPair(run1);
        });
    }

    //check method for only letters in the runway name. It fails because you cannot make an integer
    //out of letters
    @Test
    public void testForOppositeRunway4(){
        Runway run1 = new Runway("R", 3902,3902,3902, 3593, 306, 60, 0, 0,240);
        ResultsController resultsController = new ResultsController();
        assertThrows(NumberFormatException.class, () -> {
            resultsController.checkForPair(run1);
        });
    }

    //check method returns string 36L instead of 0L as the opposite runway for 18R (because range of runway numbers are 1 to 36)
    @Test
    public void testForOppositeRunway5(){
        Runway run1 = new Runway("18L",3902,3902,3902,3593,306,60,0,0,240);
        ResultsController resultsController = new ResultsController();
        assertEquals("36R",resultsController.checkForPair(run1));
    }

    private Runway runway;

    //method checks if -1 is returned when the runway number is wrong
    @Test
    public void testForOppositeRunway6(){
        ResultsController resultsController = new ResultsController();
        runway = new Runway("37R",3902,3902,3902,3593,306,60,0,0,240);
        assertEquals("Runway number not in range between 1 to 36","-1",resultsController.checkForPair(runway));
        runway = new Runway("37L",3902,3902,3902,3593,306,60,0,0,240);
        assertEquals("Runway number not in range between 1 to 36","-1",resultsController.checkForPair(runway));
        runway = new Runway("37C",3902,3902,3902,3593,306,60,0,0,240);
        assertEquals("Runway number not in range between 1 to 36","-1",resultsController.checkForPair(runway));
        runway = new Runway ("100L",3902,3902,3902,3593,306,60,0,0,240);
        assertEquals("Runway number not in range between 1 to 36","-1",resultsController.checkForPair(runway));
        runway = new Runway ("1000C",3902,3902,3902,3593,306,60,0,0,240);
        assertEquals("Runway number not in range between 1 to 36","-1",resultsController.checkForPair(runway));
        runway = new Runway ("9000",3902,3902,3902,3593,306,60,0,0,240);
        assertEquals("Runway number not in range between 1 to 36","-1",resultsController.checkForPair(runway));
        runway = new Runway ("0R",3902,3902,3902,3593,306,60,0,0,240);
        assertEquals("Runway number not in range between 1 to 36","-1",resultsController.checkForPair(runway));
        runway = new Runway ("0L",3902,3902,3902,3593,306,60,0,0,240);
        assertEquals("Runway number not in range between 1 to 36","-1",resultsController.checkForPair(runway));
        runway = new Runway ("0C",3902,3902,3902,3593,306,60,0,0,240);
        assertEquals("Runway number not in range between 1 to 36","-1",resultsController.checkForPair(runway));
        runway = new Runway ("36R",3902,3902,3902,3593,306,60,0,0,240);
        assertEquals("Something is wrong","18L",resultsController.checkForPair(runway));
    }

    //Test for opposite runway ending on a C
    @Test
    public void testForOppositeRunway7(){
        Runway run1 = new Runway("09C", 3902,3902,3902, 3593, 306, 60, 0, 0,240);
        ResultsController resultsController = new ResultsController();
        assertEquals("27C",resultsController.checkForPair(run1));
    }

}
