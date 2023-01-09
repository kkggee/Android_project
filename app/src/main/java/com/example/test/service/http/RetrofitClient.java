package com.example.test.service.http;

import com.example.test.DTO.http.CalendarRequest;
import com.example.test.DTO.http.CalendarResponse;
import com.example.test.DTO.http.LoginRequest;
import com.example.test.DTO.http.LoginResponse;
import com.example.test.DTO.http.MainResponse;
import com.example.test.DTO.http.NonRequest;
import com.example.test.DTO.http.PostGetRequest;
import com.example.test.DTO.http.PostGetResponse;
import com.example.test.DTO.http.PostRequest;
import com.example.test.DTO.http.PostResponse;
import com.example.test.DTO.http.ReplyDeleteRequest;
import com.example.test.DTO.http.ReplyDeleteResponse;
import com.example.test.DTO.http.ReplyEditRequest;
import com.example.test.DTO.http.ReplyEditResponse;
import com.example.test.DTO.http.ReplyRequest;
import com.example.test.DTO.http.ReplyResponse;
import com.example.test.DTO.http.SignupRequest;
import com.example.test.DTO.http.SignupResponse;
import com.example.test.DTO.http.TagListResponse;
import com.example.test.DTO.http.TagRequest;
import com.example.test.DTO.http.TagResponse;

import java.sql.SQLException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class RetrofitClient  {
    private static RetrofitClient instance = null;
    private static initAPI initApi;

    private static String ip="220.68.27.123";
    private static String port ="8080";
    //사용하고 있는 서버 BASE 주소
    private static String baseUrl = "http://"+ip+":"+port;



    private RetrofitClient() {
        //로그를 보기 위한 Interceptor

        //retrofit 설정
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        initApi = retrofit.create(initAPI.class);
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public static initAPI getRetrofitInterface() {
        return initApi;
    }
    interface initAPI{
        //@통신 방식("통신 API명")
        @POST("/app/login")
        Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest)throws SQLException,Exception;
        @POST("/app/signup")
        Call<SignupResponse> getSignupResponse(@Body SignupRequest signupRequest)throws SQLException,Exception;
        @POST("/app/checkID")
        Call<SignupResponse> getCheckID(@Body SignupRequest signupRequest)throws SQLException,Exception;
        @POST("/app/post")
        Call<PostResponse> getPostResponse(@Body PostRequest postRequest)throws SQLException,Exception;
        @POST("/app/main")
        Call<MainResponse> getPostList(@Body NonRequest nonRequest) throws SQLException, Exception;
        @POST("/app/tagListUP")
        Call<TagListResponse> getTagList(@Body NonRequest nonRequest) throws SQLException, Exception;
        @POST("/app/tag")
        Call<TagResponse> getTagResponse(@Body TagRequest tagRequest)throws SQLException,Exception;
        @POST("/app/reply")
        Call<ReplyResponse> getReplyResponse(@Body ReplyRequest ReplyRequest)throws SQLException,Exception;
        @POST("/app/getPost")
        Call<PostGetResponse> getPostResponse(@Body PostGetRequest postGetRequest)throws SQLException,Exception;
        @POST("/app/replyEdit")
        Call<ReplyEditResponse> getReplyEditResponse(@Body ReplyEditRequest replyEditRequest)throws SQLException,Exception;
        @POST("/app/replyDelete")
        Call<ReplyDeleteResponse> getReplyDeleteResponse(@Body ReplyDeleteRequest replyDeleteRequest)throws SQLException,Exception;
        @POST("/app/getCalendar")
        Call<CalendarResponse> getCalendarResponse(@Body CalendarRequest calendarRequest)throws SQLException,Exception;
//        @POST("/app/icon_load")
//        Call<LoginResponse> getMainResponse(@Body LoginRequest loginRequest)throws SQLException, Exception;
    }

}


