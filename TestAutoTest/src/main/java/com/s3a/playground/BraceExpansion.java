package com.s3a.playground;

public class BraceExpansion {
    static String FinalStr=null;
	 
    public static void main(String[] args) {
        
        String str1 = "A,{B,C }2,{D,E}"; //A,B,C,B,C,D,E
        String str2 = "A,{B ,{D, E}2,C}2"; //A,{B,D,E,D,E,C}*2
        								//A,B,D,E,D,E,C,B,D,E,D,E,C
        String str3 = "A,{B,{D,{E}2}2,C}2,F,G,{H}2";//A, B, D, E,E,D, E,E,C,B, D, E,E,D, E,E,C,F,G, H,H
        String str4 = "A,{B,C}3,F,{{D}3,E}2,G"; //A, B,C,B,C,B,C,F,  D,D,D,E, D,D,D,E,G
        
        parseExpand(str3);
    }
    
    
    public static String parseExpand(String s) {
    	expandUtil("",s.replaceAll("\\s+",""),"");
    	System.out.println(":: "+ FinalStr);
    	return FinalStr;
    }
    
    private static void expandUtil(String pre, String s, String suf){
    	int i1 = -1, i2 = 0;
    	int dup=1;
    	String duplicate=null;
        String noEscape = s.replaceAll("([\\\\]{2}|[\\\\][,}{])", "  ");
        StringBuilder sb = null;
        StringBuilder tmp = null;
        System.out.println("~~~~~~~~~     > "+pre + " | " + noEscape + " | "+ suf + " || ");
        String nExx=pre;
        sb = new StringBuilder(noEscape);
        
        outer:
        while ((i1 = noEscape.indexOf('{', i1 + 1)) != -1) {
            sb.setCharAt(i1, '\u0000');
            i2 = i1 + 1;
            tmp = new StringBuilder();
            for (int depth = 1; i2 < s.length() && depth > 0; i2++) {
                char c = noEscape.charAt(i2);
//	        	System.out.println(i2+" "+c);
                depth = (c == '{') ? ++depth : depth;
                depth = (c == '}') ? --depth : depth;
                if (c == '}' && depth == 0)
                {
                    try
                    {
                      dup = Integer.parseInt(String.valueOf(noEscape.charAt(i2+1)));
                      i2++;
                    }
                    catch (NumberFormatException | StringIndexOutOfBoundsException nfe)
                    {
                      System.out.println("NumberFormatException: " + nfe.getMessage());
                    }
                    break outer;
                }else{
                	tmp.append(c);
                }
            }
        }
        
//        System.out.println(i1);
        if (i1 == -1 && nExx.indexOf('{')==-1) {
            FinalStr =  nExx+noEscape;
        } else if (nExx.indexOf('{')!=-1){
        	expandUtil("",nExx+noEscape,suf);
        }else{
        	duplicate = sb.substring(0, i1-1);
        	for(int d=0; d<dup; d++){
        		duplicate=duplicate+","+ tmp;
        	};
        	expandUtil(pre  + duplicate, s.substring(i2+1), suf);
    	}
    }
    
    
    
    
    
    
    private static void expandUtil2(String pre, String s, String suf){
    	int i1 = -1, i2 = 0;
        String noEscape = s.replaceAll("([\\\\]{2}|[\\\\][,}{])", "  ");
        StringBuilder sb = null;
        StringBuilder tmp = null;
        System.out.println("~~~~~~~~~     > "+pre + " | " + noEscape + " | "+ suf + " || ");
        String nExx=pre;
        sb = new StringBuilder(noEscape);
        
        outer:
        while ((i1 = noEscape.indexOf('{', i1 + 1)) != -1) {
            sb.setCharAt(i1, '\u0000');
            i2 = i1 + 1;
            tmp = new StringBuilder();
            for (int depth = 1; i2 < s.length() && depth > 0; i2++) {
                char c = noEscape.charAt(i2);
//	        	System.out.println(i2+" "+c);
                depth = (c == '{') ? ++depth : depth;
                depth = (c == '}') ? --depth : depth;
                if (c == '}' && depth == 0)
                {
                    break outer;
                }else{
                	tmp.append(c);
                }
            }
        }
        
//        System.out.println(i1);
        if (i1 == -1 && nExx.indexOf('{')==-1) {
            FinalStr =  nExx+noEscape;
        } else if (nExx.indexOf('{')!=-1){
        	expandUtil("",nExx+noEscape,suf);
        }else{
        	expandUtil(pre  + sb.substring(0, i2)+","+ tmp, s.substring(i2+1), suf);
    	}
    }
    
}