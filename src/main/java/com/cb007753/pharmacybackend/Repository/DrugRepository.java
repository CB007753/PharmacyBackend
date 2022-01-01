package com.cb007753.pharmacybackend.Repository;

import com.cb007753.pharmacybackend.Model.Drugs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DrugRepository extends JpaRepository<Drugs,Long> {
}
