package com.xgf.domain.company;

import java.io.*;

interface A{


}

interface B{}

class BBB{

    public BBB(AAA A){
        System.out.println("BBB：this : " + this + " \t " + A);
    }
}

class AAA{

    public AAA(BBB B){
        System.out.println("AAA：this : " + this + " \t " + B);
    }
}


public class Demo {


    public static void main(String[] args) {
        AAA A = null;
        A = new AAA(new BBB(A));
    }


    /**
     * function: 编写一个程序，将f:\code目录下的所有.txt文件复制 到f:\code2目录下，并将文件的扩展名从.txt改为.doc。
     * author:wangpeng time：2018年8月15日上午11:15:33
     */


/*
    public void test4() {
        File file = new File("F:\\code");
        String str = "F:\\code2";
        copy(file, str);
    }

    public void copy(File file, String str) {
        // TODO Auto-generated method stub
        File[] file1 = file.listFiles();
        for (File f : file1) {
            if (f.isFile() && f.getName().endsWith(".txt")) {
                FileInputStream is = null;
                FileOutputStream os = null;

                try {
                    is = new FileInputStream(f);

                    String s = f.getName().substring(0, f.getName().length() - 4);
                    s = s.concat(".doc");

                    os = new FileOutputStream(new File(str + File.separator + s));
                    int len = -1;
                    byte[] by = new byte[1024 * 4];
                    while ((len = is.read(by)) != -1) {
                        os.write(by, 0, len);
                    }
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    if (is != null)
                        try {
                            is.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    if (os != null)
                        try {
                            os.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                }

            }
        }
    }
*/

}


