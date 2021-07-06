package com.hiwijaya.springsecurity;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * @author Happy Indra Wijaya
 */
public class Lib {

    private static final ZoneOffset DEFAULT_OFFSET = ZoneOffset.of("+00");

    /**
     * @return OffsetDateTime with +00 offset
     * */
    public static OffsetDateTime now(){
        return OffsetDateTime.now(DEFAULT_OFFSET);
    }

    public static OffsetDateTime getServerCurrentTime(){
        return  OffsetDateTime.now(ZoneId.systemDefault());
    }


}
