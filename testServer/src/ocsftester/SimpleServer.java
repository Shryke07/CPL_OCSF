// This file contains material supporting section 10.9 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

/*
 * SimpleServer.java   2001-02-08
 *
 * Copyright (c) 2000 Robert Laganiere and Timothy C. Lethbridge.
 * All Rights Reserved.
 *
 */
package ocsftester;

import java.awt.List;
import java.awt.Color;
import java.awt.event.ActionListener;

import ocsf.server.*;
import ocsftester.excel;


import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.* ;
/**
* The <code> SimpleServer </code> class is a simple subclass
* of the <code> ocsf.server.AbstractServer </code> class.
* It allows testing of the functionalities offered by the
* OCSF framework. The <code> java.awt.List </code> instance
* is used to display informative messages. This list is red
* when the server is closed, yellow when the server is stopped
* and green when open.
*
* @author Dr. Robert Lagani&egrave;re
* @version February 2001
* @see ocsf.server.AbstractServer
*/
public class SimpleServer extends AbstractServer
{
  private List liste ;  
  private pelletDisp PD0 , PD1 , PD2 , PD3 , PD4 , PD5 , PD6 , PD7 , PD8 , PD9 , PDLED ;
  int delay = 200; //milliseconds

  /**
   * Creates a simple server. The default port is 12345.
   *
   * @param liste the liste on which information will be displayed.
   */
  public SimpleServer(List liste)
  {
    super(11111);
    this.liste = liste;
    
    this.PD0 = new pelletDisp(0,0,0,0,0,0);
    this.PD1 = new pelletDisp(0,1,0,0,0,0);
    this.PD2 = new pelletDisp(0,2,0,0,0,0);
    this.PD3 = new pelletDisp(0,3,0,0,0,0);
    this.PD4 = new pelletDisp(0,4,0,0,0,0);
    this.PD5 = new pelletDisp(0,5,0,0,0,0);
    this.PD6 = new pelletDisp(0,6,0,0,0,0);
    this.PD7 = new pelletDisp(0,7,0,0,0,0);
    this.PD8 = new pelletDisp(0,8,0,0,0,0);
    this.PD9 = new pelletDisp(0,9,0,0,0,0);
    this.PDLED = new pelletDisp(0,10,0,0,0,0) ; 
    


  }
  


  /**
   * Creates a simple server.
   *
   * @param port the port on which the server will listen.
   * @param liste the liste on which information will be displayed.
   */
  public SimpleServer(int port, List liste)
  {
    super(port);
    this.liste = liste;
  
    this.PD0 = new pelletDisp(0,0,0,0,0,0);
    this.PD1 = new pelletDisp(0,1,0,0,0,0);
    this.PD2 = new pelletDisp(0,2,0,0,0,0);
    this.PD3 = new pelletDisp(0,3,0,0,0,0);
    this.PD4 = new pelletDisp(0,4,0,0,0,0);
    this.PD5 = new pelletDisp(0,5,0,0,0,0);
    this.PD6 = new pelletDisp(0,6,0,0,0,0);
    this.PD7 = new pelletDisp(0,7,0,0,0,0);
    this.PD8 = new pelletDisp(0,8,0,0,0,0);
    this.PD9 = new pelletDisp(0,9,0,0,0,0);
    this.PDLED = new pelletDisp(0,10,0,0,0,0) ; 

    
  }

  /**
   * Hook method called each time a new client connection is
   * accepted.
   *
   * @param client the connection connected to the client.
   */
  synchronized protected void clientConnected(ConnectionToClient client)
  {
    liste.add("Client connected: " + client);
    liste.makeVisible(liste.getItemCount()-1);
  }

  /**
   * Hook method called each time a client disconnects.
   *
   * @param client the connection with the client.
   */
  synchronized protected void clientDisconnected(ConnectionToClient client)
  {
    liste.add("Client disconnected: " + client);
    liste.makeVisible(liste.getItemCount()-1);
  }

  /**
   * Hook method called each time an exception is thrown in a
   * ConnectionToClient thread.
   *
   * @param client the client that raised the exception.
   * @param Throwable the exception thrown.
   */
  synchronized protected void clientException(ConnectionToClient client,
                                        Throwable exception)
  {
    liste.add("Client exception: " + exception + " with " + client);
    liste.makeVisible(liste.getItemCount()-1);
  }

