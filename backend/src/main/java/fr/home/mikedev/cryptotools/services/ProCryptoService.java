package fr.home.mikedev.cryptotools.services;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class ProCryptoService implements ICryptoService
{
    
    public String encrypt(String masterKey, String password)
    {
        try
        {
            PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
            SimpleStringPBEConfig config = new SimpleStringPBEConfig();
            config.setPassword(masterKey);
            config.setAlgorithm("PBEWithMD5AndTripleDES");
            config.setPoolSize("4");
            encryptor.setConfig(config);
            
            return encryptor.encrypt(password);
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    public String decrypt(String masterKey, String passwordEncrypted)
    {
        try
        {
            PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
            SimpleStringPBEConfig config = new SimpleStringPBEConfig();
            config.setPassword(masterKey);
            config.setAlgorithm("PBEWithMD5AndTripleDES");
            config.setPoolSize("4");
            encryptor.setConfig(config);
            
            return encryptor.decrypt(passwordEncrypted);
        }
        catch(Exception ex)
        {
            return null;
        }
    }
}
