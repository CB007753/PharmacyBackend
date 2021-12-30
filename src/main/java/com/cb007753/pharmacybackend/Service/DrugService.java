package com.cb007753.pharmacybackend.Service;

import com.cb007753.pharmacybackend.Model.BuyDrugs;
import com.cb007753.pharmacybackend.Model.Drugs;

import java.util.List;
import java.util.Optional;

public interface DrugService {

    List<Drugs> getAllDrugs();

    List<BuyDrugs> getBuyDrugs();

    Optional<BuyDrugs> getDrugByID(Long id);

    void deleteFile(Long id);

    boolean saveDrug(BuyDrugs buyDrugs);
}
