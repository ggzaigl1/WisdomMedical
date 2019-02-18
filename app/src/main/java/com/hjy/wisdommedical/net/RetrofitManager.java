package com.hjy.wisdommedical.net;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.utils.L;
import com.example.handsomelibrary.utils.SpfUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.hjy.wisdommedical.constant.Constant;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by QKun on 2018/5/25.
 */
public class RetrofitManager {

    private static long CONNECT_TIMEOUT = 60L;
    private static long READ_TIMEOUT = 10L;
    private static long WRITE_TIMEOUT = 10L;
    //单例
    private volatile static Retrofit retrofitInstance = null;


    public static <T> T createApi(Class<T> clazz) {
        return getInstance().create(clazz);
    }

    public static Retrofit getInstance() {
        if (null == retrofitInstance) {
            synchronized (Retrofit.class) {
                if (retrofitInstance == null) {
                    retrofitInstance = new Retrofit.Builder()
                            .baseUrl(ApiService.BASE_URL)
                            .client(buildOKHttpClient())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(buildGsonConverterFactory())
                            .build();
                }
            }
        }
        return retrofitInstance;
    }

    private static OkHttpClient buildOKHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
//                .addInterceptor(mRewriteCacheControlInterceptor)
                .addInterceptor(LogInterceptor())
                .addInterceptor(CommentInterceptor())
                .build();
    }

    /**
     * log
     * @return
     */
    private static HttpLoggingInterceptor LogInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                L.d(message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private static Interceptor CommentInterceptor() {
       return new Interceptor() {
           @Override
           public Response intercept(Chain chain) throws IOException {
//               Request original = chain.request();
//               Request.Builder requestBuilder = original.newBuilder();
//
//               if (original.body() instanceof FormBody) {
//                   FormBody.Builder newFormBody = new FormBody.Builder();
//                   FormBody oldFormBody = (FormBody) original.body();
//                   for (int i = 0; i < oldFormBody.size(); i++) {
//                       newFormBody.addEncoded(oldFormBody.encodedName(i), oldFormBody.encodedValue(i));
//                   }
//
//                   newFormBody.add("token", SpfUtils.getSpfSaveStr(Constant.token));
//                   requestBuilder.method(original.method(), newFormBody.build());
//               }else if (original.body() instanceof MultipartBody) {
//                   MultipartBody.Builder newFormBody = new MultipartBody.Builder();
//                   // 默认是multipart/mixed，大坑【主要是我们php后台接收时头信息要求严格】
//                   newFormBody.setType(MediaType.parse("multipart/form-data"));
//                   MultipartBody oldFormBody = (MultipartBody) original.body();
//                   for (int i = 0; i < oldFormBody.size(); i++) {
//                       newFormBody.addPart(oldFormBody.part(i));
//                   }
//                   newFormBody.addFormDataPart("token", SpfUtils.getSpfSaveStr(Constant.token));
//                   requestBuilder.method(original.method(), newFormBody.build());
//               }else if (TextUtils.equals(original.method(), "POST")) {
//                   FormBody.Builder newFormBody = new FormBody.Builder();
//                   newFormBody.add("token", SpfUtils.getSpfSaveStr(Constant.token));
//                   requestBuilder.method(original.method(), newFormBody.build());
//               }else if (TextUtils.equals(original.method(), "GET")) {
//                   HttpUrl httpUrl = original.url()
//                           .newBuilder()
//                           .addQueryParameter("token", SpfUtils.getSpfSaveStr(Constant.token))
//                           .build();
//                   requestBuilder.url(httpUrl).build();
//               }
//
//               Request request = requestBuilder.build();
//               return chain.proceed(request);


               Response response = null;

               //获取request
               Request request = chain.request()
                       .newBuilder()
                       .addHeader("Content-Type", "multipart/form-data;charse=UTF-8")
//                        .addHeader("Accept-Encoding", "gzip, deflate")//根据服务器要求添加（避免重复压缩乱码）
                       .addHeader("Connection", "keep-alive")
                       .addHeader("Accept", "application/json")
                       .addHeader("Cookie", "add cookies here")
                       .addHeader("token",SpfUtils.getSpfSaveStr(Constant.token))
                       .build();



               if (null == response) {
                   Request.Builder requestBuilder = request.newBuilder();

                   Request newRequest = requestBuilder.build();
                   response = chain.proceed(newRequest);
               }

               return response;

           }
       };
    }


    /**
     * 构建GSON转换器
     *
     * @return GsonConverterFactory
     */
    private static GsonConverterFactory buildGsonConverterFactory() {
        GsonBuilder builder = new GsonBuilder();
        builder.setLenient();

        // 注册类型转换适配器
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return null == json ? null : new Date(json.getAsLong());
            }
        });

        Gson gson = builder.create();
        return GsonConverterFactory.create(gson);
    }
}
