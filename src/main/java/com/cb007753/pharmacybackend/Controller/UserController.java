package com.cb007753.pharmacybackend.Controller;

import com.cb007753.pharmacybackend.Model.Order;
import com.cb007753.pharmacybackend.Model.User;
import com.cb007753.pharmacybackend.Repository.OrderRepository;
import com.cb007753.pharmacybackend.Repository.UserRepository;
import com.cb007753.pharmacybackend.Service.OrderService;
import com.cb007753.pharmacybackend.Service.UserService;
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

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


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

        //redirecting to OnTheWayOrdersUser html page
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

        //redirecting to ConfirmUpdateOrderUser html page
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

        //redirecting to OnTheWayOrdersUser html page
        return "OnTheWayOrdersUser";
    }

//    -------------------------------------------------------------------------------------------------

    //displays all the orders in the pharmacy inventory(inventory contains all items which are delivered)
    @RequestMapping(value = "/user/viewallorders/{status}")
    public String viewAllOrders(@PathVariable("status") String status, Model model)
    {
        List<Order> pharmacist_orderlist = orderRepository.findByStatus(status);

        model.addAttribute("pharmacist_orderlist",pharmacist_orderlist);

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        model.addAttribute("useremail",userDetails);

        //redirecting to ViewPharmacyInventoryUser html page
        return "ViewPharmacyInventoryUser";
    }

//    -------------------------------------------------------------------------------------------------

    //displays all details of the logged in user- view profile
    @RequestMapping(value = "/user/viewprofile/{email}")
    public String viewProfile(@PathVariable("email") String email, Model model)
    {
        User view_profile = userRepository.findByEmail(email);

        model.addAttribute("view_profile",view_profile);

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        model.addAttribute("useremail",userDetails);

        //redirecting to ViewProfile html page
        return "ViewProfile";
    }

    //    ------------------------------------------------------------------------------------------------

    //Gets the user details and adding it to a model to access it in EditProfile page
    @GetMapping(value = "/user/editprofile/{id}")
    public String UpdateUserButton(@PathVariable("id") Long id, Model model)
    {
        Optional<User> view_profile = userRepository.findById(id);

        model.addAttribute("id",view_profile.get().getId());
        model.addAttribute("name",view_profile.get().getFullname());
        model.addAttribute("email",view_profile.get().getEmail());
        model.addAttribute("contact",view_profile.get().getMobile());
        model.addAttribute("password",view_profile.get().getPassword());

        //redirecting to EditProfile html page
        return "EditProfile";
    }


}
