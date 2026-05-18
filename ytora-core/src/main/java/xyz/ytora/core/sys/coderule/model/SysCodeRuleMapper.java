package xyz.ytora.core.sys.coderule.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.sys.coderule.model.data.SysCodeRuleData;
import xyz.ytora.core.sys.coderule.model.entity.SysCodeRule;
import xyz.ytora.core.sys.coderule.model.excel.SysCodeRuleExcel;
import xyz.ytora.core.sys.coderule.model.param.SysCodeRuleParam;

/**
 * 系统编码规则模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysCodeRuleMapper {

    SysCodeRuleMapper mapper = Mappers.getMapper(SysCodeRuleMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysCodeRule toEntity(SysCodeRuleParam param);

    /**
     * ENTITY 转 DATA
     */
    SysCodeRuleData toData(SysCodeRule entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysCodeRule toEntity(SysCodeRuleExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysCodeRuleExcel toExcel(SysCodeRuleData data);

}
