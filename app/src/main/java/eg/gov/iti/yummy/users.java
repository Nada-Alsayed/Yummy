package eg.gov.iti.yummy;

public class users {
    String UserId,UserName,ProfilePic,Email;

    public users(String userId, String userName, String profilePic, String email) {
        UserId = userId;
        UserName = userName;
        ProfilePic = profilePic;
        Email = email;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public users(){};
}
