package br.com.marcia.marvel.app.module;

import javax.inject.Singleton;

import br.com.marcia.marvel.view.MainActivity;
import dagger.Component;

@Component(modules = MarvelModule.class)
@Singleton
public interface MarvelComponent {

    void inject(MainActivity mainActivity);

}