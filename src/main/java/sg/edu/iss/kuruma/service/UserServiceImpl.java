package sg.edu.iss.kuruma.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bytebuddy.utility.RandomString;
import sg.edu.iss.kuruma.model.Car;
import sg.edu.iss.kuruma.model.User;
import sg.edu.iss.kuruma.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository urepo;

	@Transactional
    @Override
    public User getUser(Integer id) {        
            return urepo.findById(id).orElse(null);
    } 
    @Transactional
    @Override
    public void removeFromWishlist(Car car, String username) {        
    	User user = findByUsername(username);
    	List<Car> wishlist = user.getWishlist();	   	
		wishlist.remove(car);
    	user.setWishlist(wishlist);
    	urepo.saveAndFlush(user);
    }   
    
    @Transactional
	@Override
	public void addToWishlist(Car car, String username) {
    	User user = findByUsername(username);
    	List<Car> wishlist = user.getWishlist();	   	
		wishlist.add(car);
    	user.setWishlist(wishlist);
    	urepo.saveAndFlush(user);
    }		
    
    @Transactional
    @Override
	public User findByUsername(String username) {
		return urepo.findByUsername(username);
	}
    
    @Transactional
    @Override
	public User findByEmail(String email) {
		return urepo.findByEmail(email);
	}

    @Transactional
	@Override
	public List<Integer> getWishlist(String username) {
    	List<Integer> wishList = new ArrayList<Integer>();
    	for(Car c:findByUsername(username).getWishlist())
    	{wishList.add(c.getId());}
		return wishList;
	}
	
	@Transactional
	@Override
	public User addUser(User user) {
	    String randomCode = RandomString.make(64);
	    user.setVerificationCode(randomCode);
	    user.setEnabled(false);
		return urepo.saveAndFlush(user);
		//sendVerificationEmail(user, siteURL);
	}

	@Transactional
	@Override
	public void save(User u) {
		urepo.save(u);
	}
	
	@Transactional
	@Override
	public boolean isEmailUnique(String email) {
		User userByEmail = urepo.findByEmail(email);
		
		return userByEmail == null;
	}
	
	@Transactional
	@Override
	public boolean verify(String verificationCode) {
	    User user = urepo.findByVerificationCode(verificationCode);
	     
	    if (user == null || user.isEnabled()) {
	        return false;
	    } else {
	        user.setVerificationCode(null);
	        user.setEnabled(true);
	        urepo.save(user);
	         
	        return true;
	    }
	
}
}