package org.yeastrc.limelight.xml.magnumtpp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class HashUtils {

    /**
     * Get SHA-384 hash for the given file
     *
     * @param inputFile
     * @return
     * @throws Exception
     */
    public static byte[] compute_file_sha_384_hash( File inputFile ) throws Exception {

        MessageDigest messageDigest = MessageDigest.getInstance( "SHA-384" );

        try (FileInputStream fis = new FileInputStream( inputFile ); ) {

            byte[] dataBytes = new byte[4096];
            int numberBytesRead = 0;

            while ( ( numberBytesRead = fis.read( dataBytes ) ) != -1 ) {

                messageDigest.update( dataBytes, 0, numberBytesRead );
            }
        }

        return messageDigest.digest();
    }

    /**
     * Convert a byte array to a hex string
     *
     * @param hashBytes
     * @return
     */
    public static String get_HashBytes_As_HexString( byte[] hashBytes ) {

        StringBuilder hashBytesAsHexSB = new StringBuilder( hashBytes.length * 2 + 2 );

        for ( int i = 0; i < hashBytes.length; i++ ) {
            String byteAsHex = Integer.toHexString( Byte.toUnsignedInt( hashBytes[ i ] ) );
            if ( byteAsHex.length() == 1 ) {
                hashBytesAsHexSB.append( "0" ); //  Leading zero dropped by 'toHexString' so add here
            }
            hashBytesAsHexSB.append( byteAsHex );
        }

        String result = hashBytesAsHexSB.toString();

        return result;
    }

}
