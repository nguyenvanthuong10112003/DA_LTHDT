package libary;

import java.util.LinkedList;

public class DATE {
	private LinkedList<Integer> year;
	private LinkedList<Integer> month;
	private LinkedList<Integer> day;

	public DATE(int year) {
		this.year = new LinkedList<Integer>();
		this.month = new LinkedList<Integer>();
		for (int i = 1; i <= 12; i++)
			month.add(i);
		for (int i = 1900; i <= year; i++)
			this.year.add(i);
	}

	public LinkedList<Integer> month() {
		return month;
	}

	public LinkedList<Integer> year() {
		return year;
	}

	public LinkedList<Integer> day(int month, int year) {
		int n = 0;
		this.day = new LinkedList<Integer>();
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			n = 31;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			n = 30;
		} else if (month == 2) {
			if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) {
				n = 29;
			} else
				n = 28;
		} else
			return null;
		for (int i = 1; i <= n; i++)
			day.add(i);
		return day;
	}
}
