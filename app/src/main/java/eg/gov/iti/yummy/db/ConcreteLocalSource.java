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
}
