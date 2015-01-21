/**
 * 
 */
package nodenet;

/**
 * @author rocko
 *
 */
public class Gerechtigkeit implements NodeBehavior {

	private Object paket = null;
	private int kanalOutNr = 0, kanalInNr = 0;

	@Override
	public void transmitPacket(InputChannelVector inputChannels,
			OutputChannelVector outputChannels) {

		if (paket != null) {
			schreiben(outputChannels);
		} else {
			lesen(inputChannels);
		}

	}	

	private void lesen(InputChannelVector kanal) {
		if ((kanal.size() == 0)) {
			return;
		}
		kanalInNr = (kanalInNr + 1) % kanal.size();
		try {
			paket = kanal.elementAt(kanalInNr).readObject(); // Packet empfangen
		}

		catch (ChannelEmptyException e) {
			return;
		}

		catch (ChannelDisabledException e) {
			return;
		}

	}

	// Methode zum schreiben in den Kanal
	private void schreiben(OutputChannelVector kanal) {
		if ((kanal.size() == 0)) {
			return;
		}
		kanalOutNr = (kanalOutNr + 1) % kanal.size();
		try {
			kanal.elementAt(kanalOutNr).writeObject(paket); // Paket versenden
		} catch (ChannelFullException e) {
			return;
		} catch (ChannelDisabledException e) {
			return;
		}
		paket = null;

	}

}
