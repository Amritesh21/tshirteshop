package com.tshirtShop.serverSide.security.config;

import java.util.HashMap;

public class APIWhiteListing {

    private HashMap<String, String> apiAuth = new HashMap<>();

    public void whiteListAPI(String apiName, String access) {
        apiAuth.put(access, apiName);
    }

    public String getAPIAccess(String access) {
        return apiAuth.get(access);
    }

}
