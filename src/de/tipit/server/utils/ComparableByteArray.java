package de.tipit.server.utils;

public class ComparableByteArray implements Comparable<ComparableByteArray> {

    private final byte[] array;

    public ComparableByteArray(final byte[] array) {
        this.array = (array != null) ? array : new byte[0];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            return compareTo((ComparableByteArray) obj) == 0;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result += array[i];
        }
        return result;
    }

    @Override
    public int compareTo(ComparableByteArray other) {
        if (other != null) {
            int index = 0;
            for (; index < array.length && index < other.array.length; index++) {
                byte b1 = array[index];
                byte b2 = other.array[index];

                if (b1 < b2) {
                    return -1;
                }

                if (b1 > b2) {
                    return +1;
                }
            }

            if (other.array.length > index) {
                return -1;
            }

            if (array.length > index) {
                return +1;
            }

            return 0;
        } else {
            return 1;
        }
    }

    public byte[] getArray() {
        return array;
    }
}
