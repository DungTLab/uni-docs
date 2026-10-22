package model;

import java.io.Serializable;

/**
 * User Model - Đại diện cho tài khoản người dùng/quản trị (khớp với dự án WebPRJ).
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String password;
    
    // Các thuộc tính mở rộng cho thi PE phân quyền và xác thực
    private String displayName;
    private String role; // ADMIN hoặc USER
    private boolean status; // true: Hoạt động, false: Khóa

    public User() {
    }

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(Integer id, String username, String password, String displayName, String role, boolean status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.role = role;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Trả về câu chào tên hiển thị (Khớp với cấu trúc WebPRJ).
     */
    public String getDisplayInfo() {
        return String.format("Welcome [ %s ]", this.displayName != null ? this.displayName : this.username);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
