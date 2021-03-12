package sample;
/*This abstract class encompasses the calculation aspect of the runway redeclaration tool.It's only method
involves the recalculation of runway parameters. Four specific calculation classes inherit from this abstract
class
 */

public abstract class Calculation {

    //Abstract recalculate method to be inherited by the specific calculation classes
    public abstract void recalculate();

}
