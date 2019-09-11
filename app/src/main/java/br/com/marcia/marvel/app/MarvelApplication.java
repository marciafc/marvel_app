package br.com.marcia.marvel.app;

import android.app.Application;

import br.com.marcia.marvel.app.module.DaggerMarvelComponent;
import br.com.marcia.marvel.app.module.MarvelComponent;
import br.com.marcia.marvel.app.module.MarvelModule;

public class MarvelApplication extends Application {

    private MarvelComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initDagger();

    }

    private void initDagger() {

      mComponent = DaggerMarvelComponent.
                builder().
                marvelModule(new MarvelModule(this)).
                build();
    }

    public MarvelComponent getComponent() {
        return this.mComponent;
    }
}