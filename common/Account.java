package common;

import java.io.Serializable;

public class Account implements Serializable {

    private String userId;

    private String password;

    public Account(String userId,String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId(){
        return userId;
    }

    public String getPassword(){
        return password;
    }


}
