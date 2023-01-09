package com.example.test.service.http;


import android.util.Log;

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
import com.example.test.MainActivity;
import com.example.test.calendar.CalendarActivity;
import com.example.test.login.LoginActivity;
import com.example.test.main.MainSceneActivity;
import com.example.test.post.PostActivity;
import com.example.test.post.dialog;
import com.example.test.reply.ReplyActivity;
import com.example.test.signup.RegisterActivity;

import java.sql.SQLException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HttpService  {
    private static HttpService instance;
    private RetrofitClient retrofitClient;
    private RetrofitClient.initAPI initApi;
    private LoginResponse loginResponse;
    private SignupResponse signupResponse;
    private PostResponse postResponse;
    private MainResponse mainResponse;
    private ReplyResponse replyResponse;
    private TagResponse tagResponse;
    private TagListResponse tagListResponse;
    private PostGetResponse postGetResponse;
    private ReplyEditResponse replyEditResponse;
    private ReplyDeleteResponse replyDeleteResponse;
    private CalendarResponse calendarResponse;

    public HttpService() {
        retrofitClient = RetrofitClient.getInstance();
        initApi = RetrofitClient.getRetrofitInterface();
        loginResponse= new LoginResponse();
        signupResponse = new SignupResponse();
        postResponse = new PostResponse();
        mainResponse = new MainResponse();
        replyResponse = new ReplyResponse();
        tagResponse = new TagResponse();
        tagListResponse = new TagListResponse();
        replyEditResponse = new ReplyEditResponse();
        replyDeleteResponse = new ReplyDeleteResponse();
        calendarResponse = new CalendarResponse();
    }
    public static HttpService getService() {
        if (instance == null) {
            instance = new HttpService();
        }
        return instance;
    }

    protected void onStart() {
    }

    public MainResponse getPostList(NonRequest nonRequest, MainSceneActivity mainSceneActivity) throws SQLException, Exception{
        initApi.getPostList(nonRequest).enqueue((new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if(response.isSuccessful()){
                    MainResponse data = response.body();
                    switch (data.getCode()){
                        case(code.OK):{
                            Log.d("postList","성공");
                            Log.d("postList",data.isResult()+"");
                            break;
                        } case(code.SQLError):{
                            Log.d("postList", "SQL 관련 문제");
                            try {
                                throw new SQLException("SQL 관련 문제");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            break;
                        } case(code.UnknownError):{
                            Log.d("postList", "서버에서의 문제");
                            try {
                                throw new Exception("서버에서의 문제");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                    mainSceneActivity.mainProcess(data);
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                setMainResponse(new MainResponse());
                mainResponse.setCode(code.httpError);
                Log.d("postList", "통신 문제로 실패");
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
        return mainResponse;
    }

    public TagListResponse getTagList(NonRequest nonRequest, PostActivity postActivity) throws SQLException, Exception{
        initApi.getTagList(nonRequest).enqueue((new Callback<TagListResponse>() {
            @Override
            public void onResponse(Call<TagListResponse> call, Response<TagListResponse> response) {
                if(response.isSuccessful()){
                    TagListResponse data = response.body();
                    switch (data.getCode()){
                        case(code.OK):{
                            Log.d("tagList","성공");
                            Log.d("tagList",data.isResult()+"");
                            break;
                        } case(code.SQLError):{
                            Log.d("tagList", "SQL 관련 문제");
                            try {
                                throw new SQLException("SQL 관련 문제");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            break;
                        } case(code.UnknownError):{
                            Log.d("tagList", "서버에서의 문제");
                            try {
                                throw new Exception("서버에서의 문제");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                    postActivity.tagListUpProcess(data);
                }
            }

            @Override
            public void onFailure(Call<TagListResponse> call, Throwable t) {
                setMainResponse(new MainResponse());
                mainResponse.setCode(code.httpError);
                Log.d("tagList", "통신 문제로 실패");
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }));

        return tagListResponse;
    }

    public PostGetResponse getPost(PostGetRequest postGetRequest, ReplyActivity replyActivity) throws SQLException, Exception{
        initApi.getPostResponse(postGetRequest).enqueue((new Callback<PostGetResponse>() {
            @Override
            public void onResponse(Call<PostGetResponse> call, Response<PostGetResponse> response) {
                if(response.isSuccessful()){
                    PostGetResponse data = response.body();
                    switch (data.getCode()){
                        case(code.OK):{
                            Log.d("getPost","성공");
                            break;
                        } case(code.SQLError):{
                            Log.d("getPost", "SQL 관련 문제");
                            try {
                                throw new SQLException("SQL 관련 문제");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            break;
                        } case(code.UnknownError):{
                            Log.d("getPost", "서버에서의 문제");
                            try {
                                throw new Exception("서버에서의 문제");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                    replyActivity.postGetProcess(data);
                }
            }

            @Override
            public void onFailure(Call<PostGetResponse> call, Throwable t) {
                setPostGetResponse(new PostGetResponse());
                mainResponse.setCode(code.httpError);
                Log.d("postList", "통신 문제로 실패");
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
        return postGetResponse;
    }

    public SignupResponse checkUserID(SignupRequest signupRequest, RegisterActivity registerActivity) throws SQLException, Exception {
        setSignupResponse(new SignupResponse());
        initApi.getCheckID(signupRequest).enqueue((new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if(response.isSuccessful()){
                    SignupResponse data= response.body();
                    switch (data.getCode()){
                        case(code.OK):{
                            Log.d("signup","성공");
                            Log.d("signup",data.isResult()+"");
                            break;
                        } case(code.SQLError):{
                            Log.d("signup", "SQL 관련 문제");
                            try {
                                throw new SQLException("SQL 관련 문제");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            break;
                        } case(code.UnknownError):{
                            Log.d("signup", "서버에서의 문제");
                            try {
                                throw new Exception("서버에서의 문제");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                    registerActivity.checkIDProcess(data);
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                setSignupResponse(new SignupResponse());
                loginResponse.setCode(code.httpError);
                Log.d("signup", "통신 문제로 실패");
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
        return signupResponse;
    }

    public LoginResponse login(LoginRequest loginRequest, LoginActivity loginActivity) throws SQLException,Exception{
        setLoginResponse(new LoginResponse());
       initApi.getLoginResponse(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse data = response.body();
                    System.out.println(data.toString());
                    switch (data.getCode()){
                        case(code.OK):{
                            Log.d("login", "성공");
                            break;
                        } case(code.SQLError):{
                            Log.d("login", "SQL 관련 문제");
                            try {
                                throw new SQLException("SQL 관련 문제");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            break;
                        } case(code.UnknownError):{
                            Log.d("login", "서버에서의 문제");
                            try {
                                throw new Exception("서버에서의 문제");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        } default:{
                            break;
                        }
                    }
                    loginActivity.loginProcess(data);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                setLoginResponse(new LoginResponse());
                loginResponse.setCode(code.httpError);
                Log.d("login", "통신 문제로 실패");
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return loginResponse;
    }

//    public TagResponse
public TagResponse tagRegister(TagRequest tagRequest, dialog dialog) throws SQLException,Exception{
    setTagResponse(new TagResponse());
    initApi.getTagResponse(tagRequest).enqueue(new Callback<TagResponse>() {
        @Override
        public void onResponse(Call<TagResponse> call, Response<TagResponse> response) {
            if (response.isSuccessful()) {
                TagResponse data = response.body();
                System.out.println(data.toString());
                switch (data.getCode()){
                    case(code.OK):{
                        Log.d("tag_register", "성공");
                        break;
                    } case(code.SQLError):{
                        Log.d("tag_register", "SQL 관련 문제");
                        try {
                            throw new SQLException("SQL 관련 문제");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        break;
                    } case(code.UnknownError):{
                        Log.d("tag_register", "서버에서의 문제");
                        try {
                            throw new Exception("서버에서의 문제");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    } default:{
                        break;
                    }
                }
                dialog.tagProcess(data);
            }
        }

        @Override
        public void onFailure(Call<TagResponse> call, Throwable t) {
            setTagResponse(new TagResponse());
            tagResponse.code = 400;
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            Log.d("tag_register", "통신 문제로 실패");
            Log.d("tag_register", t.toString());
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    return tagResponse;
}

    public PostResponse post(PostRequest postRequest, PostActivity postActivity) throws SQLException,Exception{
        setPostResponse(new PostResponse());
        initApi.getPostResponse(postRequest).enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if (response.isSuccessful()) {
                    PostResponse data = response.body();
                    System.out.println(data.toString());
                    switch (data.getCode()){
                        case(code.OK):{
                            Log.d("post", "성공");
                            break;
                        } case(code.SQLError):{
                            Log.d("post", "SQL 관련 문제");
                            try {
                                throw new SQLException("SQL 관련 문제");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            break;
                        } case(code.UnknownError):{
                            Log.d("post", "서버에서의 문제");
                            try {
                                throw new Exception("서버에서의 문제");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        } default:{
                            break;
                        }
                    }
                    postActivity.postProcess(data);
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                setPostResponse(new PostResponse());
                PostResponse.code = 400;
                OkHttpClient.Builder client = new OkHttpClient.Builder();
                Log.d("post", "통신 문제로 실패");
                Log.d("post", t.toString());
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return postResponse;
    }


    public SignupResponse signUp(SignupRequest signupRequest, RegisterActivity registerActivity)  throws SQLException,Exception{
        setSignupResponse(new SignupResponse());
        initApi.getSignupResponse(signupRequest).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if(response.isSuccessful()){
                    SignupResponse data= response.body();
                    switch (data.getCode()){
                        case(code.OK):{
                            Log.d("signup","성공");
                            Log.d("signup",data.isResult()+"");
                            break;
                        } case(code.SQLError):{
                            Log.d("signup", "SQL 관련 문제");
                            try {
                                throw new SQLException("SQL 관련 문제");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            break;
                        } case(code.UnknownError):{
                            Log.d("signup", "서버에서의 문제");
                            try {
                                throw new Exception("서버에서의 문제");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                    registerActivity.signUpProcess(data);
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                setSignupResponse(new SignupResponse());
                loginResponse.setCode(code.httpError);
                Log.d("signup", "통신 문제로 실패");
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return signupResponse;
    }


    public ReplyResponse reply(ReplyRequest replyRequest, ReplyActivity replyActivity) throws SQLException,Exception{
        setReplyResponse(new ReplyResponse());
        initApi.getReplyResponse(replyRequest).enqueue(new Callback<ReplyResponse>() {
            @Override
            public void onResponse(Call<ReplyResponse> call, Response<ReplyResponse> response) {
                if (response.isSuccessful()) {
                    ReplyResponse data = response.body();
                    System.out.println(data.toString());
                    switch (data.getCode()){
                        case(code.OK):{
                            Log.d("reply", "성공");
                            break;
                        } case(code.SQLError):{
                            Log.d("reply", "SQL 관련 문제");
                            try {
                                throw new SQLException("SQL 관련 문제");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            break;
                        } case(code.UnknownError):{
                            Log.d("reply", "서버에서의 문제");
                            try {
                                throw new Exception("서버에서의 문제");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        } default:{
                            break;
                        }
                    }
                    replyActivity.replyProcess(data);
                }
            }

            @Override
            public void onFailure(Call<ReplyResponse> call, Throwable t) {
                setReplyResponse(new ReplyResponse());
                replyResponse.code = 400;
                OkHttpClient.Builder client = new OkHttpClient.Builder();
                Log.d("reply", "통신 문제로 실패");
                Log.d("reply", t.toString());
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return replyResponse;
    }

    public ReplyEditResponse replyEdit(ReplyEditRequest replyEditRequest, ReplyActivity replyActivity) throws SQLException,Exception{
        setReplyEditResponse(new ReplyEditResponse());
        initApi.getReplyEditResponse(replyEditRequest).enqueue(new Callback<ReplyEditResponse>() {
            @Override
            public void onResponse(Call<ReplyEditResponse> call, Response<ReplyEditResponse> response) {
                if (response.isSuccessful()) {
                    ReplyEditResponse data = response.body();
                    System.out.println(data.toString());
                    switch (data.getCode()){
                        case(code.OK):{
                            Log.d("reply수정", "성공");
                            break;
                        } case(code.SQLError):{
                            Log.d("reply수정", "SQL 관련 문제");
                            try {
                                throw new SQLException("SQL 관련 문제");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            break;
                        } case(code.UnknownError):{
                            Log.d("reply수정", "서버에서의 문제");
                            try {
                                throw new Exception("서버에서의 문제");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        } default:{
                            break;
                        }
                    }
                    replyActivity.replyEditProcess(data);
                }
            }

            @Override
            public void onFailure(Call<ReplyEditResponse> call, Throwable t) {
                setReplyEditResponse(new ReplyEditResponse());
                replyEditResponse.code = 400;
                OkHttpClient.Builder client = new OkHttpClient.Builder();
                Log.d("reply수정", "통신 문제로 실패");
                Log.d("reply수정", t.toString());
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return replyEditResponse;
    }

    public ReplyDeleteResponse replyDelete(ReplyDeleteRequest replyDeleteRequest, ReplyActivity replyActivity) throws SQLException,Exception{
        setReplyDeleteResponse(new ReplyDeleteResponse());
        initApi.getReplyDeleteResponse(replyDeleteRequest).enqueue(new Callback<ReplyDeleteResponse>() {
            @Override
            public void onResponse(Call<ReplyDeleteResponse> call, Response<ReplyDeleteResponse> response) {
                if (response.isSuccessful()) {
                    ReplyDeleteResponse data = response.body();
                    System.out.println(data.toString());
                    switch (data.getCode()){
                        case(code.OK):{
                            Log.d("reply삭제", "성공");
                            break;
                        } case(code.SQLError):{
                            Log.d("reply삭제", "SQL 관련 문제");
                            try {
                                throw new SQLException("SQL 관련 문제");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            break;
                        } case(code.UnknownError):{
                            Log.d("reply삭제", "서버에서의 문제");
                            try {
                                throw new Exception("서버에서의 문제");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        } default:{
                            break;
                        }
                    }
                    replyActivity.replyDeleteProcess(data);
                }
            }

            @Override
            public void onFailure(Call<ReplyDeleteResponse> call, Throwable t) {
                setReplyDeleteResponse(new ReplyDeleteResponse());
                replyDeleteResponse.code = 400;
                OkHttpClient.Builder client = new OkHttpClient.Builder();
                Log.d("reply삭제", "통신 문제로 실패");
                Log.d("reply삭제", t.toString());
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return replyDeleteResponse;
    }

    public CalendarResponse getCalendar(CalendarRequest calendarRequest, CalendarActivity calendarActivity) throws SQLException, Exception{
        initApi.getCalendarResponse(calendarRequest).enqueue((new Callback<CalendarResponse>() {
            @Override
            public void onResponse(Call<CalendarResponse> call, Response<CalendarResponse> response) {
                if(response.isSuccessful()){
                    CalendarResponse data = response.body();
                    switch (data.getCode()){
                        case(code.OK):{
                            Log.d("calendar","성공");
                            Log.d("calendar",data.isResult()+"");
                            break;
                        } case(code.SQLError):{
                            Log.d("calendar", "SQL 관련 문제");
                            try {
                                throw new SQLException("SQL 관련 문제");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            break;
                        } case(code.UnknownError):{
                            Log.d("calendar", "서버에서의 문제");
                            try {
                                throw new Exception("서버에서의 문제");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                    calendarActivity.calendarProcess(data);
                }
            }

            @Override
            public void onFailure(Call<CalendarResponse> call, Throwable t) {
                setCalendarResponse(new CalendarResponse());
                calendarResponse.setCode(code.httpError);
                Log.d("calendar", "통신 문제로 실패");
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }));

        return calendarResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }


    public void setMainResponse(MainResponse mainResponse) {
        this.mainResponse = mainResponse;
    }


    public void setSignupResponse(SignupResponse signupResponse) {
        this.signupResponse = signupResponse;
    }

    private void setTagResponse(TagResponse tagResponse) {
        this.tagResponse = tagResponse;
    }

    private void setPostResponse(PostResponse postResponse) {
        this.postResponse = postResponse;
    }
    private void setPostGetResponse(PostGetResponse postGetResponse) { this.postGetResponse = postGetResponse; }
    private void setReplyResponse(ReplyResponse replyResponse) {
        this.replyResponse = replyResponse;
    }

    private void setReplyEditResponse(ReplyEditResponse replyEditResponse) { this.replyEditResponse = replyEditResponse; }

    private void setReplyDeleteResponse(ReplyDeleteResponse replyDeleteResponse) { this.replyDeleteResponse = replyDeleteResponse; }

    private void setCalendarResponse(CalendarResponse calendarResponse) { this.calendarResponse = calendarResponse; }

}
