package com.example.newnetty.ch2;

import io.netty.util.AttributeKey;

public class FarmConstant {
    private FarmConstant(){}
    public static final AttributeKey<String> CHANNEL_MAC_ADDRESS = AttributeKey.valueOf("channel.mac.address");
}
