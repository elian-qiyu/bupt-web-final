package cn.edu.bupt.springmvc.demo.util;

import java.io.Serializable;

public class JsonRsult<E> implements Serializable {
    private Integer state;
    private String MESSAGE;
    private E data;

    public JsonRsult(){
    }

    public  JsonRsult(Integer state){
    }

    public JsonRsult(Throwable e){
    }

    public JsonRsult(Integer state,E data){
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
