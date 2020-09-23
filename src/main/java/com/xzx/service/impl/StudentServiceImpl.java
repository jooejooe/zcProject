package com.xzx.service.impl;

import com.xzx.model.Student;
import com.xzx.dao.StudentMapper;
import com.xzx.service.IStudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-09-19
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
	@Autowired
	StudentMapper studentMapper;
	
	public List<Student> getStuList()
	{
		return studentMapper.getStuList();
	}
}
