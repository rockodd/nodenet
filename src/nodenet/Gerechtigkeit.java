/**
 * 
 */
package nodenet;

/**
 * @author rocko
 *
 */
public class Gerechtigkeit implements NodeBehavior {
	
	protected Object paket = null;
	int kanalOutNr = 0, kanalInNr = 0;



	@Override
	public void transmitPacket(InputChannelVector inputChannels,
			OutputChannelVector outputChannels) {
	    
	    if (paket != null){	schreiben(outputChannels);	}
	    else {	lesen(inputChannels);	}
	    
	}

	


	//Methode um Pakete aus dem Kanal zu lesen
	private void lesen(InputChannelVector kanal) {
		if ((kanal.size() == 0)){ return;	}
	  	try {
			paket = kanal.elementAt(kanalInNr).readObject(); //Packet empfangen
		} 
    	catch (ChannelEmptyException e) {			} 
    	catch (ChannelDisabledException e) {			}
		
		kanalInNr = (kanalInNr+1) % kanal.size();
	}
	
	
	
	// Methode zum schreiben in den Kanal
	protected void schreiben(OutputChannelVector kanal) {
		if ((kanal.size() == 0)){ return;	}
		
        try {
			kanal.elementAt(kanalOutNr).writeObject(paket);	//Paket versenden
		} 
	    catch (ChannelFullException e) {	} 
	    catch (ChannelDisabledException e) {		} 
	    paket = null;
	    kanalOutNr = (kanalOutNr+1) % kanal.size();
	}

		
}
		    
