package map;

import java.util.Objects;

public class Key {
    private String key;

    public Key(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Key key1 = (Key) o;
        return Objects.equals(key, key1.key);
    }

    //метод eqauls создан конечно тлк для того, чтобы протетсить
 //   @Override
    /*public boolean equals(Object o) {
        if (o == null) return false;
        return key.equals(o.toString());
    }*/


    @Override
    public int hashCode() {
        return 17 + key.charAt(0);
    }
}
