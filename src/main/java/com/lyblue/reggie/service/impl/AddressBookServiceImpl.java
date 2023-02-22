package com.lyblue.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyblue.reggie.entity.AddressBook;
import com.lyblue.reggie.mapper.AddressBookMapper;
import com.lyblue.reggie.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}