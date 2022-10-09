package com.bovan.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.bovan.entity.TUser;
import com.bovan.service.TUserService;
import com.bovan.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 *用户realm，用于在SecuriUtils.login()时进行登录验证信息
 *
 */
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;
    JwtToken jwtToken;

    @Autowired
    TUserService tUserService;

    /*
     * token支持
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        JwtToken jwt = (JwtToken) authenticationToken;

        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();

        TUser user = tUserService.getById(Long.valueOf(userId));

        if (user == null) {
            throw new UnknownAccountException("账户不存在");
        }
        if (user.getStatus() == -1) {
            throw new LockedAccountException("账户已被锁定");
        }
        AccountProfile profile = new AccountProfile();
        //将获取到的用户信息copy到新的映射中
        BeanUtil.copyProperties(user, profile);


        System.out.println("-------------");

        return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
    }
}
