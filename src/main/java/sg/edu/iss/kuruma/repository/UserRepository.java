package sg.edu.iss.kuruma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.kuruma.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    public User findByVerificationCode(String code);
}
