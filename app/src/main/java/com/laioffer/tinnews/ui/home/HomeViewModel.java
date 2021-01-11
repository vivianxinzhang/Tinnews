package com.laioffer.tinnews.ui.home;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.laioffer.tinnews.model.Article;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.repository.NewsRepository;

public class HomeViewModel extends ViewModel {
    private final NewsRepository repository;
    private final MutableLiveData<String> countryInput = new MutableLiveData<>();
    private final MutableLiveData<Article> favoriteArticleInput = new MutableLiveData<>();

    public HomeViewModel(NewsRepository newsRepository) {
        this.repository = newsRepository;
    }

    public void setCountryInput(String country) {
        // input
        countryInput.setValue(country);
    }

    public void setFavoriteArticleInput(Article article) {
        favoriteArticleInput.setValue(article);
    }

    public LiveData<NewsResponse> getTopHeadlines() {
        return Transformations.switchMap(countryInput, repository::getTopHeadlines);
    }

    public LiveData<Boolean> onFavorite() {
        return Transformations.switchMap(favoriteArticleInput, repository::favoriteArticle);
    }

    public void onCancel() {
        repository.onCancel();
    }

//    public LiveData<NewsResponse> getTopHeadlines() {
//        return Transformations.switchMap(countryInput, new Function<String, LiveData<NewsResponse>>() {
//            @Override
//            public LiveData<NewsResponse> apply(String output) {
//                return repository.getTopHeadlines(output);
//            }
//        });
//    }

}
