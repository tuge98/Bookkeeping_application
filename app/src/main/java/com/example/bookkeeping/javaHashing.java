package com.example.bookkeeping;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


    // class for securing password
    public class javaHashing {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public static String getSecurePassword(String password, byte[] salt) {

            String generatedPassword = null;
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-512");
                md.update(salt);
                byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                generatedPassword = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return generatedPassword;
        }

        public static byte[] getSalt() throws NoSuchAlgorithmException {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            return salt;
        }
}
