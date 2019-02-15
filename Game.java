import java.util.*;
/************************************************************
This is the main class of an adventure game application. 
Players move around some scenery. They can pick up items
and sometimes eat the items.  Potential goals to winning
the game is moving to a particular location after completing
required tasks. 

@author Scott Grissom
@version March 15, 2012 
 ***********************************************************/

public class Game {

/** current and previous locations */
    private Location currentRoom, lastRoom;
    
    /** collection of items held by the player */
    private ArrayList <Item> myStuff;
    
    /** all locations in the game world */
    private Location outside, theatre, pub, lab, office, treasureRoom, beamer;
    
    /**  all items found in the world */
    private Item shoe, candy, treasure;  
    
    /** current game message continually updated */
    private String msg;
        
/**********************************************************
Create the game environment
**********************************************************/
    public Game() {
        myStuff = new ArrayList <Item>(); 
        createWorld();
        currentRoom = outside; 
        setWelcomeMessage();  
         
    }

/**********************************************************
Create all the items, rooms and neighbors.
**********************************************************/
    private void createWorld(){
        
        // create all items
        shoe = new Item("shoe","a red Nike shoe", 10, false);
        candy = new Item("candy", "a jar of candy", 20, true);
        treasure = new Item("treasure", "a very large treasure chest", 200, false);        

        // create all locations
        outside = new Location("outside the main entrance of the university", shoe);
        theatre = new Location("in a lecture theatre");
        pub = new Location("in the campus pub");
        lab = new Location("in a computing lab");
        office = new Location("in the computing admin office", candy);
        treasureRoom = new Location("in the magic treasure room", treasure);        

        // create all neighbors
        outside.addNeighbor("east", theatre);
        outside.addNeighbor("inside", lab);   
        outside.addNeighbor("west", pub);        
        theatre.addNeighbor("west", outside);
        pub.addNeighbor("east", outside);
        lab.addNeighbor("upstairs", office);
        lab.addNeighbor("east", office);        
        office.addNeighbor("west", lab);
        office.addNeighbor("downstairs", treasureRoom);        
    }

/**********************************************************
Move from the current room in the provided direction
if possible.
@param direction - requested direction to move
**********************************************************/
    public void move(String direction){
      
        // find the neighbor
        Location nextLocation = currentRoom.getNeighbor(direction);
        
        // alert the player there is no neighbor
        // or update the current room
        if (nextLocation == null)
            msg = "You can't go in that direction";
        else {
            //lastRoom = currentRoom;
            currentRoom = nextLocation;
            look();
        }
        
    }
    
/**********************************************************
@return the current message
**********************************************************/
    public String getMessage(){
        return msg;
    }

/**********************************************************
Update the generic help message.
**********************************************************/
    public void help() {
        msg = "You are lost. You are alone. You wander";
        msg += " around GVSU looking for Louie the Laker.";
    }
    
/**********************************************************
Set the initial game message.  Could be lengthy so hidden
away in this helper method.
**********************************************************/
    private void setWelcomeMessage(){
        msg = "Welcome to GVSU!  Home of the Lakers.";
        msg += "'In Search of Louie' is a \n new and incredibly boring adventure game.\n\n";
        msg += currentRoom.getLongDescription();
    }    

/**************************************************************
Attempt to pick up an item if it is not too heavy
**************************************************************/     
    public void take(){
        // The room has no item
        if (!currentRoom.hasItem())
            msg = "There is nothing to take.";
            
        // The item is too heavy    
        else if (currentRoom.getItem().getWeight() > 100)
            msg = "The "+ currentRoom.getItem().getName() +
                        " is too heavy to pick up!";
                        
        // player picked up the item                
        else{
            Item i = currentRoom.removeItem();
            msg = "You are holding " + i.getDescription();
            myStuff.add(i);
        }
        
    }

/**********************************************************
Update game message with long description of current
location.
**********************************************************/    
    public void look(){
        msg = currentRoom.getLongDescription();
    }

/**********************************************************
check if the game has been won, lost or still going.  Update
the final message if game is over.

@return true if game has been won or lost
**********************************************************/    
    public boolean gameOver(){
        boolean over = false;
        
        // has game been won?
        if (currentRoom == treasureRoom && searchPouch("treasure") == treasure){
            msg = "Congratulations!  You win!";
            over = true;
        }
        return over;
    }
    
/**************************************************************
Player may eat edible items that are currently held

@param word name of item to eat
**************************************************************/    
public void eat(String word){
    Item i = null;
    
    // player did not provide second word
    if (word.equals(""))
        msg = "What do you want to eat?";
    else{
        
        // look in the player's sack for the item
        i = searchPouch(word);
        
        // the item is not being held
        if (i == null){
            msg = "You are not holding a " + word +".";         

        // the item can be eaten
        }else if(i.isEdible()){
            msg = "Mmmm. That "+ word + " was tasty!";
            myStuff.remove(i);
            
        // the item is held but not edible    
        }else
            msg = "Gross! You can not eat a "  + word +".";
        }
    }   
    
/**************************************************************
Check if the item is being held. If so, return it.

@param str one word name of an item
@return the item, if found (or null).
**************************************************************/      
    private Item searchPouch(String str){
        Item found = null;
        for (Item i :myStuff){
            if(i.getName().equals(str))  
                found = i;
        }
        return found;
    }
    
/**************************************************************
Attempt to drop an item in the current location

@param word item name to be dropped
**************************************************************/       
    public void drop(String word){
        Item i;
        
        // player did not specify an item
        if (word.equals("")){
            msg = "What do you want to drop?";
                  
        }else{        
            
            i = searchPouch(word);
            
            // player is not holding the requested item
            if (i == null)
                msg = "You are not holding a " + word;
        
            // item can not be dropped since another item is already in room   
            else if (currentRoom.hasItem()){
                msg = "You can not drop the " + word + " here.";            
                
            // item is dropped into the current room    
            }else{
                msg = "You dropped a " + word;
                myStuff.remove(i);
                currentRoom.setItem(i);
            }
        }   
        
    }
    
/**************************************************************
Update the game message to list all items the player is holding
**************************************************************/       
    public void list(){

        if (myStuff.size() == 0)
            msg = "You are not holding anything";
        else{
            msg = "You are holding:";
            for (Item i : myStuff)
                msg += "\n   " + i.getDescription();
        }
    }

/**********************************************************

**********************************************************/    
    public void shrink(){
        if (currentRoom != treasureRoom)
            msg = "There is nothing here to shrink";
        else{
            treasureRoom.getItem().setWeight(50);
            msg = "As if by magic, the treasure chest shrinks to the size of a mouse";
        }
    }   
    
/**********************************************************

**********************************************************/    
public static void main (String args[]){
   Game g = new Game();
   System.out.println(g.getMessage());
   g.take();
   System.out.println(g.getMessage());
   g.move("south");   
   System.out.println(g.getMessage());
   g.drop("book");   
   System.out.println(g.getMessage());
   g.move("north");
   System.out.println(g.getMessage());
   g.move("south");
   System.out.println(g.getMessage());
   g.shrink();
   System.out.println(g.getMessage());
   g.take();
   System.out.println(g.getMessage()); 
   if(g.gameOver()){ 
      System.out.println(g.getMessage());
   }
}

/**********************************************************

**********************************************************/    
//     public void speak(){
//         if (currentRoom.hasCharacter()){
//              msg = currentRoom.getCharacter().speak();
//         }else
//              msg = "There is no one else here";  
//     }

//     private void charge(Command command){
//         if (command.getSecondWord() == null)
//             System.out.println("What do you want to charge?");
//         else if (command.getSecondWord().equals("iPorter")){
//             System.out.println("Your iPorter is now charged");
//             beamer = currentRoom;
//         } else
//             System.out.println("You can't charge a "+command.getSecondWord());
//     }

/**********************************************************

**********************************************************/    
//     private void shazam( ){
//         if (beamer == null)
//             System.out.println("Your iPorter is not charged.");
//         else {
//             System.out.println("You magically transform.");
//             currentRoom = beamer;
//             printLocationInfo(); 
//             beamer = null;
//         }
//     }  

/**************************************************************
Go back one step, if legal.  This is not legal if at the
start of the game.  
**************************************************************/          
// public void retreat(){
// 
//     // not legal to go back
//     if (lastRoom == null)
//         msg = "You can't retreat from here";
//         
//     // update current room and prevent another back step    
//     else{
//         currentRoom = lastRoom;
//         lastRoom = null;
//         look();
//     }
// }

/**********************************************************

**********************************************************/    
//     private void unlock(){
//         if (currentRoom != theatre)
//             System.out.println("There is no locked door here");
//         else{
//             theatre.setExits("east", pub);
//             System.out.println("The door is now unlocked");
//         }        
//         
//         
//     }

}
