package br.cefetmg.vitor.udp_broker.models.message.body;


import br.cefetmg.vitor.udp_broker.Constants;
import br.cefetmg.vitor.udp_broker.models.Condition;
import br.cefetmg.vitor.udp_broker.models.PinType;
import br.cefetmg.vitor.udp_broker.utils.MessageUtils;
import br.cefetmg.vitor.udp_broker.utils.VectorUtils;
import lombok.Data;

@Data
public class MessageBodyUpdateParam extends MessageBody {
	
	public static class Param {
		public int id;
		public float kp;
		public float ki;
		public float kd;
		public int sampleTime;
		public float setpoint;
		public Condition condition;
		public PinType pinType;
		public int inputId;
	}

	private String token;
	private int[] ids;
	private PinType[] pinTypes;
	private float[] kps;
	private float[] kis;
	private float[] kds;
	private int[] sampleTimes;
	private float[] setpoints;
	private int [] inputsId;
	private Condition[] conditions;
	private int pinsAmount;
	
	public MessageBodyUpdateParam(int pinsAmount) {
		this.pinsAmount = pinsAmount;
		
		ids = new int[pinsAmount];
		pinTypes = new PinType[pinsAmount];
		kps = new float[pinsAmount];
		kis = new float[pinsAmount];
		kds = new float[pinsAmount];
		sampleTimes = new int[pinsAmount];
		setpoints = new float[pinsAmount];
		inputsId = new int[pinsAmount];
		conditions = new Condition[pinsAmount];
	}
	
	public void addItems(int pinNumber, Param param) {
		ids[pinNumber] = param.id;
		pinTypes[pinNumber] = param.pinType;
		kps[pinNumber] = param.kp;
		kis[pinNumber] = param.ki;
		kds[pinNumber] = param.kd;
		sampleTimes[pinNumber] = param.sampleTime;
		setpoints[pinNumber] = param.setpoint;
		conditions[pinNumber] = param.condition;
		inputsId[pinNumber] = param.inputId;
	}
	
	private String params;
	
	public String getParams() {
		if (params == null)
			instanceParams();
		
		return params;
	}
	
	@Override
	public byte[] getBytes() {
		
		byte[] bytes = getBytesIds();
		bytes = VectorUtils.concactByteVector(bytes, getBytesPinTypes());
		bytes = VectorUtils.concactByteVector(bytes, getBytesKps());
		bytes = VectorUtils.concactByteVector(bytes, getBytesKis());
		bytes = VectorUtils.concactByteVector(bytes, getBytesKds());
		bytes = VectorUtils.concactByteVector(bytes, getBytesSampleTimes());
		bytes = VectorUtils.concactByteVector(bytes, getBytesSetpoints());
		bytes = VectorUtils.concactByteVector(bytes, getBytesInputsId());
		bytes = concatConditions(bytes);
		
		return bytes;
	}
	
	private byte[] concatConditions(byte[] bytes) {
		
		for (int i = 0; i < pinsAmount; i++) {
			if (conditions[i] == null) {
				bytes = VectorUtils.concactByteVector(bytes, Condition.getVoidCondition());
			} else {
				bytes = VectorUtils.concactByteVector(bytes, conditions[i].getBytes());
			}
		}
		
		return bytes;
	}
	
	private byte[] getBytesSetpoints() {
		byte[] bytesSetpoints = new byte[pinsAmount * Constants.MESSAGE_VALUE_LENGTH];
		
		for (int i = 0; i < pinsAmount; i++) {
			String strSetpoint = setpoints[i] + "";
			
			while (strSetpoint.length() < Constants.MESSAGE_VALUE_LENGTH) {
				strSetpoint = "0" + strSetpoint;
			}
			
			for (int j = 0; j < Constants.MESSAGE_VALUE_LENGTH; j++) {
				bytesSetpoints[i * Constants.MESSAGE_VALUE_LENGTH + j] = (byte) strSetpoint.charAt(j);
			}
		}
		
		return bytesSetpoints;
	}
	
