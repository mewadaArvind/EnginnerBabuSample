package com.example.engineerbabusample.DBUser.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.engineerbabusample.DBUser.DataModelUser.UserDataModel;
import com.example.engineerbabusample.DBUser.Repository.UserRepositiry;

import java.util.List;

public class ViewModelUser  extends AndroidViewModel {

    public UserRepositiry mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    public LiveData<List<UserDataModel>> mAllWords;

    public ViewModelUser(Application application) {
        super(application);
        mRepository = new UserRepositiry(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<UserDataModel>> getmAllWords() {
        return mAllWords;
    }

    public void setmAllWords(LiveData<List<UserDataModel>> mAllWords) {
        this.mAllWords = mAllWords;
    }

    public UserRepositiry getmRepository() {
        return mRepository;
    }

    public void setmRepository(UserRepositiry mRepository) {
        this.mRepository = mRepository;
    }

    public void insert(UserDataModel word) {
        mRepository.insert(word);
    }
}
