package br.com.marcia.marvel.view;

import java.util.List;

import br.com.marcia.marvel.model.gson.Character;

public interface MainActivityView {

    void showLoading();

    void hideLoading();

    void showCharacters(List<Character> characters);

    void showConnectionErrorMessage();

    void showConversionErrorMessage();

    void showServerErrorMessage();

}
