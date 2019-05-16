package com.example.engineerbabusample.DBUser.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.engineerbabusample.DBUser.DataModelUser.UserDataModel;
import com.example.engineerbabusample.DBUser.Repository.UserRepository;

import java.util.List;

public class ViewModelUser  extends AndroidViewModel {

    public UserRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    public LiveData<List<UserDataModel>> mAllWords;

    public ViewModelUser(Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<UserDataModel>> getmAllWords() {
        return mAllWords;
    }


    public void insert(UserDataModel word) {
        mRepository.insert(word);
    }
}
