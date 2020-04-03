package org.mxframework.contentflow.domain.model.iaa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.mxframework.contentflow.domain.model.IdentifiedEntityObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * User: 用户
 *
 * @author mx
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class User extends IdentifiedEntityObject implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Embedded
    private UserId userId;

    @NotNull(message = "账号不能为空")
    @Size(min = 4, max = 20, message = "账号不能超出范围‘4~20’")
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(20) COMMENT '账号'")
    private String username;

    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 300, message = "用密码不能超出范围‘6~300’")
    @Column(nullable = false, columnDefinition = "VARCHAR(300) COMMENT '密码'")
    private String password;

    @Column(nullable = false, columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 1 COMMENT '账户是否过期'")
    private Boolean accountNonExpired;

    @Column(nullable = false, columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 1 COMMENT '账号是否锁定'")
    private Boolean accountNonLocked;

    @Column(nullable = false, columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 1 COMMENT '凭证是否过期'")
    private Boolean credentialsNonExpired;

    @Column(nullable = false, columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 1 COMMENT '是否有效'")
    private Boolean enabled;

    @ManyToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority"
            , joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", columnDefinition = "BIGINT(20) COMMENT '用户ID'")
            , inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id", columnDefinition = "BIGINT(20) COMMENT '权限ID'"))
    private Set<Authority> authorities;

    @NotNull(message = "用户邮箱不能为空")
    @Email(message = "用户邮箱格式不正确")
    @Size(min = 4, max = 50, message = "邮箱长度范围不能超出‘4~50’")
    @Column(nullable = false, columnDefinition = "VARCHAR(50) COMMENT '邮箱'")
    private String email;

    @Size(max = 20, message = "昵称不能超出范围‘4~20’")
    @Column(columnDefinition = "VARCHAR(20) COMMENT '昵称'")
    private String nickname;

    @Size(max = 200, message = "头像地址长度不能超过‘200’")
    @Column(columnDefinition = "VARCHAR(200) COMMENT '头像地址'")
    private String avatar;

    @Size(max = 100, message = "网站地址长度不能超过‘100’")
    @Column(columnDefinition = "VARCHAR(100) COMMENT '网站地址'")
    private String website;

    @Size(max = 300, message = "个人简介长度不能超过‘300’")
    @Column(columnDefinition = "VARCHAR(300) COMMENT '个人简介'")
    private String note;

    @Size(max = 600, message = "用户公告大小不能超过‘600’")
    @Column(columnDefinition = "VARCHAR(600) COMMENT '公告'")
    private String notice;

    public User(UserId userId, String username, String password, Set<Authority> authorities) {
        this(userId, username, password, true, true, true, true, authorities);
    }

    public User(UserId userId, String username, String password
            , Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled
            , Set<Authority> authorities) {
        this();
        this.setUserId(userId);
        this.setUsername(username);
        this.setPassword(password);
        this.setAccountNonExpired(accountNonExpired);
        this.setAccountNonLocked(accountNonLocked);
        this.setCredentialsNonExpired(credentialsNonExpired);
        this.setEnabled(enabled);
        this.setAuthorities(authorities);
    }

    protected User() {
        super();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : this.authorities) {
            simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(grantedAuthority.getAuthority()));
        }
        return simpleGrantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return this.password();
    }

    @Override
    public String getUsername() {
        return this.username();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.enabled();
    }

    public UserId userId() {
        return this.userId;
    }

    public String username() {
        return this.username;
    }

    public String password() {
        return this.password;
    }

    public Boolean accountNonExpired() {
        return this.accountNonExpired;
    }

    public Boolean accountNonLocked() {
        return this.accountNonLocked;
    }

    public Boolean credentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public Boolean enabled() {
        return this.enabled;
    }

    public Set<Authority> authorities() {
        return this.authorities;
    }

    public String email() {
        return this.email;
    }

    public String nickname() {
        return this.nickname;
    }

    public String avatar() {
        return this.avatar;
    }

    public String website() {
        return this.website;
    }

    public String note() {
        return this.note;
    }

    public String notice() {
        return this.notice;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", website='" + website + '\'' +
                ", note='" + note + '\'' +
                ", notice='" + notice + '\'' +
                '}';
    }
}
