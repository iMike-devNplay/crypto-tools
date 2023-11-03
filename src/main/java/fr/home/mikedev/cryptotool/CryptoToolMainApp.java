package fr.home.mikedev.cryptotool;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class CryptoToolMainApp {

    public static void main(String[] args) 
    {
    }
    
    private static void generateCryptedPassword(String app, String env, String masterKey, String webservicePassword, String dbPassword)
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

        System.out.println("##############################");
        System.out.println(String.format("########## %s", app));
        System.out.println("##############################");
        System.out.println(String.format("###### %s", env));
        config.setPassword(masterKey);
        encryptor.setConfig(config);
        System.out.println(String.format("Webservices-password : %s", encryptor.encrypt(webservicePassword)));
        System.out.println(String.format("DB-password : %s", encryptor.encrypt(dbPassword)));        
    }

}
