package com.varxyz.wgt.user.domain;

import java.util.regex.Pattern;

import org.springframework.ui.Model;

public class RegularExpression {
    
	public void regularText(String str, Model model){
        if(Pattern.matches("[!@#$%^&*(),.?\":{}|<>]", str)){
            model.addAttribute("msg", "특수문자는 사용할 수 없습니다.");
        }
    }
}
