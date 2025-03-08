package com.example.demo.service;

import com.azure.security.keyvault.secrets.SecretClient;
import org.springframework.stereotype.Service;

@Service
public class KeyVaultService {

    private final SecretClient secretClient;

    public KeyVaultService(SecretClient secretClient) {
        this.secretClient = secretClient;
    }

    /**
     * Retrieves a secret from Azure Key Vault.
     */
    public String getSecret(String secretName) {
        try {
            return secretClient.getSecret(secretName).getValue();
        } catch (Exception e) {
            System.out.println("❌ ERROR: Unable to retrieve secret '" + secretName + "' from Key Vault.");
            return null;
        }
    }

    /**
     * Debugging: Print database credentials from Key Vault.
     */
    public void printDatabaseCredentials() {
        System.out.println("🔐 Retrieved from Key Vault:");
        System.out.println(" - db-username = " + getSecret("db-username"));
        System.out.println(" - db-password = ******** (Hidden for security)");
    }
}
