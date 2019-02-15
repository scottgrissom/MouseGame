/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character
{
    // instance variables - replace the example below with your own
    private String name;
    private String speech;

    /**
     * Constructor for objects of class Item
     */
    public Character(String n, String s)
    {
        // initialise instance variables
        speech = s;
        name = n;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */

public String getName(){
    return name;
}
public String speak(){
    return speech;
}

}