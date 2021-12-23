package com.cb007753.pharmacybackend.Repository;

import com.cb007753.pharmacybackend.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
}
