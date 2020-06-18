import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Major elements of a GUI program
 * 1. JFrame: The JFrame object that you instantiate is the actual window that you see on the screen.  All the buttons,
 *              text fields, labels, etc that you create get put into the JFrame
 * 2. JButtons, JLabels, and JTextFields: These are the different elements that you see on the screen.  They are what people
 *              click on and type on in order to interact with your program.  There are a lot of other options that you can use, but
 *              you will need to go look those up.
 * 3. GridBagLayout: This is the Layout manager for our program.  You have a few different choices for geometry/layout handling and we 
 *              will be using the GridBagLayout manager.  This is the thing that controls how things are placed in the JFrame.
 * 4. GridBagConstraints: This is a class that allows us to configure the aspects of each item that we place into the JFrame.  For each
 *              JButton, JLabel, etc that you want to put into the JFrame, we have to configure things like how wide it is, how tall it is, what cell to put it in, and
 *              if you want it to stretch to the edges of its cell.  There are a ton of things you can configure, and you do so before adding the Button or whatever into the JFrame.
 *              You can reuse one instance of the GridBagConstraints class over and over when adding elements to a JFrame, but be careful to change all the parameters of the constraints
 *              object because it will keep whatever you set from the last time you used it.  Once all the aspects for the constraints are set, you use the constraints object
 *              and the Button or whatever as input simultaneously when adding the button to the JFrame.
 * 5. Event Handling: Okay, so this is a big concept in modern programming.  We need our program to take some kind of action when buttons are clicked or text is entered in a text
 *              field, or radio buttons are checked, or whatever.  In order to do this, we must implement the ActionListener interface.  The Action Listener interface has one method
 *              called actionPerformed.  actionPerformed is the handler function for when an ActionEvent is created by the operating system.  When the operating system creates an ActionEvent
 *              object, the listener (we will get to that in a second) grabs the ActionEvent and feeds it to your actionPerformed method.  At that point, it is up to you to write code in
 *              the actionPerformed method that "handles" the event.  
 *              Here is how we accomplish this.
 *                  a. After you create a button, text field, whatever, use the setActionCommand method inside the button object in order to set the string that will be assigned to the events
 *                      ActionCommand variable inside the event object.  This is just a String variable that is inside the event, and you can use the event objects getter method for the
 *                      variable to read it in your event handler method.  Each button, text field, radio button, whatever, should set the variable to a different string, so that you can
 *                      figure out later which button was clicked, text field was typed on, check box was checked, or whatever.  This string is how we know what element on the screen was
 *                      interacted with, and what we are supposed to do about it.  And you can make up anything you want for the string, its just an identifier for yourself that you use
 *                      to tell all the buttons apart when events are created by the operating system.
 *                  b. Next, use the addActionListener method that is part of the button object, or whatever your making, to create a listener for the button.  You have to assign the 
 *                      listener to a specific window on the screen though.  Since your button is being put inside our JFrame window, we will attach the listener to the JFrame window object
 *                      that we are creating.  We use the keyword "this" as input in order to attach the listener for our button to the window created by this specific instance of the
 *                      GraphicsExample class.  This means that the listener will now listen to the current JFrame window for anyone clicking on our new button.  When the listener 
 *                      hears someone clicking on our button in our JFrame window, it will grab the event object from the operating system and pass it to our event handler function
 *                      which is called actionPerformed.
 *                  c. Handle the event.  So, now that someone has clicked on our button, and the event has been grabbed by the listener we created and passed to the actionPerformed method,
 *                      what do we do with it now?  Well, thats up to you!  The event object has a variable in it called ActionCommand that you set earlier when you called the setActionCommand
 *                      method from our button.  The event object also has a getter for that variable called getActionCommand, so just use the getter to find out what is in the 
 *                      ActionCommand variable.  Compare the value of the ActionCommand variable to the strings that you set with your buttons, and figure out which button was pushed; then
 *                      do whatever it is that you want to do as a result.  
 * 
 *                      It is important to note that you should always have only one handler function for any single type of event.  If we
 *                      have multiple handlers it creates synchronization problems between the different handlers.  Due to CPU scheduling, it becomes very difficult to tell which handler 
 *                      will do what in what order as they both try to run and handle the same event at the same time; this can cause lots of problems.  In short, always have one handler
 *                      for each type of event that can be generated, and then use if-else in order to identify which button you are handling.
 * @author Shree Murthy
 *
 */

/**
 * Write a description of class ListGui here.
 *
 * @author (Shree Murthy)
 * @version (1.0)
 */
public class ListGUI extends Name implements ActionListener
{
    // instance variables - replace the example below with your own
    public JFrame initial;
    private JButton newWindow;
    private JButton saveName;
    private JButton retriveList;
    public JTextField introLabel;
    String saveNameFromTextField;
    ArrayList<String> names = new ArrayList<String>();
    
    public ListGUI(){
        initial = new JFrame("Welcome to your Shopping List");
        initial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initial.setSize(500,500);
        initial.getContentPane().setLayout(new GridBagLayout());
        
        //New Window Button that creates a new List window after the button is clicked.
        newWindow = new JButton("New List");
        newWindow.setActionCommand("New Window");
        newWindow.addActionListener(this);
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.weightx=3;         // Determines the weighting for how much space this object expands to fill relative to the other objects on the screen
        cons.gridwidth=5;       // This determines how many cells the object spans
        cons.gridx=1;
        cons.gridy=4;
        initial.getContentPane().add(newWindow, cons);
        newWindow.setVisible(false);
        
        //Button to save name
        saveName = new JButton("Save Name");
        saveName.setActionCommand("Save Name");
        saveName.addActionListener(this);
        cons.weightx=3;         // Determines the weighting for how much space this object expands to fill relative to the other objects on the screen
        cons.gridwidth=3;       // This determines how many cells the object spans
        cons.gridx=0;
        cons.gridy=0;
        initial.getContentPane().add(saveName, cons);
        
        //Text field to save name
        introLabel = new JTextField("");
        cons.weightx=3;         // Determines the weighting for how much space this object expands to fill relative to the other objects on the screen
        cons.gridwidth=4;       // This determines how many cells the object spans
        cons.gridx=1;
        cons.gridy=1;
        initial.getContentPane().add(introLabel, cons);
    
        initial.pack();
        initial.setVisible(true);
    }
    //A method to return the name inputed by the user
    public String getName(){
        String n = "";
        if(introLabel.getText().equals(""))
        {
        }
        else{ 
            names.add(introLabel.getText());
            for(int i = 0; i < names.size();i++){
                n+=names.get(i);
                
                
            }
        }
        return n;    
    }
    
    public void actionPerformed(ActionEvent myEvent){
 
       if(myEvent.getActionCommand().equals("New Window")){
           initial.dispose(); 
           //Creates new JFrame with the input of the person's name...helps create a new file in the new JFrame
           new secondWindowGUI(getName());
            

        }
         
       if(myEvent.getActionCommand().equals("Save Name")){
            newWindow.setVisible(true);
            
            saveName.setVisible(false);
            introLabel.setVisible(false);
        }

    }
    
}
