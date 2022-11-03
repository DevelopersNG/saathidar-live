package com.sathidar.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {

    // Function to return the modified string
    static String extractInt(String str)
    {
        // Replacing every non-digit number
        // with a space(" ")
        str = str.replaceAll("[^0-9]", " "); // regular expression
 
        // Replace all the consecutive white
        // spaces with a single space
        str = str.replaceAll(" +", ",");
        str =str.startsWith(",") ? str.substring(1) : str;
        String[] arrayInt=str.split(",");
        int from_annual_income=Integer.parseInt(arrayInt[0]);
        int to_annual_income=Integer.parseInt(arrayInt[1]);
        
        System.out.println(from_annual_income);
        System.out.println(to_annual_income);
        
        if (str.equals(""))
            return "-1";
 
        return str;
    }
 
    // Driver code
    public static void main(String[] args)
    {
        String memberAnnualIncome = "INR 1 lakh to 2 lakh";
        String myAnnualIncome = "INR 5 lakh to 5 lakh,INR 5 lakh to 6 lakh";
        boolean matchesStatus=false;
//        System.out.print(extractInt(str));
        
        List<String> anualList = new ArrayList<String>();
		if (myAnnualIncome != null && !myAnnualIncome.equals("") && !myAnnualIncome.equals("null")) {
			if (!myAnnualIncome.contains(",")) {
				anualList.add(myAnnualIncome);
			} else {
				anualList = new ArrayList<String>(Arrays.asList(myAnnualIncome.split(",")));
			}
		}
		
		List<String> memberAnnualList =new ArrayList<String>();
		if (memberAnnualIncome!= null && !memberAnnualIncome.equals("") && !memberAnnualIncome.equals("null")) {
			if (!memberAnnualIncome.contains(",")) {
				memberAnnualList.add(memberAnnualIncome);
			} else {
				memberAnnualList = new ArrayList<String>(Arrays.asList(memberAnnualIncome.split(",")));
			}
		}
        
		for (int j = 0; j < anualList.size(); j++) {
			for (int k = 0; k < memberAnnualList.size(); k++) {
				if(anualList.get(j).equals(memberAnnualList.get(k))) {
					matchesStatus = true;
				}
			}
		}
		
		System.out.println(matchesStatus);
		
		
//        for (int j = 0; j < anualList.size(); j++) {
//			String str = anualList.get(j).replaceAll("[^0-9]", " ");
//			str = str.replaceAll(" +", ",").startsWith(",") ? str.substring(1) : str;
//			String main_str = str.startsWith(",") ? str.substring(1) : str;
//			String[] arrayInt = main_str.split(",");
//			int from_annual_income = Integer.parseInt(arrayInt[0]);
//			int to_annual_income = Integer.parseInt(arrayInt[1]);
//			
//			for (int k = 0; k < memberAnnualList.size(); k++) {
//				String member_str = anualList.get(k).replaceAll("[^0-9]", " ");
//				member_str = member_str.replaceAll(" +", ",").startsWith(",") ? member_str.substring(1) : member_str;
//				String main_member_str = str.startsWith(",") ? member_str.substring(1) : member_str;
//				String[] arrayMemberInt = main_member_str.split(",");
//				int from_member_annual_income = Integer.parseInt(arrayMemberInt[0]);
//				int to_member_annual_income = Integer.parseInt(arrayMemberInt[1]);
//				if(from_member_annual_income>from_annual_income && to_annual_income<to_member_annual_income)
//				{
//					matchesStatus = true;
//					break;
//				}
//			}
//		}
        
    }
}
