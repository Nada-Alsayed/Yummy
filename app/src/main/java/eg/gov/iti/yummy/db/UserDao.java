package eg.gov.iti.yummy.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert
    void registerUser(UserEntity userEntity);
    @Query("Select * from UserData where userName=(:username) and password=(:password)")
    LiveData<UserEntity> login(String username, String password);
}
