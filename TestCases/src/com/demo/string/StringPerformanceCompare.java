package com.demo.string;
import java.util.Date;
import java.sql.Timestamp;

public class StringPerformanceCompare {
	public static void main(String[] args) {
		Date sDate = new Date();
		long sTime = sDate.getTime();
		System.out.println("Start Time for StringBuffer: " + new Timestamp(sTime));
		StringBuffer s = new StringBuffer("AA");		
		for (int i=0; i< 10000; i++){		
				s.append(i);
		}
		
		Date eDate = new Date();
		long eTime = eDate.getTime();
		System.out.println("End Time for StringBuffer: " + new Timestamp(eTime));
		System.out.println("Time taken to Execute StringBuffer process " + (eTime-sTime) + "ms");
		System.out.println("=====================================================================");
		
		Date strDate = new Date();
		long strTime = strDate.getTime();
		System.out.println("Start Time for String: " + new Timestamp(strTime));
		String str = new String("AA");		
		for (int i=0; i< 10000; i++){		
				str+=i;
		}
		
		Date eStrDate = new Date();
		long eStrTime = eStrDate.getTime();
		System.out.println("End Time for String: " + new Timestamp(eStrTime));
		System.out.println("Time taken to Execute String process " + (eStrTime-strTime) + "ms");
	}
}
