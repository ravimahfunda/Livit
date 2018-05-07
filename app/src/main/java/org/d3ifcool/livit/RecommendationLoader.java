package org.d3ifcool.livit;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import org.d3ifcool.livit.entity.Recommendation;
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

/**
 * Created by user on 06/05/2018.
 */

public class RecommendationLoader extends AsyncTaskLoader<List<Recommendation>> {
    private String mUrl;

    public RecommendationLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Recommendation> loadInBackground() {
        if (mUrl == null)return null;

        return fetchRecommendationData(mUrl);
    }

    private List<Recommendation> fetchRecommendationData (String stringUrl){
        URL url = createUrl(stringUrl);
        try {
            String json = makeHttpRequest(url);
            return parseJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
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

        if (url == null) return jsonResponse;

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200){//200 artinya http OK, dan kita hanya memproses kalau OK
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
            if (inputStream != null) inputStream.close();
        }
        return jsonResponse;
    }
    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if (inputStream != null){
            InputStreamReader streamReader = new InputStreamReader(
                    inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(streamReader);
            String line = reader.readLine();
            while (line != null){
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }
    private ArrayList<Recommendation> parseJson(String json) {
        ArrayList<Recommendation> result = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(json);
            JSONArray features = root.getJSONArray("features");
            for (int i = 0; i < features.length(); i++) {
                JSONObject currentFeature = features.getJSONObject(i);
                JSONObject properties = currentFeature.getJSONObject("properties");

                String nama = properties.getString("nama");
                String tipe = properties.getString("tipe");
                result.add(new Recommendation(nama, tipe));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}
