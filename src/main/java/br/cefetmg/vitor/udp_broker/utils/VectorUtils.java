package br.cefetmg.vitor.udp_broker.utils;

public class VectorUtils {

	public static byte[] concactByteVector(byte[] vector1, byte[] vector2) {
		
		if (vector1 == null || vector2 == null) return null;
		
		int length = vector1.length + vector2.length;
		
		byte[] vector = new byte[length];
		
		for (int i = 0; i < vector1.length; i++) {
			vector[i] = vector1[i];
		}
		
		for (int i = 0; i < vector2.length; i++) {
			vector[i + vector1.length] = vector2[i];
		}
		
		return vector;
	}
	
	public static byte[] subvector(byte[] vector, int start, int end) {
		
		if (vector == null) return null;
		
//		if (start < 0 || end >= vector.length)
//			throw new ArrayIndexOutOfBoundsException();
			
		byte[] vectorReturn = new byte[end - start];
		
		int count = 0;
		for (int i = start; i < end; i++) {
			vectorReturn[count++] = vector[i];
		}
		
		return vectorReturn;
	}
}
