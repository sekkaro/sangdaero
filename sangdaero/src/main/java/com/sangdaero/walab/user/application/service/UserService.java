package com.sangdaero.walab.user.application.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.sangdaero.walab.common.entity.User;
import com.sangdaero.walab.user.application.dto.UserDto;
import com.sangdaero.walab.user.repository.UserRepository;

@Service
public class UserService extends OidcUserService {
	
	private UserRepository mUserRepository;
	
	public UserService(UserRepository userRepository) {
		mUserRepository = userRepository;
	}
	
	@Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        Map attributes = oidcUser.getAttributes();
        UserDto userDto = new UserDto();
        userDto.setName((String) attributes.get("name"));
        userDto.setNickName((String) attributes.get("nickname"));
        userDto.setProfile((String) attributes.get("picture"));
        userDto.setSocialId((String) attributes.get("sub"));
        userDto.setPhone((String) attributes.get("phone_number"));
        
        updateUser(userDto);
        return oidcUser;
    }

    private void updateUser(UserDto userDto) {
        User user = mUserRepository.findBySocialId(userDto.getSocialId());
        if(user == null) {
            user = userDto.toEntity();   
        }
        
        user.setLastLogin(LocalDateTime.now());
        
		mUserRepository.save(user);
    }
    
    public UserDto getUser(@AuthenticationPrincipal OAuth2User principal) {
    	User user = mUserRepository.findBySocialId(principal.getAttribute("sub"));
    	
    	UserDto userDto = convertEntityToDto(user);
    	
    	return userDto;
    }
    
    public void setStatus(@AuthenticationPrincipal OAuth2User principal, Boolean isOn) {
    	User user = mUserRepository.findBySocialId(principal.getAttribute("sub"));
    	
    	if(isOn) {
    		user.setStatus(1);
    	}
    	else {
    		user.setStatus(0);
    	}
    	
    	mUserRepository.save(user);
    	
    }
    
    private UserDto convertEntityToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .nickName(user.getNickName())
                .profile(user.getProfile())
                .socialId(user.getSocialId())
                .phone(user.getPhone())
                .userType(user.getUserType())
                .status(user.getStatus())
                .volunteerTime(user.getVolunteerTime())
                .interest(user.getInterest())
                .service(user.getService())
                .memo(user.getMemo())
                .locationAgree(user.getLocationAgree())
                .phoneAgree(user.getPhoneAgree())
                .community(user.getCommunity())
                .lastLogin(user.getLastLogin())
                .build();
    }

}
