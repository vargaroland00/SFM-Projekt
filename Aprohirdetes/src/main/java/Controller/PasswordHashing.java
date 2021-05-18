package Controller;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHashing 
{
    private static final SecureRandom RAND = new SecureRandom();
    private static final int ITERATIONS = 65536; //how many times we should perform the hashing algorithm
    private static final int KEY_LENGTH = 512; //the desired length of the resulting cryptographic key, in bits.
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    
    //Optional is a container object used to contain not-null objects. Optional object is used to represent null with absent value. 
    public static Optional<String> generateSalt (final int length) 
    {
        if (length < 1) 
        {
            System.err.println("error in generateSalt: length must be > 0");
            return Optional.empty();
        }
        
        byte[] salt = new byte[length];
        RAND.nextBytes(salt);
        
        //Optional.of - throws NullPointerException if passed parameter is null
        return Optional.of(Base64.getEncoder().encodeToString(salt));
    }
    
    public static Optional<String> hashPassword (String password, String salt) 
    {
        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();
        
        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);
        
        //Finally, note that spec holds all of the information about the algorithm, the original plaintext password, and so on. We really want to delete all of that when we're finished.
        //We're done with the chars array now, so we can clear it out. Here, we set all elements of the array to \000 (the null character).
        Arrays.fill(chars, Character.MIN_VALUE);
        
        try 
        {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));
        } 
        catch (NoSuchAlgorithmException | InvalidKeySpecException ex) 
        {
            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();
        } 
        finally 
        {
            spec.clearPassword();
        }
    }
    
    public static boolean verifyPassword (String password, String key, String salt) 
    {
        Optional<String> optEncrypted = hashPassword(password, salt);
        if (!optEncrypted.isPresent()) return false;
        return optEncrypted.get().equals(key);
    }
}