  /**
   * Hook method called when the server stops accepting
   * connections because an exception has been raised.
   *
   * @param exception the exception raised.
   */
  protected void listeningException(Throwable exception)
  {
    liste.add("Listening exception: " + exception);
    liste.makeVisible(liste.getItemCount()-1);
    liste.setBackground(Color.red);
  }

  /**
   * Hook method called when the server stops accepting
   * connections.
   */
  protected void serverStopped()
  {
    liste.add("Server stopped");
    liste.makeVisible(liste.getItemCount()-1);
    liste.setBackground(Color.yellow);
  }

  /**
   * Hook method called when the server is clased.
   */
  protected void serverClosed()
  {
	
			
    liste.add("Server closed");
    liste.makeVisible(liste.getItemCount()-1);
    liste.setBackground(Color.red);
   // pelletDisp dispobj = new pelletDisp(0,0,0,0,0,0) ; 
   // dispobj.shutdownGPIO();
   }
  

  /**
   * Hook method called when the server starts listening for
   * connections.
   */
  protected void serverStarted()
  {
    liste.add("Server started : " + this.getPort());
    liste.makeVisible(liste.getItemCount()-1);
    liste.setBackground(Color.green);
 
    
 
  } 
 
  /**
   * Handles a command sent from one client to the server.
   *
   * @param msg   the message sent.
   * @param client the connection connected to the client that
   *  sent the message.
   */
  public int findMaxVal  (int[] r){
	  int max = 0;
	
		for(int i = 0; i < r.length; i++) {
		      if(r[i] > max) {
		         max = r[i]; }
		} return max ; 
 
  }
  
  
//  private void writeToLog( int[] info)
//  {
  	 
  	
  //	excel writeobj = new excel() ;
  	
  	// writeobj.writer(info ) ; 
  	
  	 
  	 
  			
 // }
  
	  
  protected void handleMessageFromClient(Object msg, ConnectionToClient client)
  
