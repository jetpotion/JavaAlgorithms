/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import java.util.Objects;

/**
 *
 * @author William Zhang
 * @param <E> any comparable object is used either.We are not going to use primitive types
 */
public class Vertex<E extends Comparable>{
    private E data;
 public Vertex(E data)
 {
     this.data = data;
 }
    @Override
 public boolean equals(Object type)
 {
     E object = (E)type;
    return data.compareTo(object) == 1;
 }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.data);
        return hash;
    }
}
