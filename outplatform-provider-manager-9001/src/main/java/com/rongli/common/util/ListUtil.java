package com.rongli.common.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListUtil {
	/**
	 * 删除List中重复元素，并保持顺序
	 * @param list 待去重的list
	 * @return 去重后的list
	 */
	public static <T> List<T> removeDuplicateKeepOrder(List<T> list){
	    Set set = new HashSet();
	    List<T> newList = new ArrayList<>();
	    for (T element : list) {
	        //set能添加进去就代表不是重复的元素
	        if (set.add(element)) newList.add(element);
	    }
	    list.clear();
	    list.addAll(newList);
	    return list;
	}
}
