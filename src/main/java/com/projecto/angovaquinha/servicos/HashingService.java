package com.projecto.angovaquinha.servicos;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class HashingService {

    private static final int SALT_LENGTH = 16;

    public String gerarHash(String senha) {
        try {
            byte[] salt = new byte[SALT_LENGTH];
            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(senha.getBytes());

            byte[] saltAndHash = new byte[SALT_LENGTH + hashedPassword.length];
            System.arraycopy(salt, 0, saltAndHash, 0, SALT_LENGTH);
            System.arraycopy(hashedPassword, 0, saltAndHash, SALT_LENGTH, hashedPassword.length);

            return Base64.getEncoder().encodeToString(saltAndHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verificarHash(String senha, String hashBase64) {
        try {
            // Decodificar o hashBase64
            byte[] saltAndHash = Base64.getDecoder().decode(hashBase64);

            // Extrair o salt
            byte[] salt = new byte[SALT_LENGTH];
            System.arraycopy(saltAndHash, 0, salt, 0, SALT_LENGTH);

            // Extrair o hash
            byte[] hash = new byte[saltAndHash.length - SALT_LENGTH];
            System.arraycopy(saltAndHash, SALT_LENGTH, hash, 0, hash.length);

            // Hash a senha com o salt extraído
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(senha.getBytes());

            // Comparar o hash calculado com o hash armazenado
            return MessageDigest.isEqual(hashedPassword, hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
