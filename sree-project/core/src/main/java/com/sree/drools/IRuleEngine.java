package com.sree.drools;

import java.util.Map;

import org.drools.KnowledgeBase;

public interface IRuleEngine {

	public abstract Map<String, KnowledgeBase> getRuleKnowledgeBaseMap();
	
	public void createRuleBaseMap();

}