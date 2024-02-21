package fr.home.mikedev.cryptotools.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.home.mikedev.cryptotools.dtos.CryptoDto;
import fr.home.mikedev.cryptotools.services.ICryptoService;
import fr.home.mikedev.cryptotools.services.PersoCryptoService;
import fr.home.mikedev.cryptotools.services.ProCryptoService;

@CrossOrigin
@RestController
public class CryptoController 
{
    public static Logger logger = LoggerFactory.getLogger(CryptoController.class);
    
    @PostMapping("/api/encrypt/{version}")
    public ResponseEntity<CryptoDto> encryptPro(@PathVariable String version, @RequestBody CryptoDto cryptoDto)
    {
        try 
        {
        	logger.info(version);
        	logger.info(cryptoDto.getMasterKey() + " ; " + cryptoDto.getPassword());
        	ICryptoService cryptoService = getCryptoService(version);
        	if (cryptoService == null) return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
            return new ResponseEntity<>(CryptoDto.builder().password(cryptoService.encrypt(cryptoDto.getMasterKey(), cryptoDto.getPassword())).build(), HttpStatus.OK);
        } 
        catch (Exception e) 
        {
            logger.error("encrypt()", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
    
    @PostMapping("/api/decrypt/{version}")
    public ResponseEntity<CryptoDto> decryptPro(@PathVariable String version,@RequestBody CryptoDto cryptoDto)
    {
        try 
        {
        	logger.info(version);
        	logger.info(cryptoDto.getMasterKey() + " ; " + cryptoDto.getPassword());
        	ICryptoService cryptoService = getCryptoService(version);
        	if (cryptoService == null) return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
            return new ResponseEntity<>(CryptoDto.builder().password(cryptoService.decrypt(cryptoDto.getMasterKey(), cryptoDto.getPassword())).build(), HttpStatus.OK);
        } 
        catch (Exception e) 
        {
            logger.error("decrypt()", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
    
    private ICryptoService getCryptoService(String version)
    {
    	if ("PRO".equals(version)) return new ProCryptoService();
    	else if ("PERSO".equals(version)) return new PersoCryptoService();
    	else return null;
    }
}
