package com.xzx.dao;

import com.xzx.model.Student;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-09-19
 */

public interface StudentMapper extends BaseMapper<Student> {
	List<Student> getStuList();
}
