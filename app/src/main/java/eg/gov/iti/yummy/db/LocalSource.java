package eg.gov.iti.yummy.db;

import androidx.lifecycle.LiveData;

public interface LocalSource {
    void registerUser(UserEntity userEntity);
    LiveData<UserEntity> login(String userName, String password);
}
