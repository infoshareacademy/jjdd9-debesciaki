package com.infoshareacademy.context;

import com.infoshareacademy.domain.view.RoleEnum;
import com.infoshareacademy.domain.view.UserView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ContextHolder {
    private static final String NAME = "name";
    private static final String ROLE = "role";
    private static final String EMAIL = "email";
    private static final String ID = "id";

    private final HttpSession httpSession;


    public ContextHolder(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public ContextHolder(HttpSession httpSession, UserView userView) {
        this.httpSession = httpSession;
        setContext(userView);
    }

    public String getEmail() {
        return (String) httpSession.getAttribute(EMAIL);
    }

    public String getRole() {
        return Optional.ofNullable((String) httpSession.getAttribute(ROLE)).orElse(String.valueOf(RoleEnum.GUEST));
    }

    private void setContext(UserView userView) {
        httpSession.setAttribute(NAME, userView.getName());
        httpSession.setAttribute(EMAIL, userView.getEmail());
        httpSession.setAttribute(ID, userView.getId());
        httpSession.setAttribute(ROLE, userView.getRole());
    }

    public void invalidate() {
        httpSession.removeAttribute(EMAIL);
        httpSession.removeAttribute(NAME);
        httpSession.removeAttribute(ROLE);
        httpSession.removeAttribute(ID);
    }
}
