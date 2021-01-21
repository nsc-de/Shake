package com.github.nsc.de.shake.generators.java.util.classvisitor;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class JavaClassConstant {

    public abstract byte getTag();

    public JSONObject toJson() {
        return new JSONObject().put("tag_type", getClass().getSimpleName()).put("tag", getTag());
    }

    @Override
    public String toString() {
        return toJson().toString();
    }

    public static class JavaClassStringConstant extends JavaClassConstant {

        private final String value;

        public JavaClassStringConstant(String value) {
            this.value = value;
        }

        @Override
        public byte getTag() {
            return 1;
        }

        public String getValue() {
            return value;
        }

        public JSONObject toJson() {
            return super.toJson().put("value", value);
        }

    }

    public static class JavaClassIntegerConstant extends JavaClassConstant {

        private final int value;

        public JavaClassIntegerConstant(int value) {
            this.value = value;
        }

        @Override
        public byte getTag() {
            return 3;
        }

        public int getValue() {
            return value;
        }

        @Override
        public JSONObject toJson() {
            return super.toJson().put("value", value);
        }

    }

    public static class JavaClassFloatConstant extends JavaClassConstant {

        private final float value;

        public JavaClassFloatConstant(float value) {
            this.value = value;
        }

        @Override
        public byte getTag() {
            return 4;
        }

        public float getValue() {
            return value;
        }

        @Override
        public JSONObject toJson() {
            return super.toJson().put("value", value);
        }

    }

    public static class JavaClassLongConstant extends JavaClassConstant {

        private final long value;

        public JavaClassLongConstant(long value) {
            this.value = value;
        }

        @Override
        public byte getTag() {
            return 5;
        }

        public long getValue() {
            return value;
        }

        @Override
        public JSONObject toJson() {
            return super.toJson().put("value", value);
        }

    }

    public static class JavaClassDoubleConstant extends JavaClassConstant {

        private final double value;

        public JavaClassDoubleConstant(double value) {
            this.value = value;
        }

        @Override
        public byte getTag() {
            return 5;
        }

        public double getValue() {
            return value;
        }

        @Override
        public JSONObject toJson() {
            return super.toJson().put("value", value);
        }

    }

    public static class ClassReference extends JavaClassConstant {

        private final int value;

        public ClassReference(int value) {
            this.value = value;
        }

        @Override
        public byte getTag() {
            return 6;
        }

        public double getValue() {
            return value;
        }

        @Override
        public JSONObject toJson() {
            return super.toJson().put("value", value);
        }

    }

    public static class StringReference extends JavaClassConstant {

        private final int value;

        public StringReference(int value) {
            this.value = value;
        }

        @Override
        public byte getTag() {
            return 8;
        }

        public double getValue() {
            return value;
        }

        @Override
        public JSONObject toJson() {
            return super.toJson().put("value", value);
        }

    }

    public static class FieldReference extends JavaClassConstant {

        private final int classRef;
        private final int nameTypeRef;

        public FieldReference(int classRef, int nameTypeRef) {
            this.classRef = classRef;
            this.nameTypeRef = nameTypeRef;
        }

        @Override
        public byte getTag() {
            return 9;
        }

        public int getClassReference() {
            return classRef;
        }

        public int getNameTypeReference() {
            return nameTypeRef;
        }

        @Override
        public JSONObject toJson() {
            return super.toJson().put("classRef", classRef).put("nameTypeRef", nameTypeRef);
        }

    }

    public static class MethodReference extends JavaClassConstant {

        private final int classRef;
        private final int nameTypeRef;

        public MethodReference(int classRef, int nameTypeRef) {
            this.classRef = classRef;
            this.nameTypeRef = nameTypeRef;
        }

        @Override
        public byte getTag() {
            return 10;
        }

        public int getClassReference() {
            return classRef;
        }

        public int getNameTypeReference() {
            return nameTypeRef;
        }

        @Override
        public JSONObject toJson() {
            return super.toJson().put("classRef", classRef).put("nameTypeRef", nameTypeRef);
        }
    }

    public static class InterfaceMethodReference extends JavaClassConstant {

        private final int classRef;
        private final int nameTypeRef;

        public InterfaceMethodReference(int classRef, int nameTypeRef) {
            this.classRef = classRef;
            this.nameTypeRef = nameTypeRef;
        }

        @Override
        public byte getTag() {
            return 10;
        }

        public int getClassReference() {
            return classRef;
        }

        public int getNameTypeReference() {
            return nameTypeRef;
        }

        @Override
        public JSONObject toJson() {
            return super.toJson().put("classRef", classRef).put("nameTypeRef", nameTypeRef);
        }
    }

    public static class NameAndTypeDescriptor extends JavaClassConstant {

        private final int name;
        private final int type;

        public NameAndTypeDescriptor(int name, int type) {
            this.name = name;
            this.type = type;
        }

        @Override
        public byte getTag() {
            return 12;
        }

        public int getType() {
            return type;
        }

        public int getName() {
            return name;
        }

        @Override
        public JSONObject toJson() {
            return super.toJson().put("name", name).put("type", type);
        }
    }

    public static class MethodHandleDescriptor extends JavaClassConstant {

        private final byte type;
        private final int index;

        public MethodHandleDescriptor(byte type, int index) {
            this.type = type;
            this.index = index;
        }

        @Override
        public byte getTag() {
            return 15;
        }

        public byte getType() {
            return type;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public JSONObject toJson() {
            return super.toJson().put("type", type).put("index", index);
        }
    }

    public static class MethodTypeReference extends JavaClassConstant {

        private final int index;

        public MethodTypeReference(int index) {
            this.index = index;
        }

        @Override
        public byte getTag() {
            return 16;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public JSONObject toJson() {
            return super.toJson().put("index", index);
        }
    }

    public static class Dynamic extends JavaClassConstant {

        private final int value;

        public Dynamic(int value) {
            this.value = value;
        }

        @Override
        public byte getTag() {
            return 16;
        }

        public int getValue() {
            return value;
        }

        @Override
        public JSONObject toJson() {
            return super.toJson().put("value", value);
        }

    }

    public static class InvokeDynamic extends JavaClassConstant {

        private final byte byte0;
        private final byte byte1;
        private final byte byte2;
        private final byte byte3;

        public InvokeDynamic(byte byte0, byte byte1, byte byte2, byte byte3) {
            this.byte0 = byte0;
            this.byte1 = byte1;
            this.byte2 = byte2;
            this.byte3 = byte3;
        }

        public InvokeDynamic(byte[] bytes) {
            if(bytes.length != 4) throw new Error("Expecting to get 4 bytes!");
            this.byte0 = bytes[0];
            this.byte1 = bytes[1];
            this.byte2 = bytes[2];
            this.byte3 = bytes[3];
        }

        @Override
        public byte getTag() {
            return 16;
        }

        public byte getByte0() {
            return byte0;
        }

        public byte getByte1() {
            return byte1;
        }

        public byte getByte2() {
            return byte2;
        }

        public byte getByte3() {
            return byte3;
        }

        @Override
        public JSONObject toJson() {
            return super.toJson().put("bytes", new JSONArray().put(byte0).put(byte1).put(byte2).put(byte3));
        }
    }

    public static class IdentifyModule extends JavaClassConstant {

        private final short identify_module;


        public IdentifyModule(short identify_module) {
            this.identify_module = identify_module;
        }

        @Override
        public byte getTag() {
            return 19;
        }

        public short getIdentifyModule() {
            return identify_module;
        }

        @Override
        public JSONObject toJson() {
            return super.toJson().put("identify_module", identify_module);
        }
    }

    public static class IdentifyPackage extends JavaClassConstant {

        private final short identify_package;


        public IdentifyPackage(short identify_package) {
            this.identify_package = identify_package;
        }

        @Override
        public byte getTag() {
            return 20;
        }

        public short getIdentifyPackage() {
            return identify_package;
        }

        @Override
        public JSONObject toJson() {
            return super.toJson().put("identify_package", identify_package);
        }
    }
}
