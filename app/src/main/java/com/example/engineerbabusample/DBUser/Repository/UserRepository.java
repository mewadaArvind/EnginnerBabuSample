package com.example.engineerbabusample.DBUser.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.engineerbabusample.DBUser.DAO.DAOUser;
import com.example.engineerbabusample.DBUser.DataModelUser.UserDataModel;
import com.example.engineerbabusample.DBUser.RoomDataBase.RoomUser;

import java.util.List;

public class UserRepository {
    private DAOUser mWordDao;
    private LiveData<List<UserDataModel>> mAllWords;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public UserRepository(Application application) {
        RoomUser db = RoomUser.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<UserDataModel>> getAllWords() {
        return mAllWords;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    public void insert(UserDataModel word) {
        new insertAsyncTask(mWordDao).execute(word);
    }


    private static class insertAsyncTask extends AsyncTask<UserDataModel, Void, Void> {

        private DAOUser mAsyncTaskDao;

        insertAsyncTask(DAOUser dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final UserDataModel... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
