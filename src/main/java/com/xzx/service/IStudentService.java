package com.xzx.service;

import com.xzx.model.Student;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-19
 */
public interface IStudentService extends IService<Student> {
	List<Student> getStuList();
}
