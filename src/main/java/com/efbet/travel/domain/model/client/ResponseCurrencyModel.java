package com.efbet.travel.domain.model.client;

import java.util.Set;

public class ResponseCurrencyModel {

    private Meta meta;

    private Response response;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
