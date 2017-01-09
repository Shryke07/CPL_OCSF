package ocsftester;




//This file contains material supporting section 10.9 of the textbook:
//"Object Oriented Software Engineering" and is issued under the open-source
//license found at www.lloseng.com

/*
* ClientFrame.java   2001-02-08
*
* Copyright (c) 2001 Robert Laganiere and Timothy C. Lethbridge.
* All Rights Reserved.
*
*/














//http://tsvetan-stoyanov.github.io/launchpi/
//https://raw.githubusercontent.com/tsvetan-stoyanov/launchpi/master/org.launchpi.us/target/site/site.xml
import java.awt.*;
import java.awt.event.*;

//import ocsf.client.*;
//import ocsftester.ClientFrame.TimedStuff.Dispensing;

import javax.swing.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.wb.swing.FocusTraversalOnArray;

//import java.awt.Toolkit;







/**
* The <code> ClientFrame </code> class is a simple interactive
* application made to exercise the OCSF framework.<p>
* Type <code>java ocsftester.ClientFrame host port_number</code> to start
* one client.<p>
* The window is
* pink when the connection has been closed, red
* when an exception is received,
* and green when connected to the server.
*
* @author Dr. Robert Lagani&egrave;re
* @version February 2001
* @see ocsftester.SimpleClient
*/
public class ClientFrame extends Frame
{
/**
	 * Just a note
	 */
	private static final long serialVersionUID = 1L;
	
	 static String IP = "129.82.38.224" ;
	
private JButton closeB =     new JButton("Close");
private JButton openB =      new JButton("Open");
private JButton sendB =      new JButton("Send");
private JButton quitB =      new JButton("Quit");
private TextField port =    new TextField("11111");
// private TextField host =    new TextField("localhost");
//private TextField host =    new TextField("129.82.175.224"); // use for WiFi
private TextField host =    new TextField(IP); // Ethernet
private Label portLB =      new Label("Port: ", Label.RIGHT);
private Label hostLB =      new Label("Host: ", Label.RIGHT);
private List liste =        new List();
private SimpleClient client;
private final TextField pdNum = new TextField();
private final JLabel lblNewLabel = new JLabel("Pellet Dispenser Number");
private final JLabel lblFeedHour = new JLabel("Feed Hour 1");
private final JLabel lblFeedHour_1 = new JLabel("Feed Hour 2");
private final TextField feedH1 = new TextField();
private final TextField feedH2 = new TextField();
private final JButton btnDispense = new JButton("Dispense");
private final TextField dispnum = new TextField();
private final JButton btnReadWeights = new JButton("Read Excel");
private final JButton btnOverride = new JButton("Override");
private final JRadioButton rdbtnNewRadioButton = new JRadioButton("Pi 1");
private final JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Pi 2");
private final JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Pi 3");
private final JButton btnCancel = new JButton("Cancel");

//public double[] PWS = new double[30] ; 
//public double PW0,  PW1 , PW2 , PW3 , PW4 , PW5 , PW6 , PW7 , PW8 , PW9 , PW10 , PW11 , PW12 , PW13 , PW14 , PW15 , PW16 , PW17 , PW18 , PW19 , PW20 , 
// 			  PW21 , PW22 , PW23 , PW24 , PW25 , PW26 , PW27 , PW28 , PW29 ;
int[] pellets = new int[30] ;
int piNum	= 1 ;
Date date;
String hourOut;
DateFormat dateFormatter;
int hr ;
int r = 1 ; 
boolean t = false ; 
Date log ;
String logdate;
DateFormat dateFormatter1;







public ClientFrame(String h, int p)
{
 super("OCSF Client");

 
 
 
 addWindowListener(new WindowAdapter() {
   public void windowClosing(WindowEvent e)
   {
     quit();
   }
 });

 Panel bottom = new Panel();
         GridBagLayout gbl_bottom = new GridBagLayout();
         gbl_bottom.columnWidths = new int[] {54, 79, 79, 97, 30};
         gbl_bottom.rowHeights = new int[]{30, 0, 0, 0, 23, 0, 35, 23, 35, 0, 0, 23, 35, 23, 0, 0};
         gbl_bottom.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0};
         gbl_bottom.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
         bottom.setLayout(gbl_bottom);
                     
