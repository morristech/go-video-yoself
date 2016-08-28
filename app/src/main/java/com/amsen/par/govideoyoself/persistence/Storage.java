package com.amsen.par.govideoyoself.persistence;

import java.util.List;

/**
 * @author Pär Amsen 2016
 */
public interface Storage<T> {
    void put(T t);
    T get(int id);
    List<T> getAll();
    void invalidate();
}
