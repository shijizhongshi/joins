package com.ola.qh.util;

import java.time.LocalDate;
import java.util.Random;

public class Invitation
{

    /**
     * 邀请码的获取
     * 
     * @return
     */
    public static String generateNumber()
    {
	String no = "";
	int num[] = new int[8];
	int c = 0;
	for (int i = 0; i < 8; i++)
	{
	    num[i] = new Random().nextInt(10);
	    c = num[i];
	    for (int j = 0; j < i; j++)
	    {
		if (num[j] == c)
		{
		    i--;
		    break;
		}
	    }
	}
	if (num.length > 0)
	{
	    for (int i = 0; i < num.length; i++)
	    {
		no += num[i];
	    }
	}
	return no;
    }
    
    
    
    
    
    public static String buildpaths()
    {
	LocalDate date = LocalDate.now();
	String dpath = new StringBuilder().append(date.getYear()).append(date.getMonthValue()).append(date.getDayOfMonth())
		.append(generateNumber()).toString();
	return dpath;
    }

}