                     GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
                     gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
                     gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
                     gbc_lblNewLabel.gridx = 2;
                     gbc_lblNewLabel.gridy = 0;
                     bottom.add(lblNewLabel, gbc_lblNewLabel);
                     pdNum.setText("1");
                     pdNum.setColumns(10);
                     
                     GridBagConstraints gbc_pdNum = new GridBagConstraints();
                     gbc_pdNum.fill = GridBagConstraints.HORIZONTAL;
                     gbc_pdNum.anchor = GridBagConstraints.NORTH;
                     gbc_pdNum.insets = new Insets(0, 0, 5, 5);
                     gbc_pdNum.gridx = 3;
                     gbc_pdNum.gridy = 0;
                     bottom.add(pdNum, gbc_pdNum);
                     
                     GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
                     gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
                     gbc_rdbtnNewRadioButton.gridx = 3;
                     gbc_rdbtnNewRadioButton.gridy = 2;
                     rdbtnNewRadioButton.setSelected(true);
                     bottom.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
                     rdbtnNewRadioButton.addActionListener(new ActionListener() {
                         public void actionPerformed(ActionEvent e)
                         {
                          piNum = 1 ; 
                          port.setText("11111");
                         }
                       });
                     
                     GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
                     gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
                     gbc_rdbtnNewRadioButton_1.gridx = 3;
                     gbc_rdbtnNewRadioButton_1.gridy = 3;
                     bottom.add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
                     
