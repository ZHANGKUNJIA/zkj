package com.atstudy.es;

//es的操作实体类（需要对于es的数据进行增删改查，都要基于这个操作实体类来进行）
public final class EsEntity<T> {

    private String id;
    private T data;

    public EsEntity() {
    }

    public EsEntity(String id, T data) {
        this.data = data;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}