const { Transaction, SystemProgram, PublicKey, Connection, clusterApiUrl } = require('@solana/web3.js');

async function createTransaction(publicKeyBase58) {

    try {
        const connection = new Connection(clusterApiUrl('devnet'), 'confirmed');

        const transaction = new Transaction();

        const publicKey = new PublicKey(publicKeyBase58);

        const instructions = SystemProgram.transfer({
            fromPubkey: publicKey,
            toPubkey: publicKey,
            lamports: 1000,
        });
        transaction.add(instructions);

        const { blockhash, lastValidBlockHeight } = await connection.getLatestBlockhash('confirmed');
        transaction.recentBlockhash = blockhash;
        transaction.lastValidBlockHeight = lastValidBlockHeight;
        transaction.feePayer = publicKey;


        const serializedTransaction = transaction.serialize({
            requireAllSignatures: false,
            verifySignatures: false,
        }).toString('base64');

        console.log('Serialized Transaction:', serializedTransaction);
    } catch (error) {
        console.error('Error creating transaction:', error.message);
    }
}

const publicKeyBase58 = process.argv[2];

if (!publicKeyBase58) {
    console.error('Please provide publicKey as argument.');
    process.exit(1);
}

createTransaction(publicKeyBase58);
