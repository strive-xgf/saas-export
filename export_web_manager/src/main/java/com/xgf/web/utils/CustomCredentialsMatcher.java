package com.xgf.web.utils;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**创建自己的密码匹配器 - 通过加盐加密对用户输入的明文进行加密然后和数据库中存储的密文比较
 * 必须指定父类SimpleCredentialsMatcher
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
    private Logger l = LoggerFactory.getLogger(this.getClass());

    //将密码加密成密文，但需要使用账号（这里账号是邮箱）作盐
    //subject.login(token) 获取页面提交的密码 （比如123456）  通过加盐加密转为 -> 密文1
    //info  调relam 查询数据库中的密码（数据库中的密码存储就是加密存储的） -> 密文2
    //通过比较密文1（用户输入的值）和密文2（数据库中的值），来判断用户输入是否正确
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //token获取账号密码
        UsernamePasswordToken t = (UsernamePasswordToken) token;
        String email = t.getUsername(); //获取用户名（邮箱）
        //密码是char[] '1','2','3'  => "123"  new String(char[])
        String pwd1 = new String(t.getPassword());  //密码获取的是字符数组，转换为字符串
        l.info("doCredentialsMatch 用户输入的密码明文pwd1 = "+pwd1);
        //进行加盐加密，得到用户输入的密码明文加密后的密文
        Md5Hash md5Hash = new Md5Hash(pwd1, email);//参1 传入明文  参2盐
        pwd1 = md5Hash.toString();  //转换为字符串
        l.info("doCredentialsMatch 用户输入的密码加盐加密后pwd1 "+pwd1);

        //读取数据库的密码，数据库存储的就是密文
        String pwd2 = (String) info.getCredentials();
        l.info("doCredentialsMatch 数据库的密码密文pwd2 = "+pwd2);
        if (pwd1.equals(pwd2)) {
            return true;//密码正确（两个密文比较）
        } else {
            return false;//密码不正确
        }

    }
}
