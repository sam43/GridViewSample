package com.example.asmsayem.gridviewsample;

import com.example.asmsayem.gridviewsample.models.PhotoInfoModel;
import com.example.asmsayem.gridviewsample.models.ResponsePathsPhoto;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Sayem on 18-Dec-17.
 */

public interface MybdjobsAPI {

    String baseURL = "http://my.bdjobs.com/apps/";

    @FormUrlEncoded
    @POST("mybdjobs/upload_img.aspx")
    Call<ResponsePathsPhoto> uploadPhoto(
            @Field("Image") String image,
            @Field("userid") String userid,
            @Field("decodeid") String decodeid,
            @Field("folderName") String folderName,
            @Field("folderId") String folderId,
            @Field("imageName") String imageName,
            @Field("isResumeUpdate") String isResumeUpdate,
            @Field("status") String status);

    @FormUrlEncoded
    @POST("mybdjobs/apps_photoInfo.asp")
    Call<PhotoInfoModel> getPhotoInfo(@Field("userId") String UserID, @Field("decodeId") String decodeId);

    class Factory {
        public static MybdjobsAPI mybdjobsAPI;

        public static MybdjobsAPI getInstance() {
            if (mybdjobsAPI == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseURL)
                        .build();

                mybdjobsAPI = retrofit.create(MybdjobsAPI.class);
                return mybdjobsAPI;
            } else {
                return mybdjobsAPI;
            }
        }
    }


}
