//認証サービス　DBに関する認証やDB入手などのメソッドを記述する　このインスタンスを利用してコントローラは各種操作を行う　DB操作はリポジトリに任せる
package io.github.rokloc.login.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.rokloc.login.entity.User;
import io.github.rokloc.login.repository.UserRepository;

@Service
public class AuthService{
	private UserRepository userrepository;
	
	public AuthService(UserRepository userrepository) {
		this.userrepository = userrepository;
	}
	
	//認証サービス usernameとpasswordを受取。DBにusernameとpasswordが合致したレコードがあればそのUserインスタンスを返す。ないならnull返す
	public User authenticate(String username, String password) {
		Optional<User> optionalUser = userrepository.findByUsername(username);
		
		if (optionalUser.isEmpty()) {
			return (null);
		}
		User user = optionalUser.get();
		if (!(user.getPassword().equals(password))) {
			return (null);
		}
		return (user);
	}
}
