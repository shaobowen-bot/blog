package com.bovan.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.bovan.domain.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsProject: blog_boot
 * @BelongsPackage: com.bovan.domain.entity
 * @Author: bovan
 * @Date: 2022/10/13 14:50
 * @Description:
 */
@Data
@NoArgsConstructor
public class SecurityUser implements UserDetails {
    //当前登录
    private User currentUserInfo;

    //当前权限
    private List<String> permissionValueList;


    public SecurityUser(User currentUserInfo, List<String> permissionValueList) {
        this.currentUserInfo = currentUserInfo;
        this.permissionValueList = permissionValueList;
    }

    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities != null){
            return authorities;
        }
        authorities = permissionValueList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return currentUserInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return currentUserInfo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
