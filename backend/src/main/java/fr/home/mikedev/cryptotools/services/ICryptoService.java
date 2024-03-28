package fr.home.mikedev.cryptotools.services;

import org.springframework.stereotype.Service;

@Service
public interface ICryptoService 
{
    public String encrypt(String masterKey, String password);
    public String decrypt(String masterKey, String passwordEncrypted);
}
