package com.card.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文件处理共通类
 * </p>
 * 文件处理共通类
 *
 * @author DeanJacky
 * @version 1.0
 */
public class FileUtils {

    /**
     * <p>
     * 复制文件
     * </p>
     * 复制文件
     *
     * @param src
     *            源文件
     * @param target
     *            目标路径
     * @return true:处理成功
     */
    public static boolean copyFile(File src, String target) throws Exception {
        System.out.println("复制文件到："+target);
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        BufferedOutputStream bos = null;
        try {
            is = new FileInputStream(src);
            bis = new BufferedInputStream(is);

            File targetFile = new File(target);
            if (!targetFile.exists()) {
                new File(targetFile.getParent()).mkdirs();
                targetFile.createNewFile();
            }

            os = new FileOutputStream(targetFile);
            bos = new BufferedOutputStream(os);

            byte[] bts = new byte[1024];
            int by = -1;
            while ((by = bis.read(bts)) != -1) {
                bos.write(bts, 0, by);
                bos.flush();
            }
        } finally {
            if (bos != null) {
                bos.close();
            }
            if (bis != null) {
                bis.close();
            }
        }
        return true;
    }

    public static double getSize(File f) {
        long size = 0;
        FileChannel fc = null;
        try {
            if (f.exists() && f.isFile()) {
                FileInputStream fis = new FileInputStream(f);
                fc = fis.getChannel();
                size = fc.size();
                fis.close();
            }
        } catch (Exception e) {
            try {
                fc.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return (double) (size / 1024 / 1024);
    }

    /**
     * 读取文件内容
     * 
     * @param filePath
     * @return
     */
    public static String read(String filePath) {
        BufferedReader br = null;
        String line = null;
        StringBuffer buf = new StringBuffer();

        try {
            // 根据文件路径创建缓冲输入流
            br = new BufferedReader(new FileReader(filePath));

            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
            while ((line = br.readLine()) != null) {
                buf.append(line);
                buf.append(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                }
            }
        }

        return buf.toString();
    }

    /**
     * 将内容回写到文件中
     * 
     * @param filePath
     * @param content
     */
    public static void write(String filePath, String content) {
        BufferedWriter bw = null;

        try {
            // 根据文件路径创建缓冲输出流
            bw = new BufferedWriter(new FileWriter(filePath));
            // 将内容写入文件中
            bw.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    bw = null;
                }
            }
        }
    }

    /**
     * 按行写入到文件内
     * 
     * @param path
     * @param lines
     * @throws Exception
     */
    public static void writeLines(String path, String[] lines) throws Exception {
        File file = new File(path);
        // 文件不存在时创建新文件
        if (!file.exists())
            file.createNewFile();

        // 按行写入文件
        FileWriter writer = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(writer);
        for (String line : lines) {
            bw.write(line);
            bw.newLine();
        }
        bw.close();
        writer.close();
    }

    /**
     * 读取文件内的所有行
     * 
     * @param path
     * @return
     */
    public static List<String> readLines(String path) throws Exception {
        FileReader reader = new FileReader(path);
        BufferedReader br = new BufferedReader(reader);
        ArrayList<String> lines = new ArrayList<String>();
        String str = null;
        while ((str = br.readLine()) != null) {
            lines.add(str);
            str = null;
        }
        br.close();
        reader.close();
        return lines;
    }

    public static void fileAppender(String fileName, String content) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = null;
        // 一行一行的读
        StringBuilder sb = new StringBuilder();
        sb.append(content);
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\r\n");
        }
        reader.close();

        // 写回去
        RandomAccessFile mm = new RandomAccessFile(fileName, "rw");
        mm.writeBytes(sb.toString());
        mm.close();
    }
    
    /**
     * 删除文件夹内的所有文件
     * @Description
     * @param path
     */
    public static void deleteAllFilesOfDir(File path) {
        if (!path.exists())
            return;
        if (path.isFile()) {
            path.delete();
            return;
        }
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            deleteAllFilesOfDir(files[i]);
        }
        path.delete();
    }
}
