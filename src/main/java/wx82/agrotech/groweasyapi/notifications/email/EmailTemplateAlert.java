package wx82.agrotech.groweasyapi.notifications.email;

import lombok.Getter;

@Getter
public enum EmailTemplateAlert {
    ACTIVATE_ALERT("activate_alert");

    private final String message;
    EmailTemplateAlert(String message) {
        this.message = message;
    }
}
