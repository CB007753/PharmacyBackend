package com.cb007753.pharmacybackend.Service;

import com.cb007753.pharmacybackend.Model.Drugs;
import com.cb007753.pharmacybackend.Repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugServiceImpl implements DrugService{

    @Autowired
    private DrugRepository drugRepository;




    //Overridded methods of Drug service interface
    @Override
    public List<Drugs> getAllDrugs() {
        return drugRepository.findAll();
    }
}
