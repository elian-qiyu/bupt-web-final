package cn.edu.bupt.springmvc.demo.service.impl;

import cn.edu.bupt.springmvc.demo.entities.Authorities;
import cn.edu.bupt.springmvc.demo.mapper.AuthoritiesMapper;
import cn.edu.bupt.springmvc.demo.service.IAuthoritiesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesServiceImpl extends ServiceImpl<AuthoritiesMapper, Authorities>  implements IAuthoritiesService {
}
