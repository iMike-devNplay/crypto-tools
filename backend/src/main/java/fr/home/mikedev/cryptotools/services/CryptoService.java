package fr.home.mikedev.cryptotools.services;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.stereotype.Service;

@Service
public class CryptoService 
{
    
    public String encrypt(String masterKey, String password)
    {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("8");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");

        config.setPassword(masterKey);
        encryptor.setConfig(config);
        
        return encryptor.encrypt(password);
    }

    public String decrypt(String masterKey, String passwordEncrypted)
    {
        try
        {
            PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
            SimpleStringPBEConfig config = new SimpleStringPBEConfig();
            config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
            config.setKeyObtentionIterations("1000");
            config.setPoolSize("8");
            config.setProviderName("SunJCE");
            config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
            config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
            config.setStringOutputType("base64");
    
            config.setPassword(masterKey);
            encryptor.setConfig(config);
            
            return encryptor.decrypt(passwordEncrypted);
        }
        catch(Exception ex)
        {
            return null;
        }
    }
}