                     rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
                         public void actionPerformed(ActionEvent e)
                         {
                        	 piNum = 2 ;
                        	 port.setText("22222");
                         }
                       });
                 
                   
                     GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
                     gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 5);
                     gbc_rdbtnNewRadioButton_2.gridx = 3;
                     gbc_rdbtnNewRadioButton_2.gridy = 4;
                     bottom.add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);
                     
                     rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
                         public void actionPerformed(ActionEvent e)
                         {
                        	 piNum = 3 ; 
                        	 port.setText("33333");
                         }
                       });
                     
                     ButtonGroup group = new ButtonGroup();
                     group.add(rdbtnNewRadioButton);
                     group.add(rdbtnNewRadioButton_1);
                     group.add(rdbtnNewRadioButton_2);
                     
                     
                  
                     GridBagConstraints gbc_btnDispense = new GridBagConstraints();
                     gbc_btnDispense.fill = GridBagConstraints.HORIZONTAL;
                     gbc_btnDispense.insets = new Insets(0, 0, 5, 5);
                     gbc_btnDispense.gridx = 2;
                     gbc_btnDispense.gridy = 8;
                     btnDispense.addActionListener(new ActionListener() {
                     	public void actionPerformed(ActionEvent arg0) {
                     		
                     		dispense();
                     		
                     		
                     	}
                     });
                     
                   
                     GridBagConstraints gbc_lblFeedHour = new GridBagConstraints();
                     gbc_lblFeedHour.anchor = GridBagConstraints.EAST;
                     gbc_lblFeedHour.insets = new Insets(0, 0, 5, 5);
                     gbc_lblFeedHour.gridx = 2;
                     gbc_lblFeedHour.gridy = 6;
                     bottom.add(lblFeedHour, gbc_lblFeedHour);
                     feedH1.setText("0");
                     feedH1.setColumns(10);
                     
                     GridBagConstraints gbc_feedH1 = new GridBagConstraints();
                     gbc_feedH1.insets = new Insets(0, 0, 5, 5);
                     gbc_feedH1.fill = GridBagConstraints.HORIZONTAL;
                     gbc_feedH1.gridx = 3;
                     gbc_feedH1.gridy = 6;
                     bottom.add(feedH1, gbc_feedH1);
                     
                     GridBagConstraints gbc_lblFeedHour_1 = new GridBagConstraints();
                     gbc_lblFeedHour_1.anchor = GridBagConstraints.EAST;
                     gbc_lblFeedHour_1.insets = new Insets(0, 0, 5, 5);
                     gbc_lblFeedHour_1.gridx = 2;
                     gbc_lblFeedHour_1.gridy = 7;
                     bottom.add(lblFeedHour_1, gbc_lblFeedHour_1);
                     feedH2.setText("0");
                     feedH2.setColumns(10);
                     
                     GridBagConstraints gbc_feedH2 = new GridBagConstraints();
                     gbc_feedH2.insets = new Insets(0, 0, 5, 5);
                     gbc_feedH2.fill = GridBagConstraints.HORIZONTAL;
                     gbc_feedH2.gridx = 3;
                     gbc_feedH2.gridy = 7;
                     bottom.add(feedH2, gbc_feedH2);
                     bottom.add(btnDispense, gbc_btnDispense);
                     dispnum.setText("0");
                     dispnum.setColumns(10);
                     
                     GridBagConstraints gbc_dispnum = new GridBagConstraints();
                     gbc_dispnum.insets = new Insets(0, 0, 5, 5);
                     gbc_dispnum.fill = GridBagConstraints.HORIZONTAL;
                     gbc_dispnum.gridx = 3;
                     gbc_dispnum.gridy = 8;
                     bottom.add(dispnum, gbc_dispnum);
                     
                     
                     client = new SimpleClient(h, p, liste);
                     GridBagConstraints gbc_liste = new GridBagConstraints();
                     gbc_liste.fill = GridBagConstraints.HORIZONTAL;
                     gbc_liste.gridwidth = 4;
                     gbc_liste.insets = new Insets(0, 0, 5, 5);
                     gbc_liste.gridx = 0;
                     gbc_liste.gridy = 9;
                     bottom.add(liste, gbc_liste);
                 GridBagConstraints gbc_hostLB = new GridBagConstraints();
                 gbc_hostLB.fill = GridBagConstraints.VERTICAL;
                 gbc_hostLB.insets = new Insets(0, 0, 5, 5);
                 gbc_hostLB.gridx = 0;
                 gbc_hostLB.gridy = 10;
                 bottom.add(hostLB, gbc_hostLB);
                 GridBagConstraints gbc_host = new GridBagConstraints();
                 gbc_host.fill = GridBagConstraints.BOTH;
                 gbc_host.insets = new Insets(0, 0, 5, 5);
                 gbc_host.gridx = 1;
                 gbc_host.gridy = 10;
                 bottom.add(host, gbc_host);
                 GridBagConstraints gbc_portLB = new GridBagConstraints();
                 gbc_portLB.fill = GridBagConstraints.VERTICAL;
                 gbc_portLB.insets = new Insets(0, 0, 5, 5);
                 gbc_portLB.gridx = 0;
                 gbc_portLB.gridy = 11;
                 bottom.add(portLB, gbc_portLB);
                     
                         openB.addActionListener(new ActionListener() {
                           public void actionPerformed(ActionEvent e)
                           {
                             open();
                             
                           }
                         });
                         port.setText(String.valueOf(p));
                         GridBagConstraints gbc_port = new GridBagConstraints();
                         gbc_port.fill = GridBagConstraints.BOTH;
                         gbc_port.insets = new Insets(0, 0, 5, 5);
                         gbc_port.gridx = 1;
                         gbc_port.gridy = 11;
                         bottom.add(port, gbc_port);
                         
                         
                         btnReadWeights.addActionListener(new ActionListener() {
                             public void actionPerformed(ActionEvent e)
                             {
                               read () ; 
                               System.out.println(" Data has been successfully read");
                             }
                           });                      
                         GridBagConstraints gbc_btnReadWeights = new GridBagConstraints();
                         gbc_btnReadWeights.fill = GridBagConstraints.HORIZONTAL;
                         gbc_btnReadWeights.insets = new Insets(0, 0, 5, 5);
                         gbc_btnReadWeights.gridx = 2;
                         gbc_btnReadWeights.gridy = 12;
                         bottom.add(btnReadWeights, gbc_btnReadWeights);
                         
                             sendB.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e)
                               {
                            	
                                send(); // use to pair with feed hr
                              //  TimedStuff cobj = new TimedStuff() ;
                              //  cobj.timeOut();
                               }
                             });
                             GridBagConstraints gbc_sendB = new GridBagConstraints();
                             gbc_sendB.fill = GridBagConstraints.HORIZONTAL;
                             gbc_sendB.insets = new Insets(0, 0, 5, 5);
                             gbc_sendB.gridx = 3;
                             gbc_sendB.gridy = 12;
                             bottom.add(sendB, gbc_sendB);
                             setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{bottom, lblNewLabel, pdNum, lblFeedHour, feedH1, lblFeedHour_1, feedH2, rdbtnNewRadioButton, rdbtnNewRadioButton_1, rdbtnNewRadioButton_2, liste, hostLB, host, portLB, port, btnReadWeights, sendB, openB, btnDispense, dispnum, closeB, quitB}));
                         GridBagConstraints gbc_openB = new GridBagConstraints();
                         gbc_openB.fill = GridBagConstraints.BOTH;
                         gbc_openB.insets = new Insets(0, 0, 5, 5);
                         gbc_openB.gridx = 0;
                         gbc_openB.gridy = 13;
                         bottom.add(openB, gbc_openB);

 setLayout(new BorderLayout(5,5));
 add("South", bottom);
                 
                     closeB.addActionListener(new ActionListener() {
                       public void actionPerformed(ActionEvent e)
                       {
                         close();
                       }
                     });
                     
                     GridBagConstraints gbc_btnOverride = new GridBagConstraints();
                     gbc_btnOverride.fill = GridBagConstraints.HORIZONTAL;
                     gbc_btnOverride.insets = new Insets(0, 0, 5, 5);
                     gbc_btnOverride.gridx = 2;
                     gbc_btnOverride.gridy = 13;
                     bottom.add(btnOverride, gbc_btnOverride);
                     btnOverride.addActionListener(new ActionListener() {
                         public void actionPerformed(ActionEvent e)
                         {
                           sendNow();
                          
                           //checker cobj = new checker() ;
                          // cobj.timeOut();
                         }
                       });
                     
                     GridBagConstraints gbc_btnCancel = new GridBagConstraints();
                     gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
                     gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
                     gbc_btnCancel.gridx = 3;
                     gbc_btnCancel.gridy = 13;
                     bottom.add(btnCancel, gbc_btnCancel);
                  btnCancel.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e)
                         {
                        	 TimedStuff timeobj = new TimedStuff() ;
                       	 
                        	 if (t == true){
                       		 timeobj.t2cancel();
                        		 sendB.setEnabled(true);
                        	 }else{
                       	 liste.add( "No data has been sent! ");
                                   }
                         }
                       });
                                      
                     
                     GridBagConstraints gbc_closeB = new GridBagConstraints();
                     gbc_closeB.fill = GridBagConstraints.BOTH;
                     gbc_closeB.insets = new Insets(0, 0, 0, 5);
                     gbc_closeB.gridx = 0;
                     gbc_closeB.gridy = 14;
                     bottom.add(closeB, gbc_closeB);
                     
                     
                   
                    
                     quitB.addActionListener(new ActionListener() {
                       public void actionPerformed(ActionEvent e)
                       {
                         quit();
                       }
                     });
                     GridBagConstraints gbc_quitB = new GridBagConstraints();
                     gbc_quitB.insets = new Insets(0, 0, 0, 5);
                     gbc_quitB.fill = GridBagConstraints.BOTH;
                     gbc_quitB.gridx = 1;
                     gbc_quitB.gridy = 14;
                     bottom.add(quitB, gbc_quitB);         
                   
                   
 setSize(700,500);
 setVisible(true);
} 
public int getCurrentHR (){
	
	Calendar calendar = Calendar.getInstance();
	int hours = calendar.get(Calendar.HOUR_OF_DAY) - 12;
	System.out.println(hours );
return hours ; 
}
public String  logDate(){
	
	 DateFormat dateFormatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    log = new Date();
	    logdate = dateFormatter1.format(log);  
	    return logdate ;
}



