package com.cb007753.pharmacybackend.Service;

import com.cb007753.pharmacybackend.Model.BuyDrugs;
import com.cb007753.pharmacybackend.Model.Drugs;

import java.util.List;

public interface DrugService {

    List<Drugs> getAllDrugs();

    List<BuyDrugs> getBuyDrugs();
}
