
package evoting.dto;


public class UserDetails {

    private String username;
    private String userid;
    private String email;
    private String address;
    private long mobile;
    private String city;
    private String password;
    private char gender;

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDetails{" + "username=" + username + ", userid=" + userid + ", email=" + email + ", address=" + address + ", mobile=" + mobile + ", city=" + city + ", password=" + password + ", gender=" + gender + '}';
    }

    public UserDetails(String username, String userid, String email, String address, long mobile, String city, String password, char gender) {
        this.username = username;
        this.userid = userid;
        this.email = email;
        this.address = address;
        this.mobile = mobile;
        this.city = city;
        this.password = password;
        this.gender = gender;
    }

    public UserDetails() {

    }
}
