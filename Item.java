/***************************************************
An item holds a name, description, weight and 
boolean flag indicating if the item is edible.

@author Scott Grissom 
@version March 30, 2012
 **************************************************/
public class Item{

    /** item weight */ 
    private int weight;

    /** item name (one word) and longer description */
    private String name, description;

    /** is the item edible? */
    private boolean edible;

    /***************************************************
    Test Method
     ***************************************************/
    public static void main(String args[]){
        Item shoe = new Item("shoe","3 inch sling back", 4, false);
        System.out.println(shoe.getName());
        System.out.println(shoe.getDescription());
        System.out.println(shoe.getWeight());
        System.out.println(shoe.isEdible());   
    }    

    /***************************************************
    Constructor to initialize the instance variables
    @param n item name
    @param d item description
    @param w item weight
    @param e edible flag
     ***************************************************/
    public Item(String n, String d, int w, boolean e){
        weight = w;
        name = n;
        description = d;
        edible = e;
    }

    /***************************************************
    @return the item weight
     ***************************************************/
    public int getWeight(){
        return weight;
    }

    /***************************************************
    Change the item's weight
    @param w new item weight
     ***************************************************/
    public void setWeight(int w){
        weight = w;
    }

    /***************************************************
    Determine if the item is edible
    @return true if item is edible
     ***************************************************/
    public boolean isEdible(){
        return edible;
    }

    /***************************************************
    Change the item's edible flag
    @param e new item edible flag
     ***************************************************/
    public void setEdible(boolean e){
        edible = e;
    }

    /***************************************************
    @return the long description
     ***************************************************/
    public String getDescription(){
        return description;
    }

    /***************************************************
    Change the item's description
    @param d new item description
     ***************************************************/
    public void setDescription(String d){
        description = d;
    }

    /***************************************************
    @return the item name
     ***************************************************/
    public String getName(){
        return name;
    }

    /***************************************************
    Change the item's name
    @param n new item name
     ***************************************************/
    public void setName(String n){
        name = n;
    }

}
