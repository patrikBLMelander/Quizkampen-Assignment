package server;

import java.io.Serializable;

/**
 * Created by Patrik Melander
 * Date: 2020-11-12
 * Time: 14:03
 * Project: Quiskampen
 * Copyright: MIT
 */
public class Resonse implements Serializable {
    boolean success;

    public Resonse(boolean answer) {
        this.success = answer;
    }

    public boolean isSuccess() {
        return success;
    }
}
