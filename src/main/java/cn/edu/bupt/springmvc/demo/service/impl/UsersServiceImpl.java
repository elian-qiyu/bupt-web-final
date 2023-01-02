package cn.edu.bupt.springmvc.demo.service.impl;

import cn.edu.bupt.springmvc.demo.entities.Users;
import cn.edu.bupt.springmvc.demo.mapper.UsersMapper;
import cn.edu.bupt.springmvc.demo.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
}
