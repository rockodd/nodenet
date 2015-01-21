/**
 * 
 */
package nodenet;

/**
 * @author rocko
 *
 */
public class Zufall implements NodeBehavior {
	
	protected Object paket = null;
	int kanalOutNr = 0, kanalInNr = 0;



	@Override
	public void transmitPacket(InputChannelVector inputChannels,
			OutputChannelVector outputChannels) {
	    
	    if (paket != null){	schreiben(outputChannels);	}
	    else {	lesen(inputChannels);	}
	    
	}

	

	//ewerer
	//Methode um Pakete aus dem Kanal zu lesen
	private void lesen(InputChannelVector kanal) {
		if ((kanal.size() == 0)){ return;	}
		kanalInNr = (int) (Math.random() * kanal.size());
	  	try {
			paket = kanal.elementAt(kanalInNr).readObject(); //Packet empfangen
		} 
    	catch (ChannelEmptyException e) {			} 
    	catch (ChannelDisabledException e) {			}
	}
	
	
	
	// Methode zum schreiben in den Kanal
	protected void schreiben(OutputChannelVector kanal) {
		if ((kanal.size() == 0)){ return;	}
		kanalOutNr = (int) (Math.random() * kanal.size());
        try {
			kanal.elementAt(kanalOutNr).writeObject(paket);	//Paket versenden
		} 
	    catch (ChannelFullException e) {	} 
	    catch (ChannelDisabledException e) {		} 
	    paket = null;
	}

		
}
		    
