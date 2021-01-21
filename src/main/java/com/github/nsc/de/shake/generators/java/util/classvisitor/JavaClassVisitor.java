package com.github.nsc.de.shake.generators.java.util.classvisitor;

import java.io.*;
import java.util.Arrays;

public class JavaClassVisitor {

    public static void main(String[] args) throws IOException {

        if(args.length != 1) throw new Error("Need class file as first argument, and not more than one argument");

        File f = new File(args[0]);
        if(!f.exists()) throw new Error("This class-file does not exist!");

        InputStream in = new BufferedInputStream(new FileInputStream(f));

        processClassInputStream(new CountingInputStream(in));

        in.close();
    }

    public static void processClassInputStream(CountingInputStream in) throws IOException {

        // Default class file start (This identifies the class-file as a class-file. If the
        // stream does not start with these bytes we will throw an Error)
        if(in.read() != 0xCA || in.read() != 0xFE || in.read() != 0xBA || in.read() != 0xBE)
            throw new Error("File seems not to be a class file!");

        // Minor class version number
        int class_version = (in.read() << 8) + in.read();

        // Java version number
        int java_version = (in.read() << 8) + in.read();

        // constant pool count
        int const_pool_count = (in.read() << 8) + in.read() - 1;

        short[][] cpSize = new short[const_pool_count][];

        System.out.printf("class-version %d, java-version %s, const-pool-count %d%n", class_version,
                resolve_java_version(java_version), const_pool_count);

        for(int i = 0; i < const_pool_count; i++) {

            int tag = in.read();

            System.out.printf("tag: %d, position: %d (%s)%n", tag, in.getPosition(), in.getPositionIdentifier());
            if(tag == 1) {
                int length = (in.read() << 8) + in.read();

                cpSize[i] = new short[length+1];
                cpSize[i][0] = 1;
                for(int c = 1; c <= length; c++) cpSize[i][c] = (short) in.read();
                System.out.print("String: ");
                for(int c = 1; c <= length; c++) System.out.print((char) cpSize[i][c]);
                System.out.println();

            }

            else {

                // tag bytes and their following bytes
                byte length = (byte) ((tag == 7 || tag == 8 || tag == 16 || tag == 19 || tag == 20) ? 3                     // 2 bytes
                        : (tag == 15) ? 4                                                                   // 3 bytes
                        : (tag == 3 || tag == 4 || (tag >= 9 && tag <= 12) || tag == 17 || tag == 18) ? 5   // 4 bytes
                        : (tag == 5 || tag == 6) ? 9                                                        // 8 bytes
                        : -1);                                                                              // -1 (error)
                if (length == -1) throw new Error("Wrong tag: File could be corrupt");

                cpSize[i] = new short[length];
                cpSize[i][0] = (short) tag;
                for (byte c = 1; c < length; c++) cpSize[i][c] = (short) in.read();
            }

            System.out.println(Arrays.toString(cpSize[i]));


        }


    }

    public static String resolve_java_version(int v) {
        if(v >= 0x2D && v <= 0x30) return "JDK 1." + (v - 0x2D);
        if(v >= 0x31 && v <= 0x32) return "Java SE " + (v - 0x31) + ".0";
        if(v >= 0x33 && v <= 0x3D) return "Java SE " + (v - 0x33);

        throw new Error("Unknown java version");
    }

}
