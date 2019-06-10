package com.eastern.jinxin.redis.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eastern.jinxin.redis.entity.Match;
import com.eastern.jinxin.redis.entity.TagConditionBase;
import com.eastern.jinxin.redis.entity.TagCtgyEntity;
import com.eastern.jinxin.redis.entity.TagGrpEntity;

public final class TagGrpToCondition {
	private static final Logger logger = LoggerFactory.getLogger(TagGrpToCondition.class);

	public static TagConditionBase getCondition(List tagGrpEntityList) {
		logger.debug("start work :tag json data to tag condition-->" + tagGrpEntityList);

		TagConditionBase matchResult = null;
		for (Iterator localIterator = tagGrpEntityList.iterator(); localIterator.hasNext();) {
			Object tagObj = localIterator.next();
			TagConditionBase tagCondition = null;

			if ((tagObj instanceof TagGrpEntity)) {
				tagCondition = getCondition(((TagGrpEntity) tagObj).getTagCtgys());
			} else if ((tagObj instanceof TagCtgyEntity)) {
				tagCondition = getCondition(((TagCtgyEntity) tagObj).getTagIds());
			} else if ((tagObj instanceof String)) {
				String tag = (String) tagObj;
				Match math = new Match();
				math.setTag(tag);
				math.setTagId(tag);
				tagCondition = math;
			}

			if (matchResult == null) {
				matchResult = tagCondition;
			} else if ((tagObj instanceof TagCtgyEntity))
				matchResult = matchResult.and(tagCondition);
			else {
				matchResult = matchResult.or(tagCondition);
			}
		}
		return matchResult;
	}

	public static TagConditionBase jsonMapToCondition(Map<String, Object> paramMap) {
		List<TagGrpEntity> tagGrpEntityList = new ArrayList<TagGrpEntity>();

		Set<String> grpSeqSet = paramMap.keySet();
		for (String grpSeq : grpSeqSet) {
			Map<String, Object> tagCtgys = (Map) paramMap.get(grpSeq);
			Set<String> tagCtgySet = tagCtgys.keySet();

			TagGrpEntity tagGrpEntity = new TagGrpEntity();
			tagGrpEntity.setGrpSeq(grpSeq);

			List tagCtgyEntityList = new ArrayList();
			tagGrpEntity.setTagCtgys(tagCtgyEntityList);
			tagGrpEntityList.add(tagGrpEntity);

			for (String tagCtgy : tagCtgySet) {
				TagCtgyEntity tagCtgyEntity = new TagCtgyEntity();
				tagCtgyEntity.setTagCtgyId(tagCtgy);
				tagCtgyEntity.setTagIds((List) tagCtgys.get(tagCtgy));
				tagCtgyEntityList.add(tagCtgyEntity);
			}
		}
//		Map tagCtgys;
//		List tagCtgyEntityList;
		return getCondition(tagGrpEntityList);
	}

	public static TagConditionBase dbMapToCondition(List<Map<String, Object>> dbDataList) {
		Map<String, Object> grpMap = new HashMap<String, Object>();

		for (Map dbDataMap : dbDataList) {
			String grpSeq = dbDataMap.get("grpSeq").toString();
			String tagId = dbDataMap.get("tagId").toString();
			String tagCtgyId = dbDataMap.get("tagCtgyId").toString();
			if (grpMap.containsKey(grpSeq)) {
				Map ctgysMap = (Map) grpMap.get(grpSeq);
				if (ctgysMap.containsKey(tagCtgyId)) {
					List tagList = (List) ctgysMap.get(tagCtgyId);
					tagList.add(tagId);
				} else {
					List tagList = new ArrayList();
					tagList.add(tagId);
					ctgysMap.put(tagCtgyId, tagList);
				}
			} else {
				Map ctgysMap = new HashMap();
				List tagList = new ArrayList();
				tagList.add(tagId);
				ctgysMap.put(tagCtgyId, tagList);
				grpMap.put(grpSeq, ctgysMap);
			}
		}

		TagConditionBase condition = jsonMapToCondition(grpMap);

		return condition;
	}
}

/*
 * Location: C:\Users\root\Desktop\SeaBoxTag\WEB-INF\classes\ Qualified Name:
 * com.seabox.tagsys.persongroup.utils.TagGrpToCondition JD-Core Version: 0.6.0
 */