import java.util.*;
/*********************************************************
Class Room - a room in an adventure game.

This class is part of the "World of Zuul" application. 
"World of Zuul" is a very simple, text based adventure game.  

A "Room" represents one location in the scenery of the game.  It is 
connected to other rooms via exits.  The exits are labelled north, 
east, south, west.  For each direction, the room stores a reference
to the neighboring room, or null if there is no exit in that direction.

@author  Michael Kolling and David J. Barnes
@version 1.0 (February 2002)
*********************************************************/

public class Room {
    private String description;
    private HashMap <String, Room> neighbors;
    private Item myItem;


/***************************************************
Test Method
***************************************************/  
    public static void main(String args[]){
        Room kitchen = new Room("in the kitchen",null);

        Item car = new Item("car","65 Mustang Convertible",2000, false);
        Room garage = new Room("in the garage", car);
        System.out.println(kitchen.getLongDescription());
        System.out.println(garage.getLongDescription());
    }
    
/***************************************************
Create a room with a description AND an item
***************************************************/    
    public Room(String description, Item i) {
        this.description = description;
        myItem = i;
        neighbors = new HashMap <String, Room> ();
    }

/***************************************************
Add a neighbor and a direction
***************************************************/
    public void addNeighbor(String direction, Room r) {
        neighbors.put(direction, r); 
    }

/***************************************************
Return the neighbor in the request direction.  Returns
'null' if there is no neighbor in that direction
***************************************************/    
    public Room getNeighbor(String direction){
        return neighbors.get(direction);
    }
    
/***************************************************
Return the current item
***************************************************/    
    public Item getItem(){
        return myItem;
    }

/***************************************************
Return the current character
***************************************************/    
//     public Character getCharacter(){
//         return myCharacter;
//     }    
/***************************************************
Return the room description
***************************************************/    
    public String getDescription()
    {
        return description;
    }    

/***************************************************
Add an item to the room.  Any existing item is replaced.
***************************************************/    
    public void setItem(Item i){
        myItem = i;
    }

/***************************************************
Does the room have an item?
***************************************************/    
    public boolean hasItem(){
        return myItem != null;
    }

/***************************************************
Does the room have an item?
***************************************************/    
//     public boolean hasCharacter(){
//         return myCharacter != null;
//     }
    
/***************************************************
Remove AND return the item
***************************************************/    
    public Item removeItem(){
        Item temp = myItem;
        myItem = null;
        return temp;
    }
    

/***************************************************
Return a longer description including the item
if it exists
***************************************************/    
    public String getLongDescription(){
        String str = "You are " + description;
        if (hasItem())
            str += "\nYou see " + myItem.getDescription();
//         if (hasCharacter())
//             str += "\nYou meet " + myCharacter.getName();
        return str;
    }
       
}
