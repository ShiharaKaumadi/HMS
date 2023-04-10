package lk.ijse.hibernate.dto;

public class UserDTO {
    private String userId;
    private String usrename;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String userId, String usrename, String password) {
        this.userId = userId;
        this.usrename = usrename;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsrename() {
        return usrename;
    }

    public void setUsrename(String usrename) {
        this.usrename = usrename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
