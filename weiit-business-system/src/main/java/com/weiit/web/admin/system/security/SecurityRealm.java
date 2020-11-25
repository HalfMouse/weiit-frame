package com.weiit.web.admin.system.security;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.weiit.core.entity.E;
import com.weiit.core.entity.FormMap;
import com.weiit.web.admin.system.service.ManagerService;
import com.weiit.web.admin.system.service.ResourceService;
import com.weiit.web.admin.system.service.RoleService;


/**
 * 用户身份验证
 * @author 半个鼠标
 * @Email：137075251@qq.com
 * @date：2017年2月14日 上午2:36:38
 * @version 1.0
 */
@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {

    @Resource
    private ManagerService managerService;

    @Resource
    private RoleService roleService;
    
    @Resource
    private ResourceService resourceService;


    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        E authentication = (E)principals.getPrimaryPrincipal();
        List<E> resources=(List<E>)authentication.get("resources");
        for (E e : resources) {
        	//添加菜单权限
			authorizationInfo.addRole(e.getStr("resource_sign"));
			if(e.getInt("type")==3){
				for (E e2 : resources) {
					if(e.getInt("parent_id")==e2.getInt("resource_id")){
						authorizationInfo.addStringPermission(e2.getStr("resource_sign")+":"+e.getStr("resource_sign"));
						break;
					}
				}
				
			}
		}
        return authorizationInfo;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String account_name = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        FormMap formMap=new FormMap();
        formMap.set("account_name", account_name);
        formMap.set("password", password);
        E authentication=managerService.login(formMap);
        if (authentication == null) {
            throw new AuthenticationException("用户名或密码错误.");
        }
        //登录信息正确后加载用户菜单
        formMap=new FormMap();
        formMap.set("manager_id", authentication.getInt("manager_id"));
        List<E> roles=roleService.selectRolesByManagerId(formMap);
        List<E> resources=roleService.selectResourceByRoles(roles);
        authentication.set("resources", resources);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(authentication, password, getName());
        return authenticationInfo;
    }

}
