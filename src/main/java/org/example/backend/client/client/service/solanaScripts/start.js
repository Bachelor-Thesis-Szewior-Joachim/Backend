const express = require('express');
const { exec } = require('child_process');
const app = express();
const PORT = 3001;

app.use(express.json());

app.get('/createAccount', (req, res) => {
    exec('node create.js', (error, stdout, stderr) => {
        if (error) {
            console.error(`Execution error: ${stderr}`);
            return res.status(500).send({ error: 'Failed to create Solana account', details: stderr });
        }
        if (!stdout) {
            console.error('Empty output from create.js');
            return res.status(500).send({ error: 'Empty output from create.js' });
        }

        try {
            const result = JSON.parse(stdout);
            res.send(result);
        } catch (parseError) {
            console.error('Failed to parse JSON output:', parseError);
            return res.status(500).send({ error: 'Invalid JSON output from create.js', details: parseError.message });
        }
    });
});


// Endpoint to request airdrop
app.post('/requestAirdrop', (req, res) => {
    const publicKey = req.body.publicKey;
    if (!publicKey) {
        return res.status(400).send({ error: 'Public key is required' });
    }

    exec(`node airdrop.js ${publicKey}`, (error, stdout, stderr) => {
        if (error) {
            console.error(`Error: ${stderr}`);
            res.status(500).send({ error: 'Failed to request airdrop' });
        } else {
            res.send(JSON.parse(stdout)); // Return parsed JSON response
        }
    });
});

// Endpoint to create a transaction
app.post('/createTransaction', (req, res) => {
    const { publicKey } = req.body;
    if (!publicKey) {
        return res.status(400).send({ error: 'Both information and publicKey are required' });
    }

    exec(`node createTransaction.js ${publicKey}`, (error, stdout, stderr) => {
        if (error) {
            console.error(`Error: ${stderr}`);
            res.status(500).send({ error: 'Failed to create transaction' });
        } else {
            res.send({ transaction: stdout.trim() }); // Send response as base64 encoded transaction
        }
    });
});

app.listen(PORT, () => {
    console.log(`Node.js server running on port ${PORT}`);
});
