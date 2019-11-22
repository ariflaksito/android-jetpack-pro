package net.ariflaksito.mymovie.utils;

import android.app.Application;
import android.os.AsyncTask;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import net.ariflaksito.mymovie.BuildConfig;
import net.ariflaksito.mymovie.data.source.remote.response.MovieResponse;
import net.ariflaksito.mymovie.data.source.remote.response.TvShowResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class ApiTvShow extends AsyncTask<Void, Void, ArrayList<TvShowResponse>> {

    public static final String API_KEY = BuildConfig.TheMovieDBApi;
    private Application application;

    public ApiTvShow() {
    }

    public ApiTvShow(Application application) {
        this.application = application;
    }

    @Override
    protected ArrayList<TvShowResponse> doInBackground(Void... voids) {
        SyncHttpClient client = new SyncHttpClient();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY + "&language=en-US";

        ArrayList<TvShowResponse> list = new ArrayList<>();
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject movieJsonObject = jsonArray.getJSONObject(i);
                        String movieId = movieJsonObject.getString("id");
                        String title = movieJsonObject.getString("original_name");
                        String desc = movieJsonObject.getString("overview");
                        String release = movieJsonObject.getString("first_air_date");
                        String image = movieJsonObject.getString("poster_path");

                        TvShowResponse tvShowResponse = new TvShowResponse(movieId, title, desc, release, image);
                        list.add(tvShowResponse);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                error.printStackTrace();
            }
        });

        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<TvShowResponse> listTvShow) {
        getResult(listTvShow);
    }

    public ArrayList<TvShowResponse> getResult(ArrayList<TvShowResponse> list) {
        return list;
    }
}
