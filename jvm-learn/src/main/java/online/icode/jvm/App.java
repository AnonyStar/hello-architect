package online.icode.jvm;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        decryptMode();
    }


    public static void decryptMode() {


        String key = "DFDEFC4255D38DBB9FCE83589BA6793948582E5A0FD82510"; // 密钥长度变成 192bit

        String src = "H:\\newlandwork\\project\\TestCommon\\KH20210202.591.B2_001(1).001";

        String des = "H:\\bosscselog\\KH20210122.591.B2_001.0011";
        CryptoUtils cryptoUtils = new CryptoUtils();
        try {
            String decryptFile = CryptoUtils.tripleDesDecryptFile(key, src);
            System.out.println(decryptFile);
        } catch (Exception e) {
            e.printStackTrace();

        }
        try {
            CryptoUtils.tripleDesDecryptFile(key,src,des);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
