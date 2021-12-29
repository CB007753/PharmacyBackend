package com.cb007753.pharmacybackend.Controller;

import com.cb007753.pharmacybackend.Model.BuyDrugs;
import com.cb007753.pharmacybackend.Model.Order;
import com.cb007753.pharmacybackend.Repository.BuyDrugRepository;
import com.cb007753.pharmacybackend.Repository.OrderRepository;
import com.cb007753.pharmacybackend.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SupplierController {

    @Autowired
    BuyDrugRepository buyDrugRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

//    -------------------------------------------------------------------------------------------------

    //displays all the drugs in supplier market
    @RequestMapping(value = "/supplier/allbuydrugs")
    public String viewAllBuyDrugs(Model model)
    {
        List<BuyDrugs> supplierBuyDrugsList = buyDrugRepository.findAll();

        model.addAttribute("supplierBuyDrugsList",supplierBuyDrugsList);

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        model.addAttribute("useremail",userDetails);

        //redirecting to ViewPharmacyInventoryUser html page
        return "ViewAllBuyDrugsSupplier";
    }


//    -------------------------------------------------------------------------------------------------
//    -------------------------------------------------------------------------------------------------

    //displays all the drugs in supplier market
    @RequestMapping(value = "/supplier/allotworders/{status}")
    public String viewAllOTWOrders(@PathVariable("status") String status, Model model)
    {
        List<Order> supplierOTWList = orderRepository.findByStatus(status);

        model.addAttribute("supplierOTWList",supplierOTWList);

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        model.addAttribute("useremail",userDetails);

        //redirecting to ViewPharmacyInventoryUser html page
        return "ViewAllOTWSupplier";
    }


//    -------------------------------------------------------------------------------------------------



}
