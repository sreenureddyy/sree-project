package com.sree.mysqlDialect;

import java.sql.Types;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * @author srinivasr
 * 
 */

public class MySql5Fix extends MySQL5InnoDBDialect {

	public MySql5Fix() {
		super();
		registerColumnType(Types.BIT, "tinyint(1)");
	}

}