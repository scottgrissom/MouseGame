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

public class MouseGame {

    /** current cheese count */
    private int cheeseCnt;
    
    /** is cheese available */
    private boolean cheese;
    private int WIN=2;
    private Random rand;
        
    /** current game message continually updated */
    private String msg;
        
/**********************************************************
Create the game environment
**********************************************************/
    public MouseGame() {
        setWelcomeMessage();  
        cheeseCnt = 0;
        cheese = false;
        rand = new Random();
    }

/**********************************************************
Move from the current room in the provided direction
if possible.
@param direction - requested direction to move
**********************************************************/
    public void move(String direction){  
        msg = "You move " + direction; 
        if(rand.nextInt(10) < 2)
           cheese = true;
        if(cheese)
            msg += "\nYou see some cheese!";
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
        msg = "You are a mouse lost in a maze.  Find and ";
        msg += " eat the cheese to win.";
    }
    
/**********************************************************
Set the initial game message.  Could be lengthy so hidden
away in this helper method.
**********************************************************/
    private void setWelcomeMessage(){
        msg = "Welcome to Find the Cheese!";
        msg += "\nWander around looking for the cheese";
    }    

/**************************************************************
Attempt to pick up an item if it is not too heavy
**************************************************************/     
    public void pickup(){
        msg = "There is nothing to pick up here.";        
    }

/**********************************************************
Update game message with long description of current
location.
**********************************************************/    
    public void look(){
        msg = "Paths lead in all directions from here";
    }

/**********************************************************
check if the game has been won, lost or still going.  Update
the final message if game is over.

@return true if game has been won or lost
**********************************************************/    
    public boolean gameOver(){
        boolean over = false;
        
        // has game been won?
        if (cheeseCnt >= WIN){
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
    else if (!word.equals("cheese"))
        msg = "Why would you want to eat " + word+"?";        
    else if (!cheese)
        msg = "There is no cheese to eat here."; 
    else{    
         msg = "Yum! You ate some tasty cheese!"; 
         cheeseCnt++;
         cheese = false;
        }
    }   
    
/**************************************************************
Attempt to drop an item in the current location

@param word item name to be dropped
**************************************************************/       
    public void drop(String word){   
        // player did not specify an item
        if (word.equals("")){
            msg = "What do you want to drop?";           
        }else{        
           msg = "You are not holding a " + word; 
        }      
    }
    
/**************************************************************
Update the game message to list all items the player is holding
**************************************************************/       
    public void list(){
        msg = "You are not holding anything";
    }   
}
