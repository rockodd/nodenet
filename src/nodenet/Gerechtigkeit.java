/**
 * 
 */
package nodenet;

/**
 * @author rocko
 *
 */
public class Gerechtigkeit implements NodeBehavior {
	
	private Object Packet;
    int anzInputChannels, anzOutputChannels;



	@Override
	public void transmitPacket(InputChannelVector inputChannels,
			OutputChannelVector outputChannels) {
		// TODO Auto-generated method stub
		
	    anzInputChannels = inputChannels.size();
	    anzOutputChannels = outputChannels.size();

	    // kein EIngang oder Ausgang
	    if ((anzInputChannels == 0) || (anzOutputChannels == 0)) { return; }
	    else {
	    
	    //leseChannel();
	    try {
			Packet = inputChannels.elementAt(0).readObject(); //Packet empfangen
		} catch (ChannelEmptyException e) {
			// 
		
		} catch (ChannelDisabledException e) {

			
		}
	    
	    
	    //senden
	    try {
			outputChannels.elementAt(0).writeObject(Packet);
		} catch (ChannelFullException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (ChannelDisabledException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} // Packet senden
	    
	    }
	    
	    
	    
	    
	    
		
	}

	
	

}
