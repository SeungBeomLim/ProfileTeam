package org.example.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.domain.User;
import org.example.dto.request.UserRequest;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void create( UserRequest request ){
        User user = User.builder()
                .userId(request.getUserId())
                .userName(request.getUserName())
                .userEmail(request.getUserEmail())
                .userImage(request.getUserImage())
                .build();
        userRepository.save(user);
    }
    public List<User> read() {
        return userRepository.findAll();
    }

    public User update(Long id, UserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
        user.setUserName(request.getUserName());
        user.setUserEmail(request.getUserEmail());
        user.setUserImage(request.getUserImage());
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}