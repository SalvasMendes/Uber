package uber;

import home.*;
import user.*;

public interface UberInterface {

	void createUser(String userId, String email, String phone, String name, String address, String nationality);

	void createHome(String homeId, String userId, int price, int cap, String local, String description, String address);

	void alterUser(String userId, String email, String phone, String name, String address, String nationality);

	void removeUser(String userId);

	void removeHome(String homeId);

	UserInterface userInfo(String userId);

	HomeInterface homeInfo(String homeId);

	void addScore(String homeId, String userId, int score);

}
