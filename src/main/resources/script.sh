#!/bin/bash

# Generate RSA key pair
openssl genrsa -out keypair.pem 2048

# Extract the public key
openssl rsa -in keypair.pem -pubout -out publicKey.pem

# Convert the private key to PKCS#8 format
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out privateKey.pem

# Clean up the original key pair file
rm keypair.pem

echo "RSA keys and keystore generated: publicKey.pem, privateKey.pem"