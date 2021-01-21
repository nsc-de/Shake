package com.github.nsc.de.shake.generators.java.util.classvisitor;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class CountingInputStream extends FilterInputStream {

    private static char[] hexChars = "0123456789abcdef".toCharArray();

    private long pos = 0;
    private long mark = 0;

    public CountingInputStream(InputStream in) {
        super(in);
    }

    public synchronized long getPosition()
    {
        return pos;
    }

    public synchronized char getColumnIdentifier() {
        return hexChars[(int) (getPosition() % 16)];
    }

    public synchronized String getRowIdentifier() {
        return createHexNumber(getPosition() / 16, 7);
    }

    public synchronized String getPositionIdentifier() {
        return createHexNumber(getPosition(), 8);
    }

    @Override
    public synchronized int read()
            throws IOException
    {
        int b = super.read();
        if (b >= 0)
            pos += 1;
        return b;
    }

    @Override
    public synchronized int read(byte[] b, int off, int len)
            throws IOException
    {
        int n = super.read(b, off, len);
        if (n > 0)
            pos += n;
        return n;
    }

    @Override
    public synchronized long skip(long skip)
            throws IOException
    {
        long n = super.skip(skip);
        if (n > 0)
            pos += n;
        return n;
    }

    @Override
    public synchronized void mark(int readlimit)
    {
        super.mark(readlimit);
        mark = pos;
    }

    @Override
    public synchronized void reset()
            throws IOException
    {
        /* A call to reset can still succeed if mark is not supported, but the
         * resulting stream position is undefined, so it's not allowed here. */
        if (!markSupported())
            throw new IOException("Mark not supported.");
        super.reset();
        pos = mark;
    }


    private static String createHexNumber(long number, int places) {

        StringBuilder sb = new StringBuilder();

        for(int i = places - 1; i >= 0; i--) {
            long dividend = simple_pow(16, i);
            sb.append(hexChars[(int) (number / dividend)]);
            number %= dividend;
        }

        return sb.toString();
    }

    private static long simple_pow(long base, int exp) {
        long res = 1;
        for(int i = 0; i < exp; i++) res *= base;
        return res;
    }
}