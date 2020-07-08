package com.boss.bes.permission.controller;

import com.boss.bes.permission.pojo.dto.position.PositionDTO;
import com.boss.bes.permission.pojo.entity.Position;
import com.boss.bes.permission.pojo.vo.position.PositionListVO;
import com.boss.bes.permission.service.PositionService;
import com.boss.xtrain.common.core.exception.BusinessException;
import com.boss.xtrain.common.core.exception.ServiceException;
import com.boss.xtrain.common.core.exception.error.BusinessError;
import com.boss.xtrain.common.core.http.CommonRequest;
import com.boss.xtrain.common.core.http.CommonResponse;
import com.boss.xtrain.common.core.http.ResponseHeader;
import com.boss.xtrain.common.log.annotation.ApiLog;
import com.boss.xtrain.common.log.annotation.GlobalExceptionLog;
import com.boss.xtrain.common.util.IdWorker;
import com.boss.xtrain.common.util.PojoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @Author  :yushiqian
 * @Date    :16:59 2020/07/07
 * @Description :controller类设计
 * @Version: 1.0
 */

@RestController
@RequestMapping("/position")
@Slf4j
public class PositionController{

    @Autowired
    private PositionService positionService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping
    @ResponseBody
    @CrossOrigin
    @ApiLog
    public CommonResponse<Integer> addPosition(@RequestBody@Valid CommonRequest<PositionListVO> request){
        Map<String,PositionListVO> body = request.getBody();
        PositionListVO addVo = body.get("positionVo");
        PositionDTO dto = new PositionDTO();
        PojoUtils.copyProperties(addVo,dto);
        IdWorker idWorker = new IdWorker();
        dto.setId(idWorker.nextId());
        int affectRow;
        try {
            affectRow = positionService.create(dto);
        }catch (ServiceException e){
            throw  new BusinessException(BusinessError.SYSTEM_MANAGER_POSITION_INSERT_ERROR);
        }
        CommonResponse<Integer> response = new CommonResponse<>();
        // 设置head
        ResponseHeader head = new ResponseHeader();
        // 成功情况下默认为0
        head.setCode("0");
        head.setVersion("v1.0.0");
        response.setHeader(head);
        // 设置body
        response.setData(affectRow);
        return response;

    }

    @GetMapping("/all")
    public void selectAll(){
        List<Position> positions = positionService.selectAll();
        for(int i=0;i<positions.size();i++) {
            log.info(positions.get(i).getId() + " " + positions.get(i).getName());
        }
    }


}
