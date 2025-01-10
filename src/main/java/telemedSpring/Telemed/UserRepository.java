package telemedSpring.Telemed;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

    List<AppUser> findByType(int type);


}