 Linear Feedback Shift Register (LFSR) Encryption in Java

📌 Overview

This project demonstrates how to implement a Linear Feedback Shift Register (LFSR) in Java for stream cipher encryption. The LFSR generates a pseudorandom key stream which is then used to encrypt and decrypt text using the XOR operation (modulo 2 addition). This type of encryption is fundamental in digital communications and cryptography.

⚙️ How It Works

The LFSR operates by shifting a binary seed to the right while calculating a feedback bit based on selected "tap" positions. The feedback bit is computed using modulo 2 addition (binary XOR) of bits at the specified tap positions. The key features of this implementation include:

Seed Input: A binary string (e.g., 1100101) representing the initial state.

Tap Positions: Indices in the seed that are used to compute feedback bits.

Key Stream Generation: Produces a pseudorandom binary stream from the LFSR.

Text Encryption & Decryption: Uses XOR between the plaintext and keystream to produce ciphertext, and the same process is used for decryption.


🛠 Features

Simple implementation of LFSR-based stream cipher

ASCII to binary conversion and vice versa

Key stream generation using tap-based feedback

XOR-based encryption and decryption

Works with any plaintext input length

💡 How to Use

Open the LFSR.java file in IntelliJ IDEA or any Java-compatible IDE.

Run the main method.

Follow the interactive prompts:

Enter a binary seed.

Enter the number of tap positions.

Provide space-separated tap indices (0-based).

Enter the plaintext to encrypt.

The program will:

Generate a key stream based on the LFSR.

Encrypt the input using XOR.

Decrypt it back using the same keystream.

🧩 File Breakdown

LFSR.java – Contains:

Feedback() – Core LFSR shift + feedback mechanism

generate_sequence() – Produces keystream of required length

XOR_cipher() – Performs XOR-based encryption and decryption

textToBits() – Converts ASCII to binary

bitsToText() – Converts binary back to ASCII

📚 References

Schneier, Bruce. Applied Cryptography. Wiley, 2nd Edition, 1996.

👤 Author
Developed by Basmala Nabil El-Sayed
Faculty of Computer Science – Cairo University (International Branch)
Department: Networking and Cybersecurity
