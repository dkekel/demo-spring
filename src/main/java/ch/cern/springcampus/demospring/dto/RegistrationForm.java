package ch.cern.springcampus.demospring.dto;

import ch.cern.springcampus.demospring.validation.FieldsValueMatch;
import org.hibernate.validator.constraints.Length;

@FieldsValueMatch(field = "password", fieldMatch = "repeatPassword")
public class RegistrationForm {
    @Length(min = 4, max = 16)
    private String username;
    @Length(min = 4, max = 32)
    private String password;
    @Length(min = 4, max = 32)
    private String repeatPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(final String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
