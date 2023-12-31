package com.example.connect.retrofit;

//import android.database.Observable;
import io.reactivex.rxjava3.core.Observable;

import com.example.connect.model.Donhangmodel;
import com.example.connect.model.Loaispmodel;
import com.example.connect.model.Usermodel;
import com.example.connect.model.spmoimodel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface Apibanhang {
    @GET("getloaisp.php")
    Observable<Loaispmodel> getloaisanpham();
    @GET("getspmoi.php")
    Observable<spmoimodel> getspMoi();

    @POST("chitiet.php")
    @FormUrlEncoded
    Observable<spmoimodel> getSp(
         @Field("page") int page,
         @Field("loai") int loai
    );
    @POST("dangky.php")
    @FormUrlEncoded
    Observable<Usermodel> dangky(
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("ten") String ten,
            @Field("sdt")   String sdt
    );
    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<Usermodel> dangnhap(
            @Field("email") String email,
            @Field("pass") String pass
    );
    @POST("donhang.php")
    @FormUrlEncoded
    Observable<Usermodel> donhang(
            @Field("ten") String ten,
            @Field("sdt") String sdt,
            @Field("tong") String tong,
            @Field("id") int id,
            @Field("diachi") String diachi,
            @Field("sl") int sl,
            @Field("chitiet") String chitiet
    );
    @POST("timkiem.php")
    @FormUrlEncoded
    Observable<spmoimodel> search(
            @Field("search") String search

            );
    @POST("xemdonhang.php")
    @FormUrlEncoded
    Observable<Donhangmodel> xemdonhang(
            @Field("id") int id

    );
}
