package vdm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vdm.helpers.Role;
import vdm.controllers.helpsOfControllers.FormUser;
import vdm.dto.UserDTO;
import vdm.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserService {

    public UserService() {
    }

    private UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public List<UserDTO> getAllDeveloper(){
        List<vdm.entity.User> persistenceUsers = userRepository
                                         .findUsersByRole(Role.ROLE_DEVELOPER);
        return persistenceUsers.stream()
                               .map(this::convertUserToUserDTO)
                               .collect(Collectors.toList());
    }

    @Transactional
    public vdm.entity.User getUser(int userId){
        return  userRepository.getOne(userId);
    }

    public UserDTO addUser(vdm.entity.User user){
        vdm.entity.User persistenceUser = userRepository.saveAndFlush(user);
        return convertUserToUserDTO(persistenceUser);
    }

    public vdm.entity.User addFormUser(FormUser formUser){
        vdm.entity.User user = new vdm.entity.User();
        user.setName(formUser.getName());
        user.setLastName(formUser.getLastName());
        user.setEmail(formUser.getEmail());
        user.setPasswordHash(formUser.getPassword());
        user.setRole(formUser.getRole());
        user.setEnabled(formUser.isEnabled());
        return userRepository.saveAndFlush(user);
    }

    public List<UserDTO> getDeveloper(String name, String lastName){
        List<vdm.entity.User> userList = userRepository
                                 .findByNameAndLastName(name, lastName);
        return userList.stream()
                       .map(this::convertUserToUserDTO)
                       .collect(Collectors.toList());
    }

    private UserDTO convertUserToUserDTO(vdm.entity.User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setRole(user.getRole().name());
        userDTO.setName(user.getName());
        userDTO.setLastName(user.getLastName());
        return userDTO;
    }
}
