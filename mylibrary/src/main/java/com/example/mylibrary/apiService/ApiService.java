package com.example.mylibrary.apiService;

import com.example.mylibrary.http.Field;
import com.example.mylibrary.http.FormUrlEncoded;
import com.example.mylibrary.http.POST;
/**
 * Created by Administrator on 2017/12/4 0004.
 */

public interface ApiService {
    @POST("/hello")
    @FormUrlEncoded
    void getService(@Field("id") String id);
}
