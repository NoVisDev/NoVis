package com.novis.common;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class SerializationHelper {
    private static final Kryo kryo = new Kryo();

    public static void init() {
        kryo.register(Message.class);
    }

    public static Message deserializeMessage(byte[] serializedData) {
        ByteArrayInputStream bais = new ByteArrayInputStream(serializedData);
        Input input = new Input(bais);
        Message deserialized = kryo.readObject(input, Message.class);
        input.close();

        return deserialized;
    }

    public static byte[] serializeMessage(Message message) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, message);
        output.close();

        return baos.toByteArray();
    }
}
