import java.util.*;
/*********************************************************
a location in an adventure game. 

A "Location" represents one location in the scenery of the game.  
It is connected to neighboring locations.  The exits are labeled 
with words such as north, east, upstairs and inside. For each 
direction, the location stores a reference to the neighbor, 
or null if there is no exit in that direction.

@author Scott Grissom
@version March 10, 2012
*********************************************************/

public class Location {

    /** location description */
    private String description;
    
    /** location neighbors associated with directions */
    private HashMap <String, Location> neighbors;
    
    /** optional item in the room */
    private Item myItem;

/***************************************************
Test Method
***************************************************/  
    public static void main(String args[]){
        Location kitchen = new Location("in the kitchen",null);

        Item car = new Item("car","65 Mustang Convertible",2000, false);
        Location garage = new Location("in the garage", car);
        System.out.println(kitchen.getLongDescription());
        System.out.println(garage.getLongDescription());
    }
    
/***************************************************
Create a locatioin with a description and no item
@param d location description
***************************************************/       
    public Location(String description) {
        this.description = description;
        myItem = null;
        neighbors = new HashMap <String, Location> ();
    }
    
/***************************************************
Create a locatioin with a description AND an item
@param d location description
@param i optional item
***************************************************/    
    public Location(String description, Item i) {
        this.description = description;
        myItem = i;
        neighbors = new HashMap <String, Location> ();
    }
    
/***************************************************
Add a neighbor and associated direction
@param dir direction towards neighbor
@param neighbor
***************************************************/
    public void addNeighbor(String dir, Location neighbor) {
        neighbors.put(dir, neighbor); 
    }

/***************************************************
Return the neighbor in the request direction.  Returns
'null' if there is no neighbor in that direction
@return neighbor in requested direction
***************************************************/    
    public Location getNeighbor(String direction){
        return neighbors.get(direction);
    }
    
/***************************************************
@eturn the item (even if null)
***************************************************/    
    public Item getItem(){
        return myItem;
    }   

/***************************************************
Add an item to the room.  Any existing item is replaced.
@param i the new item
***************************************************/    
    public void setItem(Item i){
        myItem = i;
    }

/***************************************************
Does the room have an item?
@return true if location has an item
***************************************************/    
    public boolean hasItem(){
        return myItem != null;
    }
 
/***************************************************
Remove AND return the item
@return the removed item
***************************************************/    
    public Item removeItem(){
        Item temp = myItem;
        myItem = null;
        return temp;
    }
    
/***************************************************
Returns a description including the optional item
@return the long description
***************************************************/    
    public String getLongDescription(){
        String str = "You are " + description;
        if (hasItem())
            str += "\nYou see " + myItem.getDescription();
//         if (hasCharacter())
//             str += "\nYou meet " + myCharacter.getName();
        return str;
    }
     
/***************************************************
@return the room description
***************************************************/    
    public String getShortDescription(){
        String str = "You are " + description;
        return str;
    } 
    
/***************************************************
Return the current character
***************************************************/    
//     public Character getCharacter(){
//         return myCharacter;
//     }  

/***************************************************
Does the room have an item?
***************************************************/    
//     public boolean hasCharacter(){
//         return myCharacter != null;
//     }    
}
