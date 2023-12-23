package fr.home.mikedev.cryptotools.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.home.mikedev.cryptotools.dtos.CryptoDto;
import fr.home.mikedev.cryptotools.services.CryptoService;


@RestController
public class CryptoController 
{
    public static Logger logger = LoggerFactory.getLogger(CryptoController.class);
    
    @Autowired CryptoService cryptoService;
    
    @PostMapping("/api/encrypt")
    public ResponseEntity<CryptoDto> encrypt(@RequestBody CryptoDto cryptoDto)
    {
        try 
        {
            return new ResponseEntity<>(CryptoDto.builder().password(cryptoService.encrypt(cryptoDto.getMasterKey(), cryptoDto.getPassword())).build(), HttpStatus.OK);
        } 
        catch (Exception e) 
        {
            logger.error("encrypt()", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
    
    @PostMapping("/api/decrypt")
    public ResponseEntity<CryptoDto> decrypt(@RequestBody CryptoDto cryptoDto)
    {
        try 
        {
            return new ResponseEntity<>(CryptoDto.builder().password(cryptoService.decrypt(cryptoDto.getMasterKey(), cryptoDto.getPassword())).build(), HttpStatus.OK);
        } 
        catch (Exception e) 
        {
            logger.error("decrypt()", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
}
