package cn.edu.bupt.springmvc.demo.service.impl;

import cn.edu.bupt.springmvc.demo.entities.Address;
import cn.edu.bupt.springmvc.demo.mapper.AddressMapper;
import cn.edu.bupt.springmvc.demo.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {
}
