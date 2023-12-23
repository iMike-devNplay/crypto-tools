package fr.home.mikedev.cryptotools.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CryptoDto 
{
    private String masterKey;
    private String password;
    
    @Override
    public String toString() {
        return "CryptoDto []";
    }
}
