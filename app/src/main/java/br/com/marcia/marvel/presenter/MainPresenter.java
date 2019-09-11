package br.com.marcia.marvel.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import br.com.marcia.marvel.model.gson.Character;
import br.com.marcia.marvel.model.gson.DataWrapper;
import br.com.marcia.marvel.model.Request;
import br.com.marcia.marvel.base.BasePresenter;
import br.com.marcia.marvel.remote.MarvelService;
import br.com.marcia.marvel.view.MainActivityView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter extends BasePresenter {

    private static final String TAG = MainPresenter.class.getName();

    private MainActivityView mView;

    private MarvelService mMarvelService;

    private Request mRequest;

    public MainPresenter(@NonNull final MainActivityView view,
                         @NonNull final MarvelService marvelService,
                         @NonNull final Request request) {

        this.mView = view;
        this.mMarvelService = marvelService;
        this.mRequest = request;
    }

    @Override
    public void onResume() {
        super.onResume();

        findCharacters();
    }

    /**
     * Busca os personagens da Marvel via API
     */
    public void findCharacters() {

        Log.d(TAG, "Procurando personagens...");

        mView.showLoading();

        Call<DataWrapper<Character>> call = mMarvelService.getCharacters(
                                                                mRequest.getLimit(),
                                                                mRequest.getOffset(),
                                                                mRequest.getApiKey(),
                                                                mRequest.getHash(),
                                                                mRequest.getTs()
                                                            );

        call.enqueue(new Callback<DataWrapper<Character>>() {

            @Override
            public void onResponse(Call<DataWrapper<Character>> call, Response<DataWrapper<Character>> response) {

                if (response.isSuccessful()) {

                    Log.d(TAG, "Busca de personagens realizada com sucesso");
                    DataWrapper<Character> result = response.body();
                    List<Character> characters = result.getData().getResults();
                    mView.showCharacters(characters);

                    mRequest.increaseOffset();
                    mRequest.setLoading(true);

                } else {
                    mView.showServerErrorMessage();
                    Log.e(TAG, "Erro no servidor ao acessar serviço de listagem de personagens");
                }

                mView.hideLoading();
            }

            @Override
            public void onFailure(Call<DataWrapper<Character>> call, Throwable t) {

                //Referência: https://futurestud.io/tutorials/retrofit-2-how-to-detect-network-and-conversion-errors-in-onfailure
                if (t instanceof IOException) {
                    mView.showConnectionErrorMessage();
                    Log.d(TAG, "Usuário sem conexão: " + t.getMessage());

                } else {

                    mView.showConversionErrorMessage();
                    Log.e(TAG, "Erro de conversão ao acessar os dados de personagens: " + t.getMessage());
                }

                mView.hideLoading();
            }
        });

    }

}
