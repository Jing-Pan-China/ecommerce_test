package ecommerce.example.ecommerce.dto.user;

public class SignInResponseDto {
    private String status;
    private String token;

    public String getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SignInResponseDto(String status, String token) {
        this.status = status;
        this.token = token;
    }
}
