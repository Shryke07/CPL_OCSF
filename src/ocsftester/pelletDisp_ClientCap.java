package ocsftester;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;


public class pelletDisp_ClientCap  {
	 
/*
PD_0 pd0 =new PD_0() ;    				I tried to use threads here, turns out they can only safely be used once
PD_1 pd1 =new PD_1() ;					also, the max current output from the raspberry pi gpio is 50ma
PD_2 pd2 =new PD_2() ;					which makes pulsing 10 pins at a time a really bad idea.
PD_3 pd3 =new PD_3() ;
PD_4 pd4 =new PD_4() ;
PD_5 pd5 =new PD_5() ;
PD_6 pd6 =new PD_6() ;
PD_7 pd7 =new PD_7() ;
PD_8 pd8 =new PD_8() ;
PD_9 pd9 =new PD_9() ;
PD_LED pdled =new PD_LED() ;	*/ 

	   int pelletBin;
	   int pelletDispID;
	   int DELAYperPELLET; //in milliseconds
	   int feedHour1; 
	   int feedHour2;
	   int feedAmt1; 
	   int feedAmt2;
	    // create gpio controller
	    final GpioController gpio = GpioFactory.getInstance();
	   
	    
	    // provision gpio pin #00 as an output pin and turn on
	  
	  final GpioPinDigitalOutput pin0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, "PD0", PinState.LOW);
	  final GpioPinDigitalOutput pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16, "PD1", PinState.LOW);
	  final GpioPinDigitalOutput pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "PD2", PinState.LOW);
	  final GpioPinDigitalOutput pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "PD3", PinState.LOW);
	  final GpioPinDigitalOutput pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "PD4", PinState.LOW);
	  final GpioPinDigitalOutput pin5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "PD5", PinState.LOW);
	  final GpioPinDigitalOutput pin6 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, "PD6", PinState.LOW);
	  final GpioPinDigitalOutput pin7 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, "PD7", PinState.LOW);
	  final GpioPinDigitalOutput pin8 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, "PD8", PinState.LOW);
	  final GpioPinDigitalOutput pin9 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "PD9", PinState.LOW);
	  final GpioPinDigitalOutput pinLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "LED", PinState.LOW);
	    
	  
	   

	    
   public pelletDisp_ClientCap(int initialNumPellet, int PDID,int feedHr1,int feedAt1,int feedHr2,int feedAt2) {
	  // : pelletBin(initialNumPellet), pelletDispID(PDID),DELAYperPELLET(300) 
		  // 	 wiringPiSetup () ;
	      //   pinMode (0, OUTPUT) ;
	  

	   
	   pelletBin=initialNumPellet;
	   pelletDispID=PDID;
	   DELAYperPELLET=200;
	   feedHour1=feedHr1; 
	   feedHour2=feedHr2;
	   feedAmt1=feedAt1; 
	   feedAmt2=feedAt2;
	   pin0.setShutdownOptions(true, PinState.LOW);
	 pin1.setShutdownOptions(true, PinState.LOW);
	 pin2.setShutdownOptions(true, PinState.LOW);
	 pin3.setShutdownOptions(true, PinState.LOW);
	 pin4.setShutdownOptions(true, PinState.LOW);
	 pin5.setShutdownOptions(true, PinState.LOW);
	 pin6.setShutdownOptions(true, PinState.LOW);
	 pin7.setShutdownOptions(true, PinState.LOW);
	 pin8.setShutdownOptions(true, PinState.LOW);
	 pin9.setShutdownOptions(true, PinState.LOW); 
	 pinLED.setShutdownOptions(true, PinState.LOW);
	 
	 gpio.shutdown() ;
	   gpio.unprovisionPin(pin0);		// I'm not sure why, but if these lines aren't here the class wont work.
	   gpio.unprovisionPin(pin1); 		
	   gpio.unprovisionPin(pin2); 
	   gpio.unprovisionPin(pin3); 
	   gpio.unprovisionPin(pin4); 
	   gpio.unprovisionPin(pin5); 
	   gpio.unprovisionPin(pin6); 
	   gpio.unprovisionPin(pin7); 
	   gpio.unprovisionPin(pin8); 
	   gpio.unprovisionPin(pin9); 
	   gpio.unprovisionPin(pinLED);
	
	
		   }
   
   
   public void setfeedHour1(int FH1){
	   
	   feedHour1=FH1;
	   
}	   
   
   public void setfeedHour2(int FH2){
	   
	   feedHour2=FH2;
	   
}
   public void setfeedAmt1(int FA1){
	   
	   feedAmt1=FA1;
	   
}	
   public void setfeedAmt2(int FA2){
	   
	   feedAmt2=FA2;
	   
}	
   public void setpelletQueue(int total){
	   
	 	  if (total > 0){ 
	   pelletBin = total;
	  }
	   
   }

   int getPDiD(){
	   
	   return pelletDispID;
	   
   }
   
   int getQueueTotal(){
	   
	   return pelletBin;
	   
   }
   
   int getPDdelay(){
	   
	   return DELAYperPELLET;
	   
   }
   

   

   void dispense(int peld , int NumPel) throws InterruptedException{
	   
	 //  while(pelletBin>0){
	  //digitalWrite (0, HIGH) ; delay (DELAYperPELLET) ;
	 // digitalWrite (0,  LOW) ; delay (DELAYperPELLET) ;
	 // printf("dropping pellet %d\n",pelletBin);
	//  pelletBin = pelletBin-1;
	  
    // }
	   
	  
	  // System.out.println("Dispensed:  " + "NUMBER DISPENSED   " +"TIME    ");
	  
	   

	    // set shutdown state for this pin
	   // pin.setShutdownOptions(true, PinState.LOW);
	 //  pin0.pulse(DELAYperPELLET true);
	   
	

	    	
	    
	   for(int i =0;i<NumPel;i++){
		   
	    	
	    	switch (peld) { 
	    	
	    	case 0:
	    		pin0.pulse(DELAYperPELLET, true);
	    	//	if (pd0.isAlive() == true) { pd0.join() ; }
	    	//	pd0.start();
	    		
	    		
	    		break ; 
	    	case 1: 
	    		
	    		pin1.pulse(DELAYperPELLET, true);
	    	//	if (pd1.isAlive() == true) { pd1.join() ; }
	    	//	pd1.start();
	    		 
	    		
	    		break ; 
	    	case 2:
	    		pin2.pulse(DELAYperPELLET, true);
	    	//	if (pd2.isAlive() == true) { pd2.join() ; }
	    	//	pd2.start();
	    		
	    		
	    		break ; 
	    	case 3:
	    		pin3.pulse(DELAYperPELLET, true);
	    	//	if (pd3.isAlive() == true) { pd3.join() ; }
	    	//  pd3.start();
	    	
	    		
	    		break ; 
	    	case 4:
	    		pin4.pulse(DELAYperPELLET, true);
	    	//	if (pd4.isAlive() == true) { pd4.join() ; }
	    	//	pd4.start();
	    	 
	    		
	    		break ; 
	    	case 5:
	    		pin5.pulse(DELAYperPELLET, true);
	    	//	if (pd5.isAlive() == true) { pd5.join() ; }
	    	//	pd5.start();
	    		;  
	    		
	    		break ; 
	    	case 6:
	    	    pin6.pulse(DELAYperPELLET, true);
	    	//	if (pd6.isAlive() == true) { pd6.join() ; }
	    	//	pd6.start();
	    	 
	    		
	    		break ; 
	    	case 7:
	    		pin7.pulse(DELAYperPELLET, true);
	    	//	if (pd7.isAlive() == true) { pd7.join() ; }
	    	//	pd7.start();
	    		
	    		break ; 
	    	case 8:
	    		pin8.pulse(DELAYperPELLET, true);
	    	//	if (pd8.isAlive() == true) { pd8.join() ; }
	    	//	pd8.start();
	    		
	    		
	    		break ; 
	    	case 9:
	    		pin9.pulse(DELAYperPELLET, true);
	    	//	if (pd9.isAlive() == true) { pd9.join() ; }
	    	//	pd9.start();
	    		
	    		
	    		break ;	
	    	default :
	    		pinLED.pulse(100, true);
	    	//	if (pdled.isAlive() == true) { pdled.join() ; }		
	    	//	pdled.start(); 
	    		 
	    		
	    		break ; 
	    	}
	   }
   }
	 
	    	
