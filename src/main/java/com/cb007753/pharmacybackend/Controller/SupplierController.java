package com.cb007753.pharmacybackend.Controller;

import com.cb007753.pharmacybackend.Model.*;
import com.cb007753.pharmacybackend.Repository.BuyDrugRepository;
import com.cb007753.pharmacybackend.Repository.OrderRepository;
import com.cb007753.pharmacybackend.Service.DrugService;
import com.cb007753.pharmacybackend.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

        //redirecting to ViewAllBuyDrugsSupplier html page
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

        //redirecting to ViewAllOTWSupplier html page
        return "ViewAllOTWSupplier";
    }

//    -------------------------------------------------------------------------------------------------

    //This is a confirmation page to delete drug
    @GetMapping(value = "/supplier/deletedrugpage/{id}")
    public String DeleteDrugButton(@PathVariable("id") Long id, Model model)
    {
        Optional<BuyDrugs> delete_drug = buyDrugRepository.findById(id);

        model.addAttribute("id",delete_drug.get().getId());
        model.addAttribute("name",delete_drug.get().getName());
        model.addAttribute("descripion",delete_drug.get().getDescription());
        model.addAttribute("unit",delete_drug.get().getUnit());
        model.addAttribute("price",delete_drug.get().getPrice());

        //redirecting to DeleteDrugSupplier html page
        return "DeleteDrugSupplier";
    }

//    -------------------------------------------------------------------------------------------------

    //this deletes the selected drug from buy_drug table in database
    @GetMapping(value = "/supplier/deletedrug/{id}")
    public String deleteDrug(@RequestParam("drug_id") Long id)
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

        //redirecting to ViewAllDeliveredSupplier html page
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

    //    ------------------------------------------------------------------------------------------------

    //Gets the drug details and adding it to a model to access it in edit drug page
    @GetMapping(value = "/supplier/editdrug/{id}")
    public String UpdateDrugButton(@PathVariable("id") Long id, Model model)
    {
        Optional<BuyDrugs> view_buydrugs = buyDrugRepository.findById(id);

        model.addAttribute("id",view_buydrugs.get().getId());
        model.addAttribute("name",view_buydrugs.get().getName());
        model.addAttribute("description",view_buydrugs.get().getDescription());
        model.addAttribute("price",view_buydrugs.get().getPrice());
        model.addAttribute("unit",view_buydrugs.get().getUnit());

        //redirecting to EditDrugSupplier html page
        return "EditDrugSupplier";
    }

    //    -------------------------------------------------------------------------------------------------

    //Updates the drug details-supplier
    @PostMapping(value = "/supplier/updatedrug")
    public String UpdateDrugDetails(@Valid BuyDrugs buyDrugs,
                                    @RequestParam("drug_id")Long id, @RequestParam("description")String description,
                                    @RequestParam("name") String drugname,
                                    @RequestParam("price") int price,
                                    @RequestParam("unit")String unit, Model model) {
        try {
            buyDrugs.setId(id);
            buyDrugs.setName(drugname);
            buyDrugs.setDescription(description);
            buyDrugs.setPrice(price);
            buyDrugs.setUnit(unit);


            //updating the drug details with entered details
            drugService.saveDrug(buyDrugs);

            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails=(UserDetails)authentication.getPrincipal();
            model.addAttribute("useremail",userDetails);

        } catch (Exception e) {

            e.printStackTrace();

        }

        //redirecting to OnTheWayOrdersUser html page
        return "redirect:/supplier/allbuydrugs?editsuccess";
    }

}
