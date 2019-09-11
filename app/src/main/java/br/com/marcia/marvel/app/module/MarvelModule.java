package br.com.marcia.marvel.app.module;

import javax.inject.Singleton;

import br.com.marcia.marvel.R;
import br.com.marcia.marvel.app.MarvelApplication;
import br.com.marcia.marvel.model.Request;
import br.com.marcia.marvel.remote.MarvelService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MarvelModule {

    private MarvelApplication mMarvelApplication;

   public MarvelModule(MarvelApplication marvelApplication) {
        this.mMarvelApplication = marvelApplication;
    }

    @Singleton
    @Provides
    public MarvelService getMarvelService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.mMarvelApplication.getString(R.string.marvel_api_endpoint))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MarvelService marvelService = retrofit.create(MarvelService.class);

        return marvelService;
    }

    @Provides
    public Request getRequest() {
        Request request = new Request(this.mMarvelApplication);
        request.setLimit(20);
        request.setOffset(0);
        request.setLoading(true);

        return request;
    }
}



