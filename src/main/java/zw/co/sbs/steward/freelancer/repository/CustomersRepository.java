package zw.co.sbs.steward.freelancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import zw.co.sbs.steward.freelancer.domain.Customers;


@Service
public interface CustomersRepository extends JpaRepository<Customers, Long> {



}