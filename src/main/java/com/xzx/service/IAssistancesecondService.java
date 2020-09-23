package com.xzx.service;

import com.baomidou.mybatisplus.service.IService;
import com.xzx.model.Assistancesecond;
import com.xzx.model.Zcdw;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
public interface IAssistancesecondService extends IService<Assistancesecond> {

    List<Assistancesecond> getSecondAssistanceList(int assistanceId);


}
