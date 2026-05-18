package xyz.ytora.core.biz.deploy.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.biz.deploy.model.data.BizDeployData;
import xyz.ytora.core.biz.deploy.model.entity.BizDeploy;
import xyz.ytora.core.biz.deploy.model.excel.BizDeployExcel;
import xyz.ytora.core.biz.deploy.model.param.BizDeployParam;

/**
 * 开发者模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0
 */
@Mapper(config = GlobalMapperConfig.class)
public interface BizDeployMapper {

    BizDeployMapper mapper = Mappers.getMapper(BizDeployMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    BizDeploy toEntity(BizDeployParam param);

    /**
     * ENTITY 转 DATA
     */
    BizDeployData toData(BizDeploy entity);

    /**
     * EXCEL 转 ENTITY
     */
    BizDeploy toEntity(BizDeployExcel excel);

    /**
     * DATA 转 EXCEL
     */
    BizDeployExcel toExcel(BizDeployData data);

}
