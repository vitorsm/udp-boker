package br.cefetmg.vitor.udp_broker.models.message.body;

import br.cefetmg.vitor.udp_broker.Constants;
import br.cefetmg.vitor.udp_broker.utils.MessageUtils;
import lombok.Data;

@Data
public class MessageBodyUpdateParam extends MessageBody {

	private String params;
	
	public String getParams() {
		if (params == null)
			instanceParams();
		
		return params;
	}
	
	@Override
	public byte[] getBytes() {
		
		byte[] bytesPayload = new byte[Constants.MESSAGE_BODY_LENGTH];
		
		if (params != null) {
			
			byte[] bytesParams = params.getBytes();
			
			for (int i = 0; i < Constants.MESSAGE_BODY_LENGTH && i < bytesParams.length; i++) {
				bytesPayload[i] = bytesParams[i];
			}
			
			this.setPayload(bytesPayload);
		}
		
		return super.getBytes();
	}
	
	private void instanceParams() {
		params = MessageUtils.convertBytesToString(this.getPayload());
	}
}
