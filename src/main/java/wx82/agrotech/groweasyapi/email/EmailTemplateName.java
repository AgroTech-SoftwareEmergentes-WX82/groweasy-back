package wx82.agrotech.groweasyapi.email;

import lombok.Getter;

@Getter
public enum EmailTemplateName {

    activate_account("activate_account");

    private final String name;
    EmailTemplateName(String name) {
        this.name = name;
    }
}
