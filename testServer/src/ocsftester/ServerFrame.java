// This file contains material supporting section 10.9 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

/*
 * ServerFrame.java   2001-02-08
 *
 * Copyright (c) 2001 Robert Laganiere and Timothy C. Lethbridge.
 * All Rights Reserved.
 *
 */
package ocsftester;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.Date;
import java.util.TimerTask;

import ocsf.server.*;


import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.system.NetworkInfo;

import javax.swing.JLabel;
import javax.swing.JEditorPane;

//import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JComponent ;
import javax.swing.SwingConstants; 

/**
* The <code> ServerFrame </code> class is a simple interactive
* application made to exercise the OCSF framework.<p>
* Type <code>java ocsftester.ServerFrame port_number</code> to start
* the server.<p>
* The window is red
* when the server is closed, yellow when the server is stopped
* and green when open.
*
* @author Dr. Robert Lagani&egrave;re
* @version February 2001
* @see ocsftester.SimpleServer
*/
public class ServerFrame extends Frame
{
  /**
	 * 
	 */

	private static final long serialVersionUID = 2L;
  private Button closeB =     new Button("Close");
  private Button listenB =    new Button("Listen");
  private Button stopB =      new Button("Stop");
  private Button quitB =      new Button("Quit");
  private TextField port =    new TextField("55555");
  private TextField backlog = new TextField("5");
  private TextField timeout = new TextField("500");
  private Label portLB =      new Label("Port: ", Label.RIGHT);
  private Label timeoutLB =   new Label("Timeout: ", Label.RIGHT);
  private Label backlogLB =   new Label("Backlog: ", Label.RIGHT);
  private List liste =        new List();
  private SimpleServer server;
  private final JLabel lblTime = new JLabel("Time:");
 
  private final JLabel label = new JLabel("");
  private final JLabel label_1 = new JLabel("");
  private final JLabel label_2 = new JLabel("");
  private final JLabel label_3 = new JLabel("");
  private final JLabel label_4 = new JLabel("");
  private final JLabel label_5 = new JLabel("");
  private final JLabel lblIp = new JLabel("IP:");
  private final JTextArea TimeDisp = new JTextArea("");
  private final JTextArea IP_TXT = new JTextArea("");
  int c = 0 ; 
  	Timer timer;
  	Date today;
 	String dateOut;
 	DateFormat dateFormatter;
 	
