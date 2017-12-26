package zw.co.sbs.steward.freelancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import zw.co.sbs.steward.freelancer.domain.Devices;

@Service
public interface DeviceRepository extends JpaRepository<Devices, Long> {


}
