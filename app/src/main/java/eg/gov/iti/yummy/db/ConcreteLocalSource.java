package eg.gov.iti.yummy.db;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.WeekPlan;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ConcreteLocalSource implements LocalSource {
    private final UserDao userDao;
    private static ConcreteLocalSource concreteLocalSource = null;
    private Observable<List<MealDetail>> storeMeals;
    public ConcreteLocalSource(Context context) {
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        this.userDao = db.userDao();
        this.storeMeals = this.userDao.getAllProducts();
    }

    public static ConcreteLocalSource getInstance(Context context) {
        if (concreteLocalSource == null) {
            concreteLocalSource = new ConcreteLocalSource(context);
        }
        return concreteLocalSource;
    }


    @Override
    public Observable<List<WeekPlan>> getSundayMeals() {
        return userDao.getSundayMeals("1");
    }

    @Override
    public Observable<List<WeekPlan>> getMondayMeals() {
        return userDao.getMondayMeals("1");
    }

    @Override
    public Observable<List<WeekPlan>> getTuesdayMeals() {
        return userDao.getTuesdayMeals("1");
    }

    @Override
    public Observable<List<WeekPlan>> getWeddayMeals() {
        return userDao.getWednesdayMeals("1");
    }

    @Override
    public Observable<List<WeekPlan>> getThursdayMeals() {
        return userDao.getThursdayMeals("1");
    }

    @Override
    public Observable<List<WeekPlan>> getSatdayMeals() {
        return userDao.getSaturdayMeals("1");
    }

    @Override
    public Observable<List<WeekPlan>> getFridayMeals() {
        return userDao.getFridayMeals("1");
    }

    @Override
    public void insertMeal(MealDetail meal) {
        userDao.insertMeal(meal).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }
            @Override
            public void onComplete() {
                Log.e("HI", "success insert meal: +++++++++++++++++++++++concrete localsource ");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("HI", "failed insert meal: ++++++++++++++++++++++++concrete localsource ");
            }
        });
    }

    @Override
    public void insertMealToWeekPlan(WeekPlan meal) {
        userDao.insertMealToWeekPlan(meal).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }
            @Override
            public void onComplete() {
                Log.e("HI", "success insert meal into week: +++++++++++++++++++++++concrete localsource ");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("HI", "failed insert meal into week: ++++++++++++++++++++++++concrete localsource ");
            }
        });
    }

    @Override
    public void deleteMeal(MealDetail meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.deleteMeal(meal) ;
            }
        }).start();
    }

    @Override
    public Observable<MealDetail> getOfflineMeal(String mealName) {
        Log.e("joo", "getOfflineMeal:concrete localso ");
        return userDao.getOfflineMeal(mealName);
    }

    @Override
    public void registerUser(UserEntity userEntity) {
        userDao.registerUser(userEntity).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.e("HI", "++++++++++++++success insert user: concrete localsource ");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("HI", "++++++++++++++++++++++++++failed insert user: concrete localsource ");
            }
        });
    }

    @Override
    public Observable<List<MealDetail>> getAllStoredMeals() {
        return storeMeals;
    }

    @Override
    public String login(String userName, String password) {

       String c= userDao.login(userName, password).subscribeOn(Schedulers.io())
                .subscribe(item ->
                            Log.e(TAG, "itemfalselogin: " + item),
                        error -> error.printStackTrace()).toString();
        return c;
    }

    @Override
    public String is_Taken(String name) {
        String c= userDao.is_Taken(name).subscribeOn(Schedulers.io())
                .subscribe(item -> Log.e(TAG, "Exist: " + item),
                        error -> error.printStackTrace()).toString();
        return c;
    }

    @Override
    public void updateSaturday(String saturday) {
        userDao.updateSaturday(saturday).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.e("HI", "++++++++++++++success insert sat: concrete localsource ");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("HI", "failed insert thurs: concrete localsource ");

            }
        });
    }

    @Override
    public void updateSunday(String sunday) {
        userDao.updateSunday(sunday).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.e("HI", "++++++++++++++success insert sun: concrete localsource ");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("HI", "failed insert thurs: concrete localsource ");

            }
        });
    }

    @Override
    public void updateMonday(String monday) {
        userDao.updateMonday(monday).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.e("HI", "++++++++++++++success insert mon: concrete localsource ");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("HI", "failed insert thurs: concrete localsource ");

            }
        });
    }

    @Override
    public void updateTuesday(String tuesday) {
        userDao.updateTuesday(tuesday).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.e("HI", "++++++++++++++success insert tues: concrete localsource ");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("HI", "failed insert thurs: concrete localsource ");
                e.printStackTrace();
            }
        });
    }

    @Override
    public void updateWednesday(String wednesday) {
        userDao.updateWednesday(wednesday).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.e("HI", "++++++++++++++success insert wed: concrete localsource ");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("HI", "failed insert thurs: concrete localsource ");

            }
        });
    }

    @Override
    public void updateThursday(String thursday) {
        userDao.updateThursday(thursday).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.e("HI", "++++++++++++++success insert thurs: concrete localsource ");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("HI", "failed insert thurs: concrete localsource ");

            }
        });
    }

    @Override
    public void updateFriday(String friday) {
        userDao.updateFriday(friday).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.e("HI", "++++++++++++++success insert fri: concrete localsource ");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("HI", "failed insert thurs: concrete localsource ");

            }
        });
    }

//    @Override
//    public void updateFavourite(String favourite) {
//
//    }


//
//    @Override
//    public void registerUser(UserEntity userEntity) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                userDao.registerUser(userEntity);
//            }
//        }).start();
//    }
//
//    @Override
//    public Observable<Boolean> login(String userName, String password) {
//
//        return userDao.login(userName, password);
//    }
//
//    @Override
//    public Observable<Boolean> is_Taken(String name) {
//        return null;
//    }

//    @Override
//    public Observable<Boolean> is_Taken(String name) {
//        return userDao.is_Taken(name);
//    }
//
//    @Override
//    public LiveData<UserEntity> getData(String username) {
//        return userDao.getData(username);
//    }
//
//    @Override
//    public void updateSaturday(String saturday , String username) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                userDao.updateSaturday(saturday,username);
//            }
//        }).start();
//    }
//
//    @Override
//    public void updateSunday(String sunday,String username) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                userDao.updateSunday(sunday,username);
//            }
//        }).start();
//    }
//
//    @Override
//    public void updateMonday(String monday,String username) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                userDao.updateMonday(monday,username);
//            }
//        }).start();
//    }
//
//    @Override
//    public void updateTuesday(String tuesday,String username) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                userDao.updateTuesday(tuesday,username);
//            }
//        }).start();
//    }
//
//    @Override
//    public void updateWednesday(String wednesday,String username) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                userDao.updateWednesday(wednesday,username);
//            }
//        }).start();
//    }
//
//    @Override
//    public void updateThursday(String thursday,String username) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                userDao.updateThursday(thursday,username);
//            }
//        }).start();
//    }
//
//    @Override
//    public void updateFriday(String friday,String username) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                userDao.updateFriday(friday,username);
//            }
//        }).start();
//    }
//
//    @Override
//    public void updateFavourite(String favourite,String username) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                userDao.updateFavourite(favourite,username);
//            }
//        }).start();
//    }
}