   // long delay = 1000 ;
   // long repeat = 1*1000 ; 

  
  public ServerFrame(int p)
  {
    super("OCSF Server");
    IP_TXT.setColumns(10);
    liste.add("Test Server");
    server = new SimpleServer(p, liste);
    getIP();
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e)
      {
    	
        quit();
      }
    });

    Panel bottom = new Panel();
            GridBagLayout gbl_bottom = new GridBagLayout();
            gbl_bottom.columnWidths = new int[] {117, 117, 50, 184, 0};
            gbl_bottom.rowHeights = new int[]{23, 23, 23, 23, 23, 0};
            gbl_bottom.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
            gbl_bottom.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
            bottom.setLayout(gbl_bottom);
            GridBagConstraints gbc_portLB = new GridBagConstraints();
            gbc_portLB.fill = GridBagConstraints.BOTH;
            gbc_portLB.insets = new Insets(0, 0, 5, 5);
            gbc_portLB.gridx = 0;
            gbc_portLB.gridy = 0;
            bottom.add(portLB, gbc_portLB);
            
                stopB.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e)
                  {
                    stop();
                  }
                });
                
                    listenB.addActionListener(new ActionListener() {
                      public void actionPerformed(ActionEvent e)
                      {
                        listen();
                        addTime() ; 
                       
                      }
                    });
                    port.setText(String.valueOf(p));
                    GridBagConstraints gbc_port = new GridBagConstraints();
                    gbc_port.fill = GridBagConstraints.BOTH;
                    gbc_port.insets = new Insets(0, 0, 5, 5);
                    gbc_port.gridx = 1;
                    gbc_port.gridy = 0;
                    bottom.add(port, gbc_port);
                    
                    GridBagConstraints gbc_label = new GridBagConstraints();
                    gbc_label.fill = GridBagConstraints.BOTH;
                    gbc_label.insets = new Insets(0, 0, 5, 5);
                    gbc_label.gridx = 2;
                    gbc_label.gridy = 0;
                    bottom.add(label, gbc_label);
                    
          
           
                    
                    GridBagConstraints gbc_label_1 = new GridBagConstraints();
                    gbc_label_1.fill = GridBagConstraints.BOTH;
                    gbc_label_1.insets = new Insets(0, 0, 5, 0);
                    gbc_label_1.gridx = 3;
                    gbc_label_1.gridy = 0;
                    bottom.add(label_1, gbc_label_1);
                    
                    GridBagConstraints gbc_backlogLB = new GridBagConstraints();
                    gbc_backlogLB.fill = GridBagConstraints.BOTH;
                    gbc_backlogLB.insets = new Insets(0, 0, 5, 5);
                    gbc_backlogLB.gridx = 0;
                    gbc_backlogLB.gridy = 1;
                    bottom.add(backlogLB, gbc_backlogLB);
                    
                    GridBagConstraints gbc_backlog = new GridBagConstraints();
                    gbc_backlog.fill = GridBagConstraints.BOTH;
                    gbc_backlog.insets = new Insets(0, 0, 5, 5);
                    gbc_backlog.gridx = 1;
                    gbc_backlog.gridy = 1;
                    bottom.add(backlog, gbc_backlog);
                    
                    GridBagConstraints gbc_label_2 = new GridBagConstraints();
                    gbc_label_2.fill = GridBagConstraints.BOTH;
                    gbc_label_2.insets = new Insets(0, 0, 5, 5);
                    gbc_label_2.gridx = 2;
                    gbc_label_2.gridy = 1;
                    bottom.add(label_2, gbc_label_2);
                    
                    GridBagConstraints gbc_label_3 = new GridBagConstraints();
                    gbc_label_3.fill = GridBagConstraints.BOTH;
                    gbc_label_3.insets = new Insets(0, 0, 5, 0);
                    gbc_label_3.gridx = 3;
                    gbc_label_3.gridy = 1;
                    bottom.add(label_3, gbc_label_3);
                    
                    GridBagConstraints gbc_timeoutLB = new GridBagConstraints();
                    gbc_timeoutLB.fill = GridBagConstraints.BOTH;
                    gbc_timeoutLB.insets = new Insets(0, 0, 5, 5);
                    gbc_timeoutLB.gridx = 0;
                    gbc_timeoutLB.gridy = 2;
                    bottom.add(timeoutLB, gbc_timeoutLB);
                    
                    GridBagConstraints gbc_timeout = new GridBagConstraints();
                    gbc_timeout.fill = GridBagConstraints.BOTH;
                    gbc_timeout.insets = new Insets(0, 0, 5, 5);
                    gbc_timeout.gridx = 1;
                    gbc_timeout.gridy = 2;
                    bottom.add(timeout, gbc_timeout);
                    
                    GridBagConstraints gbc_label_4 = new GridBagConstraints();
                    gbc_label_4.fill = GridBagConstraints.BOTH;
                    gbc_label_4.insets = new Insets(0, 0, 5, 5);
                    gbc_label_4.gridx = 2;
                    gbc_label_4.gridy = 2;
                    bottom.add(label_4, gbc_label_4);
                    
                    GridBagConstraints gbc_label_5 = new GridBagConstraints();
                    gbc_label_5.fill = GridBagConstraints.BOTH;
                    gbc_label_5.insets = new Insets(0, 0, 5, 0);
                    gbc_label_5.gridx = 3;
                    gbc_label_5.gridy = 2;
                    bottom.add(label_5, gbc_label_5);
                    GridBagConstraints gbc_listenB = new GridBagConstraints();
                    gbc_listenB.fill = GridBagConstraints.BOTH;
                    gbc_listenB.insets = new Insets(0, 0, 5, 5);
                    gbc_listenB.gridx = 0;
                    gbc_listenB.gridy = 3;
                    bottom.add(listenB, gbc_listenB);
                GridBagConstraints gbc_stopB = new GridBagConstraints();
                gbc_stopB.fill = GridBagConstraints.BOTH;
                gbc_stopB.insets = new Insets(0, 0, 5, 5);
                gbc_stopB.gridx = 1;
                gbc_stopB.gridy = 3;
                bottom.add(stopB, gbc_stopB);
            
           
            GridBagConstraints gbc_lblTime = new GridBagConstraints();
            gbc_lblTime.fill = GridBagConstraints.VERTICAL;
            gbc_lblTime.insets = new Insets(0, 0, 5, 5);
            gbc_lblTime.gridx = 2;
            gbc_lblTime.gridy = 3;
            bottom.add(lblTime, gbc_lblTime);
            
            GridBagConstraints gbc_TimeDisp = new GridBagConstraints();
            gbc_TimeDisp.fill = GridBagConstraints.BOTH;
            gbc_TimeDisp.insets = new Insets(0, 0, 5, 0);
            gbc_TimeDisp.gridx = 3;
            gbc_TimeDisp.gridy = 3;
            bottom.add(TimeDisp, gbc_TimeDisp);
            TimeDisp.setEditable(false) ; 
            TimeDisp.setVisible(true) ; 
            
            
                closeB.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e)
                  {
                    close();
                  }
                });
                 quitB.addActionListener(new ActionListener() {
                      public void actionPerformed(ActionEvent e)
                      {
                    	
                        quit();
                      }
                    });
                    GridBagConstraints gbc_quitB = new GridBagConstraints();
                    gbc_quitB.fill = GridBagConstraints.BOTH;
                    gbc_quitB.insets = new Insets(0, 0, 0, 5);
                    gbc_quitB.gridx = 0;
                    gbc_quitB.gridy = 4;
                    bottom.add(quitB, gbc_quitB);
                GridBagConstraints gbc_closeB = new GridBagConstraints();
                gbc_closeB.fill = GridBagConstraints.BOTH;
                gbc_closeB.insets = new Insets(0, 0, 0, 5);
                gbc_closeB.gridx = 1;
                gbc_closeB.gridy = 4;
                bottom.add(closeB, gbc_closeB);
            
            GridBagConstraints gbc_lblIp = new GridBagConstraints();
            gbc_lblIp.anchor = GridBagConstraints.EAST;
            gbc_lblIp.fill = GridBagConstraints.VERTICAL;
            gbc_lblIp.insets = new Insets(0, 0, 0, 5);
            gbc_lblIp.gridx = 2;
            gbc_lblIp.gridy = 4;
            lblIp.setHorizontalAlignment(SwingConstants.CENTER);
            bottom.add(lblIp, gbc_lblIp);

    setLayout(new BorderLayout(5,5));
    add("Center", liste);
    add("South", bottom);
    
    GridBagConstraints gbc_IP_TXT = new GridBagConstraints();
    gbc_IP_TXT.fill = GridBagConstraints.HORIZONTAL;
    gbc_IP_TXT.gridx = 3;
    gbc_IP_TXT.gridy = 4;
    bottom.add(IP_TXT, gbc_IP_TXT);
    setSize(484,400);
    setVisible(true);
  }

  
 
