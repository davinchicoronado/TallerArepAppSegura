/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.app;

/**
 *
 * @author David Coronado
 */
import static spark.Spark.*;
import spark.Request;
import spark.Response;

public class LoginAppClient {
    
        
        private static final APIClient api = new APIClient();
        
        public static void main(String[] args) {
        
            
        secure("keyscerts/ecikeystore.p12","123456",null,null );
        port(getPort());
        get("/index", (req, res ) -> inputDataPage(req, res));
        get("/result", (req, res) -> serviceNews(req, res));
       
    }
        
    private static String inputDataPage(Request req, Response res) {
        
       res.status(202);
       res.type("text/html");
       String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h2>Login News World</h2>"
                + "<form action=\"/result\" method=\"get\">"
                + "  Username:<br>"
                + "  <input type=\"text\" name=\"Username\" value=\"\">"
                + "  <br><br>" 
                +    " Password:<br>"
                + "<input type=\"password\" name=\"Password\" value=\"\">"
                               + "  <br><br>" 
                + "  <input type=\"submit\" value=\"Aceptar\">"
                + "</form>"
                + "<p>If you click the \"Submit\" button, the form-data will be sent to a page called \"/results\".</p>"
                + "</body>"
                + "</html>";
        return pageContent;
    }  
    
    private static String serviceNews(Request req, Response res){
        
            String value = req.queryParams("Username");
            String value2 = req.queryParams("Password");
            
            
            
            res.status(202);
            res.type("text/html");
            String pageContent;
            if (!api.checkUser(value, value2)){
                pageContent  = "<!DOCTYPE html>" 
                + "<html>"
                + "<body>"
                + "<h2> Wrong username or password</h2>"
                + "</body>"
                + "</html>";
            }
            else{
                pageContent = api.getNews();
            }
                      
            return pageContent;
         
    }     
    public static int getPort() {
            if (System.getenv("PORT") != null) {
                    return Integer.parseInt(System.getenv("PORT"));
            }
            return 4567; //returns default port if heroku-port isn't set(i.e. on localhost)
    }
    
}
