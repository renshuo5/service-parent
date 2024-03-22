package com.renshuo.cloud.util;

import java.io.*;

/**
 * Created by Lenovo on 2024/2/22.
 */
public class FileHandlerTest {


    public static void main(String[] args) {

        String inPath = "D:\\备份\\logback.xml";
        String outPath = "D:\\备份\\logback1.txt";

        try {
            t1(inPath,outPath);
//            t2(inPath, outPath);
//            t3(inPath, outPath);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void t3(String inPath, String outPath) throws Exception {

        InputStream in = new FileInputStream(inPath);
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);

        FileWriter fw = new FileWriter(outPath);
        String line  = null;
        while ((line = br.readLine())!=null){
            System.out.println(line);
            fw.write(line);
            fw.write("\n");
        }
        fw.close();
        isr.close();


    }

    private static void t2(String inPath, String outPath) throws Exception  {
        FileReader fr = new FileReader(inPath);

        FileWriter fw = new FileWriter(outPath);
        String line  = null;
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine())!=null){
            System.out.println(line);
            fw.write(line);
        }
        fw.close();
        fr.close();

    }

    private static void t1(String inPath, String outPath) throws Exception {
        InputStream in = new FileInputStream(inPath);
        OutputStream out = new FileOutputStream(outPath);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) > 0) {
            String str =new String(buffer);
            System.out.println(str);
            out.write(buffer, 0, length);

        }
        out.close();
        in.close();

    }


}
