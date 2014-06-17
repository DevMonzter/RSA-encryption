//Resources used:
//http://www.javapractices.com/topic/TopicAction.do?Id=62
//http://www.tutorialspoint.com/java/math/biginteger_pow.htm
//http://www.tutorialspoint.com/java/math/biginteger_remainder.htm
//http://www.cs.utexas.edu/users/mitra/uxFall2004/cs303/assgn/prime.html
//http://aleph0.clarku.edu/~djoyce/numbers/PublicKey.java
//http://pajhome.org.uk/crypt/rsa/contrib/RSA_Project.pdf
//http://introcs.cs.princeton.edu/java/78crypto/RSA.java.html

import java.security.SecureRandom;
import java.util.Random;
import java.math.*;

public class RSA {
    
    private final static SecureRandom rnd = new SecureRandom();
    
    public static void main(String[] args) {
        
        //generates pseudo random exponent, between 64 and 256, to be used in generation of p and q values
    	Random random1 = new Random();
        Random random2 = new Random();
        int Low = 64;
        int High = 256;
        int bitValue = random1.nextInt(High-Low) + Low;
        int bitValue2 = random2.nextInt(High-Low) + Low;
        
        //generates probable prime numbers p and q
        BigInteger p = BigInteger.probablePrime(bitValue, rnd);
        BigInteger q = BigInteger.probablePrime(bitValue2, rnd);

        BigInteger BigInt1 = BigInteger.ONE;
        
        //generates modulus value
        BigInteger n = p.multiply(q);
        
        BigInteger m = (p.subtract(BigInt1)).multiply((q.subtract(BigInt1)));
        
        //uses standard public key value
        BigInteger e = BigInteger.valueOf(65537);
        
        //checks that m and e are coprime; throws ArithmeticException - if value is zero.
        BigInteger coPrime = m.remainder(e);
        
        //generates private key value
        BigInteger d = e.modInverse(m);

        //generates pseudo random number to be used as plain text with a value < n        
        BigInteger plaintextNumber;
        plaintextNumber = new BigInteger((bitValue+bitValue2)-1, new Random());
        
        //BigInteger needed to successfully encrypt/decrypt PRNG value
        BigInteger encrypt = plaintextNumber;
        BigInteger encrypted = encrypt.modPow(e, n);
                
        //C represents encrypted value
        BigInteger C = encrypted.remainder(n);
        
        BigInteger decrypt = C;
        BigInteger decrypted = decrypt.modPow(d, n);
        
        //M represents decrypted, or plaintext, value
        BigInteger M = decrypted.remainder(n);
        
        System.out.println("RSA Algorithm Values");
        System.out.println("p: " + p);
        System.out.println("q: " + q);
        System.out.println("n: " + n);
        System.out.println("m: " + m);
        System.out.println("e: " + e);
        System.out.println("d: " + d);
        System.out.println("\nPublic Key: (" + e + ", " + n +")");
        System.out.println("Private Key: (" + d + ", " + n +")");
        System.out.println("\nPlaintext Value: " + plaintextNumber);
        System.out.println("Encryption Value: " + C);
        System.out.println("Decryption Value: " + M);
    }

}