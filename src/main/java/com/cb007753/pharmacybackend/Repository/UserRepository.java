package com.cb007753.pharmacybackend.Repository;


import com.cb007753.pharmacybackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

        List<User> findDetailsByEmail(String email);

        User findByEmail(String email);

        //This runs a nativeSql query bcz the usual update function of JPARepo is more similar to save function which removes the user role on update
        @Modifying
        @Query(value = "UPDATE user u set fullname =?1, mobile=?2, email=?3 where u.id = ?4",
                nativeQuery = true)
        void updateUser(@Param("fullname") String fullname,@Param("mobile") String mobile,@Param("email") String email, @Param("id") Long id);

}
