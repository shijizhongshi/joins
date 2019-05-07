package com.ola.qh.service.imp;

import static org.mockito.Mockito.reset;

import java.io.IOException;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.ola.qh.dao.DoctorReplyDao;
import com.ola.qh.dao.DoctorsDao;
import com.ola.qh.dao.ShopDao;
import com.ola.qh.dao.UserDao;
import com.ola.qh.dao.UserVideoDao;
import com.ola.qh.entity.Doctors;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.User;
import com.ola.qh.entity.UserLikes;
import com.ola.qh.entity.UserVideo;
import com.ola.qh.entity.UserVideoComment;
import com.ola.qh.service.IUserService;
import com.ola.qh.service.IUserVideoService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@Service
public class UserVideoService implements IUserVideoService {

	@Autowired
	private UserVideoDao userVideoDao;
	@Autowired
	private IUserService userService;
	@Autowired
	private DoctorsDao doctorDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private DoctorReplyDao doctorReplyDao;

	@Transactional
	@Override
	public Results<String> save(UserVideo uv) throws DigestException {
		// TODO Auto-generated method stub
		Results<String> result = new Results<String>();
		Results<User> userResult = userService.existUser(uv.getUserId());
		if ("1".equals(userResult.getStatus())) {
			result.setStatus("1");
			result.setMessage(userResult.getMessage());
			return result;
		}
		uv.setUserId(userResult.getData().getId());
		User user = userService.sinleUser(uv.getUserId(), null);
		if (user.getIsdoctor() != 1 && user.getUserrole() == 0) {
			///// 说明既不是医师护也不是店铺没有权限发送小视频
			result.setStatus("1");
			result.setMessage("您没有上传小视频的权限");
			return result;

		}
		if (user.getIsdoctor() == 1) {

			Doctors d = doctorDao.singleDoctors(null, user.getId(), "1");
			if (d != null) {
				uv.setHeadImgUrl(d.getHeadImg());
				uv.setNickname(d.getName());
				uv.setProfessional(d.getProfessional());
				uv.setDoctorId(d.getId());////// 这个是医生的id
			}

		} else {
			Shop s = new Shop();
			if (user.getUserrole() == 1) {
				//// 服务店铺
				s = shopDao.singleShop(uv.getUserId(), null, 1, null);

			} else if (user.getUserrole() == 2) {
				//// 商城店铺
				s = shopDao.singleShop(uv.getUserId(), null, 2, null);

			} else {
				////// 两种店铺都有(取商城店铺的名称)
				s = shopDao.singleShop(uv.getUserId(), null, 2, null);

			}
			uv.setShopId(s.getId());
			uv.setShopname(s.getShopName());
		}

		uv.setId(KeyGen.uuid());
		uv.setAddtime(new Date());
		String firstImage = getVideo(uv.getVideoId());
		uv.setFirstImage(firstImage);
		userVideoDao.insert(uv);
		result.setStatus("0");////// 保存用户上传的视频成功
		return result;
	}

