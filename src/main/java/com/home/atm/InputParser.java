package com.home.atm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.home.atm.command.AddCommand;
import com.home.atm.command.Command;

public class InputParser {
	
	public Command parseInput(String inputString) {
		//��� ������� �������� �� ����
		 Pattern addPattern = Pattern.compile("^[+]{1} ([A-Z]{3}) ([0-9]{1,10})$");
		//�������� ������ ������
		 Matcher m = addPattern.matcher(inputString);
		// m.usePattern(addPattern);
		 //��������� �� ������������ ������ �������
		if(m.find()) {
			String currency = m.group(1);
			Integer amount = Integer.parseInt(m.group(2));
			return new AddCommand(currency, amount);
		}
		throw new IllegalArgumentException("�� ���������� ������� :" + inputString );
		 
	}

}
