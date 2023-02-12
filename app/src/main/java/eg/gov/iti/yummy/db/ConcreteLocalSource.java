package eg.gov.iti.yummy.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import io.reactivex.rxjava3.core.Observable;

public class ConcreteLocalSource implements LocalSource {
    private final UserDao userDao;
    private static ConcreteLocalSource concreteLocalSource=null;

    public ConcreteLocalSource(Context context) {
        AppDataBase db=AppDataBase.getInstance(context.getApplicationContext());
        this.userDao = db.userDao();
    }

    public static ConcreteLocalSource getInstance(Context context){
        if(concreteLocalSource==null){
            concreteLocalSource=new ConcreteLocalSource(context);
        }
        return concreteLocalSource;
    }

    @Override
    public void registerUser(UserEntity userEntity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.registerUser(userEntity);
            }
        }).start();
    }

    @Override
    public Observable<Boolean> login(String userName, String password) {

        return userDao.login(userName, password);
    }

    @Override
    public Observable<Boolean> is_Taken(String name) {
        return userDao.is_Taken(name);
    }

    @Override
    public LiveData<UserEntity> getData(String username) {
        return userDao.getData(username);
    }

    @Override
    public void updateSaturday(String saturday , String username) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                userDao.updateSaturday(saturday,username);
            }
        }).start();
    }

    @Override
    public void updateSunday(String sunday,String username) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.updateSunday(sunday,username);
            }
        }).start();
    }

    @Override
    public void updateMonday(String monday,String username) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.updateMonday(monday,username);
            }
        }).start();
    }

    @Override
    public void updateTuesday(String tuesday,String username) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.updateTuesday(tuesday,username);
            }
        }).start();
    }

    @Override
    public void updateWednesday(String wednesday,String username) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.updateWednesday(wednesday,username);
            }
        }).start();
    }

    @Override
    public void updateThursday(String thursday,String username) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.updateThursday(thursday,username);
            }
        }).start();
    }

    @Override
    public void updateFriday(String friday,String username) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.updateFriday(friday,username);
            }
        }).start();
    }

    @Override
    public void updateFavourite(String favourite,String username) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.updateFavourite(favourite,username);
            }
        }).start();
    }
}
