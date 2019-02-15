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
public class FullGameGUI extends JFrame implements ActionListener{

    /** the analyzer that doe all the real work */
    Game myGame;
    
    /** Buttons to initiate each action */
    private JButton east, west, upstairs, inside, downstairs;
    private JButton take, eat, retreat, drop, look, pouch, speak, help, shrink;
    
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
        FullGameGUI gui = new FullGameGUI();
        gui.setSize(600,300);
        gui.setTitle("Adventure Game"); 
        gui.setVisible(true);
        gui.pack();
    }
    
/*****************************************************************
 * constructor installs all of the GUI components
 ****************************************************************/    
    public FullGameGUI(){
  
        
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
    take = new JButton("pickup");
    drop = new JButton("drop");
    retreat = new JButton("backup");
    look = new JButton("look");
    pouch = new JButton("list");  
    speak = new JButton("speak");      
    shrink = new JButton("shrink");
    eat = new JButton("eat");
    
    // add action buttons here
    position.gridx = 0;
    position.gridy = 10;
    add(new JLabel("Actions: "), position);
    position.gridx = 1;
    add(help, position);
    position.gridx = 2;
    add(take, position);
    position.gridx = 3;
    add(drop, position);
    position.gridx = 4;
    add(eat, position);
    position.gridx = 5;
    add(look, position); 
    position.gridx = 6;
    add(pouch, position); 
    position.gridx = 7;
    add(retreat, position);   
    position.gridx = 8;
    add(shrink, position);
    
    // instantiate Direction Buttons
    east = new JButton("East");
    west = new JButton("West");
    upstairs = new JButton("Upstairs");
    downstairs = new JButton("Downstairs");
    inside = new JButton("Inside");    
    
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
    add(upstairs, position);
    position.gridy = 4;   
    add(downstairs, position);    
    position.gridy = 5;      
    add(inside, position);
    
    // register the listeners
    help.addActionListener(this);
    take.addActionListener(this);
    eat.addActionListener(this);
    drop.addActionListener(this);
    retreat.addActionListener(this);
    speak.addActionListener(this);
    look.addActionListener(this);  
    pouch.addActionListener(this); 
    shrink.addActionListener(this);
    
    east.addActionListener(this);   
    west.addActionListener(this);  
    upstairs.addActionListener(this);  
    downstairs.addActionListener(this);  
    inside.addActionListener(this);      
    
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
        retreat.setEnabled(false);
        look.setEnabled(false);   
        pouch.setEnabled(false);
        speak.setEnabled(false); 
        eat.setEnabled(false);
        take.setEnabled(false);        
        shrink.setEnabled(false); 
        east.setEnabled(false);   
        west.setEnabled(false);         
        upstairs.setEnabled(false);        
        downstairs.setEnabled(false);                
        inside.setEnabled(false);            
    }
/********************************************
Create a new Game object and enable all 
buttons.
********************************************/    
    private void newGame(){
        myGame = new Game();
        results.setText("");
        results.setText(myGame.getMessage());        
        help.setEnabled(true);
        drop.setEnabled(true);
        retreat.setEnabled(true);        
        look.setEnabled(true);  
        speak.setEnabled(true); 
        eat.setEnabled(true);
        take.setEnabled(true);        
        pouch.setEnabled(true);
        shrink.setEnabled(true); 
        east.setEnabled(true);   
        west.setEnabled(true);          
        upstairs.setEnabled(true);        
        downstairs.setEnabled(true);                
        inside.setEnabled(true);         
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
    }else if (buttonPressed == upstairs){
        myGame.move("upsatirs");
    }else if (buttonPressed == downstairs){
        myGame.move("downstairs");
    }else if (buttonPressed == inside){
        myGame.move("inside");   
        
    }else if (buttonPressed == take){
        myGame.take();
        
    }else if (buttonPressed == look){
        myGame.look();
        
    }else if (buttonPressed == pouch){
        myGame.list();
        
    }else if (buttonPressed == help){
        myGame.help();   
        
     }else if (buttonPressed == eat){
         String toEat = JOptionPane.showInputDialog(null, "What do you want to eat?");
         if (toEat == null)
             return;
         myGame.eat(toEat);  
       
    // }else if (buttonPressed == speak){
    //     myGame.speak();

    //}else if (buttonPressed == retreat){
    //    myGame.retreat();     
    
    }else if (buttonPressed == shrink){
        myGame.shrink();   
        
    }else if (buttonPressed == drop){
        String toDrop = JOptionPane.showInputDialog(null, "What do you want to drop?");
        if (toDrop == null)
            return;
        myGame.drop(toDrop);    
    }
    
    results.append("\n\n" + myGame.getMessage());   
    
    if (myGame.gameOver()){
        results.append("\n\n" + myGame.getMessage());
        gameOver();
    }


}

}