private void readFields()
{
 int p = Integer.parseInt(port.getText());

 client.setPort(p);
 client.setHost(host.getText());
 
}

private  int[] read()
{
	 
	
	excel readobj = new excel() ;
	
	 
	 int [] pellets =  readobj.reader();
	 
	// int i ;
//	 for(i= 0; i<30; i++){
	// System.out.println(pellets[i] + " " + i) ; 
	// }	
	 return pellets ; 
}



public class TimedStuff{
 
	int c =  0 ; 
  Timer timer1;
  Timer timer2;
//  public void timeOut ()
//  {
//	  timer = new Timer();
//	  timer.schedule( new BlockSignal(), );
// }
  public void t2cancel (){
	  timer2.cancel() ; 
  }
public void dispblocker(){
	try {
		//client.sendToServer("Dispensing... ");
		timer1 = new Timer();
		timer1.schedule(new Dispensing(), 10 , 1*500);
	} catch (Exception ex) {	
		   liste.add(ex.toString());
		   liste.makeVisible(liste.getItemCount()-1);
		   liste.setBackground(Color.yellow);
	}

	 
}
  public void  checking() {
   
    timer2 = new Timer();
    timer2.schedule(new RemindTask(), 1*1000, 1*60*1000);
  }
 public class Dispensing extends TimerTask {
	 
