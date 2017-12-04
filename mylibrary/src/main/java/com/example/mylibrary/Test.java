package com.example.mylibrary;

import com.example.mylibrary.apiService.ApiService;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/12/4 0004.
 */

public class Test {
    public  static  void main(String[]args){
        Doge.Builder builder = new Doge.Builder();
        Doge doge =  builder.baseUrl("http://139.199.219.224/project-admin/api/web/index.php/")
                .client(new OkHttpClient()).build();
      ApiService apiService =  doge.create(ApiService.class);
      apiService.getService();
    }
}
