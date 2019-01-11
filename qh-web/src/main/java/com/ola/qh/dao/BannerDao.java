package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Banner;

public interface BannerDao {

	public List<Banner> selectBanner(@Param("type")String type);
}