/* public class PD_0 extends Thread {
	 
	 
	 public void run(){
		 pin0.pulse(DELAYperPELLET, true);
		 System.out.println("0...   ");
	 }
	 
 }   
 public class PD_1 extends Thread {
	 
	 public void run(){
		 pin1.pulse(DELAYperPELLET, true);
		 System.out.println("1...  ");
	 }
	 
 }
 public class PD_2 extends Thread {
	 
	 public void run(){
		 pin2.pulse(DELAYperPELLET, true);
		 System.out.println("2...  ");
	 }
	 
 }
 public class PD_3 extends Thread {
	 
	 public void run(){
		 pin3.pulse(DELAYperPELLET, true);
		 System.out.println("3...  ");
	 }
	 
 }
 public class PD_4 extends Thread {
	 
	 public void run(){
		 pin4.pulse(DELAYperPELLET, true);
		 System.out.println("4...   ");
	 }
	 
 }
 public class PD_5 extends Thread {
	 
	 public void run(){
		 pin5.pulse(DELAYperPELLET, true);
		 System.out.println("5...  ");
	 }
	 
 }
 public class PD_6 extends Thread {
	 
	 public void run(){
		 pin6.pulse(DELAYperPELLET, true);
		 System.out.println("6...   ");
	 }
	 
 }
 public class PD_7 extends Thread {
	 
	 public void run(){
		 pin7.pulse(DELAYperPELLET, true);
		 System.out.println("7...   ");
	 }
	 
 }
 public class PD_8 extends Thread {
	 
	 public void run(){
		 pin8.pulse(DELAYperPELLET, true);
		 System.out.println("8...   ");
	 }
	 
 }
 public class PD_9 extends Thread {
	 
	 public void run(){
		 pin9.pulse(DELAYperPELLET, true);
		 System.out.println("9...   ");
	 }
	 
 }
public class PD_LED extends Thread {
	 
	 public void run(){
		 pinLED.pulse(DELAYperPELLET, true);
		 System.out.println("BLINKY...   ");
	 }
	 
 }
 */
 
		    
	    

	    
	    // stop all GPIO activity/threads by shutting down the GPIO controller
	    // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
	   // gpio.shutdown() ; 
	   
	   
   
   
   void addPellets(int addTOBin){
	   
	  if (addTOBin > 0){ 
	   pelletBin = pelletBin + addTOBin;
	  }
	  
   }
   
  void subPellets(int subFromBin){
	   
	  if ((pelletBin >= subFromBin) && (subFromBin > 0) ){
	   pelletBin = pelletBin - subFromBin;
	  } 
   }
  void shutdownGPIO() {
	  gpio.shutdown() ;
	   gpio.unprovisionPin(pin0);
	   gpio.unprovisionPin(pin1); 
	   gpio.unprovisionPin(pin2); 
	   gpio.unprovisionPin(pin3); 
	   gpio.unprovisionPin(pin4); 
	   gpio.unprovisionPin(pin5); 
	   gpio.unprovisionPin(pin6); 
	   gpio.unprovisionPin(pin7); 
	   gpio.unprovisionPin(pin8); 
	   gpio.unprovisionPin(pin9);
	   gpio.unprovisionPin(pinLED);
	  
  }
   }

