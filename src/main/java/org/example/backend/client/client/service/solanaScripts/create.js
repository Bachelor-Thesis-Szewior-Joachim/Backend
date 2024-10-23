const { Keypair } = require('@solana/web3.js');

function createSolanaAccount() {
    const newAccount = Keypair.generate();

    const publicKey = newAccount.publicKey.toBase58();

    const secretKey = Buffer.from(newAccount.secretKey).toString('base64');

    console.log(JSON.stringify({ publicKey, secretKey }));
}

createSolanaAccount();
