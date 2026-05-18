package xyz.ytora.core.biz.deploy.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.biz.deploy.model.BizDeployMapper;
import xyz.ytora.core.biz.deploy.model.entity.BizDeploy;
import xyz.ytora.core.biz.deploy.model.excel.BizDeployExcel;

/**
 * 开发者响应数据
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "开发者响应数据")
public class BizDeployData extends BaseData<BizDeploy> {

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;

    /**
     * 身份证
     */
    @Schema(description = "身份证")
    private String idCard;

    /**
     * 专业方向
     */
    @Schema(description = "专业方向")
    private String professional;

    /**
     * 状态，待初审，初审不通过，初审通过待复核，复核不通过，复核通过
     */
    @Schema(description = "状态，待初审，初审不通过，初审通过待复核，复核不通过，复核通过")
    private String status;

    @Override
    public BizDeployExcel toExcel() {
        BizDeployMapper mapper = BizDeployMapper.mapper;
        return mapper.toExcel(this);
    }
}