	public static String getVideo(String vid) throws DigestException {

		String firstImage = null;
		// 需要视频的长度 单位是s
		// int second=0;
		// 指定返回值的类型
		String format = "json";
		// 生成毫秒级的时间戳
		long ptime = new Date().getTime();
		// 将时间戳转为String类型
		String time = String.valueOf(ptime);
		// 拼接字符串
		/// 签名规则：将非空的请求参数按照参数名字典顺序排列，连接参数名与参数值,并在尾部加上secretkey，生成40位大写SHA1值，作为sign。
		// 以下是示例过程
		// 连接参数名与参数值,并在尾部加上secretkey（从点播后台获取，假设secretkey的值为tIQp4ATe9Z），如下：
		format = "format=json&ptime=" + ptime + "&vid=" + vid + Patterns.plovysecretkey;
		String sha1 = DigestUtils.sha1Hex(format);
		// 获取SHA1 加密
		String s = SHA1(format);
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 创建httppost
		HttpPost httppost = new HttpPost("http://api.polyv.net/v2/video/" + Patterns.plovyuserId + "/get-video-msg");
		// 创建参数队列
		List formparams = new ArrayList();
		formparams.add(new BasicNameValuePair("vid", vid));
		formparams.add(new BasicNameValuePair("format", "json"));
		formparams.add(new BasicNameValuePair("ptime", time));
		formparams.add(new BasicNameValuePair("sign", s));
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					// 处理返回值
					String a = EntityUtils.toString(entity, "UTF-8");
					JSONObject jsonObject = JSONObject.parseObject(a);
					Map<String, Object> map = (Map<String, Object>) jsonObject;
					List sa = (List) map.get("data");
					Map<String, Object> data = (Map<String, Object>) sa.get(0);
					// 获取视频时长
					firstImage = (String) data.get("first_image");
					;
					// String time1= (String) data.get("duration");
					// 处理视频时长
					// 转化为秒{"df":2,"seed":1,"first_image":"http://img.videocc.net/uimage/b/b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_0.jpg","playerwidth":"600","tsfilesize3":"0","tsfilesize2":"1106016","tsfilesize1":"454416","filesize":[407073,1009408],"title":"标题","vid":"b826fec754af5fb843b22d49d0ad07ec_b","mp4_1":"http://mpv.videocc.net/b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_1.mp4","duration":"00:00:06","times":"1","mp4_2":"http://mpv.videocc.net/b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_2.mp4","md5checksum":"daf76470cb8701144a7fa3a719b07967","images_b":["b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_0_b.jpg","b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_1_b.jpg","b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_2_b.jpg","b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_3_b.jpg","b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_4_b.jpg","b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_5_b.jpg"],"context":"描述","cataid":"1","tag":"标签","ptime":"2019-01-17
					// 12:08:30","source_filesize":992904,"sourcefile":"","default_video":"http://plvod01.videocc.net/b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_2.plv","images":["b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_0.jpg","b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_1.jpg","b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_2.jpg","b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_3.jpg","b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_4.jpg","b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_5.jpg"],"previewVid":"sp523ncm487ln8np576p22k79k0lk04cm_p","hls":["http://hls.videocc.net/b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_1.m3u8","http://hls.videocc.net/b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_2.m3u8"],"mp4":"http://mpv.videocc.net/b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_1.mp4","flv1":"http://plvod01.videocc.net/b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_1.plv","flv2":"http://plvod01.videocc.net/b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_2.plv","imageUrls":["http://img.videocc.net/uimage/b/b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_0.jpg","http://img.videocc.net/uimage/b/b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_1.jpg","http://img.videocc.net/uimage/b/b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_2.jpg","http://img.videocc.net/uimage/b/b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_3.jpg","http://img.videocc.net/uimage/b/b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_4.jpg","http://img.videocc.net/uimage/b/b826fec754/c/b826fec754af5fb843b22d49d0ad07ec_5.jpg"],"original_definition":"960x544","playerheight":"339","swf_link":"http://player.polyv.net/videos/b826fec754af5fb843b22d49d0ad07ec_b.swf","status":"61"}
					/*
					 * String[] strs=time1.split(":"); try { int h = Integer.parseInt(strs[0]); int
					 * m = Integer.parseInt(strs[1]); second = Integer.parseInt(strs[2]); if(h>0){
					 * second+= h*60*60; }if( m>0){ second+=m*60; } } catch (NumberFormatException
					 * e) { e.printStackTrace(); }
					 */
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {

		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return firstImage;
	}

	public static String SHA1(String maps) throws DigestException {
		// 获取信息摘要 - 参数字典排序后字符串
		try {
			// 指定sha1算法
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(maps.getBytes());
			// 获取字节数组
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new DigestException("签名错误！");
		}
	}

	@Override
	public Results<List<UserVideo>> list(String userId, int pageNo, int pageSize, int types) {
		// TODO Auto-generated method stub
		Results<List<UserVideo>> result = new Results<List<UserVideo>>();
		// if (types == 2) {
		if (userId != null && userId != "") {
			Results<User> userResult = userService.existUser(userId);
			if ("1".equals(userResult.getStatus())) {
				result.setStatus("1");
				result.setMessage(userResult.getMessage());
				return result;
			}
			userId = userResult.getData().getId();
		}
		// }

		List<UserVideo> list = new ArrayList<>();
		if (types == 1) {
			list = userVideoDao.list(null, pageNo, pageSize);
		} else {
			list = userVideoDao.list(userId, pageNo, pageSize);
		}

		if (userId != null && userId != "") {

			for (UserVideo userVideo : list) {
				UserLikes ul = doctorReplyDao.singleLikes(userId, userVideo.getId());
				User headimg = userDao.singleUser(userVideo.getUserId(), null);
				userVideo.setUserHeadImgUrl(headimg.getHeadimg());
				if (ul != null) {
					///// 说明已经点过赞了
					userVideo.setIslikes(1);
				}
			}
		}
		result.setStatus("0");
		result.setData(list);
		return result;
	}

	/**
	 * 点赞的操作
	 */
	@Transactional
	@Override
	public Results<String> update(String userId, String id, int likeNumber, int types) {
		// TODO Auto-generated method stub
		Results<String> result = new Results<String>();
		if (userId != null && userId != "") {
			Results<User> userResult = userService.existUser(userId);
			if ("1".equals(userResult.getStatus())) {
				result.setStatus("1");
				result.setMessage(userResult.getMessage());
				return result;
			}
			userId = userResult.getData().getId();
		}
		UserLikes ul = doctorReplyDao.singleLikes(userId, id);
		UserVideo uv = new UserVideo();
		if (ul != null) {
			///// 说明已经点过赞了(取消点赞)
			doctorReplyDao.deleteLikes(ul.getId());
			if (types == 1) {
				uv.setId(id);
				uv.setLikeNumber(String.valueOf(likeNumber - 1));
				userVideoDao.update(uv);
			} else if (types == 2) {
				userVideoDao.updateComment(String.valueOf(likeNumber - 1), new Date(), id);
			}

			result.setStatus("0");
			result.setMessage("取消点赞");

			return result;
		} else {
			//// 说明没有点赞(添加点赞)
			doctorReplyDao.insertLikes(KeyGen.uuid(), userId, id, new Date());
			if (types == 1) {
				uv.setId(id);
				uv.setLikeNumber(String.valueOf(likeNumber + 1));
				userVideoDao.update(uv);
			} else if (types == 2) {
				userVideoDao.updateComment(String.valueOf(likeNumber + 1), new Date(), id);
			}
			result.setStatus("0");
			result.setMessage("点赞成功");

			return result;
		}

	}

	@Transactional
	@Override
	public Results<String> insertComment(UserVideoComment vc) {
		// TODO Auto-generated method stub
		Results<String> result = new Results<String>();

		Results<User> userResult = userService.existUser(vc.getUserId());
		if ("1".equals(userResult.getStatus())) {
			result.setStatus("1");
			result.setMessage(userResult.getMessage());
			return result;
		}
		vc.setUserId(userResult.getData().getId());
		vc.setId(KeyGen.uuid());
		vc.setAddtime(new Date());
		////////// 修改视频的评论个数
		UserVideo uvold = userVideoDao.single(vc.getVid());
		if (uvold != null) {
			UserVideo uv = new UserVideo();
			uv.setId(vc.getVid());
			uv.setCommentNumber(String.valueOf(Integer.valueOf(uvold.getCommentNumber()) + 1));
			uv.setUpdatetime(new Date());
			////// 修改视频的评论个数
			userVideoDao.update(uv);
			/////// 添加视频的评论
			if (vc.getTypes() == 2) {
				if (vc.getCommentid() == null || "".equals(vc.getCommentid())) {
					result.setStatus("1");
					result.setMessage("回复对应的评论id不能为空");
					return result;
				}
				//////// 不能自己回复自己
				UserVideoComment uvc = userVideoDao.singleComment(vc.getCommentid());
				if (uvc != null) {
					if (uvc.getUserId().equals(vc.getUserId())) {
						result.setStatus("1");
						result.setMessage("不能自己回复自己");
						return result;
					}
				}

			}
			userVideoDao.insertComment(vc);
			result.setStatus("0");
			return result;
		} else {
			result.setStatus("1");
			result.setMessage("视频的标识的id不存在");
			return result;
		}

	}

	@Override
	public Results<List<UserVideoComment>> listComment(String vid, String userId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Results<List<UserVideoComment>> result = new Results<List<UserVideoComment>>();
		if (userId != null && !"".equals(userId)) {
			Results<User> userResult = userService.existUser(userId);
			if ("1".equals(userResult.getStatus())) {
				result.setStatus("1");
				result.setMessage(userResult.getMessage());
				return result;
			}
			userId = userResult.getData().getId();
		}

		List<UserVideoComment> list = userVideoDao.listComment(vid, null, pageNo, pageSize, 1);
		for (UserVideoComment userVideoComment : list) {
			String showtime = Patterns.sfDetailTime(userVideoComment.getAddtime());
			userVideoComment.setShowtime(showtime);
			if (userId != null && !"".equals(userId)) {
				User user = userDao.singleUser(userVideoComment.getUserId(), null);
				userVideoComment.setHeadImgUrl(user.getHeadimg());
				UserLikes ul = doctorReplyDao.singleLikes(userId, userVideoComment.getId());
				if (ul != null) {
					userVideoComment.setIslike(1);///// 说明这个用户已经点过赞了
				}
			}
			/////// 评论对应的回复的集合
			List<UserVideoComment> replylist = userVideoDao.listComment(vid, userVideoComment.getId(), 0, 0, 2);
			for (UserVideoComment userVideoComment2 : replylist) {
				String replyshowtime = Patterns.sfDetailTime(userVideoComment2.getAddtime());
				userVideoComment2.setShowtime(replyshowtime);
			}
			userVideoComment.setReplylist(replylist);
		}
		result.setStatus("0");
		result.setData(list);
		return result;
	}

	@Override
	public int saveUV(UserVideo uv) {
		// TODO Auto-generated method stub

		Results<User> userResult = userService.existUser(uv.getUserId());
		if ("1".equals(userResult.getStatus())) {
			return 1;
		}
		uv.setUserId(userResult.getData().getId());

		User user = userService.sinleUser(uv.getUserId(), null);
		if (user.getIsdoctor() != 1 && user.getUserrole() == 0) {
			///// 说明既不是医师护也不是店铺没有权限发送小视频

			return 1;

		}
		if (user.getIsdoctor() == 1) {

			Doctors d = doctorDao.singleDoctors(null, user.getId(), "1");
			if (d != null) {
				uv.setHeadImgUrl(d.getHeadImg());
				uv.setNickname(d.getName());
				uv.setProfessional(d.getProfessional());
				uv.setDoctorId(d.getId());////// 这个是医生的id
			}

		} else {
			Shop s = new Shop();
			if (user.getUserrole() == 1) {
				//// 服务店铺
				s = shopDao.singleShop(uv.getUserId(), null, 1, null);

			} else if (user.getUserrole() == 2) {
				//// 商城店铺
				s = shopDao.singleShop(uv.getUserId(), null, 2, null);

			} else {
				////// 两种店铺都有(取商城店铺的名称)
				s = shopDao.singleShop(uv.getUserId(), null, 2, null);

			}
			uv.setShopId(s.getId());
			uv.setShopname(s.getShopName());
		}
		int count = userVideoDao.existVideo(uv.getVideoId());
		if (count == 0) {
			uv.setId(KeyGen.uuid());
			uv.setAddtime(new Date());
			userVideoDao.insert(uv);
		} else {
			////// 修改用户的信息
			userVideoDao.update(uv);

		}
		return 1;
	}

	@Override
	public int savesingleUV(UserVideo uv) {
		// TODO Auto-generated method stub
		return userVideoDao.insert(uv);
	}

	@Override
	public int existVideo(String videoId) {
		// TODO Auto-generated method stub
		return userVideoDao.existVideo(videoId);
	}

	@Override
	public int updateImage(String videoId, String firstImage) {
		// TODO Auto-generated method stub
		return userVideoDao.updateImage(videoId, firstImage);
	}
}
