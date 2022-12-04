package ecommerce.example.ecommerce.dto.user;

public class SignInDto {
    public String email;
    public String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SignInDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
