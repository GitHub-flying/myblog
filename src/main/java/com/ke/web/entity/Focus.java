package com.ke.web.entity;

/**
 * @author ke
 * @ClassName Focus
 * @Description TOOD
 * @Date 2019/12/10
 * @Version 1.0
 **/
public class Focus {
    private Integer id;
    private String focusTitle;
    private String focusHomepage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFocusTitle() {
        return focusTitle;
    }

    public void setFocusTitle(String focusTitle) {
        this.focusTitle = focusTitle;
    }

    public String getFocusHomepage() {
        return focusHomepage;
    }

    public void setFocusHomepage(String focusHomepage) {
        this.focusHomepage = focusHomepage;
    }
}
