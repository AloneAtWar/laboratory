package org.jeecg.modules.laboratory.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.aloneatwar.laboratory.entity.Laboratory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 实验室表
 * @Author: jeecg-boot
 * @Date:   2022-12-23
 * @Version: V1.0
 */
@Mapper
public interface LaboratoryMapper extends BaseMapper<Laboratory> {

}
