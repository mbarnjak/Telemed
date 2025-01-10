package telemedSpring.Telemed;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PatientRecordRespository extends JpaRepository<PatientRecord, Integer> {





    List<PatientRecord> findByAppUser(AppUser user);
}