	private byte[] getBytesInputsId() {
		byte[] bytesSetpoints = new byte[pinsAmount * Constants.MESSAGE_INPUT_ID_LENGTH];
		
		for (int i = 0; i < pinsAmount; i++) {
			String strInputId = inputsId[i] + "";
			
			while (strInputId.length() < Constants.MESSAGE_INPUT_ID_LENGTH) {
				strInputId = "0" + strInputId;
			}
			
			for (int j = 0; j < Constants.MESSAGE_INPUT_ID_LENGTH; j++) {
				bytesSetpoints[i * Constants.MESSAGE_INPUT_ID_LENGTH + j] = (byte) strInputId.charAt(j);
			}
		}
		
		return bytesSetpoints;
	}
	
	private byte[] getBytesSampleTimes() {
		byte[] bytesSampleTimes = new byte[pinsAmount * Constants.MESSAGE_SAMPLE_LENGTH];
		
		for (int i = 0; i < pinsAmount; i++) {
			String strSampleTime = sampleTimes[i] + "";
			
			while (strSampleTime.length() < Constants.MESSAGE_SAMPLE_LENGTH) {
				strSampleTime = "0" + strSampleTime;
			}
			
			for (int j = 0; j < Constants.MESSAGE_SAMPLE_LENGTH; j++) {
				bytesSampleTimes[i * Constants.MESSAGE_SAMPLE_LENGTH + j] = (byte) strSampleTime.charAt(j);
			}
		}
		
		return bytesSampleTimes;
	}
	
	private byte[] getBytesKds() {
		byte[] bytesKds = new byte[pinsAmount * Constants.MESSAGE_K_LENGTH];
		
		for (int i = 0; i < pinsAmount; i++) {
			String strKd = kds[i] + "";
			
			while (strKd.length() < Constants.MESSAGE_K_LENGTH) {
				strKd = "0" + strKd;
			}
			
			for (int j = 0; j < Constants.MESSAGE_K_LENGTH; j++) {
				bytesKds[i * Constants.MESSAGE_K_LENGTH + j] = (byte) strKd.charAt(j);
			}
		}
		
		return bytesKds;
	}
	
	private byte[] getBytesKis() {
		byte[] bytesKis = new byte[pinsAmount * Constants.MESSAGE_K_LENGTH];
		
		for (int i = 0; i < pinsAmount; i++) {
			String strKi = kis[i] + "";
			
			while (strKi.length() < Constants.MESSAGE_K_LENGTH) {
				strKi = "0" + strKi;
			}
			
			for (int j = 0; j < Constants.MESSAGE_K_LENGTH; j++) {
				bytesKis[i * Constants.MESSAGE_K_LENGTH + j] = (byte) strKi.charAt(j);
			}
		}
		
		return bytesKis;
	}
	
	private byte[] getBytesKps() {
		byte[] bytesKps = new byte[pinsAmount * Constants.MESSAGE_K_LENGTH];
		
		for (int i = 0; i < pinsAmount; i++) {
			String strKp = kps[i] + "";
			
			while (strKp.length() < Constants.MESSAGE_K_LENGTH) {
				strKp = "0" + strKp;
			}
			
			for (int j = 0; j < Constants.MESSAGE_K_LENGTH; j++) {
				bytesKps[i * Constants.MESSAGE_K_LENGTH + j] = (byte) strKp.charAt(j);
			}
		}
		
		return bytesKps;
	}
	
	private byte[] getBytesPinTypes() {
		byte[] bytesPinTypes = new byte[pinsAmount];
		
		for (int i = 0; i < pinsAmount; i++) {
			if (pinTypes[i] != null) {
				bytesPinTypes[i] = (byte) (pinTypes[i].getValue() + "").charAt(0);
			} else {
				bytesPinTypes[i] = (byte) '0';
			}
		}
		
		return bytesPinTypes;
	}
	
	private byte[] getBytesIds() {
		byte[] bytesIds = new byte[pinsAmount * Constants.MESSAGE_INPUT_ID_LENGTH];
		
		for (int i = 0; i < pinsAmount; i++) {
			String strId = ids[i] + "";
			
			while (strId.length() < Constants.MESSAGE_INPUT_ID_LENGTH) {
				strId = "0" + strId;
			}
			
			for (int j = 0; j < Constants.MESSAGE_INPUT_ID_LENGTH; j++) {
				bytesIds[i * Constants.MESSAGE_INPUT_ID_LENGTH + j] = (byte) strId.charAt(j);
			}
		}
		
		return bytesIds;
	}
	private void instanceParams() {
		params = MessageUtils.convertBytesToString(this.getPayload());
	}
}
