import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.util.*;
/*************************************************************
 * GUI for an Adventure Game
 * 
 * @author Scott Grissom
 * @version October 7, 2011
 ************************************************************/
public class MouseGUI extends JFrame implements ActionListener{

    /** the analyzer that doe all the real work */
    MouseGame myGame;
    
    /** Buttons to initiate each action */
    private JButton east, west, north, down;
    private JButton pickup, eat, drop, look, pouch, help;
    
    /** GUI frame */
    JTextArea results;
    
    
    /** menu items */
    // if attempting the challenge activity
    JMenuBar menus;
    JMenu fileMenu;
    JMenuItem quitItem, newItem;
    
/*****************************************************************
 * Main Method
 ****************************************************************/ 
 public static void main(String args[]){
        MouseGUI gui = new MouseGUI();
        gui.setSize(600,300);
        gui.setTitle("Find the Cheese"); 
        gui.setVisible(true);
        gui.pack();
    }
    
/*****************************************************************
 * constructor installs all of the GUI components
 ****************************************************************/    
    public MouseGUI(){
  
        
        // set the layout to GridBag
        setLayout(new GridBagLayout());
        GridBagConstraints position = new GridBagConstraints();
        setForeground(Color.red);

        // create results area to span one column and 11 rows
        results = new JTextArea(20,40);
        JScrollPane scrollPane = new JScrollPane(results);
        

    // allow word wrap
    //results.setLineWrap(true);
    //results.setWrapStyleWord(true);
    
    // allows auto scrolling within the JTextArea
    DefaultCaret caret = (DefaultCaret) results.getCaret();
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    
        position.gridx = 0;
        position.gridy = 0;
        position.gridheight = 10;  
        position.gridwidth = 10;  
        position.insets.left = 20;
        position.insets.right = 20;
        position.insets.bottom = 20;
        add(scrollPane, position);
        
        // reset position
        position = new GridBagConstraints();


    
    // instantiate each of the buttons
    help = new JButton("help");
    pickup = new JButton("pickup");
    drop = new JButton("drop");
    //retreat = new JButton("backup");
    look = new JButton("look");
    pouch = new JButton("list");  
    //speak = new JButton("speak");      
    //shrink = new JButton("shrink");
    eat = new JButton("eat");
    
    // add action buttons here
    position.gridx = 0;
    position.gridy = 10;
    add(new JLabel("Actions: "), position);
    position.gridx = 1;
    add(help, position);
    position.gridx = 2;
    add(pickup, position);
    position.gridx = 3;
    add(drop, position);
    position.gridx = 4;
    add(eat, position);
    position.gridx = 5;
    add(look, position); 
    position.gridx = 6;
    add(pouch, position); 
    //position.gridx = 7;
    //add(retreat, position);   
    //position.gridx = 8;
    //add(shrink, position);
    
    // instantiate Direction Buttons
    east = new JButton("East");
    west = new JButton("West");
    north = new JButton("North");
    down = new JButton("Down");
    
    // add buttons to Direction panel
    position.gridx = 10;
    position.gridy = 0;   
    //position.anchor= GridBagConstraints.WEST;
    add(new JLabel("Directions"), position);    
    position.gridy = 1;   
    add(east, position);
    position.gridy = 2;   
    add(west, position);
    position.gridy = 3;   
    add(north, position);
    position.gridy = 4;   
    add(down, position);    
    //position.gridy = 5;      
    //add(inside, position);
    
    // register the listeners
    help.addActionListener(this);
    pickup.addActionListener(this);
    eat.addActionListener(this);
    drop.addActionListener(this);
    look.addActionListener(this);  
    pouch.addActionListener(this); 
    
    east.addActionListener(this);   
    west.addActionListener(this);  
    north.addActionListener(this);  
    down.addActionListener(this);      
    
    // set up File menu
    fileMenu = new JMenu("File");
    quitItem = new JMenuItem("Quit");
    newItem = new JMenuItem("New Game");
    fileMenu.add(newItem);
    fileMenu.add(quitItem);
    menus = new JMenuBar();
    setJMenuBar(menus);
    menus.add(fileMenu);
    
    // set the action listener for menu items
    quitItem.addActionListener(this);
    newItem.addActionListener(this);
    
    newGame();
    }
    
/********************************************
Disable all buttons.
********************************************/   
    private void gameOver(){
        help.setEnabled(false);
        drop.setEnabled(false);
        look.setEnabled(false);   
        pouch.setEnabled(false);
        eat.setEnabled(false);
        pickup.setEnabled(false);        
        east.setEnabled(false);   
        west.setEnabled(false);               
        down.setEnabled(false);                
        north.setEnabled(false);            
    }
/********************************************
Create a new Game object and enable all 
buttons.
********************************************/    
    private void newGame(){
        myGame = new MouseGame();
        results.setText("");
        results.setText(myGame.getMessage());        
        help.setEnabled(true);
        drop.setEnabled(true);     
        look.setEnabled(true);  
        eat.setEnabled(true);
        pickup.setEnabled(true);        
        pouch.setEnabled(true);
        east.setEnabled(true);   
        west.setEnabled(true);          
        north.setEnabled(true);        
        down.setEnabled(true);                      
    }


/*****************************************************************
 * This method is called when any button is clicked.  The proper
 * internal method is called as needed.
 * 
 * @param e the event that was fired
 ****************************************************************/       
public void actionPerformed(ActionEvent e){

    JComponent buttonPressed = (JComponent) e.getSource();
    
    if (buttonPressed == quitItem){
        System.exit(1);
        
    }else if (buttonPressed == newItem){
        newGame();
        return;
    }else if (buttonPressed == east){
        myGame.move("east");

    }else if (buttonPressed == west){
        myGame.move("west");
    }else if (buttonPressed == north){
        myGame.move("north");
    }else if (buttonPressed == down){
        myGame.move("down");
        
    }else if (buttonPressed == pickup){
        myGame.pickup();     
    }else if (buttonPressed == look){
        myGame.look();
    }else if (buttonPressed == pouch){
        myGame.list();
    }else if (buttonPressed == help){
        myGame.help();   
        
     }else if (buttonPressed == eat){
         String toEat = JOptionPane.showInputDialog(this,"What do you want to eat?");
         if (toEat == null)
             return;
         myGame.eat(toEat);  
        
    }else if (buttonPressed == drop){
        String toDrop = JOptionPane.showInputDialog(this, "What do you want to drop?");
        if (toDrop == null)
            return;
        myGame.drop(toDrop);    
    }
    
    results.append("\n\n" + myGame.getMessage());   
    
    if (myGame.gameOver()){
        results.append("\n\n" + myGame.getMessage());
        JOptionPane.showMessageDialog(this, myGame.getMessage());
        gameOver();
    }


}

}