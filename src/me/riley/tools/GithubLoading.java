package me.riley.tools;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class GithubLoading {

    public static String readIPS() {
        String link = "https://gist.githubusercontent.com/RileyCalhounLIVE/f8d65a4c1bebb4da9ab8b9f71be78399/raw/4b0085c8759c49dd6542b5c0e3449bd4e7b14c20/heroic-essentials";
        String response = "";
        try {
            URL uri = new URL(link);
            HttpURLConnection http = (HttpURLConnection) uri.openConnection();
            Map<String, List<String>> headers = http.getHeaderFields();
            for (String header : headers.get(null)) {
                if (header.contains(" 302 ") || header.contains(" 301 ")) {
                    link = headers.get("Location").get(0);
                    uri = new URL(link);
                    http = (HttpURLConnection) uri.openConnection();
                    headers = http.getHeaderFields();
                }
            }

            InputStream stream = http.getInputStream();
            response = getStringFromStream(stream);
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

        private static String getStringFromStream (InputStream stream){
            if (stream != null) {
                Writer crunchifyWriter = new StringWriter();

                char[] crunchifyBuffer = new char[2048];
                try {
                    Reader crunchifyReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                    int counter;
                    while ((counter = crunchifyReader.read(crunchifyBuffer)) != -1) {
                        crunchifyWriter.write(crunchifyBuffer, 0, counter);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return crunchifyWriter.toString();
            } else {
                return "No Contents";
            }
        }
    }