  {
    liste.add(msg.toString());
    liste.makeVisible(liste.getItemCount()-1);
    sendToAllClients(msg);
    
    String[] result = msg.toString().split("\\s");		// a string array, " " delimiters 
    
    for (int x=0; x<result.length; x++){				// this turns all the data received from sendToServer() into 
    	liste.add(result[x]);							
    }
   
    
 
    
  //  if (result[0].compareTo("1")==0){
       // liste.add(result[0]);
    	/*
    	PD1.setfeedHour1(Integer.parseInt(result[1]));
    	PD1.setfeedAmt1(Integer.parseInt(result[2]));
    	PD1.setfeedHour2(Integer.parseInt(result[3]));
    	PD1.setfeedAmt2(Integer.parseInt(result[4]));  	
    	liste.add("PD " + result[0] + " updated" );
    	sendToAllClients("PD " + result[0] + " updated" );
    	*/
  	
  		 
    if (result[0].compareTo("Cancel_Dispense")==0){
    	  System.exit(0);
    }
    if(result[1].compareTo("disp")==0){
    	try {
    		switch(Integer.parseInt(result[0])) {
    		case 0 :
    			for (int c = 0 ; c<  Integer.parseInt(result[2]) ; c++ ){
    				wait(delay);
    				PD0.dispense(0 , 1);
    				
    				}
    			break;
    		case 1 :
    			for (int c = 0 ; c<  Integer.parseInt(result[2]) ; c++ ){
    				wait(delay) ; 
        			PD1.dispense(1 , 1);
        			}
    			break ; 
    		case 2 :
    			for (int c = 0 ; c<  Integer.parseInt(result[2]) ; c++ ){
    				wait(delay) ; 
        			PD2.dispense(2 , 1);
        			}
    			break;
    		case 3 :
    			for (int c = 0 ; c<  Integer.parseInt(result[2]) ; c++ ){
    				wait(delay) ; 
        			PD3.dispense(3 , 1);
        			}
    			break ; 
    		case 4 :
    			for (int c = 0 ; c<  Integer.parseInt(result[2]) ; c++ ){
    				wait(delay) ; 
        			PD4.dispense(4 , 1);
        			}
    			break ;
    		case 5 :
    			for (int c = 0 ; c<  Integer.parseInt(result[2]) ; c++ ){
    				wait(delay) ; 
        			PD5.dispense(5 , 1);
        			}
    			break ;
    		case 6 :
    			for (int c = 0 ; c<  Integer.parseInt(result[2]) ; c++ ){
    				wait(delay) ; 
        			PD6.dispense(6 , 1);
        			}
    			break;
    		case 7 :
    			for (int c = 0 ; c<  Integer.parseInt(result[2]) ; c++ ){
    				wait(delay) ; 
        			PD7.dispense(7 , 1);
        			}
    			break ; 
    		case 8 :
    			for (int c = 0 ; c<  Integer.parseInt(result[2]) ; c++ ){
    				wait(delay) ; 
        			PD8.dispense(8 , 1);
        			}
    			break ; 
    		case 9 :
    			for (int c = 0 ; c<  Integer.parseInt(result[2]) ; c++ ){
    				wait(delay) ; 
        			PD9.dispense(9 , 1);
        			}
    			break ; 
    		default : 
    			liste.add("That Pellet Dispenser does not exist") ;
    			blink(10) ; 
    			
    			
    		}
	
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
    	
	}
    if(result[0].compareTo("EXCEL")==0){
    	
    	
    	int [] pel = new int[result.length] ;				
    	for (int f = 0 ; f<result.length - 1 ; f++){		// this turns result[] from type String to type int
    	pel[f] = Integer.parseInt(result[f + 1])/2 ; 			// only if the server is sending Excel data 
    	}	//writeToLog(pel) ;
      													// divides by two, this is assumed for two feed hours
    	try{
    	switch (result[1]) {
    	case "1" :
    		liste.add("The first Pi will send on the first ten pellet #s ");
    	 
    		
    for (  int c = 1   ; c<findMaxVal(pel) ; c++ ) {
    		
    			
    		
    		if ( c<= pel[1] && c> pel[2] && c> pel[3] && c> pel[4] && c> pel[5] && c> pel[6] && c> pel[7] && c> pel[8] &&  c> pel[9] && c> pel[10]){
 
    				wait(delay) ; 			// these lines are used when all other signals are sent, this makes signals distinct
    	    		PD0.dispense(0 , 1);    
    	    
    			}else
    				if (c<= pel[1]){				// uses the dispense method to send one pellet to PD 0
    					PD0.dispense(0 , 1);		// this allows all the pellet dispensers to receive data and begin 
    												// within two seconds of sending the first signal
    				}
    		
    		
    		
    		if (c> pel[1] && c<= pel[2] &&  c> pel[3] && c> pel[4] && c> pel[5] && c> pel[6] && c> pel[7] && c> pel[8] &&  c> pel[9] && c> pel[10]){
    			
    				wait(delay) ; 
    	    		PD1.dispense(1 , 1);
    	    		    	     	  
    			}else
    				if (c<= pel[2]){
    		    		PD1.dispense(1 , 1);
    		    	  }
    		
    		
    		
    		if (c> pel[1] && c> pel[2] && c<= pel[3] && c> pel[4] && c> pel[5] && c> pel[6] && c> pel[7] && c> pel[8] &&  c> pel[9] && c> pel[10]){
    			
    				wait(delay) ; 
    	    		PD2.dispense(2 , 1);
    	    		    	     	  
    			}else
    				if (c<= pel[3]){
    					PD2.dispense(2 , 1);
    	    		 }
    		
    		
    		
    		if (c> pel[1] && c> pel[2] && c> pel[3] && c<= pel[4] && c> pel[5] && c> pel[6] && c> pel[7] && c> pel[8] &&  c> pel[9] && c> pel[10]){
    			
    				wait(delay) ; 
    	    		PD3.dispense(3 , 1);
    	    		    	     	  
    			}else
    				if (c<= pel[4]){
    					PD3.dispense(3 , 1);
    	    		 }
    		
    		
   		 
    		if (c> pel[1] && c> pel[2] && c> pel[3] && c> pel[4] && c<= pel[5] &&  c> pel[6] && c> pel[7] && c> pel[8] &&  c> pel[9] && c> pel[10]){
    			
    				wait(delay) ; 
    	    		PD4.dispense(4 , 1);
    	    		
    	     	  
    			}else
    				if (c<= pel[5]){
    					PD4.dispense(4 , 1);
    	    		}
    		
    		
    		
    		if (c> pel[1] && c> pel[2] && c> pel[3] && c> pel[4] && c> pel[5] && c<= pel[6] && c> pel[7] && c> pel[8] &&  c> pel[9] && c> pel[10]){
    			
    				wait(delay) ; 
    	    		PD5.dispense(5 , 1);
    	    		}else
    	 	        	if (c<= pel[6]){
    	 	        		PD5.dispense(5 , 1);
    	        		 }
    		
    		  	    		  	   			    		
    		
    		if (c> pel[1] && c> pel[2] && c> pel[3] && c> pel[4] && c> pel[5] && c> pel[6] && c<= pel[7] && c> pel[8] &&  c> pel[9] && c> pel[10]){
    			
    				wait(delay) ; 
    	    		PD6.dispense(6 , 1);
    	    		}else
    	    			if (c<= pel[7]){
    	    				PD6.dispense(6 , 1);
    	    			}
    		
    		
    		
    		if (c> pel[1] && c> pel[2] && c> pel[3] && c> pel[4] && c> pel[5] && c> pel[6] && c> pel[7] && c<= pel[8] && c> pel[9] && c> pel[10]){
    			
    				wait(delay) ; 
    	    		PD7.dispense(7 , 1);
    	    		}else
    	    			if (c<= pel[8]){
    	    				PD7.dispense(7 , 1);
    	    			}
    		
    		
    		
    		if (c> pel[1] && c> pel[2] && c> pel[3] && c> pel[4] && c> pel[5] && c> pel[6] && c> pel[7] &&  c> pel[8] && c<= pel[9] && c> pel[10]){
    			
    				wait(delay) ; 
    	    		PD8.dispense(8 , 1);
    	    		}else
    	    			if (c<= pel[9]){
    	    				PD8.dispense(8 , 1);
    	    			}
    		
    		
    		
    		if (c> pel[1] && c> pel[2] && c> pel[3] && c> pel[4] && c> pel[5] && c> pel[6] && c> pel[7] &&  c> pel[8] && c> pel[9] && c<= pel[10]){
    			
    				wait(delay) ; 
    	    		PD9.dispense(9 , 1);
    	    		}else
    	    			if (c<= pel[10]){
    	    				PD9.dispense(9 , 1);
    	    			}
    		
    }
    		
    		
    		break ; 
    	case "2" :
    		liste.add("The second Pi will send on the middle ten pellet #s");
 
    		
    	    for (  int c = 1   ; c<findMaxVal(pel) ; c++ ) {
        		
    			
        		
        		if ( c<= pel[11] && c> pel[12] && c> pel[13] && c> pel[14] && c> pel[15] && c> pel[16] && c> pel[17] && c> pel[18] &&  c> pel[19] && c> pel[20]){
     
        				wait(delay) ; 			
        	    		PD0.dispense(0 , 1);    	 
        			}else
        				if (c<= pel[11]){
        					PD0.dispense(0 , 1);
        				}
        		
        		
        		
        		if (c> pel[11] && c<= pel[12] &&  c> pel[13] && c> pel[14] && c> pel[15] && c> pel[16] && c> pel[17] && c> pel[18] &&  c> pel[19] && c> pel[20]){
        			
        				wait(delay) ; 
        	    		PD1.dispense(1 , 1);
        	    		    	     	  
        			}else
        				if (c<= pel[12]){
        		    		PD1.dispense(1 , 1);
        		    	  }
        		
        		
        		
        		if (c> pel[11] && c> pel[12] && c<= pel[13] && c> pel[14] && c> pel[15] && c> pel[16] && c> pel[17] && c> pel[18] &&  c> pel[19] && c> pel[20]){
        			
        				wait(delay) ; 
        	    		PD2.dispense(2 , 1);
        	    		    	     	  
        			}else
        				if (c<= pel[13]){
        					PD2.dispense(2 , 1);
        	    		 }
        		
        		
        		
        		if (c> pel[11] && c> pel[12] && c> pel[13] && c<= pel[14] && c> pel[15] && c> pel[16] && c> pel[17] && c> pel[18] &&  c> pel[19] && c> pel[20]){
        			
        				wait(delay) ; 
        	    		PD3.dispense(3 , 1);
        	    		    	     	  
        			}else
        				if (c<= pel[14]){
        					PD3.dispense(3 , 1);
        	    		 }
        		
        		
       		 
        		if (c> pel[11] && c> pel[12] && c> pel[13] && c> pel[14] && c<= pel[15] &&  c> pel[16] && c> pel[17] && c> pel[18] &&  c> pel[19] && c> pel[20]){
        			
        				wait(delay) ; 
        	    		PD4.dispense(4 , 1);
        	    		
        	     	  
        			}else
        				if (c<= pel[15]){
        					PD4.dispense(4 , 1);
        	    		}
        		
        		
        		
        		if (c> pel[11] && c> pel[12] && c> pel[13] && c> pel[14] && c> pel[15] && c<= pel[16] && c> pel[17] && c> pel[18] &&  c> pel[19] && c> pel[20]){
        			
        				wait(delay) ; 
        	    		PD5.dispense(5 , 1);
        	    		}else
        	 	        	if (c<= pel[16]){
        	 	        		PD5.dispense(5 , 1);
        	        		 }
        		
        		  	    		  	   			    		
        		
        		if (c> pel[11] && c> pel[12] && c> pel[13] && c> pel[14] && c> pel[15] && c> pel[16] && c<= pel[17] && c> pel[18] &&  c> pel[19] && c> pel[20]){
        			
        				wait(delay) ; 
        	    		PD6.dispense(6 , 1);
        	    		}else
        	    			if (c<= pel[17]){
        	    				PD6.dispense(6 , 1);
        	    			}
        		
        		
        		
        		if (c> pel[11] && c> pel[12] && c> pel[13] && c> pel[14] && c> pel[15] && c> pel[16] && c> pel[17] && c<= pel[18] && c> pel[19] && c> pel[20]){
        			
        				wait(delay) ; 
        	    		PD7.dispense(7 , 1);
        	    		}else
        	    			if (c<= pel[18]){
        	    				PD7.dispense(7 , 1);
        	    			}
        		
        		
        		
        		if (c> pel[11] && c> pel[12] && c> pel[13] && c> pel[14] && c> pel[15] && c> pel[16] && c> pel[17] &&  c> pel[18] && c<= pel[19] && c> pel[20]){
        			
        				wait(delay) ; 
        	    		PD8.dispense(8 , 1);
        	    		}else
        	    			if (c<= pel[19]){
        	    				PD8.dispense(8 , 1);
        	    			}
        		
        		
        		
        		if (c> pel[11] && c> pel[12] && c> pel[13] && c> pel[14] && c> pel[15] && c> pel[16] && c> pel[17] &&  c> pel[18] && c> pel[19] && c<= pel[20]){
        			
        				wait(delay) ; 
        	    		PD9.dispense(9 , 1);
        	    		}else
        	    			if (c<= pel[20]){
        	    				PD9.dispense(9 , 1);
        	    			}
        		
        }
    		break ; 
    	case "3" :
    		liste.add("The third Pi will send on the last ten pellet #s");
 
    	    for (  int c = 1   ; c<findMaxVal(pel) ; c++ ) {
        		
    			
        		
        		if ( c<= pel[21] && c> pel[22] && c> pel[23] && c> pel[24] && c> pel[25] && c> pel[26] && c> pel[27] && c> pel[28] &&  c> pel[29] && c> pel[30]){
     
        				wait(delay) ; 
        	    		PD0.dispense(0 , 1);    	 
        			}else
        				if (c<= pel[21]){
        					PD0.dispense(0 , 1);
        				}
        		
        		
        		
        		if (c> pel[21] && c<= pel[22] &&  c> pel[23] && c> pel[24] && c> pel[25] && c> pel[26] && c> pel[27] && c> pel[28] &&  c> pel[29] && c> pel[30]){
        			
        				wait(delay) ; 
        	    		PD1.dispense(1 , 1);
        	    		    	     	  
        			}else
        				if (c<= pel[22]){
        		    		PD1.dispense(1 , 1);
        		    	  }
        		
        		
        		
        		if (c> pel[21] && c> pel[22] && c<= pel[23] && c> pel[24] && c> pel[25] && c> pel[26] && c> pel[27] && c> pel[28] &&  c> pel[29] && c> pel[30]){
        			
        				wait(delay) ; 
        	    		PD2.dispense(2 , 1);
        	    		    	     	  
        			}else
        				if (c<= pel[23]){
        					PD2.dispense(2 , 1);
        	    		 }
        		
        		
        		
        		if (c> pel[21] && c> pel[22] && c> pel[23] && c<= pel[24] && c> pel[25] && c> pel[26] && c> pel[27] && c> pel[28] &&  c> pel[29] && c> pel[30]){
        			
        				wait(delay) ; 
        	    		PD3.dispense(3 , 1);
        	    		    	     	  
        			}else
        				if (c<= pel[24]){
        					PD3.dispense(3 , 1);
        	    		 }
        		
        		
       		 
        		if (c> pel[21] && c> pel[22] && c> pel[23] && c> pel[24] && c<= pel[25] &&  c> pel[26] && c> pel[27] && c> pel[28] &&  c> pel[29] && c> pel[30]){
        			
        				wait(delay) ; 
        	    		PD4.dispense(4 , 1);
        	    		
        	     	  
        			}else
        				if (c<= pel[25]){
        					PD4.dispense(4 , 1);
        	    		}
        		
        		
        		
        		if (c> pel[21] && c> pel[22] && c> pel[23] && c> pel[24] && c> pel[25] && c<= pel[26] && c> pel[27] && c> pel[28] &&  c> pel[29] && c> pel[30]){
        			
        				wait(delay) ; 
        	    		PD5.dispense(5 , 1);
        	    		}else
        	 	        	if (c<= pel[26]){
        	 	        		PD5.dispense(5 , 1);
        	        		 }
        		
        		  	    		  	   			    		
        		
        		if (c> pel[21] && c> pel[22] && c> pel[23] && c> pel[24] && c> pel[25] && c> pel[26] && c<= pel[27] && c> pel[28] &&  c> pel[29] && c> pel[30]){
        			
        				wait(delay) ; 
        	    		PD6.dispense(6 , 1);
        	    		}else
        	    			if (c<= pel[27]){
        	    				PD6.dispense(6 , 1);
        	    			}
        		
        		
        		
        		if (c> pel[21] && c> pel[22] && c> pel[23] && c> pel[24] && c> pel[25] && c> pel[26] && c> pel[27] && c<= pel[28] && c> pel[29] && c> pel[30]){
        			
        				wait(delay) ; 
        	    		PD7.dispense(7 , 1);
        	    		}else
        	    			if (c<= pel[28]){
        	    				PD7.dispense(7 , 1);
        	    			}
        		
        		
        		
        		if (c> pel[21] && c> pel[22] && c> pel[23] && c> pel[24] && c> pel[25] && c> pel[26] && c> pel[27] &&  c> pel[28] && c<= pel[29] && c> pel[30]){
        			
        				wait(delay) ; 
        	    		PD8.dispense(8 , 1);
        	    		}else
        	    			if (c<= pel[29]){
        	    				PD8.dispense(8 , 1);
        	    			}
        		
        		
        		
        		if (c> pel[21] && c> pel[22] && c> pel[23] && c> pel[24] && c> pel[25] && c> pel[26] && c> pel[27] &&  c> pel[28] && c> pel[29] && c<= pel[30]){
        			
        				wait(delay) ; 
        	    		PD9.dispense(9 , 1);
        	    		}else
        	    			if (c<= pel[30]){
        	    				PD9.dispense(9 , 1);
        	    			}
        		
        }
    		break ; 
    		default :
    			liste.add("oops! we dont have that Pi!") ;
    			break ; 
    	}
    	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
    	
    }
    	
    }
  }



private void blink(int loops ) throws InterruptedException {
	try{
	for (int c = 0 ; c < loops ; c++){
		wait(100);
		PDLED.dispense(10 , 1);
	}
	}catch (Exception e ) { 
		liste.add(e.toString() ) ;
}
}
}
    	
    
  
    
    
    

