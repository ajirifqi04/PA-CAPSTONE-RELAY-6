package dao;

import java.util.List;

public interface Repository<T> {

    boolean tambah(T data);

    boolean ubah(T data);

    List<T> tampilkanSemua();
}