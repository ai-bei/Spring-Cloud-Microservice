package com.iotknowyou.config;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.gson.GsonDecoder;

import java.io.IOException;
import java.lang.reflect.Type;

public class MyDecoder implements Decoder {

    private GsonDecoder gsonDecoder;

    public MyDecoder(){
        gsonDecoder=new GsonDecoder();
    }

    @Override
    public Object decode(Response response,Type type)throws IOException,DecodeException,FeignException{

        return gsonDecoder.decode(response,type);

    }
}
