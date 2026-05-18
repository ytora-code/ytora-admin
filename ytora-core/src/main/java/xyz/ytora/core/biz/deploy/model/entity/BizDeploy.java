package xyz.ytora.core.biz.deploy.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.biz.deploy.model.BizDeployMapper;
import xyz.ytora.core.biz.deploy.model.data.BizDeployData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 开发者
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "biz_deploy", idType = IdType.SNOWFLAKE, comment = "开发者")
public class BizDeploy extends BaseEntity<BizDeploy> {

    /**
     * 姓名
     */
    @Column(comment = "姓名")
    private String name;

    /**
     * 身份证
     */
    @Column(comment = "身份证")
    private String idCard;

    /**
     * 专业方向
     */
    @Column(comment = "专业方向")
    private String professional;

    /**
     * 状态，待初审，初审不通过，初审通过待复核，复核不通过，复核通过
     */
    @Column(comment = "状态，待初审，初审不通过，初审通过待复核，复核不通过，复核通过")
    private String status;

    @Override
    public BizDeployData toData() {
        BizDeployMapper mapper = BizDeployMapper.mapper;
        return mapper.toData(this);
    }
}
