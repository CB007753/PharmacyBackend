package com.cb007753.pharmacybackend.Service;

import com.cb007753.pharmacybackend.Model.Contact;
import com.cb007753.pharmacybackend.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class ContactServiceImpl implements ContactService{

    @Autowired
    ContactRepository contactRepository;

    @Override
    public boolean AddMessage(Contact contact) throws IOException {
        try
        {
            if(contact!=null)
            {
                contactRepository.save(contact);
                return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return false;}
    @Override
    public void deleteFile(Long id) {
        contactRepository.deleteById(id);
    }}
