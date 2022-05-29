package com.example.demos.web.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.time.LocalDate;

public class DateTag extends TagSupport {

    public int doStartTag()  {
        JspWriter out=pageContext.getOut();//returns the instance of JspWriter
        try{
            out.print(LocalDate.now());//printing date and time using JspWriter
        }catch(Exception e){
            e.printStackTrace();
        }
        return SKIP_BODY;//will not evaluate the body content of the tag
    }
}
