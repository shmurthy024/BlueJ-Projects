import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.*;
import java.util.List; 
/**
 * Write a description of class secondWindowGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class secondWindowGUI extends ListGUI implements ActionListener

{
    // instance variables - replace the example below with your own
    private JFrame master;
    private JButton addList;
    private JButton done;
    private JButton printList;
    private JTextField item;
    ArrayList<String> list = new ArrayList<String>();
    JList<String> displayList;
    String i;
    String getName;
    public secondWindowGUI(String Name){ 
        //this line closes the window before this...there used to be a bug where the preivous window wouldn't close. 
        initial.dispose();
        getName = Name;
        
        master = new JFrame("Shopping List");
        master.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        master.setSize(500,500);
        master.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        
        //TextField for entering items:
        item = new JTextField("Enter your Item", 10);
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridwidth =1; 
        cons.gridx = 1;
        cons.gridy =1;
        master.getContentPane().add(item, cons);
        
        //addList Button
        addList = new JButton("Add To List");
        cons.fill = GridBagConstraints.HORIZONTAL;
        addList.addActionListener(this);
        addList.setActionCommand("add");
        cons.gridwidth =1;
        cons.gridx = 1;
        cons.gridy = 2;
        master.getContentPane().add(addList, cons);
        
        //done button
        done = new JButton("Done");
        done.setActionCommand("done");
        done.addActionListener(this);
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridwidth =1;
        cons.gridx = 1;
        cons.gridy = 3;
        master.getContentPane().add(done, cons);
        
        //pack the window
        master.pack();
        master.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        i = item.getText();
        
        if(e.getActionCommand().equals("add")){
            list.add(i);
            item.setText("Enter item");
        }
        if(e.getActionCommand().equals("done")){
            
            //Try catch system used to avoid error in the method signature
            try{
                String userHomeFolder = System.getProperty("user.home");
                File file = new File(userHomeFolder,getName+".txt");
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                for(String n : list){
                    writer.write(n);
                    writer.write("\n");
                }
                writer.flush();
                writer.close();
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            
            }
            catch(IOException f){
                f.printStackTrace();
            }
            System.exit(2);
        }
    }
        
}

