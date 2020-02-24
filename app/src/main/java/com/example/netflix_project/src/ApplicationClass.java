package com.example.netflix_project.src;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.netflix_project.src.main.ViewPager.User.interfaces.UserApi;
import com.example.netflix_project.src.main.interfaces.NetflixRetrofitInterface;

import java.text.SimpleDateFormat;
import java.util.Locale;

import okhttp3.MediaType;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationClass extends Application {
    public static MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=uft-8");
    public static MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");


    public static SharedPreferences sSharedPreferences = null;

    // SharedPreferences 키 값
    public static String TAG = "TEMPLATE_APP";

    // JWT Token 값
    public static String X_ACCESS_TOKEN = "X-ACCESS-TOKEN";

    //날짜 형식
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    // Retrofit 인스턴스
    public static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        if (sSharedPreferences == null) {
            sSharedPreferences = getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        }
    }

//    public static Retrofit getRetrofit() {
//        if (retrofit == null) {
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .readTimeout(5000, TimeUnit.MILLISECONDS)
//                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
//                    .addNetworkInterceptor(new XAccessTokenInterceptor()) // JWT 자동 헤더 전송
//                    .build();
//
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .client(client)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//
//        return retrofit;
//    }

    //USER SERVICE

    public static final String BaseUrl = "http://lena.seohyunguk.me/";
    private static UserApi userApi = null;


    public static UserApi getService() {

        if (userApi == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            userApi = retrofit.create(UserApi.class);
        }

        return userApi;

    }

    //MOVIE SERVICE
    private static NetflixRetrofitInterface mNetflixRetrofitInterface = null;

    public static NetflixRetrofitInterface getMovieService() {
        if (mNetflixRetrofitInterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mNetflixRetrofitInterface = retrofit.create(NetflixRetrofitInterface.class);
        }

        return mNetflixRetrofitInterface;
    }
}