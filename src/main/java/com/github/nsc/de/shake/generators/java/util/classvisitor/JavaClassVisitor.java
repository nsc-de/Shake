package com.github.nsc.de.shake.generators.java.util.classvisitor;

import org.json.JSONArray;

import java.io.*;
import java.nio.ByteBuffer;
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

        JavaClassConstant[] cp_size = new JavaClassConstant[const_pool_count];

        System.out.printf("class-version %d, java-version %s, const-pool-count %d%n", class_version,
                resolve_java_version(java_version), const_pool_count);

        for(int i = 0; i < const_pool_count; i++) {

            int tag = in.read();

            System.out.printf("tag: %d, position: %d (%s)%n", tag, in.getPosition(), in.getPositionIdentifier());
            if(tag == 1) {

                int length = (in.read() << 8) + in.read();
                StringBuilder sb = new StringBuilder();
                for(int c = 0; c < length; c++) sb.append((char) in.read());
                cp_size[i] = new JavaClassConstant.JavaClassStringConstant(sb.toString());

            }

            else {

                switch (tag) {
                        // Integer
                    case 3:
                        cp_size[i] = new JavaClassConstant.JavaClassIntegerConstant(ByteBuffer.wrap(readNBytes(in, 4)).getInt());
                        break;
                        // Float
                    case 4:
                        cp_size[i] = new JavaClassConstant.JavaClassFloatConstant(ByteBuffer.wrap(readNBytes(in, 4)).getFloat());
                        break;
                        // Long
                    case 5:
                        cp_size[i] = new JavaClassConstant.JavaClassLongConstant(ByteBuffer.wrap(readNBytes(in, 8)).getLong());
                        break;
                        // Double
                    case 6:
                        cp_size[i] = new JavaClassConstant.JavaClassDoubleConstant(ByteBuffer.wrap(readNBytes(in, 8)).getDouble());
                        break;
                        // Class reference
                    case 7:
                        cp_size[i] = new JavaClassConstant.ClassReference(getUnsignedShort(ByteBuffer.wrap(readNBytes(in, 2))));
                        break;
                        // String reference
                    case 8:
                        cp_size[i] = new JavaClassConstant.StringReference(getUnsignedShort(ByteBuffer.wrap(readNBytes(in, 2))));
                        break;
                        // Field reference
                    case 9:
                        cp_size[i] = new JavaClassConstant.FieldReference(getUnsignedShort(ByteBuffer.wrap(readNBytes(in, 2))),
                                getUnsignedShort(ByteBuffer.wrap(readNBytes(in, 2))));
                        break;
                        // Method reference
                    case 10:
                        cp_size[i] = new JavaClassConstant.MethodReference(getUnsignedShort(ByteBuffer.wrap(readNBytes(in, 2))),
                                getUnsignedShort(ByteBuffer.wrap(readNBytes(in, 2))));
                        break;
                    case 11:
                        cp_size[i] = new JavaClassConstant.InterfaceMethodReference(getUnsignedShort(ByteBuffer.wrap(readNBytes(in, 2))),
                                getUnsignedShort(ByteBuffer.wrap(readNBytes(in, 2))));
                        break;
                    case 12:
                        cp_size[i] = new JavaClassConstant.NameAndTypeDescriptor(getUnsignedShort(ByteBuffer.wrap(readNBytes(in, 2))),
                                getUnsignedShort(ByteBuffer.wrap(readNBytes(in, 2))));
                        break;
                    case 15:
                        cp_size[i] = new JavaClassConstant.MethodHandleDescriptor((byte) in.read(),
                                getUnsignedShort(ByteBuffer.wrap(readNBytes(in, 2))));
                        break;
                    case 16:
                        cp_size[i] = new JavaClassConstant.MethodTypeReference(getUnsignedShort(ByteBuffer.wrap(readNBytes(in, 2))));
                        break;
                    case 17:
                        cp_size[i] = new JavaClassConstant.Dynamic(ByteBuffer.wrap(readNBytes(in, 4)).getInt());
                        break;
                    case 18:
                        cp_size[i] = new JavaClassConstant.InvokeDynamic(readNBytes(in, 4));
                        break;
                    case 19:
                        cp_size[i] = new JavaClassConstant.IdentifyModule(ByteBuffer.wrap(readNBytes(in, 2)).getShort());
                        break;
                    case 20:
                        cp_size[i] = new JavaClassConstant.IdentifyPackage(ByteBuffer.wrap(readNBytes(in, 2)).getShort());
                        break;

                }

            }

        }

        System.out.println(new JSONArray(Arrays.toString(cp_size)).toString());


    }
    
    private static int getUnsignedShort(ByteBuffer buffer) {
        return asUnsignedShort(buffer.getShort());
    }
    
    private static int asUnsignedShort(short s) {
        return s & 0xFFFF;
    }

    private static byte[] readNBytes(InputStream in, int amount) throws IOException {
        byte[] bytes = new byte[amount];
        for(int i = 0; i < amount; i++) bytes[i] = (byte) in.read();
        return bytes;
    }

    public static String resolve_java_version(int v) {
        if(v >= 0x2D && v <= 0x30) return "JDK 1." + (v - 0x2D);
        if(v >= 0x31 && v <= 0x32) return "Java SE " + (v - 0x31) + ".0";
        if(v >= 0x33 && v <= 0x3D) return "Java SE " + (v - 0x33);

        throw new Error("Unknown java version");
    }

}
