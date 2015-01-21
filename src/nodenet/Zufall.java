/**
 * 
 */
package nodenet;

/**
 * @author rocko
 *
 */
public class Zufall implements NodeBehavior {
	
	private Object paket = null;
	private int kanalOutNr = 0, kanalInNr = 0;



	@Override
	public void transmitPacket(InputChannelVector inputChannels, OutputChannelVector outputChannels) {
		if (inputChannels.size() == 0 || outputChannels.size() == 0) return;
	    if (paket != null){	schreiben(outputChannels);	}
	    else {	lesen(inputChannels);	}
	    
	}

	

	//Methode um Pakete aus dem Kanal zu lesen
	private void lesen(InputChannelVector kanal) {
		kanalInNr = (int) (Math.random() * kanal.size());
	  	try {
			paket = kanal.elementAt(kanalInNr).readObject(); //Packet empfangen
		} 
    	catch (ChannelEmptyException e) {			} 
    	catch (ChannelDisabledException e) {			}
	}
	
	
	
	// Methode zum schreiben in den Kanal
	private void schreiben(OutputChannelVector kanal) {
		kanalOutNr = (int) (Math.random() * kanal.size());
        try {
			kanal.elementAt(kanalOutNr).writeObject(paket);	//Paket versenden
		} 
	    catch (ChannelFullException e) {	} 
	    catch (ChannelDisabledException e) {		} 
	    paket = null;
	}

		
}
		    
