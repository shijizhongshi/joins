package com.ola.qh.controller;

import java.util.List;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.DoctorPatient;
import com.ola.qh.entity.Doctors;
import com.ola.qh.service.IDoctorsService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

/**
 * 医生患者的信息
 * 
 * @ClassName: DoctorsController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author guoyuxue
 * @date 2018年12月21日
 *
 */
@RestController
@RequestMapping("/api/doctors")
public class DoctorsController {

	@Autowired
	private IDoctorsService doctorsService;

	/**
	 * 修改和保存用户的信息
	 * <p>
	 * Title: douctorSaveUpdate
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param d
	 * @param valid
	 * @return
	 */
	@RequestMapping(value="/saveupdate",method=RequestMethod.POST)
	public Results<String> douctorSaveUpdate(@RequestBody @Valid Doctors d, BindingResult valid) {
		Results<String> result = new Results<String>();
		if (d.getId() == null || "".equals(d.getId())) {
			if (valid.hasErrors()) {
				result.setStatus("1");
				result.setMessage("医生信息填写不完整");
				return result;
			}
		}
		if (d.getIdcard() != null && !"".equals(d.getIdcard())) {
			Pattern p = Pattern.compile(Patterns.IDCARD_PATTERN);
			if (!p.matcher(d.getIdcard()).matches()) {
				result.setStatus("1");
				result.setMessage("身份证号格式不准确~");
				return result;
			}
		}
		return doctorsService.doctorsSaveUpdate(d);

	}
	
	@RequestMapping("/single")
	public Results<Doctors> singleDoctors(
			@RequestParam(name="id",required=false)String id,
			@RequestParam(name="userId",required=false)String userId,
			@RequestParam(name="islimit",required=false)String islimit,
			@RequestParam(name="page",required=false)int page){
		
		Results<Doctors> result=new Results<Doctors>();
		Doctors d = doctorsService.singleDoctors(id, userId, islimit,page);
		result.setData(d);
		result.setStatus("0");
		return  result;
	}
	/**
	 * 返回医生的集合
	 * <p>Title: listDoctors</p>  
	 * <p>Description: </p>  
	 * @param page
	 * @return
	 */
	@RequestMapping("/list")
	public Results<List<Doctors>> listDoctors(
			@RequestParam(name="page",required=true)int page,
			@RequestParam(name="address",required=true)String address,
			@RequestParam(name="professional",required=true)String professional,
			@RequestParam(name="offices",required=true)String offices,
			@RequestParam(name="name",required=true)String name){
		Results<List<Doctors>> result=new Results<List<Doctors>>();
		int pageSize=Patterns.zupageSize;
		int pageNo=(page-1)*pageSize;
		List<Doctors> list = doctorsService.listDoctors(pageNo, pageSize, address, professional,offices,name);
		result.setData(list);
		result.setStatus("0");
		return result;
	}
	
	@RequestMapping(value="/patient/saveUpdate",method=RequestMethod.POST)
	public Results<String> patientSaveUpdate(@RequestBody @Valid DoctorPatient dp,BindingResult valid){
		
		Results<String> result=new Results<String>();
		if(dp.getId()==null || "".equals(dp.getId())){
			////说明是保存
			if(valid.hasErrors()){
				result.setStatus("0");
				result.setMessage("患者信息填写不完整");
			}
		}
		
		return doctorsService.patientSaveUpdate(dp);
	}
	/**
	 * 用户医学圈的集合(也是我的发布的列表,类别分类的集合)
	 * <p>Title: listPatient</p>  
	 * <p>Description: </p>  
	 * @param userId
	 * @param page
	 * @return
	 */
	@RequestMapping("/listpatient")
	public Results<List<DoctorPatient>> listPatient(
			@RequestParam(name="userId",required=false)String userId,
			@RequestParam(name="category",required=false)String category,
			@RequestParam(name="page",required=true)int page){
		
		Results<List<DoctorPatient>> result=new Results<List<DoctorPatient>>();
		int pageSize=Patterns.zupageSize;
		int pageNo=(page-1)*pageSize;
		List<DoctorPatient> list = doctorsService.listPatient(userId,category, pageNo, pageSize);
		result.setData(list);
		result.setStatus("0");
		return result;
	}
	
	
	
	@RequestMapping("/singlepatient")
	public Results<DoctorPatient> singlePatient(@RequestParam(name="id",required=true)String id){
		Results<DoctorPatient> result=new Results<DoctorPatient>();
		DoctorPatient dp = doctorsService.singlePatient(id);
		result.setData(dp);
		result.setStatus("0");
		return result;
	}
	
	
}
