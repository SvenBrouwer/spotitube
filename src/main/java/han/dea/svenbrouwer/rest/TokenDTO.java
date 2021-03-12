package han.dea.svenbrouwer.rest;

public class TokenDTO {



    public TokenDTO() {
    }

    public TokenDTO(String token, String user) {
        this.token = token;
        this.user = user;
    }

    private String token;
    private String user;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }




}
