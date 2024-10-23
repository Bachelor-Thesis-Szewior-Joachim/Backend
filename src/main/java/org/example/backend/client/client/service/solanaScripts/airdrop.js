const { Connection, clusterApiUrl, PublicKey, LAMPORTS_PER_SOL } = require('@solana/web3.js');

const connection = new Connection(clusterApiUrl('devnet'), 'confirmed');

async function requestAirdrop(publicKeyBase58) {
    try {
        const publicKey = new PublicKey(publicKeyBase58);

        const airdropSignature = await connection.requestAirdrop(
            publicKey,
            1 * LAMPORTS_PER_SOL
        );

        await connection.confirmTransaction(airdropSignature, 'confirmed');

        console.log(JSON.stringify({
            publicKey: publicKeyBase58,
            message: 'Airdrop of 1 SOL completed',
            transactionSignature: airdropSignature
        }));
    } catch (error) {
        console.error('Airdrop failed:', error);
        console.log(JSON.stringify({
            publicKey: publicKeyBase58,
            message: 'Airdrop failed',
            error: error.message
        }));
    }
}

const publicKeyBase58 = process.argv[2];
if (!publicKeyBase58) {
    console.error('Please provide a public key as an argument.');
    process.exit(1);
}

requestAirdrop(publicKeyBase58);
