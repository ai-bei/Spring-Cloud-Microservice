package com.iotknowyou.config;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.gson.GsonEncoder;

import java.lang.reflect.Type;

public class MyEncoder implements Encoder {

    private GsonEncoder gsonEncoder;

    public MyEncoder(){
        gsonEncoder = new GsonEncoder();
    }


    @Override
    public void encode(Object object,Type bodyType,RequestTemplate template) throws EncodeException{

        System.out.println("encode object is class"+object.getClass().getName());

        System.out.println("encode object is value"+object);

        System.out.println("encode bodyType is class"+bodyType.getClass().getName());

        System.out.println("encode bodyType is value"+bodyType);

        gsonEncoder.encode(object,bodyType,template);

    }
}
