package com.aloneatwar.laboratory.mapper;

import com.aloneatwar.laboratory.vo.response.BaseLaboratory;
import org.apache.ibatis.annotations.Mapper;
import com.aloneatwar.laboratory.entity.Laboratory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 实验室表
 * @Author: jeecg-boot
 * @Date:   2022-12-23
 * @Version: V1.0
 */
@Mapper
public interface LaboratoryMapper extends BaseMapper<Laboratory> {
    List<Laboratory> getLabsByStuNumber(@Param("number") String number);
    BaseLaboratory getLabDetail(@Param("id") String id);
}
