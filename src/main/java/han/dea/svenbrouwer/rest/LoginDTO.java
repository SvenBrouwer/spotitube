package han.dea.svenbrouwer.rest;

public class LoginDTO {

    public LoginDTO(String token, String user, String password) {
        this.token = token;
        this.user = user;
        this.password = password;
    }

    private String token;
    private String user;
    private String password;

    public LoginDTO(String token, String user) {
    }

    public LoginDTO() {
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
