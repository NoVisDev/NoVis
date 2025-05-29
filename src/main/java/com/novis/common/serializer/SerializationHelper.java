package com.novis.common.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class SerializationHelper {

    private static final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.setReferences(true);
        kryo.setRegistrationRequired(false);

        return kryo;
    });

    public static byte[] serialize(Object obj) {
        Kryo kryo = kryoThreadLocal.get();
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        Output output = new Output(byteStream);
        kryo.writeClassAndObject(output, obj);
        output.close();
        return byteStream.toByteArray();
    }

    public static Object deserialize(byte[] data) {
        Kryo kryo = kryoThreadLocal.get();
        Input input = new Input(new ByteArrayInputStream(data));
        Object obj = kryo.readClassAndObject(input);
        input.close();
        return obj;
    }
}
