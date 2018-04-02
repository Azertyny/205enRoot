package e.clement.a205enroot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Clément on 25/03/2018.
 */


// Classe 'utils' permettant de faire des requêtes HTTP de type GET
public class GetHttpURLConnection {
    public static String startHttpRequest(String urlString){
        StringBuilder stringBuilder = new StringBuilder();

        try{
            // Déclaration de la connexion par URL
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Ouverture du stream (canal)
            conn.connect();
            InputStream in = conn.getInputStream();
            // Télécharge et interprète la chaîne de réponse
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line = reader.readLine()) != null){
                stringBuilder.append(line);
            }

        }catch(MalformedURLException exception){

        }catch (IOException exception){

        }catch (Exception e){

        }

        return stringBuilder.toString();
    }
}
