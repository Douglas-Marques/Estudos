package com.example.android.quakereport;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;


public class QueryUtils {

    private QueryUtils() {
    }


    /**
     * Retorna uma lista de objetos {@link Earthquake} que foram obtidos da
     * decodificação da dada resposta JSON.
     */
    private static List<Earthquake> extractFeatureFromJson(String earthquakeJSON) {
        // Se a String do JSON é vazia ou nula, então retorna agora.
        if (TextUtils.isEmpty(earthquakeJSON)) {
            return null;
        }

        // Cria um ArrayList vazio para o qual podemos iniciar adicionando objetos Earthquake.
        List<Earthquake> earthquakes = new ArrayList<>();

        // Tenta decodificar a string de resposta da JSON. Se houver um problema no modo como o JSON
        // é formatado, uma exceção de objeto JSONException será chamada.
        // Ao pegar a exceção no Catch o aplicativo não quebra, e imprime a mensagem de erro para os logs.
        try {

            // Cria um objeto JSONObject da string de resposta JSON
            JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);

            // Extrai o JSONArray associado com a chave chamada "features",
            // que representa uma lista de features (ou earthquakes).
            JSONArray earthquakeArray = baseJsonResponse.getJSONArray("features");

            // Para cada earthquake no earthquakeArray, cria um objeto {@link Earthquake}
            for (int i = 0; i < earthquakeArray.length(); i++) {

                // Obtém um único earthquake na posição i dentro da lista de earthquakes
                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);

                // Para um dado earthquake, extrai o JSONObject associado com a
                // chave chamada "properties", que representa uma lista de todas as properties
                // para aquele earthquake.
                JSONObject properties = currentEarthquake.getJSONObject("properties");

                // Extrai o valor da chave chamada "mag"
                double magnitude = properties.getDouble("mag");

                // Extrai o valor da chave chamada place"
                String location = properties.getString("place");

                // Extrai o valor da chave chamada time"
                long time = properties.getLong("time");

                // Extrai o valor da chave chamada "url"
                String url = properties.getString("url");

                // Cria um novo objeto {@link Earthquake} com a magnitude, local, tempo,
                // e url da resposta JSON.
                Earthquake earthquake = new Earthquake(magnitude, location, time, url);

                // Adiciona o novo {@link Earthquake} a lista de earthquakes.
                earthquakes.add(earthquake);
            }

        } catch (JSONException e) {
            // Se um erro é acionado ao executar qualquer um dos comando no bloco "try",
            // processa o catch da exceção aqui, para que o aplicativo não quebre. Imprime a mensagem log
            // com a mensagem da exceção.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Retorna a lista de earthquakes
        return earthquakes;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Se a requisição foi bem sucedida (código de resposta 200),
            // então lê a entrada de dados e decodifica a resposta.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Fechar a entrada de dados pode acarretar IOException, e é por isto que
                // a assinatura do método makeHttpRequest(URL url) especifica um IOException
                // que pode ser causado.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static List<Earthquake> fetchEarthquakeData(String requestUrl) {
        // Cria objeto URL
        URL url = createUrl(requestUrl);

        // Realiza requisição HTTP para a URL e recebe uma resposta JSON de volta
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extrai campos relevantes da resposta JSON e cria uma lista de {@link Earthquake}s
        List<Earthquake> earthquakes = extractFeatureFromJson(jsonResponse);

        // Retorna a lista de {@link Earthquake}s
        return earthquakes;
    }
}