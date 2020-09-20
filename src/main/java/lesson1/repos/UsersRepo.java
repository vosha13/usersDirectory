package lesson1.repos;

import lesson1.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepo extends CrudRepository<User, Long> {

    List<User> findByFirstName(String firstName);

}
