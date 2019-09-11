package br.com.marcia.marvel.remote;

import br.com.marcia.marvel.model.gson.Character;
import br.com.marcia.marvel.model.gson.DataWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelService {

    @GET("characters")
    Call<DataWrapper<Character>> getCharacters(@Query("limit") int limit,
                                               @Query("offset") int offset,
                                               @Query("apikey") String apikey,
                                               @Query("hash") String hash,
                                               @Query("ts") String ts);

}
