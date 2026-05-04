package xyz.ytora.core.sys.dict.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.sys.dict.model.data.SysDictItemData;
import xyz.ytora.core.sys.dict.model.entity.SysDictItem;
import xyz.ytora.core.sys.dict.repo.SysDictItemRepo;
import xyz.ytora.sqlux.sql.stage.select.AbsSelect;

import java.util.List;

import static xyz.ytora.sqlux.core.SQL.select;

/**
 * 字典项模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Service
@AllArgsConstructor
public class SysDictItemLogic extends BaseLogic<SysDictItem, SysDictItemRepo> {

    /**
     * 根据字典code，查询所属的全部字典项
     * @param dictCode 字典code
     * @return 字典项列表
     */
    public List<SysDictItemData> listDictItem(String dictCode) {
//        List<SysDictItem> list = select()
//                .from(SysDictItem.class)
//                .where(w -> w.eq(SysDictItem::getDictCode, dictCode))
//                .submit(SysDictItem.class);

        AbsSelect select = installSelect();
        List<SysDictItem> list = select.submit(SysDictItem.class);

        return list.stream().map(SysDictItem::toData).toList();
    }
}
