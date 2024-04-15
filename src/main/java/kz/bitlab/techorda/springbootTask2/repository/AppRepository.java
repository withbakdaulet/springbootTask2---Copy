package kz.bitlab.techorda.springbootTask2.repository;

import jakarta.transaction.Transactional;

import kz.bitlab.techorda.springbootTask2.entities.ApplicationReq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AppRepository extends JpaRepository<ApplicationReq,Long> {
}
