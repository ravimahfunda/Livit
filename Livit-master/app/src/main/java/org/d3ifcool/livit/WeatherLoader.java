package org.d3ifcool.livit;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import org.d3ifcool.livit.entity.Weather;
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

public class WeatherLoader extends AsyncTaskLoader<Weather> {
    private String mUrl;

    public WeatherLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public Weather loadInBackground() {
        if (mUrl==null) return null;

        return fetchWeatherData(mUrl);
    }

    private Weather fetchWeatherData(String stringUrl){
        URL url = createUrl(stringUrl);

        try {
            String json = makeHttpRequest(url);
            return parseJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Weather();
    }

    private URL createUrl(String stringUrl){
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null)return jsonResponse;

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == 200){
                inputStream = httpURLConnection.getInputStream();

                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (httpURLConnection != null)httpURLConnection.disconnect();
            if (inputStream != null) inputStream.close();
        }

        return jsonResponse;

    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if (inputStream != null){
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();
            while (line != null){
                output.append(line);
                line = bufferedReader.readLine();
            }
        }

        return output.toString();
    }

    private Weather parseJson(String json){
        Weather result = new Weather();

        try {
            JSONObject root = new JSONObject(json);
            JSONObject properties = root.getJSONObject("main");
            double temp = properties.getDouble("temp");
            int humidity = properties.getInt("humidity");

            result = new Weather("","",temp, humidity);

        } catch (JSONException e) {
            e.printStackTrace();

        }

        return result;
    }
}
