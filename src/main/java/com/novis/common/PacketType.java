package com.novis.common;

public enum PacketType {
    MESSAGE((byte) 1),
    LETTERBOX_PULL((byte) 2),
    ACKNOWLEDGE ((byte) 3), // server -> client acknowledge packet has been sent, no further info.
    LETTERBOX_PULL_RESPONSE ((byte) 4),
    LETTERBOX_DELETE_REQUEST ((byte) 5);

    public final byte id;

    PacketType(byte id) {
        this.id = id;
    }

    public static PacketType fromId(byte id) {
        for (PacketType pt : values()) {
            if (pt.id == id) return pt;
        }
        return null;
    }
}
