//package org.example.service;
//
//import org.example.model.User;
//import org.example.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService {
//    @Autowired
//    UserRepository userRepository;
//
//    public User saveUser(User user){
//        return userRepository.save(user);
//    }
//
//    public List<User> allUsers(){
//        return userRepository.findAll();
//    }
//
//    public Optional<User> userById(Long id){
//        return userRepository.findById(id);
//    }
//
//    public User updateUser(User user){
//        return userRepository.save(user);
//    }
//
//}
