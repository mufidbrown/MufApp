package com.muf.model;

public class Response <T>{

    private ResponseStatusEnum status = ResponseStatusEnum.OK;
    private String statusText = null;
    private T body;

    public ResponseStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ResponseStatusEnum status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public static <T> Response<T> status(ResponseStatusEnum status, String statusText, T body) {
        Response<T> response = new Response<T>();
        response.setStatus(status);
        response.setBody(body);
        response.setStatusText(statusText);
        return response;
    }

    public static <T> Response<T> ok() {
        Response<T> response = new Response<T>();
        response.setStatus(ResponseStatusEnum.OK);
        return response;
    }

    public static <T> Response<T> ok(T body) {
        Response<T> response = new Response<T>();
        response.setStatus(ResponseStatusEnum.OK);
        response.setBody(body);
        return response;
    }

    public static <T> Response<T> ok(String statusText, T body) {
        Response<T> response = new Response<T>();
        response.setStatus(ResponseStatusEnum.OK);
        response.setBody(body);
        response.setStatusText(statusText);
        return response;
    }

    public static <T> Response<T> successWithError(String statusText, T body) {
        Response<T> response = new Response<T>();
        response.setStatus(ResponseStatusEnum.SUCCESS_WITH_ERROR);
        response.setBody(body);
        response.setStatusText(statusText);
        return response;
    }

    public static <T> Response<T> error(String statusText) {
        Response<T> response = new Response<T>();
        response.setStatus(ResponseStatusEnum.ERROR);
        response.setStatusText(statusText);
        return response;
    }

    public static <T> Response<T> error(String statusText, T body) {
        Response<T> response = new Response<T>();
        response.setStatus(ResponseStatusEnum.ERROR);
        response.setBody(body);
        response.setStatusText(statusText);
        return response;
    }

    public static <T> Response<T> dataExist(String statusText) {
        Response<T> response = new Response<T>();
        response.setStatus(ResponseStatusEnum.DATA_EXIST);
        response.setStatusText(statusText);
        return response;
    }

    public static <T> Response<T> dataExist(String statusText, T body) {
        Response<T> response = new Response<T>();
        response.setStatus(ResponseStatusEnum.DATA_EXIST);
        response.setStatusText(statusText);
        response.setBody(body);
        return response;
    }

    public static <T> Response<T> requiredParam(String statusText) {
        Response<T> response = new Response<T>();
        response.setStatus(ResponseStatusEnum.REQUIRED_PARAM);
        response.setStatusText(statusText);
        return response;
    }

    public static <T> Response<T> errorParam(String statusText) {
        Response<T> response = new Response<T>();
        response.setStatus(ResponseStatusEnum.ERROR_PARAM);
        response.setStatusText(statusText);
        return response;
    }

    public static <T> Response<T> dataLength(String statusText) {
        Response<T> response = new Response<T>();
        response.setStatus(ResponseStatusEnum.DATA_LENGTH);
        response.setStatusText(statusText);
        return response;
    }

    public static <T> Response<T> notFound(String statusText) {
        Response<T> response = new Response<T>();
        response.setStatus(ResponseStatusEnum.NOT_FOUND);
        response.setStatusText(statusText);
        return response;
    }

    public static <T> Response<T> notFound(String statusText, T body) {
        Response<T> response = new Response<T>();
        response.setStatus(ResponseStatusEnum.NOT_FOUND);
        response.setStatusText(statusText);
        response.setBody(body);
        return response;
    }
}
