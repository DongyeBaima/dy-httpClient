package org.dy.util.httpclient;

public class HttpResult {

    /**
     * ×´Ì¬Âë
     */
    private Integer status;
    /**
     * ·µ»ØÊı¾İ
     */
    private String data;

    public HttpResult() {
    }

    public HttpResult(Integer status, String data) {
        this.status = status;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "status=" + status +
                ", data='" + data + '\'' +
                '}';
    }
}