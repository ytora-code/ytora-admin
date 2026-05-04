package xyz.ytora.base.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * 全局的 mapstruct mapper 配置
 *
 * @author ytora
 * @since 1.0
 */
@MapperConfig(
        uses = {StringTextConvertMapper.class, JsonObjectConvertMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface GlobalMapperConfig {
}
