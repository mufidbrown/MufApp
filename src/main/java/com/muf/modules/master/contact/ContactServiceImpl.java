package com.muf.modules.master.contact;

import com.muf.common.exception.customcustomercontact.ContactNotFoundException;
import com.muf.common.exception.customcustomercontact.CustomerAccountNotFoundException;
import com.muf.common.exception.customcustomercontact.DuplicateContactEmailException;
import com.muf.modules.master.contact.mapper.ContactMapper;
import com.muf.modules.master.customer.CustomerAccount;
import com.muf.modules.master.customer.CustomerAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final CustomerAccountRepository customerAccountRepository;
    private final ContactMapper contactMapper;

    @Override
    @Transactional
    public ContactResponse createContact(CreateContactRequest request) {

        // validate customer account exists
//        customerAccountRepository.findById(request.getCustomerAccountId())
//                .orElseThrow(() -> new CustomerAccountNotFoundException(request.getCustomerAccountId()));

        // ambil object account
        CustomerAccount account = customerAccountRepository.findById(request.getCustomerAccountId())
                .orElseThrow(() -> new CustomerAccountNotFoundException(request.getCustomerAccountId()));

        // check for duplicate email (case-insensitive)
        if (request.getEmail() != null && contactRepository.existByEmailIgnoreCase(request.getEmail())) {
            throw new DuplicateContactEmailException(request.getEmail());
        }

        Contact contact = new Contact();
        contact.setCustomerAccount(account);
        contact.setFullName(request.getFullName());
        contact.setPhone(request.getPhone());
        contact.setEmail(request.getEmail());
        contact.setPosition(request.getPosition());
        contact.setCustomerAccountId(request.getCustomerAccountId());
        contact.setCreatedAt(new Date());

        // 🔥 WAJIB SET RELASI
        contact.setCustomerAccount(account);

        Contact saved = contactRepository.save(contact);

        // reload with customer account
        Contact withAccount = contactRepository.findByIdWithCustomerAccount(saved.getId())
                .orElseThrow(() -> new ContactNotFoundException(saved.getId()));

        return contactMapper.toContactResponse(withAccount);
    }
}
