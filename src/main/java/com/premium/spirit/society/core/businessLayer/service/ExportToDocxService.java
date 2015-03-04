package com.premium.spirit.society.core.businessLayer.service;

import com.premium.spirit.society.core.businessLayer.BO.form.OrderFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.UserFormBO;
import com.premium.spirit.society.core.dataLayer.entity.UserEntity;

import java.util.Locale;

public interface ExportToDocxService {

    public String export(OrderFormBO orderFormBO, UserFormBO user, Locale locale);
}
