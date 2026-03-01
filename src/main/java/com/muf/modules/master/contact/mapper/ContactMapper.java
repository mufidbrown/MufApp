package com.muf.modules.master.contact.mapper;


import com.muf.modules.master.contact.Contact;
import com.muf.modules.master.contact.ContactResponse;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {


    public ContactResponse toContactResponse(Contact contact) {
        ContactResponse response = new ContactResponse();

        response.setId(contact.getId());
        response.setFullName(contact.getFullName());
        response.setPhone(contact.getPhone());
        response.setEmail(contact.getEmail());
        response.setPosition(contact.getPosition());
        response.setCreatedAt(contact.getCreatedAt());
        response.setUpdatedAt(contact.getUpdatedAt());

        // 🔥 ambil dari relasi object
        if (contact.getCustomerAccount() != null) {
            response.setCustomerAccountId(contact.getCustomerAccount().getId());
            response.setCustomerAccountName(contact.getCustomerAccount().getCompanyName());
        }

        return response;
    }
}
