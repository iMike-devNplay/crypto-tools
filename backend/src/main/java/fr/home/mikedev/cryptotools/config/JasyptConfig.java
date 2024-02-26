package fr.home.mikedev.cryptotools.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JasyptConfig
{
    public static Logger logger = LoggerFactory.getLogger(JasyptConfig.class);
    
    @Value("${masterkey.path}")
    String masterkeyfile;
    
    @Autowired StringEncryptor stringEncryptor;
    
    @Bean(name = "jasyptStringEncryptor")
    @Primary
    public StringEncryptor stringEncryptor() 
    {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(getMasterKey());
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("8");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        
        return encryptor;
    }
    
    private String getMasterKey()
    {
        logger.info(String.format("Masterkey PATH = %s", masterkeyfile));
        String key = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(masterkeyfile)))
        {
            key = reader.readLine();
            reader.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return key;
    }
}
