package com.example.mylibrary;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/12/4 0004.
 */

public class Doge {
    private final HttpUrl baseUrl;
    private final OkHttpClient mOkHttpClient;
    private Map<Method,ServiceMethod> mServiceMethodCache = new HashMap<>();

    private Doge(HttpUrl baseUrl, OkHttpClient okHttpClient) {
        this.baseUrl = baseUrl;
        this.mOkHttpClient = okHttpClient;
    }
   public <T> T create(Class<T> serviceClass){

       Utils.isInterface(serviceClass);
      T obj = (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class[]{serviceClass}, new InvocationHandler() {
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               ServiceMethod serviceMethod = loadServiceMethod(method);

              return null;
          }
      });
       return obj;
   }

    private ServiceMethod loadServiceMethod(Method method) {
        ServiceMethod result = mServiceMethodCache.get(method);
        if (result==null){
            mServiceMethodCache.put(method, result);
        }
        return result;
    }

    public  final static class  Builder{
   private HttpUrl mBaseUrl;
   private OkHttpClient mOkHttpClient;
        public Builder baseUrl(String baseUrl){
            Utils.checkNoNull(baseUrl,"mBaseUrl==null");
            HttpUrl httpUrl = HttpUrl.parse(baseUrl);
            if (httpUrl==null){
                throw new IllegalArgumentException(" httpUrl==null");
            }
            return baseUrl(httpUrl);
        }
        public Builder baseUrl(HttpUrl baseUrl) {
            Utils.checkNoNull(baseUrl, "mBaseUrl == null");
            List<String> pathSegments = baseUrl.pathSegments();
            if (!"".equals(pathSegments.get(pathSegments.size() - 1))) {
                throw new IllegalArgumentException("mBaseUrl must end in /: " + baseUrl);
            }
            this.mBaseUrl = baseUrl;

            return this;
        }
        public Builder client(OkHttpClient client){
            Utils.checkNoNull(client,"client==null");
            mOkHttpClient = client;
            return  this;
        }
        public Doge build(){
            Utils.checkNoNull(this.mBaseUrl,"baseUrl==null");
            if (this.mOkHttpClient==null){
                this.mOkHttpClient = new OkHttpClient();
            }
            Doge doge = new Doge(this.mBaseUrl,this.mOkHttpClient);
             return  doge;
        }
    }

}
