package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.List;

public class QuestionUnitTypes {

	private List<QuestionUnit> AList=new ArrayList<QuestionUnit>();
	
	private List<QuestionUnit> BList=new ArrayList<QuestionUnit>();
	
	public List<QuestionUnit> getAList() {
		return AList;
	}

	public void setAList(List<QuestionUnit> aList) {
		AList = aList;
	}

	public List<QuestionUnit> getBList() {
		return BList;
	}

	public void setBList(List<QuestionUnit> bList) {
		BList = bList;
	}

	
}
