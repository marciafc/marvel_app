package br.com.marcia.marvel.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.marcia.marvel.model.Request;
import br.com.marcia.marvel.model.gson.Character;
import br.com.marcia.marvel.base.BaseActivity;
import br.com.marcia.marvel.R;
import br.com.marcia.marvel.app.MarvelApplication;
import br.com.marcia.marvel.presenter.MainPresenter;
import br.com.marcia.marvel.remote.MarvelService;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter>  implements MainActivityView {

    private static final String TAG = MainActivity.class.getName();

    @BindView(R.id.main_recycler_personagens)
    RecyclerView mRecyclerViewPersonagens;

    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;

    @Inject
    MarvelService mMarvelService;

    @Inject
    Request mRequest;

    CharactersAdapter mAdapterPersonagens;


    @NonNull
    @Override
    protected MainPresenter createPresenter(@NonNull Context context) {
        ((MarvelApplication) getApplication()).getComponent().inject(this);

        return new MainPresenter(this, mMarvelService, mRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        configureRecyclerViewCharacters();

    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }


    @Override
    public void showCharacters(List<Character> characters) {
        mAdapterPersonagens.updateList(characters);
    }

    @Override
    public void showConnectionErrorMessage() {
        Toast.makeText(this, getString(R.string.error_message_connection), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showConversionErrorMessage() {
        Toast.makeText(this, getString(R.string.error_message_conversion), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showServerErrorMessage() {
        Toast.makeText(this, getString(R.string.error_message_server), Toast.LENGTH_LONG).show();
    }

    private void configureRecyclerViewCharacters() {

        mRecyclerViewPersonagens.setHasFixedSize(true);

        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewPersonagens.setLayoutManager(mLayoutManager);

        mAdapterPersonagens = new CharactersAdapter(new ArrayList<Character>());
        mRecyclerViewPersonagens.setAdapter(mAdapterPersonagens);

        mRecyclerViewPersonagens.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mRecyclerViewPersonagens.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = mLayoutManager.getChildCount();
                    int totalItemCount = mLayoutManager.getItemCount();
                    int pastVisibleItems = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                    if (mRequest.isLoading()) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            mRequest.setLoading(false);
                            mPresenter.findCharacters();
                        }
                    }
                }
            }
        });

        Log.d(TAG, "Configuração do recyclerView de personagens ok...");

    }


}
