
package evoting.dto;


public class UserData {
    private String username;
    private String city;
    private String mobile;
    private String email;
    private char gen;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGen() {
        return gen;
    }

    public void setGen(char gen) {
        this.gen = gen;
    }

    public UserData(String username, String city, String mobile, String email, char gen) {
        this.username = username;
        this.city = city;
        this.mobile = mobile;
        this.email = email;
        this.gen = gen;
    }
    
}
