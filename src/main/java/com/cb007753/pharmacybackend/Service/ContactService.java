package com.cb007753.pharmacybackend.Service;

import com.cb007753.pharmacybackend.Model.Contact;

import java.io.IOException;

public interface ContactService {

    boolean AddMessage(Contact contact) throws IOException;

    //Admin- delete message by id
    void deleteFile(Long id);
}
