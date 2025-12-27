package xyz.ytora.core.sys.file.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.core.sys.file.model.entity.SysFile;
import xyz.ytora.core.sys.file.model.req.SysFileReq;
import xyz.ytora.core.sys.file.model.resp.SysFileResp;

/**
 * 文件模块类型转换mapper
 */
@Mapper
public interface SysFileMapper {
    SysFileMapper mapper = Mappers.getMapper(SysFileMapper.class);

    /**
     * REQ 转为 ENTITY
     */
    SysFile toEntity(SysFileReq SysFileReq);

    /**
     * ENTITY 转 RESP
     */
    SysFileResp toResp(SysFile SysFile);
}
