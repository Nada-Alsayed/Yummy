package eg.gov.iti.yummy.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class},version = 1)

public abstract class AppDataBase extends RoomDatabase {
        private static AppDataBase appDataBase=null;
        //the abstract method
        public abstract UserDao userDao();

        public static synchronized AppDataBase getInstance(Context context){
            if(appDataBase==null){
                appDataBase= Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"products").build();
            }
            return appDataBase;
        }
}

