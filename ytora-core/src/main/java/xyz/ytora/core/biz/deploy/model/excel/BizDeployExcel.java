package xyz.ytora.core.biz.deploy.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.biz.deploy.model.BizDeployMapper;
import xyz.ytora.core.biz.deploy.model.entity.BizDeploy;
import xyz.ytora.toolkit.document.excel.Excel;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("开发者列表")
public class BizDeployExcel extends BaseExcel<BizDeploy> {

    /**
     * 姓名
     */
    @Excel("姓名")
    private String name;

    /**
     * 身份证
     */
    @Excel("身份证")
    private String idCard;

    /**
     * 专业方向
     */
    @Excel("专业方向")
    private String professional;

    /**
     * 状态，待初审，初审不通过，初审通过待复核，复核不通过，复核通过
     */
    @Excel("状态，待初审，初审不通过，初审通过待复核，复核不通过，复核通过")
    private String status;

    @Override
    public BizDeploy toEntity() {
        BizDeployMapper mapper = BizDeployMapper.mapper;
        return mapper.toEntity(this);
    }
}
