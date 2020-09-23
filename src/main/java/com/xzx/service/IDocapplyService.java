package com.xzx.service;

import com.xzx.model.Docapply;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-12-20
 */
public interface IDocapplyService extends IService<Docapply> {
	Boolean addDocApply(Docapply docapply);
}
