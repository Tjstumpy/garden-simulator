package com.collective.app.dtos;

import com.collective.app.consts.PageIdentifier;
/**
 * This is a interface that has a page Identifier within it
 * @author Aaron
 *
 */
public interface UpdateObject {
	/**
	 * this is the pageIdentifier
	 */
	public PageIdentifier pageIdentifier = PageIdentifier.WELCOME;
}
