package com.premium.spirit.society.core.businessLayer.serviceImpl;

import com.premium.spirit.society.core.businessLayer.BO.form.ContactFormBO;
import com.premium.spirit.society.core.businessLayer.service.ContactService;
import com.premium.spirit.society.core.dataLayer.entity.ContactEntity;
import org.springframework.stereotype.Service;

/**
 * The Class ContactServiceImpl.
 */
@Service
public class ContactServiceImpl extends
        GenericServiceImpl<ContactFormBO, ContactEntity> implements
        ContactService {

}
