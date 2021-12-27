package com.cb007753.pharmacybackend.Controller;

import com.cb007753.pharmacybackend.Model.Order;
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
public class UserController {

    @Autowired
    OrderService orderService;


    //Displays all the delivered orders of the logged in pharmacist
    @RequestMapping(value = "/user/viewdeliveredorders/{email}/{status}")
    public String viewdeliveredorderlist(@PathVariable("email")String email, @PathVariable("status") String status, Model model)
    {
        List<Order> pharmacist_orderlist = orderService.getOrderByEmailAndStatus(email,status);

        model.addAttribute("pharmacist_orderlist",pharmacist_orderlist);


        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        model.addAttribute("useremail",userDetails);

        //redirecting to DeliveredOrdersUser html page
        return "DeliveredOrdersUser";
    }

//    -------------------------------------------------------------------------------------------------

    //Displays all the On The Way orders of the logged in pharmacist
    @RequestMapping(value = "/user/viewonthewayorders/{email}/{status}")
    public String viewonthewayorderlist(@PathVariable ("email")String email, @PathVariable("status") String status, Model model)
    {
        List<Order> pharmacist_orderlist=orderService.getOrderByEmailAndStatus(email,status);

        model.addAttribute("pharmacist_orderlist",pharmacist_orderlist);

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        model.addAttribute("useremail",userDetails);

        //redirecting to DeliveredOrdersUser html page
        return "OnTheWayOrdersUser";
    }
}
