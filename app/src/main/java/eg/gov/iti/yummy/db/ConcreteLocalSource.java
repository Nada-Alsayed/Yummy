package eg.gov.iti.yummy.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

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
    public LiveData<UserEntity> login(String userName, String password) {
//        new Thread(new Runnable() {
//           @Override
//           public void run() {
//
//           }
//       }).start();
        return userDao.login(userName, password);
    }

    @Override
    public LiveData<UserEntity> getData(String username) {
        return userDao.getData(username);
    }

    @Override
    public void updateSaturday(String saturday) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                userDao.updateSaturday(saturday);
            }
        }).start();
    }

    @Override
    public void updateSunday(String sunday) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.updateSunday(sunday);
            }
        }).start();
    }

    @Override
    public void updateMonday(String monday) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.updateMonday(monday);
            }
        }).start();
    }

    @Override
    public void updateTuesday(String tuesday) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.updateTuesday(tuesday);
            }
        }).start();
    }

    @Override
    public void updateWednesday(String wednesday) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.updateWednesday(wednesday);
            }
        }).start();
    }

    @Override
    public void updateThursday(String thursday) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.updateThursday(thursday);
            }
        }).start();
    }

    @Override
    public void updateFriday(String friday) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.updateFriday(friday);
            }
        }).start();
    }

    @Override
    public void updateFavourite(String favourite) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.updateFavourite(favourite);
            }
        }).start();
    }
}