	    public void run() {
	    	
	     try{
	    	if(c < Integer.parseInt(dispnum.getText())){
	    	c++; 
	    	btnDispense.setEnabled(false);
	    	
	    	}else{
	    		 c = 0 ; 
	    		 btnDispense.setEnabled(true);
	    		 timer1.cancel();
	    		 
	    	}
	    
	    	
	    }catch (Exception ex)
	    		 {
	    		 liste.add(ex.toString());
	    		 liste.makeVisible(liste.getItemCount()-1);
	  		   	 liste.setBackground(Color.yellow);
	  	
	    		 }
	    }
	    public int getcval(){
			  return c ;
		 }
	  }

 public class RemindTask extends TimerTask {
    public void run() {
     try{
    	 
    	if (getCurrentHR()  == Integer.parseInt(feedH1.getText()) || getCurrentHR()  == Integer.parseInt(feedH2.getText()) ){
    		   writeToLog( pellets ) ;
    		   client.sendToServer("EXCEL" + " " + piNum + " " + pellets[0] + " "  +  pellets[1] + " " +  pellets[2] + " " +  pellets[3] + " " +  pellets[4] + " " +  pellets[5] + " " +  pellets[6] + " " +  pellets[7] + " " +  pellets[8] + " " +  pellets[9] + " " +  pellets[10] + " " +  pellets[11] + " " +  pellets[12] + " " +  pellets[13] + " " +  pellets[14] + " " +  pellets[15] + " " +  pellets[16] + " " +  pellets[17] + " " +  pellets[18] + " " +  pellets[19] + " " +  pellets[20] + " " +  pellets[21] + " " +  pellets[22] + " " +  pellets[23] + " " +  pellets[24] + " " +  pellets[25] + " " +  pellets[26] + " " +  pellets[27] + " " +  pellets[28] + " " +  pellets[29] );
    		   timer2.cancel(); 
    		   
    		  
    		 }else {
    			 liste.add("Timer is ticking...    ") ; 
    			 t = true ;
    		 }
    			
    	}catch (Exception ex)
    		 {
    			   liste.add(ex.toString());
    			   liste.makeVisible(liste.getItemCount()-1);
    			   liste.setBackground(Color.yellow);
    		 }
    }
  }

}

private void writeToLog( int[] info )
{
	
	String d =  logDate() ;
	excel writeobj = new excel() ;
	
	 writeobj.writer(d ,info , r) ; 
	 
	 r ++ ; 
	
	 
			
}

public void close()
{
 try {
   readFields();
   client.closeConnection();
 }
 catch (Exception ex)
 {
   liste.add(ex.toString());
   liste.makeVisible(liste.getItemCount()-1);
   liste.setBackground(Color.red);
 }
}

public void open()
{
 try {
   readFields();
   client.openConnection();
 
 }
 catch (Exception ex)
 {
   liste.add(ex.toString());
   liste.makeVisible(liste.getItemCount()-1);
   liste.setBackground(Color.red);
 }
}


