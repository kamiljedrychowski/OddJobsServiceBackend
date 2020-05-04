package odd.jobs.services.user;

import odd.jobs.entities.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


public class NullAttributesUpdateFiller {

    private final User userToUpdate;
    private final User update;
    private final PasswordEncoder passwordEncoder;

    public NullAttributesUpdateFiller(User userToUpdate, User update){
        this.userToUpdate = userToUpdate;
        this.update = update;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User update(){
        String password = null;
        if(update.getPassword() != null){
            password = passwordEncoder.encode(update.getPassword());
        }

        return userToUpdate.toBuilder()
                .password(Optional.ofNullable(password)
                        .orElse(userToUpdate.getPassword()))
                .firstName(Optional.ofNullable(update.getFirstName())
                        .orElse(userToUpdate.getFirstName()))
                .lastName(Optional.ofNullable(update.getLastName())
                        .orElse(userToUpdate.getLastName()))
                .email(Optional.ofNullable(update.getEmail())
                        .orElse(userToUpdate.getEmail()))
                .phoneNumber(Optional.ofNullable(update.getPhoneNumber())
                        .orElse(userToUpdate.getPhoneNumber()))
                .build();
    }
}
