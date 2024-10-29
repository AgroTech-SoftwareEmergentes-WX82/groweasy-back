package wx82.agrotech.groweasyapi.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse getUserOwner(Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());

        return userRepository.findById(user.getId())
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new EntityNotFoundException("No user found with ID:: " + user.getId()));

    }
}
