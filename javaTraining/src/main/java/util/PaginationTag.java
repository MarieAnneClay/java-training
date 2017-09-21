package util;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public class PaginationTag implements Tag {

    private PageContext pageContext;
    // form action
    private String action;
    // total page count
    private int totalPageCount;
    // view page count
    private int viewPageCount = 5;
    private int startIndx = 1;
    private int endIndx = viewPageCount;

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    @Override
    public void setParent(Tag parentTag) {
    }

    public void setTotalPageCount(int pageCount) {
        this.totalPageCount = pageCount;
    }

    public void setAction(String action) {
        this.action = action;
    }

    /** .
     * @param minPages minimum of page
     * @exception JspException */
    public void setViewPageCount(int minPages) throws JspException {
        this.viewPageCount = minPages;
    }

    @Override
    public int doStartTag() throws JspException {
        // throw exception when minimum page count less than 1
        if (viewPageCount < 1) {
            throw new JspException("minimum page count should be greater than zero");
        }
        return Tag.SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        String page = "1";
        if (this.totalPageCount <= 1) {
            return Tag.SKIP_PAGE;
        }

        ServletRequest request = pageContext.getRequest();
        if (request.getParameter("page") != null) {
            page = request.getParameter("page");
        }

        // handle wrong request page
        if (page != null && (Integer.parseInt(page) > totalPageCount || Integer.parseInt(page) <= 0)) {
            throw new JspException("[Page Number : " + page + "] wrong requested page, requested page must be greater than 0 and less than or equal to total page count");
        }
        JspWriter out = pageContext.getOut();
        this.endIndx = this.startIndx + this.viewPageCount - 1;
        try {
            // logic for Next or Last pagination.
            if (page != null && Integer.parseInt(page) > endIndx) {
                // if Last page navigation selected
                if (Integer.parseInt(page) == totalPageCount) {
                    startIndx = totalPageCount - viewPageCount + 1;
                } else { // if Next page navigation selected
                    startIndx = startIndx + 1;
                }
                endIndx = Integer.parseInt(page);
            }
            // logic for Previous or First pagination
            if (page != null && Integer.parseInt(page) < startIndx) {
                startIndx = Integer.parseInt(page);
                // if Fist page navigation selected
                if (Integer.parseInt(page) == 1) {
                    endIndx = viewPageCount;
                } else { // if Previous page navigation selected
                    endIndx = endIndx - 1;
                }
            }
            // show First and Previous pagination
            if (startIndx > 1) {
                out.write(getLink(this.action, 1, false, "<<"));
                out.write(getLink(this.action, (Integer.parseInt(page) - 1), false, "<"));
            }
            // show pagination numbers
            for (int i = startIndx; i <= endIndx; i++) {
                // high light selected page number.
                if ((page == null && i == 1) || (page != null && i == (Integer.parseInt(page)))) {
                    out.write(getLink(this.action, i, true, String.valueOf(i)));
                } else {
                    out.write(getLink(this.action, i, false, String.valueOf(i)));
                }
            }
            // show Last and Next pagination
            if (endIndx < this.totalPageCount) {
                out.write(getLink(this.action, (page != null) ? (Integer.parseInt(page) + 1) : 2, false, ">"));
                out.write(getLink(this.action, this.totalPageCount, false, ">>"));
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new JspException(e);
        }
        return Tag.EVAL_PAGE;
    }

    /** method used to create pagination link.
     * @param action href link
     * @param page number of the page
     * @param isCurrentPage current page
     * @param desc description
     * @return */
    private String getLink(final String action, final int page, final boolean isCurrentPage, final String desc) {
        pageContext.getRequest();

        StringBuilder link = new StringBuilder();
        link.append("<a href='");
        link.append(action);
        link.append("?page=");
        link.append(page);
        link.append("'><font color='" + (isCurrentPage ? "grey" : "black") + "'>");
        link.append(desc);
        link.append("</font></a>&nbsp;");
        return link.toString();
    }

    @Override
    public Tag getParent() {
        return null;
    }

    @Override
    public void release() {

    }
}
