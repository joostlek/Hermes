package nl.jtosti.hermes.security.requests;

import java.io.Serializable;

public class ScreenRegisterRequest implements Serializable {
    private Long screenId;

    public Long getScreenId() {
        return screenId;
    }

    public void setScreenId(Long screenId) {
        this.screenId = screenId;
    }
}
