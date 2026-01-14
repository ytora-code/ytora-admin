package xyz.ytora.core.sys.dict.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.ytora.base.mvc.BaseLogic;
import xyz.ytora.core.sys.dict.model.entity.SysDict;
import xyz.ytora.core.sys.dict.model.req.SysDictReq;
import xyz.ytora.core.sys.dict.repo.SysDictRepo;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.func.support.Raw;
import xyz.ytora.ytool.str.Strs;

@Service
@RequiredArgsConstructor
public class SysDictLogic extends BaseLogic<SysDict, SysDictRepo> {

    private final SQLHelper sqlHelper;

    /**
     * 新增或编辑
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertOrUpdate(SysDictReq data) {
        if (data.getId() == null) {
            if (Strs.isEmpty(data.getPid())) {
                data.setPid("0");
            }
            repository.insert(data.toEntity());
        } else {
            // 如果修改的是字典，则判断该字典的字典Code有没有被修改，如果Code修改了，则也要将所有字典项的Code同步更改
            if (data.getType() != null && data.getType() == 1) {
                SysDict dict = repository.one(w -> w.eq(Raw.of("id"), data.getId()));
                if (dict != null && !dict.getDictCode().equals(data.getDictCode())) {
                    // 进入这，说明字典Code变了，修改所有字典的Code
                    sqlHelper.update(SysDict.class).set(SysDict::getDictCode, data.getDictCode()).where(w -> w.eq(SysDict::getDictCode, dict.getId()));
                }
            }

            data.setPid(null);
            data.setType(null);
            repository.update(data.toEntity(), w -> w.eq(Raw.of("id"), data.getId()));
        }
    }
}
