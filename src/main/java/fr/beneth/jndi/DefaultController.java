package fr.beneth.jndi;

import java.io.IOException;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

   @RequestMapping("/*")
  public void defaultController(HttpServletRequest request, HttpServletResponse response)
          throws IOException, NamingException {
       Context envContext = (Context) new InitialContext().lookup("java:comp/env");
       NamingEnumeration<Binding> en = envContext.listBindings("");
       ServletOutputStream out = response.getOutputStream();
       out.write("List of resources in java:comp/env:\n".getBytes());
       while (en.hasMore()) {
           Binding ncp = (Binding) en.next();

           out.write(("-\t" + ncp.getName() + "\n").getBytes());
       }
  }
   
   @RequestMapping("/geor_available")
   public void georAvailableController(HttpServletRequest request, HttpServletResponse response)
           throws IOException, NamingException {
        Context ic = new InitialContext();

        DataSource datasource = (DataSource) ic.lookup("java:comp/env/jdbc/georchestra");
        ServletOutputStream out = response.getOutputStream();
      if (datasource != null) {
          out.write(datasource.toString().getBytes());
      } else  {
          out.write("Expected datasource not found".getBytes());          
      }
   }
}
