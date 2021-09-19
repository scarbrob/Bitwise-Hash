import java.util.*;

/**
 * This is a class that turns a user entered string into binary.
 * @author Ben Scarbrough
 * @Version 1.0
 *
 */
class BitwiseHash {

	/**
	 * A private method to make a binary string of a value with a specified length.
	 * @param val The value to make the string out of.
	 * @param bits The number of bits to be included.
	 * @return A Binary String of the value.
	 */
	private static String makeBinaryString(int val, int bits) {
		String bString = Integer.toBinaryString(val);

		if (bString.length() > bits)
			bString = bString.substring(bString.length() - 8);

		StringBuilder builder = new StringBuilder();
		if (bString.length() < bits) {
			for (int i = bString.length(); i < bits; i++) {
				builder.append("0");
			}
		}
		builder.append(bString);
		return builder.toString();
	}

	/**
	 * A private method that gets the byte of a value.
	 * @param val The value to get the byte of.
	 * @param whichByte Which byte in the value to get.
	 * @return The byte.
	 */
	private static byte getByte(int val, int whichByte) {
		val >>>= (whichByte * 8);
		return (byte) (val & 0b11111111);
	}

	/**
	 * A private method to interact with the user.
	 */
	private static void queryUser() {
		Scanner scan = new Scanner(System.in);
		System.out.println(
				"Give me a string, and I will calculate its hash and the bitwise and, or, and xor of its 4 bytes. Or hit enter to quit.");
		while (true) {
			String input = scan.nextLine();
			if (input.length() == 0) {
				System.out.println("Goodbye!");
				scan.close();
				System.exit(0);
			}
			int hashVal = input.hashCode();
			byte lowByte = getByte(hashVal, 0);
			byte byte1 = getByte(hashVal, 1);
			byte byte2 = getByte(hashVal, 2);
			byte highByte = getByte(hashVal, 3);
			byte andVal = (byte) (lowByte & byte1 & byte2 & highByte);
			byte orVal = (byte) (lowByte | byte1 | byte2 | highByte);
			byte xorVal = (byte) (lowByte ^ byte1 ^ byte2 ^ highByte);

			System.out.println("hash:" + makeBinaryString(hashVal, 32) + " and:" + makeBinaryString(andVal, 8) + " or:"
					+ makeBinaryString(orVal, 8) + " xor:" + makeBinaryString(xorVal, 8));
		}
	}

	// Main Method
	public static void main(String[] args) {
		queryUser();
	}
}
