package com.follow.shiro;

import com.follow.entity.User;
import com.follow.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.soap.SOAPBinding;

/**
 * @author Zuan~
 * @version 1.0
 * @ClassName: UserRealm
 * @description: TODO[  ]
 * @date: 2020/8/22  11:36
 */
/**
 * 功能描述： TODO[  ]
 * @auther:  Zuan~
 * @date:  2020/8/22  11:36
 *
 * 自定义 Realm
 *      1、AuthenticatingRealm : shiro 中的用于进行认证的领域，实现 doGetAuthentcationInfo 方法实现用户登录时的认证逻辑；
 *      2、AuthorizingRealm ： shiro中用于授权的领域，实现 doGetAuthrozitionInfo 方法实现用户的授权逻辑，AuthorizingRealm 继承了AuthenticatingRealm；
 *    所以再实际使用中主要用到的就是这个 AuthenticatingRealm 类。
 *      3、AuthenticatingRealm 、 AuthorizingRealm 这两个类都是 shiro 中提供了一些线索的 realm 接口。
 *      4、在与spring 整合项目中，shiro 的 securityManager 会自动调用这两个方法，从而实现认证和授权，可以结合 shiro 的 CacheManager 将认证和授权信息保存在缓存中，
 *    这样可以提高系统的处理效率
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 功能描述： TODO[ 执行认证逻辑 ]
     * @auther:  Zuan~
     * @date:  2020/8/22  11:43
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        //doGetAuthorizationInfo方法可能会执行多次，权限判断次数多少，就会执行多少次
        System.out.println("执行授权逻辑");

        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //添加资源的授权字符串
        //info.addStringPermission("user:add");

        //到数据库查询当前登录用户的授权字符串
        //获取当前登录用户
        Subject subject =  SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        User dbUser = userService.findById(user.getId());

        info.addStringPermission(dbUser.getPassWord());

        return info;
    }

    /**
     * 功能描述： TODO[ 执行认证逻辑 ]
     * @auther:  Zuan~
     * @date:  2020/8/22  14:00
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        //编写 shiro 判断逻辑，判断账号和密码
        //1、判断账号，token中的用户信息是登录时候传进来的
        UsernamePasswordToken token = (UsernamePasswordToken) arg0;


        User user = new User();
        user.setPassWord(token.getPassword().toString());
        user.setAccount(token.getUsername());

        User user2 = userService.checkUser(user);

        if (user2 == null){
            //账号不存在
            return null;  //shiro 底层会抛出 UnKnowAccountException
        }


        //2.判断密码
        //第二个字段是user.getPassword()，注意这里是指从数据库中获取的password。第三个字段是realm，即当前realm的名称。
        //这块对比逻辑是先对比username，但是username肯定是相等的，所以真正对比的是password。
        //从这里传入的password（这里是从数据库获取的）和token（filter中登录时生成的）中的password做对比，如果相同就允许登录，
        // 不相同就抛出IncorrectCredentialsException异常。
        //如果认证不通过，就不会执行下面的授权方法了
        return new SimpleAuthenticationInfo(user,user.getPassWord(),"");
    }


}
