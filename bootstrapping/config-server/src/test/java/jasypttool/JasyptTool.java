package jasypttool;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;

public class JasyptTool {

    public static final String MASTER_PW = "LocalPW";
    public static final String ALGORITHM = "PBEWithSHA1AndDESede";

    @Test
    public void encrypt() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(MASTER_PW);
        encryptor.setAlgorithm(ALGORITHM);

        final String encrypted = encryptor.encrypt("s3cr3t");

        System.out.println(encrypted);
    }

    @Test
    public void decrypt() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(MASTER_PW);
        encryptor.setAlgorithm(ALGORITHM);

        final String decrypted = encryptor.decrypt("Q3IwXmD+b2Gi0N7zKirbYg==");

        System.out.println(decrypted);
    }
}
