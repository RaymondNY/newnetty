package com.project.utils.workutils;
import java.io.*;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
public class CipherUtils {
    /**
     * 使用AES对文件进行加密和解密
     *
     */
    private static String type = "AES";

    /**
     * 把文件srcFile加密后存储为destFile
     * @param srcFile     加密前的文件
     * @param destFile    加密后的文件
     * @param privateKey  密钥
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static void encrypt(String srcFile, String destFile, String privateKey) throws GeneralSecurityException {
        Key key = getKey(privateKey);
        Cipher cipher = Cipher.getInstance(type + "/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        crypt(srcFile, destFile, cipher);
    }

    /**
     * 把文件srcFile解密后存储为destFile
     * @param srcFile     解密前的文件
     * @param destFile    解密后的文件
     * @param privateKey  密钥
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static void decrypt(String srcFile, String destFile, String privateKey) throws GeneralSecurityException {
        Key key = getKey(privateKey);
        Cipher cipher = Cipher.getInstance(type + "/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        crypt(srcFile, destFile, cipher);

    }

    /**
     * 根据filePath创建相应的目录
     * @param filePath      要创建的文件路经
     * @return  file        文件
     * @throws IOException
     */
    private static File mkdirFiles(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();

        return file;
    }

    /**
     * 生成指定字符串的密钥
     * @param secret        要生成密钥的字符串
     * @return secretKey    生成后的密钥
     * @throws GeneralSecurityException
     */
    private static Key getKey(String secret) throws GeneralSecurityException {
        KeyGenerator kgen = KeyGenerator.getInstance(type);
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(secret.getBytes());
        kgen.init(128, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        return secretKey;
    }

    /**
     * 加密解密流
     * @param srcFile        加密解密前的流
     * @param destFile       加密解密后的流
     * @param cipher    加密解密
     * @throws IOException  String srcFile, String destFile
     * @throws GeneralSecurityException
     */
    private static void crypt(String srcFile, String destFile,  Cipher cipher) throws GeneralSecurityException {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(mkdirFiles(destFile));
            int blockSize = cipher.getBlockSize() * 1000;
            int outputSize = cipher.getOutputSize(blockSize);

            byte[] inBytes = new byte[blockSize];
            byte[] outBytes = new byte[outputSize];
            int inLength = 0;
            boolean more = true;
            while (more) {
                inLength = in.read(inBytes);
                if (inLength == blockSize) {
                    int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
                    out.write(outBytes, 0, outLength);
                } else {
                    more = false;
                }
            }
            if (inLength > 0) {
                outBytes = cipher.doFinal(inBytes, 0, inLength);
            }else {
                outBytes = cipher.doFinal();
            }
            out.write(outBytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtils.close(in,out);
        }

    }
}
