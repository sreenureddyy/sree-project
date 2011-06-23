/**
 * 
 */
package com.sree.drools;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.DecisionTableConfiguration;
import org.drools.builder.DecisionTableInputType;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

/**
 * @author srinivasr
 * 
 */
@Service("ruleEngine")
public class RuleEngine implements ServletContextAware, IRuleEngine {

	private static Logger logger = Logger.getLogger(RuleEngine.class);

	private ServletContext servletContext;

	private Map<String, KnowledgeBase> ruleKnowledgeBaseMap = new HashMap<String, KnowledgeBase>();

	@PostConstruct
	protected void init() {
		logger.info("Loading rules..........");
		createRuleBaseMap();
	}

	@PreDestroy
	protected void destroy() {
		logger.info("Destroy rules.................");
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void createRuleBaseMap() {
		logger.info(" Rules folder :: "
				+ servletContext.getRealPath(File.separator) + "uploads"
				+ File.separator + "rules" + File.separator);

		String rulesFolderPath = servletContext.getRealPath(File.separator)
				+ "uploads" + File.separator + "rules" + File.separator;
		try {
			File folder = new File(rulesFolderPath);

			File[] listOfFiles = folder.listFiles();
			String fileName;
			long start;
			for (int i = 0; i < listOfFiles.length; i++) {
				try {
					fileName = listOfFiles[i].getName();
					if (listOfFiles[i].isFile() && fileName.endsWith("xls")) {
						start = System.currentTimeMillis();
						ruleKnowledgeBaseMap.put(fileName,
								readDecisionTable(rulesFolderPath + fileName));
						logger.info("File Name:" + fileName
								+ "\t Loading RuleKnowledgeBase(Sec): "
								+ (System.currentTimeMillis() - start) / 1000F);
					}
				} catch (Exception e) {
					logger.error(e);
				}
			}
			logger.info("Loading rules Completed");
		} catch (Exception e1) {
			logger.error(e1);
		}
	}

	private KnowledgeBase readDecisionTable(String filePath) {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		DecisionTableConfiguration config = KnowledgeBuilderFactory
				.newDecisionTableConfiguration();
		config.setInputType(DecisionTableInputType.XLS);
		kbuilder.add(ResourceFactory.newFileResource(filePath),
				ResourceType.DTABLE, config);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				logger.info(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge...");
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sree.drools.IRuleEngine#getRuleKnowledgeBaseMap()
	 */
	public Map<String, KnowledgeBase> getRuleKnowledgeBaseMap() {
		return ruleKnowledgeBaseMap;
	}

	public void setRuleKnowledgeBaseMap(
			Map<String, KnowledgeBase> ruleKnowledgeBaseMap) {
		this.ruleKnowledgeBaseMap = ruleKnowledgeBaseMap;
	}
}