private void addTime() 
{
	   timer = new Timer(true);
	    timer.schedule(new RemindTask(),
	                   1000,        //initial delay
	                   1*1000);  //subsequent rate
	    DateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    today = new Date();
	    dateOut = dateFormatter.format(today);   
}
	  class RemindTask extends TimerTask {
	      

	      
	      public void run() {
	         
	              //toolkit.beep();
	        	  DateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        	  today.setTime(System.currentTimeMillis());
	        	  dateOut = dateFormatter.format(today);
	        	  TimeDisp.replaceSelection(dateOut + "                    ");
	        	  c = 1 ;
	        	  
	             // toolkit.beep(); 
	        	 // liste.add("Time's up!%n");
	              //timer.cancel(); //Not necessary because
	                                //we call System.exit
	             // System.exit(0);   //Stops the AWT thread 
	                                //(and everything else)
	          }
	      }
	  
private void readFields()
  {
    int p = Integer.parseInt(port.getText());
    int t = Integer.parseInt(timeout.getText());
    int b = Integer.parseInt(backlog.getText());

    server.setPort(p);
    server.setBacklog(b);
    server.setTimeout(t);
   
  }
public void getIP()
{
 
	  try {
		for (String ipAddress : NetworkInfo.getIPAddresses())
			IP_TXT.replaceSelection(ipAddress);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

  public void close()
  {
    System.out.println("Number of clients = " + server.getNumberOfClients());
    try {
      readFields();
      timer.cancel(); 
      server.close();
      
    }
    catch (Exception ex)
    {
      liste.add(ex.toString());
      liste.makeVisible(liste.getItemCount()-1);
      liste.setBackground(Color.red);
    }
  }

  public void listen()
  {
    System.out.println("Number of clients = " + server.getNumberOfClients());
    try {
      readFields();
      if (c == 1){
      timer.cancel();
      }
      server.listen();
    }
    catch (Exception ex)
    {
      liste.add(ex.toString());
      liste.makeVisible(liste.getItemCount()-1);
      liste.setBackground(Color.red);
    }
  }
   
  public void stop()
  {
    System.out.println("Number of clients = " + server.getNumberOfClients());
    readFields();
    server.stopListening();
  }

  public void quit()
  {
	 // pelletDisp dispobj = new pelletDisp(0,0,0,0,0,0) ; 
	 //  dispobj.shutdownGPIO();
    System.exit(0);
  }


  
  /**
   * Starts the server. The default port is 12345.
 * @throws InterruptedException 
   */
  public static void main(String[] arg) throws InterruptedException
  {
    ServerFrame sf;
    if (arg.length==0)
      sf = new ServerFrame(55555);
    if (arg.length==1)
      sf = new ServerFrame(Integer.parseInt(arg[0]));
  
  }

}
