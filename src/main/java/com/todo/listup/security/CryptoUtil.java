package com.todo.listup.security;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
public class CryptoUtil {

  public static String encryptBase64(final String value) {
    log.info("CryptoUtil.base64");
    String result = null;

    try {
      result = Base64.getEncoder().encodeToString(value.getBytes());
    } catch (Exception var3) {
      var3.printStackTrace();
    }

    return result;
  }

  public static String encryptSha256(String msg) {
    return encryptSha256(msg, "EMOTION");
  }

  public static String encryptSha256(String msg, String salt) {
    return createSha256(msg, salt);
  }

  private static String createSha256(String msg, String salt) {
    String result = "";
    byte[] byteMsg = msg.getBytes();
    byte[] byteSalt = salt.getBytes();
    byte[] bytes = new byte[byteMsg.length + byteSalt.length];
    System.arraycopy(byteMsg, 0, bytes, 0, byteMsg.length);
    System.arraycopy(byteSalt, 0, bytes, byteMsg.length, byteSalt.length);

    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
      messageDigest.update(bytes);
      byte[] byteData = messageDigest.digest();
      StringBuilder stringBuffer = new StringBuilder();
      byte[] var9 = byteData;
      int var10 = byteData.length;

      for(int var11 = 0; var11 < var10; ++var11) {
        byte byteDatum = var9[var11];
        stringBuffer.append(Integer.toString((byteDatum & 255) + 256, 16).substring(1));
      }

      result = stringBuffer.toString();
    } catch (NoSuchAlgorithmException var13) {
      var13.printStackTrace();
    }

    return result;
  }
}
