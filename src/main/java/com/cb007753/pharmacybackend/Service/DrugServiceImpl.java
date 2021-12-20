package com.cb007753.pharmacybackend.Service;

import com.cb007753.pharmacybackend.Model.BuyDrugs;
import com.cb007753.pharmacybackend.Model.Drugs;
import com.cb007753.pharmacybackend.Repository.BuyDrugRepository;
import com.cb007753.pharmacybackend.Repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugServiceImpl implements DrugService{

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private BuyDrugRepository buyDrugRepository;


    //Overridded methods of Drug service interface
    @Override
    public List<Drugs> getAllDrugs() {
        return drugRepository.findAll();
    }

    @Override
    public List<BuyDrugs> getBuyDrugs() {
        return buyDrugRepository.findAll();
    }
}
