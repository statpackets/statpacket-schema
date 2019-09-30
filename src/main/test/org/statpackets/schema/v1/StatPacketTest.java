package org.statpackets.schema.v1;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.JsonFormat;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;


public class StatPacketTest {

    @Test
    public void testBasicStatPacket () throws InvalidProtocolBufferException {

        MetaData meta = MetaData.newBuilder()
                .setId(UUID
                        .newBuilder()
                        .setValue(java.util.UUID.randomUUID().toString())
                        .build())
                .setCreated(Timestamp
                        .newBuilder()
                        .setSeconds(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                        .build())
                .build();

        StatPacket sp = StatPacket.newBuilder()
                .setMetadata(meta)
                .setMethods(Methods.newBuilder().addMethod(Method.newBuilder().setFormula("y = mx + b").build()).build())
                .setData(Data.newBuilder().build())
                .build();


        System.out.println(JsonFormat.printer().includingDefaultValueFields().print(sp));
        assertTrue(sp.getMetadata().hasCreated());

    }
}
