package com.example.mylibrary;

import com.example.mylibrary.http.FormUrlEncoded;
import com.example.mylibrary.http.POST;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import okhttp3.HttpUrl;

/**
 * Created by Administrator on 2017/12/4 0004.
 */

public class ServiceMethod {
  private HttpUrl mHttpUrl;
  private boolean isPost;
    private  String mRelativeUrl;

    public ServiceMethod(ServiceMethod.Builder builder) {
       mRelativeUrl = builder.mRelativeUrl;
       isPost = builder.isPost;
       mHttpUrl = builder.mDoge.baseUrl();

    }

    public static class Builder{
      private Method mMethod;
        private boolean isPost;
        private  String mRelativeUrl;
        private final Doge mDoge;
        private boolean isFormEncoded;
        private final Annotation[] methodAnnotations;
        private final Annotation[][] parameterAnnotationsArray;
        final Type[] parameterTypes;
      public Builder(Doge doge,Method method) {
          this.mDoge = doge;
          this.mMethod = method;
          parameterTypes = this.mMethod.getGenericParameterTypes();
          methodAnnotations = this.mMethod.getAnnotations();
          this.parameterAnnotationsArray = method.getParameterAnnotations();
          for (Type type:parameterTypes){
              String str = type.toString();
              System.out.print(str);
          }

          build();
      }
      public ServiceMethod build(){
          ServiceMethod serviceMethod = new ServiceMethod(this);

          for (Annotation annotation:methodAnnotations) {
              parseMethodAnnotation(annotation);
          }
          return serviceMethod;
      }

        private void parseMethodAnnotation(Annotation annotation) {
          if (annotation instanceof POST){
              isPost = true;
              String value = ((POST)annotation).value();
              mRelativeUrl = value;
          }else if (annotation instanceof FormUrlEncoded){
              isFormEncoded = true;
          }
        }
    }
}
