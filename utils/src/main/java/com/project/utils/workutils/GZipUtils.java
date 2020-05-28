package com.project.utils.workutils;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipUtils {
    public static final int BUFFER = 1024;
    public static final String EXT = ".gz";

    /**
     * 文件压缩
     *
     * @param path
     * @throws Exception
     */
    public static void compress(String path){
        compress(path, true);
    }
    /**
     * 文件压缩
     *
     * @param path
     * @param delete
     *            是否删除原始文件
     * @throws Exception
     */
    public static void compress(String path, boolean delete){
        File file = new File(path);
        compress(file, delete);
    }

    /**
     * 文件压缩
     *
     * @param file
     * @throws Exception
     */
    public static void compress(File file){
        compress(file, true);
    }
    /**
     * 文件压缩
     *
     * @param file
     * @param delete
     *            是否删除原始文件
     * @throws Exception
     */
    public static void compress(File file, boolean delete){
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(file.getPath() + EXT);
            compress(fis, fos);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IoUtils.close(fis,fos);
        }

        if (delete) {
            file.delete();
        }
    }

    public static void compress(InputStream is, OutputStream os) {
        GZIPOutputStream gos = null;
        try {
            gos = new GZIPOutputStream(os);
            int count;
            byte data[] = new byte[BUFFER];
            while ((count = is.read(data, 0, BUFFER)) != -1) {
                gos.write(data, 0, count);
            }
            gos.finish();
            gos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtils.close(gos);
        }

    }

    /**
     * 数据解压缩
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] decompress(byte[] data){
        ByteArrayInputStream bais = null;
        ByteArrayOutputStream baos = null;
        // 解压缩
        try {
            bais = new ByteArrayInputStream(data);
            baos = new ByteArrayOutputStream();
            decompress(bais, baos);
            data = baos.toByteArray();
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtils.close(bais,baos);
        }


        return data;
    }
    /**
     * 文件解压缩
     *
     * @param file
     * @throws Exception
     */
    public static void decompress(File file){
        decompress(file, true);
    }

    /**
     * 文件解压缩
     *
     * @param file
     * @param delete
     *            是否删除原始文件
     * @throws Exception
     */
    public static void decompress(File file, boolean delete){
        FileInputStream fis =null;
        FileOutputStream fos =null;
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(file.getPath().replace(EXT,
                    ""));
            decompress(fis, fos);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtils.close(fis,fos);
        }

        if (delete) {
            file.delete();
        }
    }
    /**
     * 数据解压缩
     *
     * @param is
     * @param os
     * @throws Exception
     */
    public static void decompress(InputStream is, OutputStream os){

        GZIPInputStream gis = null;
        try {
            gis = new GZIPInputStream(is);
            int count;
            byte data[] = new byte[BUFFER];
            while ((count = gis.read(data, 0, BUFFER)) != -1) {
                os.write(data, 0, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtils.close(gis);
        }
    }

    /**
     * 文件解压缩
     *
     * @param path
     * @throws Exception
     */
    public static void decompress(String path){
        decompress(path, true);
    }

    /**
     * 文件解压缩
     *
     * @param path
     * @param delete
     *            是否删除原始文件
     * @throws Exception
     */
    public static void decompress(String path, boolean delete){
        File file = new File(path);
        decompress(file, delete);
    }
}
