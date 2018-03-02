package com.bj.dto;

import com.bj.validator.MyConstraint;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Created by neko on 2018/3/2.
 */
public class User {

    public interface UserSimpleView{}

    public interface UserDetailView extends UserSimpleView{}

    private String id;

    @MyConstraint(message = "Test Constraint")
    private String username;

    @NotBlank(message = "密码不可为空")
    private String password;

    @Past(message = "生日不真实")
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //因为具有继承关系，所以UserDetailView在显示的时候
    //也会把UserSimpleView显示出来
    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
