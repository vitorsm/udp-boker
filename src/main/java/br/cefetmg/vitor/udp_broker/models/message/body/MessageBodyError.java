package br.cefetmg.vitor.udp_broker.models.message.body;

import br.cefetmg.vitor.udp_broker.Constants;
import br.cefetmg.vitor.udp_broker.utils.MessageUtils;
import br.cefetmg.vitor.udp_broker.utils.VectorUtils;

public class MessageBodyError extends MessageBody {
	
	private String name;
	private String details;
	
	
	@Override
	public byte[] getBytes() {
		if (name == null) instanceName();
		if (details == null) instanceDetails();
		
		byte[] bytesName = name.getBytes();
		byte[] bytesDetails = details.getBytes();
		
		byte[] bytes = new byte[Constants.MESSAGE_BODY_LENGTH];
		for (int i = 0; i < Constants.MESSAGE_ERROR_NAME_LENGTH && i < bytesName.length; i++) {
			bytes[i] = bytesName[i];
		}
		
		for (int i = Constants.MESSAGE_ERROR_NAME_LENGTH; 
				i < Constants.MESSAGE_BODY_LENGTH && i - Constants.MESSAGE_ERROR_NAME_LENGTH < bytesDetails.length;
				i++) {
			bytes[i] = bytesDetails[i - Constants.MESSAGE_TOPIC_LENGTH];
		}
		
		this.setPayload(bytes);
		
		return super.getBytes();
	}
	
	public String getName() {
		if (name == null)
			instanceName();
		
		return name;
	}
	
	public String getDetails() {
		if (details == null)
			instanceDetails();
		
		return details;
	}
	
	private void instanceName() {
		byte[] nameBytes = VectorUtils.subvector(this.getPayload(), 0, Constants.MESSAGE_ERROR_NAME_LENGTH);
		
		name = MessageUtils.convertBytesToString(nameBytes);
	}
	
	private void instanceDetails() {
		byte[] detailsBytes = VectorUtils.subvector(this.getPayload(), Constants.MESSAGE_ERROR_NAME_LENGTH, Constants.MESSAGE_BODY_LENGTH);
		
		details = MessageUtils.convertBytesToString(detailsBytes);
	}
	
}
