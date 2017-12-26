package zw.co.sbs.steward.freelancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import zw.co.sbs.steward.freelancer.domain.User;

@Service
public interface AuthRepository extends JpaRepository<User, Long> {



}