public void sendNow()
{
	try {
	
		read();
		readFields();
		//writeToLog( pellets ) ;
		client.sendToServer("EXCEL" + " " + piNum + " " + pellets[0] + " "  +  pellets[1] + " " +  pellets[2] + " " +  pellets[3] + " " +  pellets[4] + " " +  pellets[5] + " " +  pellets[6] + " " +  pellets[7] + " " +  pellets[8] + " " +  pellets[9] + " " +  pellets[10] + " " +  pellets[11] + " " +  pellets[12] + " " +  pellets[13] + " " +  pellets[14] + " " +  pellets[15] + " " +  pellets[16] + " " +  pellets[17] + " " +  pellets[18] + " " +  pellets[19] + " " +  pellets[20] + " " +  pellets[21] + " " +  pellets[22] + " " +  pellets[23] + " " +  pellets[24] + " " +  pellets[25] + " " +  pellets[26] + " " +  pellets[27] + " " +  pellets[28] + " " +  pellets[29] );
		
	}catch (Exception ex)
	 {
		   liste.add(ex.toString());
		   liste.makeVisible(liste.getItemCount()-1);
		   liste.setBackground(Color.yellow);
		 }
		}


public void send()
{
 try {
   readFields();
   sendB.setEnabled(false);
  
  // client.sendToServer(message.getText());
  // client.sendToServer(pdNum.getText() + " " + feedH1.getText() + " " + feedAmt1.getText() + " " + feedH2.getText() + " " + feedAmt2.getText() );
  if (getCurrentHR() == Integer.parseInt(feedH1.getText()) || getCurrentHR() == Integer.parseInt(feedH2.getText()) ){
	  writeToLog( pellets ) ;
   client.sendToServer("EXCEL" + " " + piNum + " " + pellets[0] + " "  +  pellets[1] + " " +  pellets[2] + " " +  pellets[3] + " " +  pellets[4] + " " +  pellets[5] + " " +  pellets[6] + " " +  pellets[7] + " " +  pellets[8] + " " +  pellets[9] + " " +  pellets[10] + " " +  pellets[11] + " " +  pellets[12] + " " +  pellets[13] + " " +  pellets[14] + " " +  pellets[15] + " " +  pellets[16] + " " +  pellets[17] + " " +  pellets[18] + " " +  pellets[19] + " " +  pellets[20] + " " +  pellets[21] + " " +  pellets[22] + " " +  pellets[23] + " " +  pellets[24] + " " +  pellets[25] + " " +  pellets[26] + " " +  pellets[27] + " " +  pellets[28] + " " +  pellets[29] );
   
 }else{
	 TimedStuff cobj = new TimedStuff() ;
	 cobj.checking();
	
  }
 }
 catch (Exception ex)
 {
   liste.add(ex.toString());
   liste.makeVisible(liste.getItemCount()-1);
   liste.setBackground(Color.yellow);
 }
}


    public void CancelDisp(){
        
    	try {
			client.sendToServer("Cancel_Dispense") ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }



public void dispense()
{
 try {
 
 
   readFields();
  // client.sendToServer(message.getText());
 
   client.sendToServer(pdNum.getText() + " " + "disp" + " " + dispnum.getText()) ;
   TimedStuff timedobj = new TimedStuff() ;
   	timedobj.dispblocker();

 }
 catch (Exception ex)
 {
   liste.add(ex.toString());
   liste.makeVisible(liste.getItemCount()-1);
   liste.setBackground(Color.yellow);
 }
}

public void quit()
{
 System.exit(0);
}

/**
* Starts the client. The default host is localhost.
* The default port is 12345.
*/
public static void main(String[] arg)
{
 ClientFrame sf;
 if (arg.length==0)
 // sf = new ClientFrame("129.82.175.195",55555); //WiFi
	sf = new ClientFrame(IP,55555);  //Ethernet
 if (arg.length==1)
  sf = new ClientFrame("localhost",Integer.parseInt(arg[0]));
 if (arg.length==2)
  sf = new ClientFrame(arg[0],Integer.parseInt(arg[1]));
}

	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
