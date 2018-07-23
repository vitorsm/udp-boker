package br.cefetmg.vitor.udp_broker.models.message.body;

import br.cefetmg.vitor.udp_broker.Constants;
import br.cefetmg.vitor.udp_broker.core.impl.Credentials;
import br.cefetmg.vitor.udp_broker.utils.MessageUtils;
import br.cefetmg.vitor.udp_broker.utils.VectorUtils;
import lombok.Data;

@Data
public class MessageBodyHello extends MessageBody {

	private Credentials credentials;
	
	public Credentials getCredentials() {
		if (credentials == null)
			instanceCredentials();
		
		return credentials;
	}
	
	public byte[] getBytes() {
		
		if (credentials != null) {
			byte[] bytesId = credentials.getId().getBytes();
			byte[] bytesPassword = credentials.getPassword().getBytes();
			byte[] bytesPayload = new byte[Constants.MESSAGE_BODY_LENGTH];
			
			for (int i = 0; i < Constants.CLIENT_ID_LENGTH && i < bytesId.length; i++) {
				bytesPayload[i] = bytesId[i];
			}
			
			for (int i = Constants.CLIENT_ID_LENGTH; i < Constants.MESSAGE_BODY_LENGTH && i - Constants.CLIENT_ID_LENGTH < bytesPassword.length; i++) {
				bytesPayload[i] = bytesPassword[i - Constants.CLIENT_ID_LENGTH];
			}
			
			this.setPayload(bytesPayload);
		}
		
		return super.getBytes();
	}
	
	private void instanceCredentials() {
		byte[] bytesId = VectorUtils.subvector(this.getPayload(), 0, Constants.CLIENT_ID_LENGTH);
		byte[] bytesPassword = VectorUtils.subvector(this.getPayload(), Constants.CLIENT_ID_LENGTH, Constants.MESSAGE_BODY_LENGTH);
		
		credentials = new Credentials();
		credentials.setId(MessageUtils.convertBytesToString(bytesId));
		credentials.setPassword(MessageUtils.convertBytesToString(bytesPassword));
		
	}
}
