package com.cb007753.pharmacybackend.Repository;

import com.cb007753.pharmacybackend.Model.BuyDrugs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//this repository is used for drugs available in supplier market.
@Repository
public interface BuyDrugRepository extends JpaRepository<BuyDrugs,Long> {
}
