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
public class ServerNews { 
    
  
    
  
    
    public static void main(String[] args) {  
  
        secure("keyscerts/ecikeystorenews.p12","123456",null,null );
        port(getPort());     
        get("/queryNews", (req, res) -> queryNews(req, res));
      
        
    }
      /**
     * Retorna una pagina web con una noticia
     *
     * @param request  The HTTP Request.
     * @param response The HTTP Response.
     * @return pagina web con Noticia.
     */

    private static String queryNews(Request req, Response res){
        
        res.status(202);
        res.type("text/html");
        
        String pageContent = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h1>Noticias del Mundo</h1>"
                + "<script>" +
                    "var f = new Date();" +
                    "document.write(f.getDate() + \"/\" + (f.getMonth() +1) + \"/\" + f.getFullYear());" +
                    "</script> "
                + "<h2>Se queda sin dientes para ganar un Record Guinness</h2>"
                + "<p>Hay personas que ajustan su vida a los retos del mas famoso libro de marcas." + "<br>" 
                + " En este caso hablamos del veterano Parkash Rishi, que ha hecho de su cuerpo una herramienta "+ "<br>" 
                + "para hacer historia. En esta ocasion, el conocido como Guinness Rishi se ha desprendido de toda su "+ "<br>" 
                + "dentadura para introducir en su boca 500 pajitas y 50 velas encendidas. "+ "<br>" 
                + "A sus 74 anos de edad, el anciano natural de Nueva Delhi acumula bajo sus hombros mas de 50"+ "<br>" 
                + " entradas en el Record Guinness, y las que le quedan si su obsesion no acaba en tragedia.</p>"           
                + "</body>"
                + "</html>";
        return pageContent;
    }
    
    /**
     * Obtiene el puerto de la aplicacion web
     *
     * @return El valor del puerto configurado en el sisteman, retorna 4568 por defecto
     */
    
 
    public static int getPort() {
            if (System.getenv("PORT") != null) {
                    return Integer.parseInt(System.getenv("PORT"));
            }
            return 4568; //returns default port if heroku-port isn't set(i.e. on localhost)
    }
}
