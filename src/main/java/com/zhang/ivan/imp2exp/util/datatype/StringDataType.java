package com.zhang.ivan.imp2exp.util.datatype;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringDataType extends AbstractDataType implements DataType<String> {

	public String excute() {
		StringBuffer stringBuffer = new StringBuffer();

		boolean tem = false;
		Pattern pattern = Pattern.compile(getRegx());
		Matcher matcher = pattern.matcher(getValue());

		tem = matcher.matches();
		return stringBuffer.toString();
	}

}
