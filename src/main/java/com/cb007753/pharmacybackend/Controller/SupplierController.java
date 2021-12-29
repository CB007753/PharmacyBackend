package com.cb007753.pharmacybackend.Controller;

import com.cb007753.pharmacybackend.Model.BuyDrugs;
import com.cb007753.pharmacybackend.Model.Drugs;
import com.cb007753.pharmacybackend.Model.Order;
import com.cb007753.pharmacybackend.Repository.BuyDrugRepository;
import com.cb007753.pharmacybackend.Repository.OrderRepository;
import com.cb007753.pharmacybackend.Service.DrugService;
import com.cb007753.pharmacybackend.Service.OrderService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class SupplierController {

    @Autowired
    BuyDrugRepository buyDrugRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    DrugService drugService;

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

    //displays all the on the way orders
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

    //this deletes the selected drug from buy_drug table in database
    @GetMapping(value = "/supplier/deletedrug/{id}")
    public String deleteDrug(@PathVariable("id") Long id)
    {
        drugService.deleteFile(id);
        return  "redirect:/supplier/allbuydrugs?deletesuccess";
    }

//    -------------------------------------------------------------------------------------------------

    //displays all the delivered orders
    @RequestMapping(value = "/supplier/alldeliveredorders/{status}")
    public String viewAllDeliveredOrders(@PathVariable("status") String status, Model model)
    {
        List<Order> supplierDeliveredList = orderRepository.findByStatus(status);

        model.addAttribute("supplierDeliveredList",supplierDeliveredList);

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        model.addAttribute("useremail",userDetails);

        //redirecting to ViewPharmacyInventoryUser html page
        return "ViewAllDeliveredSupplier";
    }

//    -------------------------------------------------------------------------------------------------

    //Displays the add drug page
    @GetMapping(value = "/supplier/adddrugpage")
    public String addDrugPage(Model model)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        model.addAttribute("useremail",userDetails);

        return "AddDrugPageSupplier";
    }

//    -------------------------------------------------------------------------------------------------

    //This function is carried out by supplier, he/she will add the drug to their wholesale market which later will  be used to purchase drugs by pharmacist
    @PostMapping(value = "/supplier/adddrugtomarket")
    public String addDrug(@Valid BuyDrugs buyDrugs, @RequestParam("name")final String name,
                          @RequestParam("description")final String desc,
                          @RequestParam("price") final int price,
                          @RequestParam("unit")final String unit) {
        try {

            if(buyDrugs == null) {

                return "redirect:/supplier/adddrugpage?unsuccess";

            }

            buyDrugs.setName(name);
            buyDrugs.setDescription(desc);
            buyDrugs.setPrice(price);
            buyDrugs.setUnit(unit);


            boolean status = drugService.saveDrug(buyDrugs);

            //displays success msg if status = true (saved the drug)
            if(status)
            {
                return "redirect:/supplier/adddrugpage?success";
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return "redirect:/supplier/adddrugpage?unsuccess";
    }
}
