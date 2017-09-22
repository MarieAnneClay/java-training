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
    private int computerTotalPages;
    // view page count
    private int nbPageShowInPagination = 5;
    private int startIndx = 1;
    private int endIndx = computerTotalPages;
    private int numberOfComputerByPage = 10;

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    @Override
    public void setParent(Tag parentTag) {
    }

    public void setcomputerTotalPages(int pageCount) {
        this.computerTotalPages = pageCount;
    }

    public void setAction(String action) {
        this.action = action;
    }

    /** .
     * @param minPages minimum of page
     * @exception JspException */
    public void setnbPageShowInPagination(int minPages) throws JspException {
        this.nbPageShowInPagination = minPages;
    }

    @Override
    public int doStartTag() throws JspException {
        // throw exception when minimum page count less than 1
        if (nbPageShowInPagination < 1) {
            throw new JspException("minimum page count should be greater than zero");
        }
        return Tag.SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        String page = "1";
        String search = "";

        if (this.computerTotalPages <= 1) {
            return Tag.SKIP_PAGE;
        }

        ServletRequest request = pageContext.getRequest();
        if (request.getParameter("page") != null) {
            page = request.getParameter("page");
        }

        if (request.getParameter("search") != null) {
            search = request.getParameter("search");
        }

        if (request.getParameter("numberOfComputerByPage") != null) {
            numberOfComputerByPage = Integer.parseInt(request.getParameter("numberOfComputerByPage"));
        }

        if (request.getParameter("computerTotalPages") != null) {
            computerTotalPages = Integer.parseInt(request.getParameter("computerTotalPages"));
        }

        int nbPage = numberOfComputerByPage < computerTotalPages ? computerTotalPages / numberOfComputerByPage : numberOfComputerByPage / computerTotalPages;
        nbPageShowInPagination = nbPage < computerTotalPages + 1 ? nbPage : computerTotalPages;

        // handle wrong request page
        if (page != null && (Integer.parseInt(page) > computerTotalPages || Integer.parseInt(page) <= 0)) {
            throw new JspException("[Page Number : " + page + "] wrong requested page, requested page must be greater than 0 and less than or equal to total page count");
        }
        JspWriter out = pageContext.getOut();
        this.endIndx = this.startIndx + this.nbPageShowInPagination - 1;
        try {
            if (computerTotalPages > 1) {
                StringBuilder link = new StringBuilder();
                link.append("<ul class=\"pagination\"><li>");
                out.write(link.toString());
                // logic for Next or Last pagination.
                if (page != null && Integer.parseInt(page) > endIndx) {
                    // if Last page navigation selected
                    if (Integer.parseInt(page) == computerTotalPages) {
                        startIndx = computerTotalPages - nbPageShowInPagination + 1;
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
                        endIndx = nbPageShowInPagination;
                    } else { // if Previous page navigation selected
                        endIndx = endIndx - 1;
                    }
                }
                // show First and Previous pagination
                if (startIndx > 1) {
                    out.write(getLink(this.action, 1, search, false, "<<"));
                    out.write(getLink(this.action, (Integer.parseInt(page) - 1), search, false, "<"));
                }
                // show pagination numbers
                for (int i = startIndx; i <= endIndx; i++) {
                    // high light selected page number.
                    if ((page == null && i == 1) || (page != null && i == (Integer.parseInt(page)))) {
                        out.write(getLink(this.action, i, search, true, String.valueOf(i)));
                    } else {
                        out.write(getLink(this.action, i, search, false, String.valueOf(i)));
                    }
                }
                // show Last and Next pagination
                if (endIndx < this.computerTotalPages) {
                    out.write(getLink(this.action, (page != null) ? (Integer.parseInt(page) + 1) : 2, search, false, ">"));
                    out.write(getLink(this.action, this.computerTotalPages, search, false, ">>"));
                }
                StringBuilder link2 = new StringBuilder();
                link2.append("</li></ul><div class=\"btn-group btn-group-sm pull-right\" role=\"group\">");
                out.write(link2.toString());
                out.write(getLinkNbByPage(this.action, Integer.parseInt(page), search, numberOfComputerByPage == 10 ? true : false, "10"));
                out.write(getLinkNbByPage(this.action, Integer.parseInt(page), search, numberOfComputerByPage == 50 ? true : false, "50"));
                out.write(getLinkNbByPage(this.action, Integer.parseInt(page), search, numberOfComputerByPage == 100 ? true : false, "100"));
                StringBuilder link3 = new StringBuilder();
                link3.append("</div>");
                out.write(link2.toString());
                out.flush();
            }
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
    private String getLink(final String action, final int page, final String search, final boolean isCurrentPage, final String desc) {
        pageContext.getRequest();

        StringBuilder link = new StringBuilder();
        link.append("<a href='");
        link.append(action);
        link.append("?page=");
        link.append(page);
        link.append("&computerTotalPages=");
        link.append(computerTotalPages);
        link.append("&nbPageShowInPagination=");
        link.append(nbPageShowInPagination);
        link.append("&search=");
        link.append(search);
        link.append("&numberOfComputerByPage=");
        link.append(numberOfComputerByPage);
        link.append("'><font color='" + (isCurrentPage ? "grey" : "black") + "'>");
        link.append(desc);
        link.append("</font></a>&nbsp;");
        return link.toString();
    }

    /** method used to create pagination link.
     * @param action href link
     * @param page number of the page
     * @param isCurrentPage current page
     * @param desc description
     * @return */
    private String getLinkNbByPage(final String action, final int page, final String search, final boolean isCurrentNbByPage, final String desc) {
        pageContext.getRequest();

        StringBuilder link = new StringBuilder();
        link.append("<a class=\"btn btn-default\" href='");
        link.append(action);
        link.append("?page=");
        link.append(1);
        link.append("&computerTotalPages=");
        link.append(computerTotalPages);
        link.append("&nbPageShowInPagination=");
        link.append(nbPageShowInPagination);
        link.append("&search=");
        link.append(search);
        link.append("&numberOfComputerByPage=");
        link.append(desc);
        link.append("'><font color='" + (isCurrentNbByPage ? "grey" : "black") + "'>");
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
