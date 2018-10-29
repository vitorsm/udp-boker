package br.cefetmg.vitor.udp_broker.models;

import br.cefetmg.vitor.udp_broker.Constants;
import br.cefetmg.vitor.udp_broker.utils.VectorUtils;
import lombok.Data;

@Data
public class Condition {

	private int inputId;
	private OperationType operationType;
	private float value;
	private Condition next;
	
	public static byte[] getVoidCondition() {
		return new byte[] { Constants.EMPTY_CHAR };
	}

	public byte[] getBytes() {
		
		byte[] bytes;
		
		if (inputId <= 0) {
			bytes = new byte[] { Constants.EMPTY_CHAR };
		} else {
			bytes = new byte[Constants.MESSAGE_INPUT_ID_LENGTH + Constants.MESSAGE_VALUE_LENGTH + 2];
			
			String strInputId = inputId + "";
			while (strInputId.length() < Constants.MESSAGE_INPUT_ID_LENGTH) {
				strInputId = "0" + strInputId;
			}
			byte[] bytesInputId = strInputId.getBytes();
			
			String strValue = value + "";
			while (strValue.length() < Constants.MESSAGE_VALUE_LENGTH) {
				strValue = "0" + strValue;
			}
			byte[] bytesValue = strValue.getBytes();
			
			for (int i = 0; i < Constants.MESSAGE_INPUT_ID_LENGTH; i++) {
				bytes[i] = bytesInputId[i];
			}

			bytes[Constants.MESSAGE_INPUT_ID_LENGTH] = (byte) (operationType.getValue() + "").charAt(0);
			
			for (int i = 0; i < Constants.MESSAGE_VALUE_LENGTH; i++) {
				bytes[Constants.MESSAGE_INPUT_ID_LENGTH + 1 + i] = bytesValue[i];
			}
			
			if (next != null)
				bytes = VectorUtils.concactByteVector(bytes, next.getBytes());
		}
		
		
		return bytes;
	}
}
