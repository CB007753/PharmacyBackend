package com.cb007753.pharmacybackend.Controller;

import com.cb007753.pharmacybackend.Model.Order;
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

//    ------------------------------------------------------------------------------------------------
    //Gets the order details and adding it to a model to access it in ConfirmUpdateOrderUser page
    @GetMapping(value = "/user/update/{id}")
    public String UpdateStatus(@PathVariable("id") Long id,Model model)
    {
        Optional<Order> orderdetails = orderService.getOrderByID(id);

        model.addAttribute("id",orderdetails.get().getOrder_id());
        model.addAttribute("email",orderdetails.get().getEmail());
        model.addAttribute("drugname",orderdetails.get().getDrugname());
        model.addAttribute("price",orderdetails.get().getPrice());
        model.addAttribute("quantity",orderdetails.get().getQnty());
        model.addAttribute("unit",orderdetails.get().getUnit());
        model.addAttribute("status",orderdetails.get().getStatus());
        model.addAttribute("total",orderdetails.get().getTotal());
        model.addAttribute("date",orderdetails.get().getDate());

        return "ConfirmUpdateOrderUser";
    }


//    -------------------------------------------------------------------------------------------------

    //Updates the order status from "On The Way" to "Delivered"
    @PostMapping(value = "/user/updateorder")
    public String UpdateOrder(@Valid Order order,
                               @RequestParam("order_id")Long id, @RequestParam("email")String email, @RequestParam("date") String date,
                               @RequestParam("drugname") String drugname, @RequestParam("price") int price,
                               @RequestParam("qnty") int quantity, @RequestParam("unit")String unit,
                               @RequestParam("updatestatus")String status, @RequestParam("total")int total
                               , Model model) {
        try {
            order.setOrder_id(id);
            order.setDrugname(drugname);
            order.setEmail(email);
            order.setStatus(status);
            order.setPrice(price);
            order.setQnty(quantity);
            order.setTotal(total);
            order.setUnit(unit);
            order.setDate(date);

            //updating the order details with status as "Delivered"
            orderService.saveOrder(order);

            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails=(UserDetails)authentication.getPrincipal();
            model.addAttribute("useremail",userDetails);

        } catch (Exception e) {

            e.printStackTrace();

        }
        return "OnTheWayOrdersUser";
    }
}
