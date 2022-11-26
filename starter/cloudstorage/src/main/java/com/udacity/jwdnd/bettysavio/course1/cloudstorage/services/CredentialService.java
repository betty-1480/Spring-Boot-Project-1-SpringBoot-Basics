package com.udacity.jwdnd.bettysavio.course1.cloudstorage.services;

import com.udacity.jwdnd.bettysavio.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.Credentials;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Random;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService=encryptionService;
    }

    public List<Credentials> getAllCredentials(Integer userid){
       return credentialMapper.getAllCredentials(userid);
    }

    public Credentials getCredentialById(Integer credentialId){
       return credentialMapper.getCredentialById(credentialId);
    }

    public Integer insertCredentials(CredentialForm credentials, Integer userid){
        byte [] key=new byte[16];
        Random random=new Random();
        random.nextBytes(key);
        System.out.println("Key:"+key);
        String encoded = Base64.getEncoder().encodeToString(key);
        System.out.println("Encoded Key:"+encoded);
        System.out.println("Password:"+credentials.getPassword());
        String password = encryptionService.encryptValue(credentials.getPassword(), encoded);
        System.out.println("Encrypted Password:"+password);
       return credentialMapper.insertCredentials(new Credentials(null,credentials.getUrl(),credentials.getUsername(),encoded,password,userid));
    }

    public Integer updateCredentials(CredentialForm credentials, Integer userid){
        byte [] key=new byte[16];
        Random random=new Random();
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String password = credentials.getPassword();
        String encryptedPassword = encryptedPassword(password, encodedKey);
        String decryptedPassword = decryptedPassword(encryptedPassword, encodedKey);
        return credentialMapper.updateCredentials(new Credentials(credentials.getCredentialId(),credentials.getUrl(),credentials.getUsername(),encodedKey,encryptedPassword,userid));
    }

    public Integer deleteCredentials(Integer credentialId){
        return credentialMapper.deleteCredentials(credentialId);
    }

    public String encryptedPassword(String password, String encodedKey){
        return encryptionService.encryptValue(password, encodedKey);
    }

    public String decryptedPassword(String encryptedPassword, String encodedKey ){
        return encryptionService.decryptValue(encryptedPassword, encodedKey);
    }

}
