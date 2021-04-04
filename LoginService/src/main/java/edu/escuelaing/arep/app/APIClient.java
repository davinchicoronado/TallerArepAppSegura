/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import com.google.common.hash.Hashing;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author David Coronado
 */
public class APIClient {
    private String url1 = "https://localhost:4568";
    private String url2 = "https://172.20.0.5:6000";
    private static HashMap<String, String> users = new HashMap<>();
    
     /**
     * Constructor para APIClient
     *
     * 
     */
    
    
    public APIClient() {
        users.put("admin",encodePassword("admin"));
        secureApiClient();
    }
    
    public boolean checkUser(String username, String password){
                
            if(users.containsKey(username) && users.containsValue(encodePassword(password))){  
                return true;
            }
            else{
                return false;
            }
    }
    
     /**
     * Realiza una peticion http get a otro server  
     * @return noticia del dia.
     */
    
    public String getNews(){
        try {
            URL siteURL = new URL(url2+"/queryNews");
            URLConnection urlConnection = siteURL.openConnection();
          BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            
            String inputLine;
            String body="";
                while ((inputLine = in.readLine()) != null)
                    
                    body+=inputLine;
            in.close();           
            return body;
        } catch (MalformedURLException ex) {
            Logger.getLogger(APIClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
                Logger.getLogger(APIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h1>500 Internal Server News Error </h1>"
                + "</body>"
                + "</html>";
    }
    
        /**
     * Codifica una contraseña con una funcion Hash.
     *
     * @param password a codificar.
     * @return password encriptado.
     */
    private String encodePassword(String password){
    
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
   
    }
      /**
     * Permite revisar si la conexion es segura o no dado un certificado  
     * permitiendo asi realizar peticiones http
     */
    
    
    private void secureApiClient(){
        try {
            
            // Create a file and a password representation
            File trustStoreFile = new File("keyscerts/myTrustStore");
            char[] trustStorePassword = "123456".toCharArray();
            // Load the trust store, the default type is "pkcs12", the alternative is "jks"
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            
                trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);

            // Get the singleton instance of the TrustManagerFactory
            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            
            // Itit the TrustManagerFactory using the truststore object
            tmf.init(trustStore);
            
            SSLContext sslContext = SSLContext.getInstance("TLS");
               
            sslContext.init(null, tmf.getTrustManagers(), null);
                
            SSLContext.setDefault(sslContext);       
            
        }catch (IOException | NoSuchAlgorithmException | CertificateException | KeyStoreException | KeyManagementException ex) {
                Logger.getLogger(APIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    
    
    
}
