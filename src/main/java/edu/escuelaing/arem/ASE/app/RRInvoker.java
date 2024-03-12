package edu.escuelaing.arem.ASE.app;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RRInvoker {
    private static final String USER_AGENT = "Mozilla/5.0";
    //private static final String POST_URL = "http://localhost:5000/logservice";

    public RRInvoker() {
    }
    public static String makePost(String urlService, String requestBody) throws IOException {
        String finalUrl = urlService;
        URL obj = new URL(finalUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);

        con.setDoOutput(true);

        // Obtiene el flujo de salida para escribir el cuerpo de la solicitud
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            // Escribe el cuerpo de la solicitud en el flujo de salida
            wr.write(requestBody.getBytes(StandardCharsets.UTF_8));
        }

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return response.toString();
    }
}