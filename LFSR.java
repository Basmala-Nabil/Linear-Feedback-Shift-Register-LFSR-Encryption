import java.util.Scanner;

public class LFSR {
    private int[] seed_arr;
    private int[] tap_positions;
    private int tap_size;
    private int seed_size;

    // Constructor to initialize LFSR with seed and tap positions
    public LFSR(String seed, int[] taps, int tapCount, int size) {
        this.seed_size = size;
        seed_arr = new int[seed_size];
        this.tap_size = tapCount;
        tap_positions = new int[tap_size];

        for (int i = 0; i < seed_size; i++) {
            seed_arr[i] = seed.charAt(i) - '0';  // Convert char to int (binary)
        }
        for (int i = 0; i < tap_size; i++) {
            tap_positions[i] = taps[i];
        }
    }

    // Generate next bit using mod 2 instead of XOR
    public int Feedback() {
        int feedback = 0;
        for (int i = 0; i < tap_size; i++) {
            feedback += seed_arr[tap_positions[i]];  // Sum of selected bits
        }
        feedback %= 2;  // Equivalent to XOR in binary

        int next = seed_arr[seed_size - 1]; // Save last bit

        // Shift array right
        for (int i = seed_size - 1; i > 0; i--) {
            seed_arr[i] = seed_arr[i - 1];
        }

        seed_arr[0] = feedback; // Insert feedback at beginning

        return next;
    }

    // Generate a sequence of bits
    public String generate_sequence(int length) {
        StringBuilder sequence = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sequence.append(Feedback());
        }
        return sequence.toString();
    }

    // Encryption/Decryption using mod 2 (Equivalent to XOR)
    public static String XOR_cipher(String input, String keyStream) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            int bit1 = input.charAt(i) - '0';
            int bit2 = keyStream.charAt(i) - '0';
            output.append((bit1 + bit2) % 2); // Perform mod 2 operation
        }
        return output.toString();
    }

    // Convert ASCII text to binary string
    public static String textToBits(String text) {
        StringBuilder bits = new StringBuilder();
        for (char c : text.toCharArray()) {
            bits.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        }
        return bits.toString();
    }

    // Convert binary string to ASCII text
    public static String bitsToText(String bits) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < bits.length(); i += 8) {
            int charCode = Integer.parseInt(bits.substring(i, i + 8), 2);
            text.append((char) charCode);
        }
        return text.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input seed
        System.out.print("Enter seed (binary string): ");
        String seed = scanner.next();
        int size = seed.length();

        // Input tap positions
        System.out.print("Enter number of tap positions (based_0) : ");
        int tapSize = scanner.nextInt();
        int[] tapPositions = new int[tapSize];

        System.out.print("Enter tap positions (space-separated): ");
        for (int i = 0; i < tapSize; i++) {
            tapPositions[i] = scanner.nextInt();
        }

        // Create LFSR object
        LFSR lfsr = new LFSR(seed, tapPositions, tapSize, size);

        // Input plaintext
        scanner.nextLine();
        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.println("Plaintext: " + plaintext);

        // Convert plaintext to bits
        String plaintextBits = textToBits(plaintext);

        int requiredLength = Math.max(100, plaintextBits.length());
        String keyStream = lfsr.generate_sequence(requiredLength);
        System.out.println("Generated KeyStream: " + keyStream);

        // Encrypt the plaintext
        String ciphertextBits = XOR_cipher(plaintextBits, keyStream);
        System.out.println("Ciphertext (bits): " + ciphertextBits);

        // Decrypt the ciphertext
        String decryptedBits = XOR_cipher(ciphertextBits, keyStream);
        String decryptedText = bitsToText(decryptedBits);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}
