/**
 * Copyright (C) SEI, PKU, PRC. - All Rights Reserved.
 * Unauthorized copying of this file via any medium is
 * strictly prohibited Proprietary and Confidential.
 * Written by Jiajun Jiang<jiajun.jiang@pku.edu.cn>.
 */

package mfix.core.parse.diff.text;

import mfix.common.util.Constant;

/**
 * @author: Jiajun
 * @date: 2018/9/21
 */
public class KeepLine extends Line {

	public KeepLine(String text) {
		super(text);
		_leading = Constant.PATCH_KEEP_LEADING;
	}
}
