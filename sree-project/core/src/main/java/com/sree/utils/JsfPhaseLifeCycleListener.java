package com.sree.utils;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class JsfPhaseLifeCycleListener implements PhaseListener {

	private Logger logger = Logger.getLogger(JsfPhaseLifeCycleListener.class);
	
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    public void beforePhase(PhaseEvent event) {
    	logger.info("START PHASE " + event.getPhaseId());
    }

    public void afterPhase(PhaseEvent event) {
    	logger.info("END PHASE " + event.getPhaseId());
    }

}