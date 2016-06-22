package fr.beneth.jndi;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

   @RequestMapping("/*")
  public void defaultController(HttpServletRequest request, HttpServletResponse response)
          throws IOException, NamingException {
       Context ic = new InitialContext();
       NamingEnumeration<NameClassPair> nameEnum = ic.list("java:comp/env");
       
       ServletOutputStream out = response.getOutputStream();
       while (nameEnum.hasMore()) {
           NameClassPair ncp = nameEnum.next();
           out.write(("-\t" + ncp.getName() + "\n").getBytes());
       }
  }
}
