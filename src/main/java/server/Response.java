package server;

import java.io.Serializable;

/**
 * Created by Patrik Melander
 * Date: 2020-11-12
 * Time: 14:03
 * Project: Quiskampen
 * Copyright: MIT
 */
public class Response implements Serializable {
    boolean success;

    public Response(boolean answer) {
        this.success = answer;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
