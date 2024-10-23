// createTransaction.js
const { Transaction, SystemProgram, PublicKey, Connection, clusterApiUrl } = require('@solana/web3.js');

async function createTransaction(information, publicKeyBase58) {
    // Set up a connection to the Devnet
    const connection = new Connection(clusterApiUrl('devnet'), 'confirmed');

    // Create a transaction object
    const transaction = new Transaction();

    // Decode the public key
    const publicKey = new PublicKey(publicKeyBase58);

    // Example transaction details
    // Adjust this logic according to the `information` parameter if needed
    const instructions = SystemProgram.transfer({
        fromPubkey: publicKey,
        toPubkey: new PublicKey('recipientPublicKeyHere'), // Replace with actual recipient
        lamports: 1000, // Example: 1000 lamports (0.000001 SOL)
    });

    // Add instructions to the transaction
    transaction.add(instructions);

    // Get the recent blockhash to ensure the transaction is up-to-date
    const { blockhash } = await connection.getLatestBlockhash('confirmed');
    transaction.recentBlockhash = blockhash;
    transaction.feePayer = publicKey;

    // Serialize the transaction to Base64 (without signing)
    const serializedTransaction = transaction.serialize({ verifySignatures: false }).toString('base64');

    console.log(serializedTransaction);
}

// Get the input arguments from the command line
const information = process.argv[2];
const publicKeyBase58 = process.argv[3];

if (!information || !publicKeyBase58) {
    console.error('Please provide both information and publicKey as arguments.');
    process.exit(1);
}

createTransaction(information, publicKeyBase58);
