import java.util.Scanner;

public class CRC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 4-bit data (e.g., 1011): ");
        String data = scanner.next();

        // Encode using Hamming (7,4)
        int[] encodedData = encodeHamming(data);
        System.out.print("Encoded Data: ");
        displayArray(encodedData);

        // Simulate a single-bit error at a random position
        encodedData[3] = (encodedData[3] == 0) ? 1 : 0; // Flip bit 3 (simulated error)
        System.out.print("Received Data (with error): ");
        displayArray(encodedData);

        // Decode and correct errors
        decodeHamming(encodedData);
        scanner.close();
    }

    public static int[] encodeHamming(String data) {
        int[] d = new int[4]; // Data bits
        int[] p = new int[3]; // Parity bits
        int[] encoded = new int[7];

        for (int i = 0; i < 4; i++) d[i] = Character.getNumericValue(data.charAt(i));

        // Compute parity bits
        p[0] = d[0] ^ d[1] ^ d[3];
        p[1] = d[0] ^ d[2] ^ d[3];
        p[2] = d[1] ^ d[2] ^ d[3];

        // Arrange encoded bits
        encoded[0] = p[0];
        encoded[1] = p[1];
        encoded[2] = d[0];
        encoded[3] = p[2];
        encoded[4] = d[1];
        encoded[5] = d[2];
        encoded[6] = d[3];

        return encoded;
    }

    public static void decodeHamming(int[] received) {
        int[] syndrome = new int[3];
        syndrome[0] = received[0] ^ received[2] ^ received[4] ^ received[6];
        syndrome[1] = received[1] ^ received[2] ^ received[5] ^ received[6];
        syndrome[2] = received[3] ^ received[4] ^ received[5] ^ received[6];

        int errorPosition = syndrome[0] * 1 + syndrome[1] * 2 + syndrome[2] * 4;

        if (errorPosition == 0) {
            System.out.println("No errors detected.");
        } else {
            System.out.println("Error detected at position: " + errorPosition);
            received[errorPosition - 1] ^= 1; // Correct the error
            System.out.print("Corrected Data: ");
            displayArray(received);
        }
    }

    public static void displayArray(int[] arr) {
        for (int bit : arr) System.out.print(bit + " ");
        System.out.println();
    }
}
