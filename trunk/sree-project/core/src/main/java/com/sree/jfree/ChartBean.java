/**
 * 
 */
package com.sree.jfree;

import org.jfree.data.general.DefaultPieDataset;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sree.web.BaseBean;

/**
 * @author Sree
 * 
 */
@SuppressWarnings({"serial"})
@Component("chartBean")
@Scope(value = "request")
public class ChartBean extends BaseBean{

	private DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
	
	public DefaultPieDataset getLocalDefaultPieDataset() {
		localDefaultPieDataset.setValue("A", 52.0D);
		localDefaultPieDataset.setValue("B", 18.0D);
		localDefaultPieDataset.setValue("C", 30.0D);
		return localDefaultPieDataset;
	}

	public void setLocalDefaultPieDataset(DefaultPieDataset localDefaultPieDataset) {
		this.localDefaultPieDataset = localDefaultPieDataset;
	}
}
