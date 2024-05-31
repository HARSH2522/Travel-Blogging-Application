/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author root
 */
//@FormAuthenticationMechanismDefinition(
//        loginToContinue = @LoginToContinue(
//                loginPage = "/index.jsp"
//                ,errorPage = "/loginError.jsp"
//              
//        )
//)

//@CustomFormAuthenticationMechanismDefinition(
//        loginToContinue = @LoginToContinue(
//                loginPage = "/Login.jsf"
//        )
//)
// Identity Store
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/travel_blog_db",
        callerQuery = "select password from tbl_user where username = ?",
        groupsQuery = "select groupname from tbl_user where username = ?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priority = 30)

@ApplicationScoped
public class Project {
    
}
