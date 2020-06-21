package com.aqiang.net;

public class TokenEntity {

    /**
     * access_token : sWO-J0Cdj1kn8XTNDj82Nrj_26B0iBWKxx-ZXHFJ7c_pkovXrt8LObD537O5xixjU1n5EvXpvvfqjeCiUG2-NkpXA9kFKT4DfSSGjAJ6D2W5JbgOOAFwIrUOSqt7p_qfGSN05DydGaVBHDpW5zxv0rcNZ41TY4KfXknX3uHbcU9f0kS7cmRru4xLUQ1Q2CENzoS41lXj_KJMyo3Jm96NGYrbneN6pRlfJ0LlvjxCteh0v0z-kgeOjtj6oCN0vnk8sPlvNvgwovg5c5eyMNmGVNRaeJ7JEbEgNZ3co7q9WmU
     * token_type : bearer
     * expires_in : 86399
     */

    private String access_token;
    private String token_type;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
