package com.diego.reputacaoservice.services;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.AES256TextEncryptor;

@Slf4j
public class MainTeste {

    public String decrypt(String text) {
        try {
            AES256TextEncryptor encryptor = initEncryptor();
            return encryptor.decrypt(text);
        } catch (EncryptionOperationNotPossibleException exception) {
            log.error("Erro de criptografia");
            return "";
        }
    }

    private AES256TextEncryptor initEncryptor() {
        String passwordKey = "123";
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(passwordKey);
        return encryptor;
    }

    public static void main(String[] args) {
        String text = "tvoxcred.mktpay";
        String passwordKey = "#C@RD5.4P1$";
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(passwordKey);
        System.out.println("Encrypting: " + text);
        String data = encryptor.encrypt(text);
        System.out.println("Encrypted text: " + data);
        System.out.println("Decrypted text: " + encryptor.decrypt(data));

        String senhaEncriptada = "lbrEQSJhpMK7OO1e9g3Xr0JXkxFj9wSi39093nAu07O5CrPr/IGTkIqsGLNxrT3Z";
        System.out.println("Senha bandeira decriptada: "+encryptor.decrypt(senhaEncriptada));


    }

}
