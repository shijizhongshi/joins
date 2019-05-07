package com.ola.qh.service.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.QuestionBankUserFinishDao;
import com.ola.qh.dao.QuestionSubcategoryDao;
import com.ola.qh.dao.UserFinishDaysDao;
import com.ola.qh.entity.QuestionBankUserFinish;
import com.ola.qh.entity.UserFinishDays;
import com.ola.qh.service.IQuestionBankUserFinishService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
import com.ola.qh.vo.UserFinishDomain;

@Service
public class QuestionBankUserFinishService implements IQuestionBankUserFinishService {

	@Autowired
	private QuestionBankUserFinishDao questionBankUserFinishDao;
	@Autowired
	private QuestionSubcategoryDao questionSubcategoryDao;
	@Autowired
	private UserFinishDaysDao userFinishDaysDao;

	@Transactional
	@Override
	public Results<String> addupdateUserFinish(UserFinishDomain userFinishDomain) {

		Results<String> results = new Results<String>();

		try {
			if (userFinishDomain.getExamBeanList().get(0).getTypes() == 1) {
				String courseTypeSubclassName = questionSubcategoryDao
						.showSubName(userFinishDomain.getExamBeanList().get(0).getSubId());
				UserFinishDays existDays = userFinishDaysDao.UserFinishDaysSingle(userFinishDomain.getUserId(),
						courseTypeSubclassName);
				if (existDays == null) {
					int userfinsih = 0;
					int trueuserfinish = 0;
					UserFinishDays userFinishDays = new UserFinishDays();
					userFinishDays.setAddtime(new Date());
					userFinishDays.setId(KeyGen.uuid());

					List<QuestionBankUserFinish> userFinishlist = userFinishDomain.getExamBeanList();

					for (QuestionBankUserFinish userFinish : userFinishlist) {

						
						if (userFinish.getStatus() != 2) {

							userFinish.setUserId(userFinishDomain.getUserId());
							int exist = questionBankUserFinishDao.existUserFinish(userFinishDomain.getUserId(),
									userFinish.getBankId());

							if (exist > 0) {

								userFinish.setUpdatetime(new Date());
								questionBankUserFinishDao.updateUserFinish(userFinish);
								if (userFinish.getStatus() == 0) {
									trueuserfinish = trueuserfinish + 1;
								}
							} else {
								userFinish.setId(KeyGen.uuid());
								userFinish.setAddtime(new Date());
								questionBankUserFinishDao.addUserFinish(userFinish);
								userfinsih = userfinsih + 1;
								if (userFinish.getStatus() == 0) {
									trueuserfinish = trueuserfinish + 1;
								}

							}
							

						}
					}
					userFinishDays.setDays(1);
					userFinishDays.setCourseTypeSubclassName(courseTypeSubclassName);
					userFinishDays.setUserFinsihCount(userfinsih);
					userFinishDays.setUserId(userFinishDomain.getUserId());
					userFinishDays.setTrueUserFinsihCount(trueuserfinish);
					userFinishDaysDao.insertUserFinishDays(userFinishDays);
				} else {
					int userfinsih = 0;
					int trueuserfinish = 0;
					UserFinishDays userFinishDays = new UserFinishDays();
					userFinishDays.setUpdatetime(new Date());
					userFinishDays.setId(KeyGen.uuid());

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date time = questionBankUserFinishDao.maxAddtime(userFinishDomain.getUserId(),
							userFinishDomain.getExamBeanList().get(0).getSubId());
					String maxTime = sdf.format(time);
					Date maxAddtime = sdf.parse(maxTime);
					String atTime = sdf.format(new Date());
					Date date = sdf.parse(atTime);
					long days = (date.getTime() - maxAddtime.getTime()) / (1000 * 3600 * 24);
					if (days == 1) {
						userFinishDays.setDays(existDays.getDays() + 1);
					} else if (days != 0) {
						userFinishDays.setDays(1);
					}

					List<QuestionBankUserFinish> userFinishlist = userFinishDomain.getExamBeanList();

					for (QuestionBankUserFinish userFinish : userFinishlist) {

						if (userFinish.getStatus() != 2) {
							
							userFinish.setUserId(userFinishDomain.getUserId());
							int exist = questionBankUserFinishDao.existUserFinish(userFinishDomain.getUserId(),
									userFinish.getBankId());

							if (exist > 0) {

								userFinish.setUpdatetime(new Date());
								questionBankUserFinishDao.updateUserFinish(userFinish);
								if (userFinish.getStatus() == 0) {
									trueuserfinish = trueuserfinish + 1;
								}
							} else {
								userFinish.setId(KeyGen.uuid());
								userFinish.setAddtime(new Date());
								questionBankUserFinishDao.addUserFinish(userFinish);
								userfinsih = userfinsih + 1;
								if (userFinish.getStatus() == 0) {
									trueuserfinish = trueuserfinish + 1;
								}

							}
						}
					}

					userFinishDays.setCourseTypeSubclassName(courseTypeSubclassName);
					userFinishDays.setUserFinsihCount(existDays.getUserFinsihCount() + userfinsih);
					userFinishDays.setUserId(userFinishDomain.getUserId());
					userFinishDays.setTrueUserFinsihCount(existDays.getTrueUserFinsihCount() + trueuserfinish);
					userFinishDaysDao.UserFinishDaysUpdate(userFinishDays);
				}

			} else {
				List<QuestionBankUserFinish> userFinishlist = userFinishDomain.getExamBeanList();

				for (QuestionBankUserFinish userFinish : userFinishlist) {

					if (userFinish.getStatus() != 2) {

						userFinish.setUserId(userFinishDomain.getUserId());

						int exist = questionBankUserFinishDao.existUserFinish(userFinishDomain.getUserId(),
								userFinish.getBankId());

						if (exist > 0) {

							userFinish.setUpdatetime(new Date());
							questionBankUserFinishDao.updateUserFinish(userFinish);

						} else {
							userFinish.setId(KeyGen.uuid());
							userFinish.setAddtime(new Date());
							questionBankUserFinishDao.addUserFinish(userFinish);

						}
					}
				}
			}
			results.setStatus("0");
			return results;
		} catch (Exception e) {

			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}

	}

}
