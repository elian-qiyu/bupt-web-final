package cn.edu.bupt.springmvc.demo.service.impl;

import cn.edu.bupt.springmvc.demo.entities.Bbs;
import cn.edu.bupt.springmvc.demo.mapper.BbsMapper;
import cn.edu.bupt.springmvc.demo.service.IBbsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BbsServiceImpl extends ServiceImpl<BbsMapper, Bbs> implements IBbsService {
}
