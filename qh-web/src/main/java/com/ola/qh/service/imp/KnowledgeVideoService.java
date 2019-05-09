package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.BannerDao;
import com.ola.qh.dao.BusinessDao;
import com.ola.qh.dao.KnowledgeVideoDao;
import com.ola.qh.dao.UserDao;
import com.ola.qh.entity.Banner;
import com.ola.qh.entity.Business;
import com.ola.qh.entity.KnowledgeVideo;
import com.ola.qh.entity.User;
import com.ola.qh.service.IKnowledgeVideoService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.Results;
import com.sun.org.apache.xalan.internal.templates.ElemApplyImport;

@Service
public class KnowledgeVideoService implements IKnowledgeVideoService {

	@Autowired
	private KnowledgeVideoDao knowledgeVideoDao;
	@Autowired
	private BusinessDao businessDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BannerDao banderDao;
	@Autowired
	private IUserService userService;

	@Transactional
	@Override
	public Results<List<KnowledgeVideo>> KnowledgeVideoList(int pageNo, int pageSize, String courseTypeSubclassName,
			String userId, String address) {

		Results<List<KnowledgeVideo>> results = new Results<List<KnowledgeVideo>>();
		try {

			List<KnowledgeVideo> list = knowledgeVideoDao.KnowledgeVideoList(pageNo, pageSize, courseTypeSubclassName);
			// 暂时这么写 肯定是可以重构的
			// 根据userId和address查询mobile和logos,循环赋值并返回
			// 1.如果userId不为空，根据userid查询
			String newAddressString = null;
			boolean defaultString = false;
			if (userId != null && !"".equals(userId)) {
				// 传过来的useID其实是token，token转userid
				Results<User> userResult = userService.existUser(userId);
				if ("1".equals(userResult.getStatus())) {
					results.setStatus("1");
					results.setMessage(userResult.getMessage());
					return results;
				}
				userId = userResult.getData().getId();

				// 根据userid查询到加盟商

				String businessId = businessDao.singleBusinessUser(userId);
				// 测试
				if (businessId != null) {
					Business business = businessDao.single(businessId, null);
					for (KnowledgeVideo knowledgeVideo : list) {
						knowledgeVideo.setLogos(business.getLogo());
						knowledgeVideo.setMobile(business.getMobile());
					}
					results.setData(list);
					results.setStatus("0");

					return results;
				}
				if (businessId == null) {
					// 根据userid查询address
					User user = userDao.singleUser(userId, null);
					if (user.getAddress() != null && !"".equals(user.getAddress())) {
						// 有 根据address查加盟商
						newAddressString = user.getAddress();
						Business business = businessDao.single(null, newAddressString);
						if (business != null) {
							for (KnowledgeVideo knowledgeVideo : list) {
								knowledgeVideo.setLogos(business.getLogo());
								knowledgeVideo.setMobile(business.getMobile());
							}
							results.setData(list);
							results.setStatus("0");

							return results;
						}
						if (business == null) {
							newAddressString = null;
						}
					}
				}

				if (newAddressString == null && address != null) {
					// 根据addres查加盟商
					Business busines = businessDao.single(null, address);
					if (busines != null) {
						for (KnowledgeVideo knowledgeVideo : list) {
							knowledgeVideo.setLogos(busines.getLogo());
							knowledgeVideo.setMobile(busines.getMobile());
						}
						results.setData(list);
						results.setStatus("0");

						return results;
					}
					if (busines == null) {
						// 默认
						defaultString = true;
					}
				} else {
					defaultString = true;
				}
			}
			
			if ((userId == null && address == null) || defaultString) {
				// 使用默认
				List<Banner> banner = banderDao.selectBanner("2");
				for (KnowledgeVideo knowledgeVideo : list) {
					knowledgeVideo.setLogos(banner.get(0).getImageurl());
					knowledgeVideo.setMobile(banner.get(0).getOutLinks());
				}
				results.setData(list);
				results.setStatus("0");

				return results;
			}
			results.setData(list);
			results.setStatus("0");

			return results;

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

}
